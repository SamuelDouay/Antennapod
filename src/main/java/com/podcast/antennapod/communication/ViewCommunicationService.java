package com.podcast.antennapod.communication;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class ViewCommunicationService {
    private static final Logger logger = LogManager.getLogger(ViewCommunicationService.class);
    private final MessageBus messageBus;
    private final AtomicLong messagesSent = new AtomicLong(0);
    private final AtomicLong messagesReceived = new AtomicLong(0);

    // Handlers pour les différents types de messages
    private Consumer<Object> statusHandler;
    private Consumer<Object> cycleCompleteHandler;
    private Consumer<Object> errorHandler;

    public ViewCommunicationService() {
        this.messageBus = MessageBus.getInstance();
        setupSubscriptions();
    }

    private void setupSubscriptions() {
        // S'abonner aux messages destinés à la vue
        messageBus.subscribe("VIEW.LOGIC_STATUS", this::handleLogicStatus);
        messageBus.subscribe("VIEW.CYCLE_COMPLETE", this::handleCycleComplete);
        messageBus.subscribe("VIEW.ERROR", this::handleError);
        messageBus.subscribe("VIEW.SHUTDOWN", this::handleShutdown);

        logger.info("ViewCommunicationService initialisé");
    }

    // Méthodes pour envoyer des commandes à la logique
    public void requestResetSimulation() {
        messageBus.publish("LOGIC.RESET_SIMULATION", null, MessagePriority.HIGH);
        messagesSent.incrementAndGet();
    }

    public void requestSetSimulationSpeed(double speed) {
        messageBus.publish("LOGIC.SET_SIMULATION_SPEED", speed, MessagePriority.NORMAL);
        messagesSent.incrementAndGet();
    }

    public void requestStatus() {
        messageBus.publish("LOGIC.GET_STATUS", null, MessagePriority.NORMAL);
        messagesSent.incrementAndGet();
    }

    public void requestShutdown() {
        messageBus.publish("LOGIC.SHUTDOWN", null, MessagePriority.CRITICAL);
        messagesSent.incrementAndGet();
    }

    // Setters pour les handlers
    public void setStatusHandler(Consumer<Object> handler) {
        this.statusHandler = handler;
    }

    public void setCycleCompleteHandler(Consumer<Object> handler) {
        this.cycleCompleteHandler = handler;
    }

    public void setErrorHandler(Consumer<Object> handler) {
        this.errorHandler = handler;
    }

    // Handlers privés pour les messages entrants
    private void handleLogicStatus(Message message) {
        messagesReceived.incrementAndGet();
        Platform.runLater(() -> {
            if (statusHandler != null) {
                try {
                    statusHandler.accept(message.getData());
                } catch (Exception e) {
                    logger.error("Erreur dans le handler de statut", e);
                }
            }
        });
    }

    private void handleCycleComplete(Message message) {
        messagesReceived.incrementAndGet();
        Platform.runLater(() -> {
            if (cycleCompleteHandler != null) {
                try {
                    cycleCompleteHandler.accept(message.getData());
                } catch (Exception e) {
                    logger.error("Erreur dans le handler de cycle complet", e);
                }
            }
        });
    }

    private void handleError(Message message) {
        messagesReceived.incrementAndGet();
        Platform.runLater(() -> {
            if (errorHandler != null) {
                try {
                    errorHandler.accept(message.getData());
                } catch (Exception e) {
                    logger.error("Erreur dans le handler d'erreur", e);
                }
            }
        });
    }

    private void handleShutdown(Message message) {
        messagesReceived.incrementAndGet();
        logger.info("Commande d'arrêt de la vue reçue");
        Platform.runLater(Platform::exit);
    }

    public long getMessagesSent() {
        return messagesSent.get();
    }

    public long getMessagesReceived() {
        return messagesReceived.get();
    }
}