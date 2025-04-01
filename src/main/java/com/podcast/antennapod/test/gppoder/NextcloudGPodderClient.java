package com.podcast.antennapod.test.gppoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class NextcloudGPodderClient {
    private final String username;
    private final String password;
    private final String nextcloudUrl;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    /**
     * Constructeur du client Nextcloud GPodder
     *
     * @param username Nom d'utilisateur Nextcloud
     * @param password Mot de passe Nextcloud
     * @param nextcloudUrl URL de votre instance Nextcloud (ex: "https://cloud.example.com")
     */
    public NextcloudGPodderClient(String username, String password, String nextcloudUrl) {
        this.username = username;
        this.password = password;
        this.nextcloudUrl = nextcloudUrl.endsWith("/") ? nextcloudUrl.substring(0, nextcloudUrl.length() - 1) : nextcloudUrl;
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Récupère la liste des abonnements podcast
     *
     * @return Liste des URLs de podcast
     */
    public List<String> getSubscriptions() throws IOException, InterruptedException {
        HttpRequest request = createAuthenticatedRequest(nextcloudUrl + "/apps/gpodder/subscriptions")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Erreur lors de la récupération des abonnements: " + response.statusCode());
        }

        Map<String, List<String>> responseData = objectMapper.readValue(response.body(), Map.class);
        return responseData.get("subscriptions");
    }

    /**
     * Envoie des modifications d'abonnements au serveur
     *
     * @param addUrls Liste des URLs à ajouter
     * @param removeUrls Liste des URLs à supprimer
     * @return ID de synchronisation
     */
    public String updateSubscriptions(List<String> addUrls, List<String> removeUrls) throws IOException, InterruptedException {
        Map<String, Object> requestData = Map.of(
                "add", addUrls,
                "remove", removeUrls
        );

        String jsonBody = objectMapper.writeValueAsString(requestData);

        HttpRequest request = createAuthenticatedRequest(nextcloudUrl + "/apps/gpoddersync/personal_settings/metrics")
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Échec de mise à jour des abonnements: " + response.statusCode());
        }

        Map<String, String> responseData = objectMapper.readValue(response.body(), Map.class);
        return responseData.get("update_url");
    }

    /**
     * Récupère les épisodes qui ont été écoutés
     *
     * @param deviceId Identifiant de l'appareil
     * @param since Timestamp de la dernière synchronisation
     * @return Les épisodes marqués comme lus
     */
    public Map<String, Object> getEpisodeActions(String deviceId, long since) throws IOException, InterruptedException {
        HttpRequest request = createAuthenticatedRequest(
                String.format("%s/apps/gpodder/episode-actions/%s?since=%d", nextcloudUrl, deviceId, since))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Échec de récupération des actions d'épisodes: " + response.statusCode());
        }

        return objectMapper.readValue(response.body(), Map.class);
    }

    /**
     * Marque des épisodes comme lus/téléchargés
     *
     * @param deviceId Identifiant de l'appareil
     * @param actions Liste des actions sur les épisodes
     * @return Statut de la synchronisation
     */
    public Map<String, Object> uploadEpisodeActions(String deviceId, List<Map<String, Object>> actions)
            throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(Map.of("actions", actions));

        HttpRequest request = createAuthenticatedRequest(
                String.format("%s/apps/gpodder/episode-actions/%s", nextcloudUrl, deviceId))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Échec d'envoi des actions d'épisodes: " + response.statusCode());
        }

        return objectMapper.readValue(response.body(), Map.class);
    }

    /**
     * Crée une requête HTTP avec authentification Basic
     */
    private HttpRequest.Builder createAuthenticatedRequest(String url) {
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Basic " + encodedAuth);
    }
}