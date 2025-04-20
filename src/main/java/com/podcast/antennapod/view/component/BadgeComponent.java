package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.BadgeType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import static com.podcast.antennapod.view.util.Constant.*;

public class BadgeComponent {
    private BadgeComponent() {
        // Constructeur privé pour éviter l'instanciation
    }

    public static Node createBadge(String text, FontIcon icon, BadgeType type, boolean iconOnly) {
        if (type == null) {
            return null;
        }

        if (iconOnly && icon != null) {
            return createIconBadge(icon, type);
        } else {
            return createTextBadge(text, icon, type);
        }
    }

    public static Node createBadge(String text, BadgeType type) {
        return createBadge(text, null, type, false);
    }

    public static Node createBadge(String text, FontIcon icon, BadgeType type) {
        return createBadge(text, icon, type, false);
    }

    public static Node createBadge(FontIcon icon, BadgeType type) {
        return createBadge(null, icon, type, true);
    }

    private static Node createTextBadge(String text, FontIcon icon, BadgeType type) {
        Label label = new Label(text);

        // Appliquer les propriétés communes
        label.setPrefWidth(BADGE_DEFAULT_WIDTH);
        label.setPadding(BADGE_DEFAULT_PADDING);
        label.setAlignment(Pos.CENTER);

        // Ajouter une icône si fournie
        if (icon != null) {
            icon.setIconColor(type.getTextColor());
            icon.setIconSize(BADGE_ICON_SIZE);
            label.setGraphic(icon);
            label.setGraphicTextGap(5);
        }

        // Appliquer les couleurs spécifiques au type
        label.setBackground(new Background(
                new BackgroundFill(type.getBackgroundColor(), CORNER_RADII, Insets.EMPTY)
        ));
        label.textFillProperty().set(type.getTextColor());

        return label;
    }

    /**
     * Méthode privée pour créer un badge avec uniquement une icône
     */
    private static Node createIconBadge(FontIcon fontIcon, BadgeType type) {
        VBox box = new VBox();
        fontIcon.setIconColor(type.getTextColor());
        fontIcon.setIconSize(BADGE_ICON_SIZE);

        box.setBackground(new Background(
                new BackgroundFill(type.getBackgroundColor(), CORNER_RADII, Insets.EMPTY)
        ));

        box.getChildren().add(fontIcon);
        box.setPadding(BADGE_ICON_PADDING);

        box.setAlignment(Pos.CENTER);
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return box;
    }
}