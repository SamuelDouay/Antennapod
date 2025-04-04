package com.podcast.antennapod.view.component;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
}
