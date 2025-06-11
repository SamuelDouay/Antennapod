package com.podcast.antennapod.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;

public class LogicCommunicationService {
    private static final Logger logger = LogManager.getLogger(LogicCommunicationService.class);
    private final MessageBus messageBus;
    private final AtomicLong messagesSent = new AtomicLong(0);
    private final AtomicLong messagesReceived = new AtomicLong(0);

    public LogicCommunicationService() {
        this.messageBus = MessageBus.getInstance();
        setupSubscriptions();
    }

    private void setupSubscriptions() {
        // S'abonner aux messages destinés à la logique
        messageBus.subscribe("LOGIC.RESET_SIMULATION", this::handleResetSimulation);
        messageBus.subscribe("LOGIC.SET_SIMULATION_SPEED", this::handleSetSimulationSpeed);
        messageBus.subscribe("LOGIC.GET_STATUS", this::handleGetStatus);
        messageBus.subscribe("LOGIC.SHUTDOWN", this::handleShutdown);

        logger.info("LogicCommunicationService initialisé");
    }

    public void sendStatusToView(Object status) {
        messageBus.publish("VIEW.LOGIC_STATUS", status, MessagePriority.NORMAL);
        messagesSent.incrementAndGet();
    }

    public void sendCycleComplete(int counter) {
        messageBus.publish("VIEW.CYCLE_COMPLETE", counter, MessagePriority.HIGH);
        messagesSent.incrementAndGet();
    }

    public void sendError(String error) {
        messageBus.publish("VIEW.ERROR", error, MessagePriority.CRITICAL);
        messagesSent.incrementAndGet();
    }

    private void handleResetSimulation(Message message) {
        messagesReceived.incrementAndGet();
        logger.info("Commande de reset de simulation reçue");
        // Le handler sera défini par MainLogic
        messageBus.publish("LOGIC.INTERNAL.RESET_SIMULATION", message.getData());
    }

    private void handleSetSimulationSpeed(Message message) {
        messagesReceived.incrementAndGet();
        logger.info("Commande de changement de vitesse reçue: {}", message.getData());
        messageBus.publish("LOGIC.INTERNAL.SET_SPEED", message.getData());
    }

    private void handleGetStatus(Message message) {
        messagesReceived.incrementAndGet();
        messageBus.publish("LOGIC.INTERNAL.GET_STATUS", message.getData());
    }

    private void handleShutdown(Message message) {
        messagesReceived.incrementAndGet();
        logger.info("Commande d'arrêt reçue");
        messageBus.publish("LOGIC.INTERNAL.SHUTDOWN", message.getData());
    }

    public long getMessagesSent() {
        return messagesSent.get();
    }

    public long getMessagesReceived() {
        return messagesReceived.get();
    }
}