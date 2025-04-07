package com.podcast.antennapod.view;

import com.podcast.antennapod.view.component.BadgeComponent;
import com.podcast.antennapod.view.component.ButtonComponent;
import com.podcast.antennapod.view.component.PodcastComponent;
import com.podcast.antennapod.view.util.TypeButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainView extends Application {
    public static final Logger logger = LogManager.getLogger(MainView.class);
    @Override
    public void start(Stage stage) {
        VBox box = new VBox(25.0);


        box.setPadding(new Insets(4.0,16.0,4.0,16.0));

        box.getChildren().add(ButtonComponent.createButton(TypeButton.PRIMARY.name(), TypeButton.PRIMARY));
        box.getChildren().add(ButtonComponent.createButton(TypeButton.SECONDARY.name(), TypeButton.SECONDARY));
        box.getChildren().add(ButtonComponent.createButton(TypeButton.TERTIARY.name(), TypeButton.TERTIARY));

        HBox hBox = new HBox(15.0);


        hBox.getChildren().add(PodcastComponent.getImage(String.valueOf(getClass().getResource("/images/others/ex.jpeg"))));
        hBox.getChildren().add(PodcastComponent.getImage(String.valueOf(getClass().getResource("/images/others/heure_du_monde.png"))));
        hBox.getChildren().add(PodcastComponent.getImage(String.valueOf(getClass().getResource("/images/others/small_talk.jpg"))));
        hBox.getChildren().add(PodcastComponent.getImage(String.valueOf(getClass().getResource("/images/others/underscore.jpeg"))));
        hBox.getChildren().add(PodcastComponent.getImage(String.valueOf(getClass().getResource("/images/others/zerl.jpg"))));

        box.getChildren().add(hBox);


        box.getChildren().add(BadgeComponent.getBadgeGreen("Download"));
        box.getChildren().add(BadgeComponent.getBadgeRed("Sans media"));
        box.getChildren().add(BadgeComponent.getBadgePurple("Téléchargé"));

        stage.setScene(new Scene(box, 320, 240));

        stage.setTitle("AntennaPod");
        stage.setMaximized(true);
        stage.show();
        logger.info("Interface utilisateur initialisée avec succès");
    }

    public static void main(String[] args) {
        logger.info("Initialisation de l'interface utilisateur principale (MainView)");
        launch(args);
        logger.info("Fermeture de l'interface utilisateur principale (MainView)");
    }
}
