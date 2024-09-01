package com.podcast.antennapod.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainView extends Application {
    public final static Logger logger = LogManager.getLogger();
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new VBox(new Label("Test")), 320, 240));
        stage.setTitle("AntennaPod");
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        logger.info("Start View");
        launch(args);
        logger.info("Close View");
    }
}
