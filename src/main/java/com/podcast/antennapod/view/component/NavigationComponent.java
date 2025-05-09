package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;

public class NavigationComponent {
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
            return createNavigationBase(imageView, item);

        } else {
            item.getIcon().setIconSize((int) ICON_SIZE);
            item.getIcon().setIconColor(ColorThemeConstants.getGrey800());
            return createNavigationBase(item.getIcon(), item);
        }
    }

    private static HBox createNavigationBase(Node graphic, NavigationItem item) {

        // Create and configure title label
        Label titleLabel = new Label(item.getTitle());
        titleLabel.setMaxWidth(TITLE_MAX_WIDTH);
        titleLabel.setFont(Font.font("Inter", FontPosture.REGULAR, 12));
        titleLabel.setTextFill(ColorThemeConstants.getGrey800());

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

        if (item.getNumber() != 0) {
            Label numberLabel = new Label(String.valueOf(item.getNumber()));
            numberLabel.setTextFill(ColorThemeConstants.getMain950());
            numberLabel.setFont(Font.font("Inter", FontWeight.BOLD, 10));
            mainBox.getChildren().addAll(iconTitleBox, spacer, numberLabel);
        } else {
            mainBox.getChildren().addAll(iconTitleBox, spacer);
        }
        updateAppearance(mainBox, item.isSelected());
        return mainBox;
    }

    public static void updateAppearance(HBox mainBox, boolean isSelected) {
        Label titleLabel = (Label) ((HBox) mainBox.getChildren().getFirst()).getChildren().get(1);
        Node icon = ((HBox) mainBox.getChildren().getFirst()).getChildren().get(0);

        if (isSelected) {
            titleLabel.setTextFill(ColorThemeConstants.getMain950());
            titleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 12));
            mainBox.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain100(), null, null)));
            if (!(icon instanceof FontIcon)) {
                return;
            }
            ((FontIcon) icon).setIconColor(ColorThemeConstants.getMain950());
        } else {
            titleLabel.setTextFill(ColorThemeConstants.getGrey800());
            titleLabel.setFont(Font.font("Inter", FontPosture.REGULAR, 12));
            mainBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
            if (!(icon instanceof FontIcon)) {
                return;
            }
            ((FontIcon) icon).setIconColor(ColorThemeConstants.getGrey800());
        }
    }
}