package com.podcast.antennapod.view;

import com.podcast.antennapod.view.component.ButtonComponent;
import com.podcast.antennapod.view.component.TypeButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainView extends Application {
    public static final Logger logger = LogManager.getLogger(MainView.class);
    @Override
    public void start(Stage stage) {
        VBox box = new VBox();

        box.getChildren().add(ButtonComponent.createButton("TEST", TypeButton.PRIMARY));
        box.getChildren().add(ButtonComponent.createButton("TEST", TypeButton.SECONDARY));
        box.getChildren().add(ButtonComponent.createButton("TEST", TypeButton.TERTIARY));

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
