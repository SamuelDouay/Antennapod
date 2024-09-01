package com.podcast.antennapod.old.abonnement;

import com.podcast.antennapod.old.style.LabelStyle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Podcast {
    private final Label namelabel;
    private final Image image;
    public final int nb;
    private final List<Episode> episodes;

    public Podcast(String name, URL image) {
        this.namelabel = new Label(name);
        this.image = new Image(String.valueOf(image));
        this.episodes = new ArrayList<>();
        this.nb = 0;
    }

    public Podcast(String name, String image) {
        this.namelabel = new Label(name);
        this.image = new Image(image);
        this.episodes = new ArrayList<>();
        this.nb = 0;
    }

    public Podcast(String name, String image, int nb) {
        this.namelabel = new Label(name);
        this.image = new Image(image);
        this.episodes = new ArrayList<>();
        this.nb = nb;
    }

    public int getNb() {
        return nb;
    }

    public Image getImage() {
        return image;
    }

    public Label getNamelabel() {
        return namelabel;
    }

    public Node getPodcastImage() {
        StackPane stackPane = new StackPane();

        ImageView view = this.makeImageRound(165.0);

        VBox inside = new VBox(10.0);
        Insets insets = new Insets(12.0);
        double size = insets.getTop() + insets.getBottom() + view.getFitHeight();

        StackPane.setAlignment(inside, Pos.CENTER);


        inside.setPadding(insets);
        inside.setBackground(new Background(new BackgroundFill(Color.hsb(183.0,0.26,0.14,0.25), new CornerRadii(size), null)));
        inside.getChildren().add(view);

        VBox f = new VBox();
        f.setPrefWidth(size);
        f.setPrefHeight(size);
        f.setBackground(new Background(new BackgroundFill(Color.hsb(183.0,0.45,0.98,0.15), new CornerRadii(size), null)));

        stackPane.getChildren().addAll(getBackgroundCircle(size), f, inside);
        return stackPane;
    }

    public Node getPodcastCard() {
        StackPane stackPane = new StackPane();
        stackPane.setBorder(new Border(new BorderStroke(null, null, new CornerRadii(4.0), null)));


        ImageView view = this.makeImageRound(165.0);

        VBox inside = new VBox(10.0);
        Insets insets = new Insets(12.0);
        double height = insets.getTop() + insets.getBottom() + view.getFitHeight();
        double width = insets.getLeft() + insets.getRight() + view.getFitWidth();

        StackPane.setAlignment(inside, Pos.CENTER);


        inside.setPadding(insets);
        inside.setBackground(new Background(new BackgroundFill(Color.hsb(183.0,0.26,0.14,0.25), null, null)));
        LabelStyle.setPodcastName(namelabel);
        namelabel.setMaxWidth(165.0);
        inside.getChildren().addAll(view, namelabel);



        VBox f = new VBox();
        f.setPrefWidth(width);
        f.setPrefHeight(height);
        f.setBackground(new Background(new BackgroundFill(Color.hsb(183.0,0.45,0.98,0.15), null, null)));

        stackPane.getChildren().addAll(getBackground(height, width), f, inside);

        return stackPane;
    }

    private ImageView makeImageRound(double size) {
        ImageView view = new ImageView(this.image);
        view.setPreserveRatio(true);
        view.setFitHeight(size);
        view.setFitWidth(size);

        Circle circle = new Circle(size/2);
        circle.setCenterX(size/2);
        circle.setCenterY(size /2);
        view.setClip(circle);
        return view;
    }

    private ImageView getBackgroundCircle(double size) {
        ImageView background = new ImageView(image);
        background.setFitHeight(size);
        background.setFitWidth(size);
        Circle circle = new Circle(size /2);
        circle.setCenterX(size /2);
        circle.setCenterY(size /2);
        background.setClip(circle);
        background.setEffect(new BoxBlur(25,25,3));
        return background;
    }

    private ImageView getBackground(double height, double width) {
        ImageView background = new ImageView(image);
        background.setPreserveRatio(false);
        background.setFitHeight(height);
        background.setFitWidth(width);
        Rectangle rectangle = new Rectangle(width, height);
        background.setClip(rectangle);
        background.setEffect(new BoxBlur(25,25,3));
        return background;
    }

    public void addEpisode(Episode episode) {
        this.episodes.add(episode);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
