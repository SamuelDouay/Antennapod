package com.podcast.antennapod.view.util;

import javafx.scene.paint.Color;

public enum BadgeType {
    GREEN("Green", Color.hsb(182.0, 0.97, 0.6, 0.2275), Color.hsb(183.0, 1, 0.28, 0.7804)),
    RED("Red", Color.hsb(1.0, 0.24, 1, 1), Color.hsb(2.0, 1, 0.62, 0.7294)),
    PURPLE("Purple", Color.hsb(233.0, 0.16, 1, 1), Color.hsb(251.0, 0.787, 0.98, 1));

    private final String typeName;
    private final Color backgroundColor;
    private final Color textColor;

    BadgeType(String typeName, Color backgroundColor, Color textColor) {
        this.typeName = typeName;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public String getTypeName() {
        return typeName;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }
}
