package com.podcast.antennapod.view.item;

import java.util.UUID;

public class Item {
    private boolean selected;
    private final UUID uuid;

    public Item() {
        this.selected = false;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
