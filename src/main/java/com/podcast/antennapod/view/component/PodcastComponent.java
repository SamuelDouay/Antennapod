package com.podcast.antennapod.view.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PodcastComponent {
    private PodcastComponent(){

    }

    public static Node getImage(String urlImage) {
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

        // Conteneur pour l'image
        VBox vBox = new VBox();
        vBox.getChildren().addAll(contentImage);
        vBox.setAlignment(Pos.CENTER);

        // Superposition d'une couleur semi-transparente (filtre coloré plus intense)
        Rectangle colorOverlay = new Rectangle(size, size);
        colorOverlay.setFill(Color.hsb(120.0, 0.5, 0.4, 0.10)); // Augmenter l'opacité à 0.25

        // Ajouter les éléments à la StackPane dans l'ordre
        stackPane.getChildren().add(blurredBackground);
        stackPane.getChildren().add(colorOverlay);
        stackPane.getChildren().add(vBox);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(12.5));

        return stackPane;
    }
}
