package com.podcast.antennapod;

import com.podcast.antennapod.communication.MessageBus;
import com.podcast.antennapod.logic.MainLogic;
import com.podcast.antennapod.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final ExecutorService logicExecutor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "LogicThread");
        t.setDaemon(false);
        t.setPriority(Thread.MAX_PRIORITY);
        return t;
    });
    private static final ExecutorService viewExecutor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "ViewThread");
        t.setDaemon(false);
        return t;
    });

    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.now();

        logger.info("=== Démarrage de l'application AntennaPod avec MessageBus ===");
        logger.info("Heure de démarrage: {}",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(startTime));

        try {
            // Initialiser le MessageBus en premier
            MessageBus messageBus = MessageBus.getInstance();
            messageBus.start();

            // Initialiser MainLogic avec ses services de communication
            MainLogic.initialize();

            // Démarrer la logique métier
            CompletableFuture<Void> logicFuture = CompletableFuture.runAsync(() -> {
                Thread.currentThread().setName("LogicThread-HighPerf");
                logger.info("Démarrage de la logique métier haute performance");
                try {
                    MainLogic.start();
                } catch (Exception e) {
                    logger.error("Erreur dans le thread de logique métier", e);
                }
            }, logicExecutor);

            // Démarrer l'interface utilisateur
            CompletableFuture<Void> viewFuture = CompletableFuture.runAsync(() -> {
                Thread.currentThread().setName("ViewThread-JavaFX");
                logger.info("Démarrage de l'interface utilisateur");
                try {
                    MainView.main(args);
                } catch (Exception e) {
                    logger.error("Erreur dans le thread d'interface utilisateur", e);
                }
            }, viewExecutor);

            // Attendre la fin des threads
            CompletableFuture.allOf(logicFuture, viewFuture).join();

        } catch (Exception e) {
            logger.error("Erreur fatale lors de l'initialisation", e);
            System.exit(1);
        } finally {
            // Arrêt propre
            MessageBus.getInstance().stop();
            shutdownExecutor(logicExecutor, "Logique");
            shutdownExecutor(viewExecutor, "Vue");

            LocalDateTime.now();
            logger.info("=== Fermeture de l'application AntennaPod ===");
        }
    }

    private static void shutdownExecutor(ExecutorService executor, String name) {
        try {
            logger.info("Arrêt du thread {}", name);
            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                logger.warn("Le thread {} ne s'est pas terminé proprement, forçage", name);
                executor.shutdownNow();
            }
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
            logger.warn("Interruption lors de l'arrêt du thread {}", name);
            executor.shutdownNow();
        }
    }
}