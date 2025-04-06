package com.podcast.antennapod.view.component;

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

public class PodcastComponent {
    private PodcastComponent(){

    }

    public static Node createPodcastImage(String name, String urlImage) {
        Label label = new Label(name);
        Image image = new Image(urlImage);
        ImageView imageView = new ImageView(image);
        VBox vBox = new VBox();

        imageView.setFitWidth(140.0);
        imageView.setFitHeight(140.0);

        vBox.getChildren().add(imageView);
        vBox.getChildren().add(label);

        vBox.setBackground(new Background(new BackgroundFill(Color.hsb(120.0,0.5,0.4, 0.0196), CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView imageView1 = new ImageView(image);

        imageView1.setFitHeight(165.0);
        imageView1.setFitWidth(165.0);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(0.0);
        rectangle.setY(0.0);
        rectangle.setHeight(165.0);
        rectangle.setWidth(165.0);

        imageView1.setClip(rectangle);
        imageView1.setEffect(new BoxBlur(25,25,15));

        vBox.getChildren().add(imageView1);

        return vBox;
    }

    public static Node test(String name, String urlImage) {
        StackPane stackPane = new StackPane();

        // Créer l'arrière-plan flou
        Image image = new Image(urlImage);
        ImageView blurredBackground = new ImageView(image);
        blurredBackground.setFitHeight(165.0);
        blurredBackground.setFitWidth(165.0);
        blurredBackground.setEffect(new BoxBlur(25, 25, 5));

        Rectangle colorOverlay = new Rectangle(165.0, 165.0, Color.hsb(120.0, 0.5, 0.4, 0.0196));

        // Créer le contenu
        ImageView contentImage = new ImageView(image);
        contentImage.setFitWidth(140.0);
        contentImage.setFitHeight(140.0);

        Label label = new Label(name);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(contentImage, label);
        vBox.setAlignment(Pos.CENTER);

        // Ajouter les éléments à la StackPane dans l'ordre (fond en premier, puis contenu)
        stackPane.getChildren().add(blurredBackground);
        stackPane.getChildren().add(colorOverlay);
        stackPane.getChildren().add(vBox);

        return stackPane;
    }

    public static Node testA(String name, String urlImage) {
        StackPane stackPane = new StackPane();

        // Dimensionnement carré pour tout l'élément
        double size = 165.0;
        stackPane.setPrefSize(size, size);
        stackPane.setMinSize(size, size);
        stackPane.setMaxSize(size, size);

        // Créer l'arrière-plan flou
        Image image = new Image(urlImage);
        ImageView blurredBackground = new ImageView(image);
        blurredBackground.setFitHeight(size);
        blurredBackground.setFitWidth(size);
        blurredBackground.setEffect(new BoxBlur(25, 25, 5));

        // Créer le contenu
        ImageView contentImage = new ImageView(image);
        contentImage.setFitWidth(140.0); // 75% de la taille totale
        contentImage.setFitHeight(140.0);

        // Conteneur pour le logo et le titre
        VBox vBox = new VBox();
        vBox.getChildren().addAll(contentImage);
        vBox.setAlignment(Pos.CENTER);

        // Superposition d'une couleur semi-transparente (filtre coloré)
        Rectangle colorOverlay = new Rectangle(size, size);
        //colorOverlay.setFill(Color.hsb(120.0, 0.5, 0.4, 0.0196));
        colorOverlay.setFill(Color.hsb(120.0, 0.5, 0.2, 0.1));

        // Ajouter les éléments à la StackPane dans l'ordre
        stackPane.getChildren().add(blurredBackground);
        stackPane.getChildren().add(colorOverlay);
        stackPane.getChildren().add(vBox);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(12.5));

        return stackPane;
    }
}
