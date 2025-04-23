package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.Constant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PodcastComponent {
    private PodcastComponent() {
        // Private constructor to prevent instantiation
    }

    public static Node createPodcastCard(String imageUrl) {
        return createCard(imageUrl, 0, null);
    }

    public static Node createPodcastCard(String imageUrl, String title, int episodeCount) {
        return createCard(imageUrl, episodeCount, title);
    }

    private static Node createCard(String imageUrl, int episodeCount, String title) {
        // Créer le conteneur principal
        StackPane stackPane = new StackPane();

        // Configurer le padding autour du contenu
        stackPane.setPadding(new Insets(Constant.PODCAST_CARD_DEFAULT_PADDING));

        // Charger l'image à l'avance pour obtenir ses dimensions
        Image image = new Image(imageUrl);

        // Créer le contenu de la carte (image + titre éventuel)
        VBox contentBox = getContentCard(title, image);

        // Déterminer les dimensions de la carte basées sur le contenu
        double contentWidth = Constant.PODCAST_CARD_DEFAULT_IMAGE_WIDTH_HEIGHT;
        double contentHeight = Constant.PODCAST_CARD_DEFAULT_IMAGE_WIDTH_HEIGHT + (title != null ? 25.0 : 0); // hauteur supplémentaire pour le titre

        // Configurer les dimensions du conteneur basées sur le contenu
        stackPane.setMinWidth(contentWidth + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING);
        stackPane.setMinHeight(contentHeight + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING);
        stackPane.setPrefWidth(contentWidth + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING);
        stackPane.setPrefHeight(contentHeight + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING);

        // Créer un clip rectangulaire pour le conteneur
        Rectangle clip = new Rectangle(
                contentWidth + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING,
                contentHeight + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING
        );
        stackPane.setClip(clip);

        // Ajouter les éléments dans l'ordre d'empilement correct
        stackPane.getChildren().add(getBlurredBackground(image, contentWidth, contentHeight));
        stackPane.getChildren().add(getColorOverlay(contentWidth, contentHeight));
        stackPane.getChildren().add(contentBox);

        if (episodeCount > 0) {
            stackPane.getChildren().add(createEpisodeCountBadge(episodeCount));
        }

        stackPane.setAlignment(Pos.CENTER);

        return stackPane;
    }

    private static ImageView getBlurredBackground(Image image, double width, double height) {
        ImageView blurredBackground = new ImageView(image);

        double totalWidth = width + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING;
        double totalHeight = height + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING;

        double scaleFactor = 1.2; // Agrandir légèrement l'image d'arrière-plan
        blurredBackground.setFitWidth(totalWidth * scaleFactor);
        blurredBackground.setFitHeight(totalHeight * scaleFactor);

        // Centrer l'image agrandie
        blurredBackground.setTranslateX((totalWidth * scaleFactor - totalWidth) / -2);
        blurredBackground.setTranslateY((totalHeight * scaleFactor - totalHeight) / -2);

        blurredBackground.setEffect(new BoxBlur(200, 200, 5));

        return blurredBackground;
    }

    private static VBox getContentCard(String title, Image image) {
        VBox vBox = new VBox(5); // Espacement vertical entre l'image et le titre

        vBox.getChildren().add(getImageCard(image));
        if (title != null) {
            vBox.getChildren().add(getTitleCard(title));
        }

        vBox.setAlignment(Pos.BASELINE_LEFT);
        return vBox;
    }

    private static ImageView getImageCard(Image image) {
        ImageView contentImage = new ImageView(image);
        contentImage.setFitWidth(Constant.PODCAST_CARD_DEFAULT_IMAGE_WIDTH_HEIGHT);
        contentImage.setFitHeight(Constant.PODCAST_CARD_DEFAULT_IMAGE_WIDTH_HEIGHT);
        contentImage.setPreserveRatio(true);
        return contentImage;
    }

    private static Label getTitleCard(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLabel.setWrapText(true);
        titleLabel.setMaxWidth(Constant.PODCAST_CARD_DEFAULT_IMAGE_WIDTH_HEIGHT);
        titleLabel.setAlignment(Pos.BASELINE_LEFT);
        return titleLabel;
    }

    private static Rectangle getColorOverlay(double width, double height) {
        Rectangle colorOverlay = new Rectangle(
                width + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING,
                height + 2 * Constant.PODCAST_CARD_DEFAULT_PADDING
        );
        colorOverlay.setFill(Color.hsb(120.0, 0.5, 0.4, 0.05));
        return colorOverlay;
    }

    private static Node createEpisodeCountBadge(int count) {
        Label countLabel = new Label(String.valueOf(count));
        countLabel.setFont(Font.font("System", FontWeight.MEDIUM, 12));
        countLabel.setTextFill(ColorThemeConstants.getMain900());

        HBox box = getEpisodeCountBox();
        box.getChildren().add(countLabel);

        StackPane.setAlignment(box, Pos.TOP_LEFT);
        StackPane.setMargin(box, new Insets(10, 0, 0, 10));
        return box;
    }

    private static HBox getEpisodeCountBox() {
        HBox box = new HBox();
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain100(), new CornerRadii(2.0), Insets.EMPTY)));
        box.setPadding(new Insets(2.0, 7.0, 2.0, 7.0));
        box.setAlignment(Pos.CENTER);
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setBorder(null);
        return box;
    }
}