package com.podcast.antennapod.old.style;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ButtonStyle {

    private ButtonStyle()

    public static void setButtonStyle(HBox hBox) {
        hBox.setBackground(null);
        hBox.setBorder(null);
        hBox.setPadding(new Insets(6.0, 12.0, 6.0, 10.0));
        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.setAlignment(Pos.CENTER_LEFT);

        hBox.setOnMouseEntered(_ -> hBox.setBackground(new Background(new BackgroundFill(Color.web("#E7EEEE"), new CornerRadii(2.0), Insets.EMPTY))));

        hBox.setOnMouseExited(_ -> hBox.setBackground(null));

        hBox.setOnMousePressed(_ -> hBox.setBackground(new Background(new BackgroundFill(Color.web("#C9D8D9"),  new CornerRadii(2.0), Insets.EMPTY))));

        hBox.setOnMouseReleased(_ -> hBox.setBackground(new Background(new BackgroundFill(Color.web("#E7EEEE"),  new CornerRadii(2.0), Insets.EMPTY))));
    }
}
