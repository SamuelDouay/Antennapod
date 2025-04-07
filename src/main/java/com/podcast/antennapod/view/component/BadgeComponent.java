package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.BadgeType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

import static com.podcast.antennapod.view.util.Constant.*;

public class BadgeComponent {
    private BadgeComponent() {

    }

    private static Node createBadge(String text, BadgeType type) {
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

    private static Node createBadgeIcon(FontIcon fontIcon, BadgeType type) {
        VBox box = new VBox();
        fontIcon.setIconColor(type.getTextColor());
        fontIcon.setIconSize(BADGE_ICON_SIZE);

        box.setBackground(new Background(
                new BackgroundFill(type.getBackgroundColor(), CORNER_RADII, Insets.EMPTY)
        ));

        box.getChildren().add(fontIcon);
        box.setPadding(BADGE_ICON_PADDING);

        return box;
    }

    public static Node getBadgeGreen(String name) {
        return createBadge(name, BadgeType.GREEN);
    }

    public static Node getBadgeRed(String name) {
        return createBadge(name, BadgeType.RED);
    }

    public static Node getBadgeBlue(String name) {
        return createBadge(name, BadgeType.BLUE);
    }

    public static Node getBadgePurple(String name) {
        return createBadge(name, BadgeType.PURPLE);
    }

    public static Node getBadgeIconGreen(FontIcon fontIcon) {
        return createBadgeIcon(fontIcon, BadgeType.GREEN);
    }

    public static Node getBadgeIconRed(FontIcon fontIcon) {
        return createBadgeIcon(fontIcon, BadgeType.RED);
    }

    public static Node getBadgeIconBlue(FontIcon fontIcon) {
        return createBadgeIcon(fontIcon, BadgeType.BLUE);
    }

    public static Node getBadgeIconPurple(FontIcon fontIcon) {
        return createBadgeIcon(fontIcon, BadgeType.PURPLE);
    }
}
