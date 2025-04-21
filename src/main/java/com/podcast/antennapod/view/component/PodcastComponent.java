package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.ColorThemeConstants;
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
    private PodcastComponent(){
        // Private constructor to prevent instantiation
    }

    public static Node createPodcastCard(String imageUrl) {
        return createCard(imageUrl, 0, null);
    }

    public static Node createPodcastCard(String imageUrl, String title, int episodeCount) {
        return createCard(imageUrl, episodeCount, title);
    }

    private static Node createCard(String imageUrl, int episodeCount, String title) {
        double size = 165.0;
        StackPane stackPane = createContainer(size);

        // Créer l'arrière-plan flou
        Image image = new Image(imageUrl);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(12.5));

        stackPane.getChildren().add(getBlurredBackground(image, size));
        stackPane.getChildren().add(getColorOverlay(size));
        stackPane.getChildren().add(getContentCard(title, image));

        if (episodeCount > 0) {
            stackPane.getChildren().add(createEpisodeCountBadge(episodeCount));
        }

        return stackPane;
    }

    private static StackPane createContainer(double size) {
        StackPane stackPane = new StackPane();

        stackPane.setPrefSize(size, size);
        stackPane.setMinSize(size, size);
        stackPane.setMaxSize(size, size);

        // Créer un clip pour contenir les effets dans les limites du stackPane
        Rectangle clip = new Rectangle(size, size);
        stackPane.setClip(clip);

        return stackPane;
    }

    private static ImageView getBlurredBackground(Image image, double size) {
         ImageView blurredBackground = new ImageView(image);

        // Redimensionnement avec une marge pour éviter les bords blancs après le blur
        double scaleFactor = 1.2; // Agrandir légèrement l'image d'arrière-plan
        double expandedSize = size * scaleFactor;
        blurredBackground.setFitHeight(expandedSize);
        blurredBackground.setFitWidth(expandedSize);

        // Centrer l'image agrandie
        blurredBackground.setTranslateX((expandedSize - size) / -2);
        blurredBackground.setTranslateY((expandedSize - size) / -2);

        // Appliquer un flou plus intense
        blurredBackground.setEffect(new BoxBlur(60, 60, 5));

        return blurredBackground;
    }

    private static VBox getContentCard(String title, Image image) {
        // Créer le contenu (image principale)
        ImageView contentImage = new ImageView(image);
        contentImage.setFitWidth(140.0);
        contentImage.setFitHeight(140.0);
        contentImage.setPreserveRatio(true);

        // Conteneur pour l'image
        VBox vBox = new VBox();
        vBox.getChildren().add(contentImage);
        vBox.setAlignment(Pos.CENTER);

        if (title != null ) {
            Label titleLabel = new Label(title);
            titleLabel.setTextFill(Color.WHITE);
            titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
            titleLabel.setWrapText(true);
            titleLabel.setMaxWidth(165.0);
            titleLabel.setAlignment(Pos.BASELINE_LEFT);
            vBox.getChildren().add(titleLabel);
        }
        return vBox;
    }

    private static Rectangle getColorOverlay(double size) {
        // Superposition d'une couleur semi-transparente (filtre coloré plus intense)
        Rectangle colorOverlay = new Rectangle(size, size);
        colorOverlay.setFill(Color.hsb(120.0, 0.5, 0.4, 0.10)); // Augmenter l'opacité à 0.25
        return colorOverlay;
    }

    private static Node createEpisodeCountBadge(int count) {

        HBox box = new HBox();
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getIc12(), new CornerRadii(2.0), Insets.EMPTY)));

        // Create count label
        Label countLabel = new Label(String.valueOf(count));
        countLabel.setFont(Font.font("System", FontWeight.MEDIUM, 12));
        countLabel.setTextFill(ColorThemeConstants.getAt08());

        box.setPadding(new Insets(4.0,16.0,4.0,16.0));
        box.setAlignment(Pos.CENTER);
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setBorder(new Border(new BorderStroke(ColorThemeConstants.getAt08(), BorderStrokeStyle.SOLID, new CornerRadii(2.0), BorderStroke.DEFAULT_WIDTHS)));
        box.getChildren().add(countLabel);

        StackPane.setAlignment(box, Pos.TOP_LEFT);
        StackPane.setMargin(box, new Insets(10, 0, 0, 10));
        return box;
    }
}