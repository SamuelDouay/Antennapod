package com.podcast.antennapod.test.gppoder;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TEST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configuration du client
        System.out.println("Configuration de la synchronisation Nextcloud GPodder");
        System.out.print("URL de votre Nextcloud (ex: https://cloud.example.com): ");
        String nextcloudUrl = scanner.nextLine();

        System.out.print("Nom d'utilisateur: ");
        String username = scanner.nextLine();

        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();

        String deviceId = "JavaPodcastApp_" + System.currentTimeMillis();
        System.out.println("Identifiant de l'appareil: " + deviceId);

        try {
            NextcloudGPodderClient client = new NextcloudGPodderClient(username, password, nextcloudUrl);

            // Menu principal
            boolean running = true;
            while (running) {
                System.out.println("\n=== Menu GPodder Nextcloud Sync ===");
                System.out.println("1. Afficher mes abonnements");
                System.out.println("2. Ajouter un podcast");
                System.out.println("3. Supprimer un podcast");
                System.out.println("4. Marquer un épisode comme écouté");
                System.out.println("5. Synchroniser les épisodes écoutés");
                System.out.println("0. Quitter");
                System.out.print("> ");

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
                        System.out.println("Au revoir!");
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void listSubscriptions(NextcloudGPodderClient client) {
        try {
            List<String> subscriptions = client.getSubscriptions();
            System.out.println("\nVos abonnements podcast (" + subscriptions.size() + "):");
            if (subscriptions.isEmpty()) {
                System.out.println("Aucun abonnement trouvé.");
            } else {
                for (int i = 0; i < subscriptions.size(); i++) {
                    System.out.println((i + 1) + ". " + subscriptions.get(i));
                }
            }
        } catch (Exception e) {
            System.err.println("Impossible de récupérer les abonnements: " + e.getMessage());
        }
    }

    private static void addPodcast(NextcloudGPodderClient client, Scanner scanner) {
        try {
            System.out.print("URL du flux RSS du podcast à ajouter: ");
            String podcastUrl = scanner.nextLine();

            String updateId = client.updateSubscriptions(List.of(podcastUrl), List.of());
            System.out.println("Podcast ajouté avec succès! ID de mise à jour: " + updateId);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout du podcast: " + e.getMessage());
        }
    }

    private static void removePodcast(NextcloudGPodderClient client, Scanner scanner) {
        try {
            List<String> subscriptions = client.getSubscriptions();
            if (subscriptions.isEmpty()) {
                System.out.println("Vous n'avez aucun podcast à supprimer.");
                return;
            }

            System.out.println("\nChoisissez le podcast à supprimer:");
            for (int i = 0; i < subscriptions.size(); i++) {
                System.out.println((i + 1) + ". " + subscriptions.get(i));
            }
            System.out.print("Numéro du podcast: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // consommer le newline

            if (index >= 0 && index < subscriptions.size()) {
                String podcastToRemove = subscriptions.get(index);
                String updateId = client.updateSubscriptions(List.of(), List.of(podcastToRemove));
                System.out.println("Podcast supprimé avec succès! ID de mise à jour: " + updateId);
            } else {
                System.out.println("Numéro de podcast invalide.");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du podcast: " + e.getMessage());
        }
    }

    private static void markEpisodeAsListened(NextcloudGPodderClient client, String deviceId, Scanner scanner) {
        try {
            System.out.print("URL du flux du podcast: ");
            String podcastUrl = scanner.nextLine();

            System.out.print("URL de l'épisode: ");
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
            System.out.println("Épisode marqué comme écouté! Résultat: " + result);
        } catch (Exception e) {
            System.err.println("Erreur lors du marquage de l'épisode: " + e.getMessage());
        }
    }

    private static void syncEpisodes(NextcloudGPodderClient client, String deviceId) {
        try {
            // Récupère les actions depuis la dernière heure (3600 secondes)
            long sinceTimestamp = (System.currentTimeMillis() / 1000) - 3600;
            Map<String, Object> episodeActions = client.getEpisodeActions(deviceId, sinceTimestamp);

            List<Map<String, Object>> actions = (List<Map<String, Object>>) episodeActions.get("actions");
            System.out.println("\nÉpisodes récemment synchronisés: " + actions.size());

            for (Map<String, Object> action : actions) {
                System.out.println("- Podcast: " + action.get("podcast"));
                System.out.println("  Épisode: " + action.get("episode"));
                System.out.println("  Action: " + action.get("action"));
                System.out.println("  Timestamp: " + action.get("timestamp"));
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la synchronisation des épisodes: " + e.getMessage());
        }
    }
}