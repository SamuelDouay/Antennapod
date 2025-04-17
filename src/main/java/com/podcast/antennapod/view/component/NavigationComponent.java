package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public final class NavigationComponent {
    // Constants
    private static final double ICON_SIZE = 25.0;
    private static final double TITLE_MAX_WIDTH = 140.0;
    private static final double MAX_WIDTH = 224.0;
    private static final double SPACING = 14.0;
    private static final Insets PADDING = new Insets(6.0, 12.0, 6.0, 12.0);

    private NavigationComponent() {
        // Prevents instantiation
    }

    public static HBox createNavigation(NavigationItem item) {
        if (item.getImageUrl() != null) {
            ImageView imageView = new ImageView(item.getImageUrl());
            imageView.setFitWidth(ICON_SIZE);
            imageView.setFitHeight(ICON_SIZE);
            return createNavigationBase(imageView, item.getTitle(), item.getNumber());

        } else {
            item.getIcon().setIconSize((int) ICON_SIZE);
            item.getIcon().setIconColor(ColorThemeConstants.getAt02());
            return createNavigationBase(item.getIcon(), item.getTitle(), item.getNumber());
        }
    }

    private static HBox createNavigationBase(Node graphic, String title, int number) {
        // Create and configure title label
        Label titleLabel = new Label(title);
        titleLabel.setMaxWidth(TITLE_MAX_WIDTH);
        titleLabel.setTextFill(ColorThemeConstants.getAt02());

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

        if (number != 0) {
            Label numberLabel = new Label(String.valueOf(number));
            numberLabel.setTextFill(ColorThemeConstants.getAt08());
            mainBox.getChildren().addAll(iconTitleBox, spacer, numberLabel);
        } else {
            mainBox.getChildren().addAll(iconTitleBox, spacer);
        }
        mainBox.setUserData(graphic);

        return mainBox;
    }
}