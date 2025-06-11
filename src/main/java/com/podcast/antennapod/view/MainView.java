package com.podcast.antennapod.view;

import com.podcast.antennapod.communication.ViewCommunicationService;
import com.podcast.antennapod.logic.MainLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainView extends Application {
    public static final Logger logger = LogManager.getLogger(MainView.class);
    private ViewCommunicationService communicationService;
    private Label statusLabel;
    private Label fpsLabel;
    private Label speedLabel;

    /*
    @Override
    public void start(Stage stage) {
        MainLayout mainLayout = new MainLayout();
        AnchorPane root = mainLayout.createInterface();
        Scene scene = new Scene(root, 320, 240);

        stage.setScene(scene);
        stage.setTitle("AntennaPod");
        stage.setMaximized(true);
        stage.show();
        logger.info("Interface utilisateur initialisée avec succès");
    }
    */

    public static void main(String[] args) {
        logger.info("Initialisation de l'interface utilisateur principale (MainView)");
        launch(args);
        logger.info("Fermeture de l'interface utilisateur principale (MainView)");
    }

    @Override
    public void start(Stage stage) {
        // Initialiser le service de communication
        communicationService = new ViewCommunicationService();
        setupCommunicationHandlers();

        // Création de l'interface
        VBox root = new VBox(10);
        root.setSpacing(15);
        root.setStyle("-fx-padding: 20;");

        // Labels de statut
        statusLabel = new Label("Statut Logic: En attente...");
        fpsLabel = new Label("FPS Logic: 0.00");
        speedLabel = new Label("Vitesse: 1.0x");

        // Contrôle de vitesse
        Slider speedSlider = new Slider(0.1, 5.0, 1.0);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(1.0);
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double speed = newVal.doubleValue();
            speedLabel.setText(String.format("Vitesse: %.1fx", speed));
            communicationService.requestSetSimulationSpeed(speed);
        });

        // Boutons de contrôle
        Button resetButton = new Button("Reset Simulation");
        resetButton.setOnAction(e -> {
            communicationService.requestResetSimulation();
            logger.info("Reset simulation demandé");
        });

        Button statusButton = new Button("Demander Statut");
        statusButton.setOnAction(e -> communicationService.requestStatus());

        Button shutdownButton = new Button("Arrêter Application");
        shutdownButton.setOnAction(e -> {
            communicationService.requestShutdown();
            stage.close();
        });

        root.getChildren().addAll(
                statusLabel, fpsLabel, speedLabel,
                new Label("Contrôle de vitesse:"), speedSlider,
                resetButton, statusButton, shutdownButton
        );

        Scene scene = new Scene(root, 450, 350);
        stage.setScene(scene);
        stage.setTitle("AntennaPod - Communication Améliorée");
        stage.setOnCloseRequest(e -> communicationService.requestShutdown());
        stage.show();

        logger.info("Interface utilisateur initialisée avec succès");
    }

    private void setupCommunicationHandlers() {
        // Handler pour les mises à jour de statut
        communicationService.setStatusHandler(data -> {
            if (data instanceof MainLogic.LogicStatus) {
                MainLogic.LogicStatus status = (MainLogic.LogicStatus) data;
                statusLabel.setText(String.format("Statut: Counter=%d, SimValue=%.2f",
                        status.counter, status.simulationValue));
                fpsLabel.setText(String.format("FPS Logic: %.2f", status.fps));
            }
        });

        // Handler pour les cycles terminés
        communicationService.setCycleCompleteHandler(data -> {
            logger.info("Cycle Logic terminé: {}", data);
            statusLabel.setText("Cycle terminé! Counter: " + data);
        });

        // Handler pour les erreurs
        communicationService.setErrorHandler(data -> {
            logger.error("Erreur reçue de Logic: {}", data);
            statusLabel.setText("Erreur: " + data);
            statusLabel.setStyle("-fx-text-fill: red;");
        });
    }
}