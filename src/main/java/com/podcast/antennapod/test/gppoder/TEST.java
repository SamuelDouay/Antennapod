package com.podcast.antennapod.test.gppoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TEST {
    private static final Logger LOGGER = LogManager.getLogger(TEST.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);

        // Configuration du client
        LOGGER.info("Configuration de la synchronisation Nextcloud GPodder");
        LOGGER.info("URL de votre Nextcloud (ex: https://cloud.example.com): ");
        String nextcloudUrl = scanner.nextLine();

        LOGGER.info("Nom d'utilisateur: ");
        String username = scanner.nextLine();

        LOGGER.info("Mot de passe: ");
        String password = scanner.nextLine();

        String deviceId = "JavaPodcastApp_" + System.currentTimeMillis();
        LOGGER.info("Identifiant de l'appareil: {}", deviceId);

        try {
            NextcloudGPodderClient client = new NextcloudGPodderClient(username, password, nextcloudUrl);

            // Menu principal
            boolean running = true;
            while (running) {
                LOGGER.info("\n=== Menu GPodder Nextcloud Sync ===");
                LOGGER.info("1. Afficher mes abonnements");
                LOGGER.info("2. Ajouter un podcast");
                LOGGER.info("3. Supprimer un podcast");
                LOGGER.info("4. Marquer un épisode comme écouté");
                LOGGER.info("5. Synchroniser les épisodes écoutés");
                LOGGER.info("0. Quitter");
                LOGGER.info("> ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consommer le newline

                switch (choice) {
                    case 1:
                        listSubscriptions(client);
                        break;
                    case 2:
                        addPodcast(client, scanner);
                        break;
                    case 3:
                        removePodcast(client, scanner);
                        break;
                    case 4:
                        markEpisodeAsListened(client, deviceId, scanner);
                        break;
                    case 5:
                        syncEpisodes(client, deviceId);
                        break;
                    case 0:
                        running = false;
                        LOGGER.info("Application terminée avec succès");
                        break;
                    default:
                        LOGGER.warn("Option invalide sélectionnée: {}", choice);
                }
            }

        } finally {
            scanner.close();
        }
    }

    private static void listSubscriptions(NextcloudGPodderClient client) throws InterruptedException, IOException {
        List<String> subscriptions = client.getSubscriptions();
        LOGGER.info("Récupération des abonnements - nombre total: {}", subscriptions.size());

        if (subscriptions.isEmpty()) {
            LOGGER.info("Aucun abonnement trouvé.");
        } else {
            LOGGER.info("Vos abonnements podcast ({})", subscriptions.size());
            for (int i = 0; i < subscriptions.size(); i++) {
                LOGGER.info("{}. {}", (i + 1), subscriptions.get(i));
            }
        }
    }

    private static void addPodcast(NextcloudGPodderClient client, Scanner scanner) throws IOException, InterruptedException {
        LOGGER.info("URL du flux RSS du podcast à ajouter: ");
        String podcastUrl = scanner.nextLine();

        LOGGER.debug("Tentative d'ajout du podcast: {}", podcastUrl);
        String updateId = client.updateSubscriptions(List.of(podcastUrl), List.of());
        LOGGER.info("Podcast ajouté avec succès - ID de mise à jour: {}", updateId);
    }

    private static void removePodcast(NextcloudGPodderClient client, Scanner scanner) throws IOException, InterruptedException {
        List<String> subscriptions = client.getSubscriptions();
        if (subscriptions.isEmpty()) {
            LOGGER.info("Vous n'avez aucun podcast à supprimer.");
            return;
        }

        LOGGER.info("Choisissez le podcast à supprimer:");
        for (int i = 0; i < subscriptions.size(); i++) {
            LOGGER.info("{}. {}", (i + 1), subscriptions.get(i));
        }
        LOGGER.info("Numéro du podcast: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consommer le newline

        if (index >= 0 && index < subscriptions.size()) {
            String podcastToRemove = subscriptions.get(index);
            LOGGER.debug("Tentative de suppression du podcast: {}", podcastToRemove);

            String updateId = client.updateSubscriptions(List.of(), List.of(podcastToRemove));
            LOGGER.info("Podcast supprimé avec succès - ID de mise à jour: {}", updateId);
        } else {
            LOGGER.warn("Tentative d'accès à un index invalide: {} (max: {})", index, subscriptions.size() - 1);
            LOGGER.info("Numéro de podcast invalide.");
        }
    }

    private static void markEpisodeAsListened(NextcloudGPodderClient client, String deviceId, Scanner scanner) throws IOException, InterruptedException {
        LOGGER.info("URL du flux du podcast: ");
        String podcastUrl = scanner.nextLine();

        LOGGER.info("URL de l'épisode: ");
        String episodeUrl = scanner.nextLine();

        LOGGER.debug("Marquage de l'épisode comme écouté - Podcast: {}, Épisode: {}", podcastUrl, episodeUrl);

        long timestamp = System.currentTimeMillis() / 1000;
        List<Map<String, Object>> actions = List.of(
                Map.of(
                        "podcast", podcastUrl,
                        "episode", episodeUrl,
                        "action", "play",
                        "timestamp", timestamp,
                        "position", 0  // position en secondes (0 = terminé)
                )
        );

        client.uploadEpisodeActions(deviceId, actions);
        LOGGER.info("Épisode marqué comme écouté - Timestamp: {}", timestamp);
    }

    private static void syncEpisodes(NextcloudGPodderClient client, String deviceId) throws InterruptedException, IOException {
        // Récupère les actions depuis la dernière heure (3600 secondes)
        long sinceTimestamp = (System.currentTimeMillis() / 1000) - 3600;
        LOGGER.debug("Synchronisation des épisodes depuis le timestamp: {}", sinceTimestamp);

        Map<String, Object> episodeActions = client.getEpisodeActions(deviceId, sinceTimestamp);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> actions = (List<Map<String, Object>>) episodeActions.get("actions");
        LOGGER.info("Synchronisation des épisodes terminée - Nombre d'épisodes: {}", actions.size());

        if (!actions.isEmpty()) {
            LOGGER.info("Épisodes récemment synchronisés: {}", actions.size());
            for (Map<String, Object> action : actions) {
                LOGGER.info("- Podcast: {}", action.get("podcast"));
                LOGGER.info("  Épisode: {}", action.get("episode"));
                LOGGER.info("  Action: {}", action.get("action"));
                LOGGER.info("  Timestamp: {}", action.get("timestamp"));
            }
        }
    }
}