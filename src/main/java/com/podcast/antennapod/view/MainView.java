package com.podcast.antennapod.view;

import com.podcast.antennapod.view.component.BadgeComponent;
import com.podcast.antennapod.view.component.ButtonComponent;
import com.podcast.antennapod.view.component.NavigationComponent;
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
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.material2.Material2MZ;

public class MainView extends Application {
    public static final Logger logger = LogManager.getLogger(MainView.class);
    @Override
    public void start(Stage stage) {
        VBox box = new VBox(5.0);


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
        box.getChildren().add(BadgeComponent.getBadgeBlue("Téléchargé"));
        box.getChildren().add(BadgeComponent.getBadgePurple("Téléchargé"));

        box.getChildren().add(BadgeComponent.getBadgeIconRed(new FontIcon(Material2AL.ADD)));
        box.getChildren().add(BadgeComponent.getBadgeIconBlue(new FontIcon(Material2AL.INBOX)));
        box.getChildren().add(BadgeComponent.getBadgeIconGreen(new FontIcon(Material2AL.INBOX)));
        box.getChildren().add(BadgeComponent.getBadgeIconPurple(new FontIcon(Material2MZ.PLAYLIST_PLAY)));

        box.getChildren().add(NavigationComponent.createNavigation(new FontIcon(Material2MZ.PLAYLIST_PLAY), "Test", 12));
        box.getChildren().add(NavigationComponent.createNavigation(String.valueOf(getClass().getResource("/images/others/zerl.jpg")), "Zack en Roue Libre by Zack Nani", 12));

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
