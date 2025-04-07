package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.BadgeType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import static com.podcast.antennapod.view.util.Constant.*;

public class BadgeComponent {
    private BadgeComponent() {

    }

    public static Node createBadge(String text, BadgeType type) {
        Label label = new Label(text);

        // Appliquer les propriétés communes
        label.setPrefWidth(BADGE_DEFAULT_WIDTH);
        label.setPadding(BADGE_DEFAULT_PADDING);
        label.setAlignment(Pos.CENTER);

        // Appliquer les couleurs spécifiques au type
        label.setBackground(new Background(
                new BackgroundFill(type.getBackgroundColor(), CORNER_RADII, Insets.EMPTY)
        ));
        label.textFillProperty().set(type.getTextColor());

        return label;
    }

    public static Node getBadgeGreen(String name) {
        return createBadge(name, BadgeType.GREEN);
    }

    public static Node getBadgeRed(String name) {
        return createBadge(name, BadgeType.RED);
    }

    public static Node getBadgePurple(String name) {
        return createBadge(name, BadgeType.PURPLE);
    }
}
