package com.podcast.antennapod.old;

import com.podcast.antennapod.old.abonnement.Episode;
import com.podcast.antennapod.old.controller.Menu;
import com.podcast.antennapod.logic.database.SQLQuery;
import com.podcast.antennapod.old.abonnement.Podcast;
import com.podcast.antennapod.old.navigation.MenuItem;
import com.podcast.antennapod.old.navigation.Navigation;
import com.podcast.antennapod.old.style.BorderStyle;
import com.podcast.antennapod.old.style.LabelStyle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MainView extends Application {
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(this.mainView(), 320, 240));
        stage.setTitle("AntennaPod");
        stage.setMaximized(true);
        stage.show();
    }

    private Parent mainView() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(new Background(new BackgroundFill(Color.web("#FEFEFF"), CornerRadii.EMPTY, Insets.EMPTY)));


        VBox v = new VBox(navigation());


        //Node  ab = subscription();
        //Node top10 = queueTo10();

        //VBox vBox = new VBox(26.0);

        //vBox.getChildren().addAll(ab, top10);


        AnchorPane.setLeftAnchor(v,0.0);
        AnchorPane.setTopAnchor(v, 0.0);

        //AnchorPane.setLeftAnchor(vBox, 300.0);
        anchorPane.getChildren().addAll(v);
        return anchorPane;
    }

    private Parent navigation() {
        Navigation navigation = new Navigation(240);
        navigation.setBorder(BorderStyle.rigth());
        navigation.setBackground(new Background(new BackgroundFill(Color.web("#F8FCFC"), CornerRadii.EMPTY, Insets.EMPTY)));

        for(int i = 0; i < 8; i++) {
            MenuItem testImage = new MenuItem(new Menu("Accueil", 10, new Image(String.valueOf(getClass().getResource("/images/others/img_base.png"))), false));
            navigation.addItem(testImage.toView());
        }

        return navigation.getMenuItem();
    }

    public Parent navPodcast() {
        Navigation navigation = new Navigation(240);
        navigation.setBorder(BorderStyle.rigth());
        navigation.setBackground(new Background(new BackgroundFill(Color.web("#F8FCFC"), CornerRadii.EMPTY, Insets.EMPTY)));

        try {
            List<Podcast> p = SQLQuery.getPodcastName();

            for(Podcast podcast : p) {
                MenuItem testImage = new MenuItem(new Menu(podcast.getNamelabel().getText(),
                        podcast.getNb(),
                        podcast.getImage(),
                        false));
                navigation.addItem(testImage.toView());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        ScrollPane scrollPane = new ScrollPane(navigation.getMenuItem());
        scrollPane.setHmax(530);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));


        return scrollPane;
    }

    private Parent subscription() {

        VBox vBox = new VBox(12.0);
        HBox hbox = new HBox(24.0);
        hbox.setPadding(new Insets(2.0));
        try {
            HashMap<String, String> podcast = SQLQuery.getPodcast();

            for(String title : podcast.keySet()) {
                Podcast a = new Podcast(title, podcast.get(title));
                hbox.getChildren().add(a.getPodcastImage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        vBox.getChildren().addAll(hbox);
        Label title = new Label("Vos Classiques");
        LabelStyle.setTitleSection(title);
        vBox.getChildren().addAll(title, getScrollPane(hbox));

        return vBox;
    }

    private Parent queueTo10() {
        VBox vBox = new VBox(12.0);
        HBox hbox = new HBox(24.0);
        hbox.setPadding(new Insets(2.0));
        try {
            List<Episode> episodes = SQLQuery.getQueueTop10();

            Podcast p = new Podcast("TEST", getClass().getResource("/images/others/zerl.jpg"));
            for(Episode e : episodes) {
                p.addEpisode(e);
            }

            for(Episode e : p.getEpisodes()) {
                hbox.getChildren().add(p.getPodcastCard());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        vBox.getChildren().addAll(hbox);
        Label title = new Label("Continuer d'écouter");
        LabelStyle.setTitleSection(title);
        vBox.getChildren().addAll(title, getScrollPane(hbox));

        return vBox;
    }

    private static ScrollPane getScrollPane(HBox hbox) {
        hbox.setBackground(new Background(new BackgroundFill(Color.web("#FEFEFF"), null, null)));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hbox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(false);
        scrollPane.setMaxWidth(1094);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        return scrollPane;
    }

    public static void main(String[]  args) {
        launch(args);
    }
}
