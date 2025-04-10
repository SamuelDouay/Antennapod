package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

public class NavigationComponent {
    private NavigationComponent() {

    }

    public static Node createNavigation(FontIcon icon, String title, int number) {
        Label titleLabel = new Label(title);
        Label numberLabel = new Label(String.valueOf(number));


        icon.setIconSize(25);
        icon.setIconColor(ColorThemeConstants.getLightAc08());
        titleLabel.setMaxWidth(140.0);

        HBox hBox = new HBox();
        hBox.getChildren().add(icon);
        hBox.getChildren().add(titleLabel);
        hBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        hBox.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        hBox.setSpacing(14.0);
        hBox.setAlignment(Pos.CENTER);


        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);


        HBox box = new HBox();

        box.setPadding(new Insets(6.0,12.0,6.0,12.0));
        box.setMaxWidth(224.0);
        box.setAlignment(Pos.CENTER);

        box.getChildren().add(hBox);
        box.getChildren().add(region);
        box.getChildren().add(numberLabel);

        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getLightBg06(), new CornerRadii(2.0), Insets.EMPTY)));

        return box;
    }

    public static Node createNavigation(String urlImage, String title, int number) {
        Label titleLabel = new Label(title);
        Label numberLabel = new Label(String.valueOf(number));
        ImageView imageView = new ImageView(urlImage);

        imageView.setFitWidth(25.0);
        imageView.setFitHeight(25.0);



        titleLabel.setMaxWidth(140.0);

        HBox hBox = new HBox();
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(titleLabel);
        hBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        hBox.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        hBox.setSpacing(14.0);
        hBox.setAlignment(Pos.CENTER);


        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);


        HBox box = new HBox();

        box.setPadding(new Insets(6.0,12.0,6.0,12.0));
        box.setMaxWidth(224.0);
        box.setAlignment(Pos.CENTER);

        box.getChildren().add(hBox);
        box.getChildren().add(region);
        box.getChildren().add(numberLabel);

        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getLightIc07(), new CornerRadii(2.0), Insets.EMPTY)));

        return box;
    }
}
