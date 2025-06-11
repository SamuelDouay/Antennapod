package com.podcast.antennapod.logic;

import com.podcast.antennapod.communication.LogicCommunicationService;
import com.podcast.antennapod.communication.MessageBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class MainLogic {
    private static final Logger logger = LogManager.getLogger(MainLogic.class);
    private static final AtomicBoolean isRunning = new AtomicBoolean(false);
    private static final AtomicLong frameCount = new AtomicLong(0);
    private static final AtomicLong lastFpsTime = new AtomicLong(System.nanoTime());
    private static double currentFps = 0.0;

    // Données de simulation
    private static volatile double simulationValue = 0.0;
    private static volatile int logicCounter = 0;
    private static volatile double simulationSpeed = 1.0;

    private static LogicCommunicationService communicationService;
    private static MessageBus messageBus;

    private MainLogic() {
        // no param
    }

    public static void initialize() {
        communicationService = new LogicCommunicationService();
        messageBus = MessageBus.getInstance();
        setupInternalMessageHandlers();
        logger.info("MainLogic initialisé");
    }

    private static void setupInternalMessageHandlers() {
        // Handlers internes pour les commandes
        messageBus.subscribe("LOGIC.INTERNAL.RESET_SIMULATION", message -> {
            simulationValue = 0.0;
            logicCounter = 0;
            logger.info("Simulation réinitialisée");
        });

        messageBus.subscribe("LOGIC.INTERNAL.SET_SPEED", message -> {
            if (message.getData() instanceof Number) {
                simulationSpeed = ((Number) message.getData()).doubleValue();
                logger.info("Vitesse de simulation changée: {}", simulationSpeed);
            }
        });

        messageBus.subscribe("LOGIC.INTERNAL.GET_STATUS", _ -> sendStatusToView());

        messageBus.subscribe("LOGIC.INTERNAL.SHUTDOWN", _ -> stop());
    }

    public static void start() {
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("MainLogic déjà en cours d'exécution");
            return;
        }

        logger.info("Démarrage de la boucle Logic haute performance");

        long lastTime = System.nanoTime();

        while (isRunning.get()) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            try {
                // Logique métier principale
                updateLogic(deltaTime);

                // Calculer et afficher les FPS
                updateFpsCounter();

                // Envoyer des données à la vue périodiquement
                if (frameCount.get() % 60 == 0) {
                    sendStatusToView();
                }

                frameCount.incrementAndGet();

            } catch (Exception e) {
                logger.error("Erreur dans la boucle Logic", e);
                communicationService.sendError("Erreur dans la boucle Logic: " + e.getMessage());
            }
        }

        logger.info("Arrêt de la boucle Logic");
    }

    private static void updateLogic(double deltaTime) {
        // Simulation de logique métier avec vitesse variable
        simulationValue += deltaTime * 10.0 * simulationSpeed;
        logicCounter++;

        // Exemple de logique métier complexe
        if (simulationValue > 100.0) {
            simulationValue = 0.0;
            communicationService.sendCycleComplete(logicCounter);
        }
    }

    private static void updateFpsCounter() {
        long currentTime = System.nanoTime();
        if (currentTime - lastFpsTime.get() >= 1_000_000_000L) {
            currentFps = frameCount.get() / ((currentTime - lastFpsTime.get()) / 1_000_000_000.0);
            frameCount.set(0);
            lastFpsTime.set(currentTime);
        }
    }

    private static void sendStatusToView() {
        if (communicationService != null) {
            LogicStatus status = new LogicStatus(
                    currentFps,
                    simulationValue,
                    logicCounter,
                    simulationSpeed,
                    System.currentTimeMillis()
            );
            communicationService.sendStatusToView(status);
        }
    }

    public static void stop() {
        if (isRunning.compareAndSet(true, false)) {
            logger.info("Arrêt demandé pour MainLogic");
        }
    }

    public static boolean isRunning() {
        return isRunning.get();
    }

    public static double getCurrentFps() {
        return currentFps;
    }

    // Classe pour encapsuler les données de statut
    public static class LogicStatus {
        public final double fps;
        public final double simulationValue;
        public final int counter;
        public final double simulationSpeed;
        public final long timestamp;

        public LogicStatus(double fps, double simulationValue, int counter,
                           double simulationSpeed, long timestamp) {
            this.fps = fps;
            this.simulationValue = simulationValue;
            this.counter = counter;
            this.simulationSpeed = simulationSpeed;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return String.format("LogicStatus{fps=%.2f, simValue=%.2f, counter=%d, speed=%.2f}",
                    fps, simulationValue, counter, simulationSpeed);
        }
    }
}