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

    public static Node getPodcastTile(String urlImage, String title, int episodeCount) {
        VBox container = new VBox(10); // 10px spacing between elements
        container.setAlignment(Pos.TOP_CENTER);

        // Add the image component
        Node imageComponent = getImage(urlImage, episodeCount);

        // Create title label
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setWrapText(true);
        titleLabel.setMaxWidth(165.0);
        titleLabel.setAlignment(Pos.CENTER);

        // Add components to container
        container.getChildren().addAll(imageComponent, titleLabel);

        return container;
    }

    private static Node getImage(String urlImage, int episodeCount) {
        StackPane stackPane = new StackPane();

        // Dimensionnement carré pour tout l'élément
        double size = 165.0;
        stackPane.setPrefSize(size, size);
        stackPane.setMinSize(size, size);
        stackPane.setMaxSize(size, size);

        // Créer un clip pour contenir les effets dans les limites du stackPane
        Rectangle clip = new Rectangle(size, size);
        stackPane.setClip(clip);

        // Créer l'arrière-plan flou
        Image image = new Image(urlImage);
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

        // Créer le contenu (image principale)
        ImageView contentImage = new ImageView(image);
        contentImage.setFitWidth(140.0);
        contentImage.setFitHeight(140.0);
        contentImage.setPreserveRatio(true);

        Label titleLabel = new Label("Title");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setWrapText(true);
        titleLabel.setMaxWidth(165.0);
        titleLabel.setAlignment(Pos.BASELINE_LEFT);

        // Conteneur pour l'image
        VBox vBox = new VBox();
        vBox.getChildren().addAll(contentImage, titleLabel);
        vBox.setAlignment(Pos.CENTER);



        // Superposition d'une couleur semi-transparente (filtre coloré plus intense)
        Rectangle colorOverlay = new Rectangle(size, size);
        colorOverlay.setFill(Color.hsb(120.0, 0.5, 0.4, 0.10)); // Augmenter l'opacité à 0.25



        // Ajouter les éléments à la StackPane dans l'ordre
        stackPane.getChildren().add(blurredBackground);
        stackPane.getChildren().add(colorOverlay);
        stackPane.getChildren().add(vBox);

        if (episodeCount > 0) {
            // Créer le badge d'épisodes
            Node episodeBadge = createEpisodeCountBadge(episodeCount);
            StackPane.setAlignment(episodeBadge, Pos.TOP_LEFT);
            StackPane.setMargin(episodeBadge, new Insets(10, 0, 0, 10));
            stackPane.getChildren().add(episodeBadge);
        }


        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(12.5));

        return stackPane;
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

        return box;
    }

    /**
     * Legacy method for backward compatibility
     */
    public static Node getImage(String urlImage) {
        return getImage(urlImage, 0);
    }
}