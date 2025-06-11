package com.podcast.antennapod.communication;

public class Message {
    private final String topic;
    private final Object data;
    private final long timestamp;
    private final MessagePriority priority;
    private final String senderId;

    public Message(String topic, Object data, MessagePriority priority) {
        this.topic = topic;
        this.data = data;
        this.priority = priority;
        this.timestamp = System.nanoTime();
        this.senderId = Thread.currentThread().getName();
    }

    public String getTopic() {
        return topic;
    }

    public Object getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    public String getSenderId() {
        return senderId;
    }

    @Override
    public String toString() {
        return String.format("Message{topic='%s', priority=%s, sender='%s', timestamp=%d}",
                topic, priority, senderId, timestamp);
    }
}