package com.podcast.antennapod.test.gppoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TEST {
    private static final Logger logger = LogManager.getLogger(TEST.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configuration du client
        logger.info("Configuration de la synchronisation Nextcloud GPodder");
        logger.info("URL de votre Nextcloud (ex: https://cloud.example.com): ");
        String nextcloudUrl = scanner.nextLine();

        logger.info("Nom d'utilisateur: ");
        String username = scanner.nextLine();

        logger.info("Mot de passe: ");
        String password = scanner.nextLine();

        String deviceId = "JavaPodcastApp_" + System.currentTimeMillis();
        logger.info("Identifiant de l'appareil: " + deviceId);

        try {
            NextcloudGPodderClient client = new NextcloudGPodderClient(username, password, nextcloudUrl);

            // Menu principal
            boolean running = true;
            while (running) {
                logger.info("\n=== Menu GPodder Nextcloud Sync ===");
                logger.info("1. Afficher mes abonnements");
                logger.info("2. Ajouter un podcast");
                logger.info("3. Supprimer un podcast");
                logger.info("4. Marquer un épisode comme écouté");
                logger.info("5. Synchroniser les épisodes écoutés");
                logger.info("0. Quitter");
                logger.info("> ");

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
                        logger.info("Au revoir!");
                        break;
                    default:
                        logger.info("Option invalide. Veuillez réessayer.");
                }
            }

        } catch (Exception e) {
            logger.error("Erreur: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void listSubscriptions(NextcloudGPodderClient client) {
        try {
            List<String> subscriptions = client.getSubscriptions();
            logger.info("\nVos abonnements podcast (" + subscriptions.size() + "):");
            if (subscriptions.isEmpty()) {
                logger.info("Aucun abonnement trouvé.");
            } else {
                for (int i = 0; i < subscriptions.size(); i++) {
                    logger.info((i + 1) + ". " + subscriptions.get(i));
                }
            }
        } catch (Exception e) {
            logger.error("Impossible de récupérer les abonnements: " + e.getMessage());
        }
    }

    private static void addPodcast(NextcloudGPodderClient client, Scanner scanner) {
        try {
            logger.info("URL du flux RSS du podcast à ajouter: ");
            String podcastUrl = scanner.nextLine();

            String updateId = client.updateSubscriptions(List.of(podcastUrl), List.of());
            logger.info("Podcast ajouté avec succès! ID de mise à jour: " + updateId);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout du podcast: " + e.getMessage());
        }
    }

    private static void removePodcast(NextcloudGPodderClient client, Scanner scanner) {
        try {
            List<String> subscriptions = client.getSubscriptions();
            if (subscriptions.isEmpty()) {
                logger.info("Vous n'avez aucun podcast à supprimer.");
                return;
            }

            logger.info("\nChoisissez le podcast à supprimer:");
            for (int i = 0; i < subscriptions.size(); i++) {
                logger.info((i + 1) + ". " + subscriptions.get(i));
            }
            logger.info("Numéro du podcast: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // consommer le newline

            if (index >= 0 && index < subscriptions.size()) {
                String podcastToRemove = subscriptions.get(index);
                String updateId = client.updateSubscriptions(List.of(), List.of(podcastToRemove));
                logger.info("Podcast supprimé avec succès! ID de mise à jour: " + updateId);
            } else {
                logger.info("Numéro de podcast invalide.");
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du podcast: " + e.getMessage());
        }
    }

    private static void markEpisodeAsListened(NextcloudGPodderClient client, String deviceId, Scanner scanner) {
        try {
            logger.info("URL du flux du podcast: ");
            String podcastUrl = scanner.nextLine();

            logger.info("URL de l'épisode: ");
            String episodeUrl = scanner.nextLine();

            List<Map<String, Object>> actions = List.of(
                    Map.of(
                            "podcast", podcastUrl,
                            "episode", episodeUrl,
                            "action", "play",
                            "timestamp", System.currentTimeMillis() / 1000,
                            "position", 0  // position en secondes (0 = terminé)
                    )
            );

            Map<String, Object> result = client.uploadEpisodeActions(deviceId, actions);
            logger.info("Épisode marqué comme écouté! Résultat: " + result);
        } catch (Exception e) {
            logger.error("Erreur lors du marquage de l'épisode: " + e.getMessage());
        }
    }

    private static void syncEpisodes(NextcloudGPodderClient client, String deviceId) {
        try {
            // Récupère les actions depuis la dernière heure (3600 secondes)
            long sinceTimestamp = (System.currentTimeMillis() / 1000) - 3600;
            Map<String, Object> episodeActions = client.getEpisodeActions(deviceId, sinceTimestamp);

            List<Map<String, Object>> actions = (List<Map<String, Object>>) episodeActions.get("actions");
            logger.info("\nÉpisodes récemment synchronisés: " + actions.size());

            for (Map<String, Object> action : actions) {
                logger.info("- Podcast: " + action.get("podcast"));
                logger.info("  Épisode: " + action.get("episode"));
                logger.info("  Action: " + action.get("action"));
                logger.info("  Timestamp: " + action.get("timestamp"));
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la synchronisation des épisodes: " + e.getMessage());
        }
    }
}