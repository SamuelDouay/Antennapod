package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.item.EpisodeItem;
import com.podcast.antennapod.view.util.BadgeType;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.TypeButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;

public class EpisodeComponent {
    private EpisodeComponent() {

    }

    public static HBox createNewEpisode(EpisodeItem episodeItem) {
        HBox box = new HBox(8.0);
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        box.getChildren().add(firstPart(episodeItem));
        box.getChildren().add(region);
        box.getChildren().add(secondPart(episodeItem));


        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain050(), null, null)));

        HBox.setHgrow(box, Priority.ALWAYS);

        box.setPadding(new Insets(8.0, 16.0, 8.0, 16.0));

        box.setAlignment(Pos.CENTER);

        return box;
    }

    private static HBox firstPart(EpisodeItem episodeItem) {
        HBox box = new HBox();
        box.setSpacing(16);

        FontIcon icon = new FontIcon(MaterialDesignS.STAR);
        icon.setIconColor(ColorThemeConstants.getMain700());

        if (!episodeItem.isFavorite()) {
            icon = new FontIcon(MaterialDesignS.STAR_OUTLINE);
            icon.setIconColor(ColorThemeConstants.getGrey900());
        }

        icon.setIconSize(15);


        ImageView image = new ImageView(episodeItem.getUrlImage());
        image.setFitHeight(40.0);
        image.setFitWidth(40.0);

        Label title = new Label(episodeItem.getName());
        title.maxWidth(360.0);
        title.setTextFill(ColorThemeConstants.getGrey950());
        title.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));

        box.getChildren().addAll(icon, image, title);

        box.setAlignment(Pos.CENTER);
        return box;
    }

    private static HBox secondPart(EpisodeItem episodeItem) {
        HBox box = new HBox();

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
        Region region3 = new Region();
        HBox.setHgrow(region3, Priority.ALWAYS);

        box.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignI.INBOX), BadgeType.BLUE));
        box.getChildren().add(region);
        box.getChildren().add(getLabel(episodeItem.getDate()));
        box.getChildren().add(region1);
        box.getChildren().add(getLabel(episodeItem.getSize()));
        box.getChildren().add(region2);
        box.getChildren().add(getLabel(episodeItem.getDuration()));
        box.getChildren().add(region3);
        box.getChildren().add(ButtonComponent.createButton("Télécharger", TypeButton.PRIMARY));

        box.setAlignment(Pos.CENTER);
        HBox.setHgrow(box, Priority.ALWAYS);

        return box;
    }

    private static Label getLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(ColorThemeConstants.getGrey950());
        return label;
    }
}
