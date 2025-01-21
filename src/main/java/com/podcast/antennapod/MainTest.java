package com.podcast.antennapod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {
    private static final Logger logger = LogManager.getLogger(MainTest.class);

    public static void main(String[] args) throws Exception {
        logger.info("=== Démarrage de l'application ===");
        logger.info("Initialisation du thread pool avec ExecutorService");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        
        // Premier CompletableFuture
        logger.info("Lancement de la première tâche asynchrone (getTestAsync)");
        CompletableFuture<String> completableFutureResult = CompletableFuture.supplyAsync(() -> {
            logger.debug("Exécution de getTestAsync avec délai de 1000ms");
            return getTestAsync(1000);
        }, executorService);

        completableFutureResult.thenAccept(result -> 
            logger.info("Résultat de la première tâche asynchrone reçu: {}", result))
        .exceptionally(throwable -> {
            logger.error("Erreur lors de l'exécution de la première tâche: {}", throwable.getMessage(), throwable);
            return null;
        });

        logger.info("Première tâche asynchrone lancée, continuation du traitement principal");

        // Deuxième CompletableFuture
        logger.info("Lancement de la deuxième tâche asynchrone (requête HTTP)");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                logger.debug("Démarrage de la requête HTTP");
                return request();
            } catch (Exception e) {
                logger.error("Erreur lors de la requête HTTP", e);
                throw new RuntimeException(e);
            }
        }, executorService);

        completableFuture.thenAccept(result ->
            logger.info("Résultat de la requête HTTP: {}", result))
        .exceptionally(throwable -> {
            logger.error("Erreur lors de la requête HTTP: {}", throwable.getMessage(), throwable);
            return null;
        });

        logger.info("Deuxième tâche asynchrone lancée, continuation du traitement principal");

        logger.info("Arrêt programmé du thread pool");
        executorService.shutdown();

        logger.info("=== Fin du programme ===");
    }

    private static String getTestAsync(int milli) {
        try {
            logger.debug("Début du délai de {}ms", milli);
            Thread.sleep(milli);
            logger.debug("Fin du délai de {}ms", milli);
        } catch (InterruptedException e) {
            logger.error("Interruption pendant le délai", e);
            Thread.currentThread().interrupt();
        }
        return "CompletableFuture Result";
    }

    public static String request() {
        logger.debug("Configuration du client HTTP");
        HttpClient client = HttpClient.newHttpClient();
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://feeds.acast.com/public/shows/5e6a404cd22bfc26784b114c"))
                    .timeout(Duration.ofSeconds(30))  // Ajout d'un timeout
                    .build();
    
            logger.info("Envoi de la requête HTTP vers Acast");
            CompletableFuture<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
    
            try {
                // Utilisation de get avec timeout au lieu d'une boucle while
                String responseBody = response.get(30, TimeUnit.SECONDS);
                logger.info("Réponse HTTP reçue, taille: {} caractères", responseBody.length());
                logger.debug("Contenu de la réponse: {}", responseBody);
                return responseBody;
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restauration du flag d'interruption
                throw new RuntimeException("Requête HTTP interrompue", e);
            } catch (TimeoutException e) {
                response.cancel(true); // Annulation de la requête
                throw new RuntimeException("Timeout de la requête HTTP après 30 secondes", e);
            } catch (ExecutionException e) {
                throw new RuntimeException("Erreur lors de l'exécution de la requête HTTP", e.getCause());
            }
    
        } catch (URISyntaxException e) {
            throw new RuntimeException("URL invalide", e);
        } catch (Exception e) {
            throw new RuntimeException("Erreur inattendue lors de la requête HTTP", e);
        }
    }
}