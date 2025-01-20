package com.podcast.antennapod.old.style;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BorderStyle {

    private BorderStyle()

    public static Border rigth() {
        return  new Border(new BorderStroke(null, Color.web("#1A2B2C"), null, null,
                null, BorderStrokeStyle.SOLID, null, null,
                CornerRadii.EMPTY,
                new BorderWidths(1),
                Insets.EMPTY));
    }
}
