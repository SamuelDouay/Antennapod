package com.podcast.antennapod.communication;

public enum MessagePriority {
    LOW(1),
    NORMAL(5),
    HIGH(10),
    CRITICAL(15);

    private final int value;

    MessagePriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}