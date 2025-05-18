package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.item.EpisodeItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignD;

public class SurpriseComponent {
    private SurpriseComponent() {
        //  no instance
    }

    public static HBox createSurprise(EpisodeItem episodeItem) {
        HBox box = new HBox();
        box.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        HBox.setHgrow(box, Priority.ALWAYS);
        box.setPadding(new Insets(8.0, 16.0,8.0,16.0));
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain500(), null, null)));
        box.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(episodeItem.getUrlImage());
        imageView.setFitHeight(45.0);
        imageView.setFitWidth(45.0);

        VBox titleBox = getTextComponent(episodeItem.getName(), episodeItem.getName());

        FontIcon icon = new FontIcon(MaterialDesignD.DOTS_VERTICAL);
        icon.setIconSize(15);


        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        box.getChildren().addAll(imageView, region, titleBox, region1, icon);

        return box;
    }

    private static VBox getTextComponent(String title, String podcast) {
        VBox box = new VBox();
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setSpacing(8.0);

        Label labelTitle = new Label(title);
        labelTitle.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        labelTitle.setTextFill(ColorThemeConstants.getMain950());
        labelTitle.setMaxWidth(250.0);


        Label labelPodcast = new Label(podcast);
        labelPodcast.setFont(Font.font("Inter", FontPosture.REGULAR, 10));
        labelPodcast.setTextFill(ColorThemeConstants.getGrey900());
        labelPodcast.setMaxWidth(150.0);

        box.getChildren().addAll(labelTitle, labelPodcast);

        return box;
    }
}
