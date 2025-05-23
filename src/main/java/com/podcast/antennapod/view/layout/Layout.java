package com.podcast.antennapod.view.layout;

import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class Layout {
    private final String title;

    protected Layout(String title) {
        this.title = title;
    }


    protected Label getTitle() {
        Label label = new Label(title);
        label.setFont(Font.font("Inter", FontWeight.BOLD, 36));
        label.setTextFill(ColorThemeConstants.getMain950());
        return label;
    }

    protected VBox getContainer() {
        VBox box = new VBox();
        box.setPadding(new Insets(32.0, 64.0, 32.0, 64.0));
        box.setSpacing(35.0);
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        HBox.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    public abstract VBox getLayout();
}
