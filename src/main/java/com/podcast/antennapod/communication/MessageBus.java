package com.podcast.antennapod.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class MessageBus {
    private static final Logger logger = LogManager.getLogger(MessageBus.class);
    private static MessageBus instance;

    private final ConcurrentHashMap<String, List<Consumer<Message>>> subscribers = new ConcurrentHashMap<>();
    private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    private MessageBus() {
    }

    public static synchronized MessageBus getInstance() {
        if (instance == null) {
            instance = new MessageBus();
        }
        return instance;
    }

    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            // Thread de traitement des messages haute performance
            scheduler.execute(this::processMessages);
            logger.info("MessageBus démarré");
        }
    }

    public void stop() {
        if (isRunning.compareAndSet(true, false)) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt();
                scheduler.shutdownNow();
            }
            logger.info("MessageBus arrêté");
        }
    }

    public void subscribe(String topic, Consumer<Message> handler) {
        subscribers.computeIfAbsent(topic, k -> new CopyOnWriteArrayList<>()).add(handler);
        logger.debug("Nouvel abonné pour le topic: {}", topic);
    }

    public void unsubscribe(String topic, Consumer<Message> handler) {
        List<Consumer<Message>> handlers = subscribers.get(topic);
        if (handlers != null) {
            handlers.remove(handler);
            if (handlers.isEmpty()) {
                subscribers.remove(topic);
            }
        }
    }

    public void publish(String topic, Object data) {
        publish(topic, data, MessagePriority.NORMAL);
    }

    public void publish(String topic, Object data, MessagePriority priority) {
        Message message = new Message(topic, data, priority);
        if (!messageQueue.offer(message)) {
            logger.warn("Impossible d'ajouter le message à la queue: {}", topic);
        }
    }

    private void processMessages() {
        Thread.currentThread().setName("MessageBus-Processor");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        while (isRunning.get()) {
            try {
                Message message = messageQueue.poll(1, TimeUnit.MILLISECONDS);
                if (message != null) {
                    deliverMessage(message);
                }
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                logger.error("Erreur lors du traitement des messages", e);
            }
        }
    }

    private void deliverMessage(Message message) {
        List<Consumer<Message>> handlers = subscribers.get(message.getTopic());
        if (handlers != null && !handlers.isEmpty()) {
            for (Consumer<Message> handler : handlers) {
                try {
                    handler.accept(message);
                } catch (Exception e) {
                    logger.error("Erreur lors de la livraison du message {}: {}",
                            message.getTopic(), e.getMessage());
                }
            }
        } else {
            logger.debug("Aucun abonné pour le topic: {}", message.getTopic());
        }
    }

    public int getQueueSize() {
        return messageQueue.size();
    }

    public int getSubscriberCount(String topic) {
        List<Consumer<Message>> handlers = subscribers.get(topic);
        return handlers != null ? handlers.size() : 0;
    }
}
