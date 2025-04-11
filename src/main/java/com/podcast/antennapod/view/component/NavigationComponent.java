package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

public final class NavigationComponent {
    // Constants
    private static final double ICON_SIZE = 25.0;
    private static final double TITLE_MAX_WIDTH = 140.0;
    private static final double MAX_WIDTH = 224.0;
    private static final double SPACING = 14.0;
    private static final Insets PADDING = new Insets(6.0, 12.0, 6.0, 12.0);
    private static final CornerRadii CORNER_RADII = new CornerRadii(2.0);

    private NavigationComponent() {
        // Prevents instantiation
    }

    public static HBox createNavigation(FontIcon icon, String title, int number) {
        icon.setIconSize((int)ICON_SIZE);
        icon.setIconColor(ColorThemeConstants.getAt02());
        return createNavigationBase(icon, title, number);
    }

    public static HBox createNavigation(String urlImage, String title, int number) {
        ImageView imageView = new ImageView(urlImage);
        imageView.setFitWidth(ICON_SIZE);
        imageView.setFitHeight(ICON_SIZE);

        return createNavigationBase(imageView, title, number);
    }

    private static HBox createNavigationBase(Node graphic, String title, int number) {
        // Create and configure title label
        Label titleLabel = new Label(title);
        titleLabel.setMaxWidth(TITLE_MAX_WIDTH);
        titleLabel.setTextFill(ColorThemeConstants.getAt02());

        // Create number label
        Label numberLabel = new Label(String.valueOf(number));
        numberLabel.setTextFill(ColorThemeConstants.getAt08());

        // Create icon and title container
        HBox iconTitleBox = new HBox(SPACING, graphic, titleLabel);
        iconTitleBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        iconTitleBox.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        iconTitleBox.setAlignment(Pos.CENTER);

        // Create spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Create main container
        HBox mainBox = new HBox();
        mainBox.setPadding(PADDING);
        mainBox.setMaxWidth(MAX_WIDTH);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(iconTitleBox, spacer, numberLabel);

        // Set background
        mainBox.setBackground(null);

        mainBox.setOnMouseEntered(e -> {
            mainBox.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getIc07(), CORNER_RADII, Insets.EMPTY)));
            titleLabel.setTextFill(ColorThemeConstants.getAt08());
            if (!(graphic instanceof FontIcon)) {
                return;
            }
            ((FontIcon) graphic).setIconColor(ColorThemeConstants.getAt08());

        });
        mainBox.setOnMouseExited(e -> {
            mainBox.setBackground(null);
            titleLabel.setTextFill(ColorThemeConstants.getAt02());
            if (!(graphic instanceof FontIcon)) {
                return;
            }
            ((FontIcon) graphic).setIconColor(ColorThemeConstants.getAt02());

        });
        mainBox.setOnMousePressed(e -> mainBox.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getIc12(), CORNER_RADII, Insets.EMPTY))));
        mainBox.setOnMouseReleased(e -> {
            if (mainBox.isHover()) {
                mainBox.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getIc07(), CORNER_RADII, Insets.EMPTY)));
            } else {
                mainBox.setBackground(null);
            }
        });

        return mainBox;
    }
}