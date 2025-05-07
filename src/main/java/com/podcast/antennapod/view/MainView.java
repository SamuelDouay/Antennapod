package com.podcast.antennapod.view;

import com.podcast.antennapod.view.container.home.HomeContainer;
import com.podcast.antennapod.view.container.navigation.NavigationContainer;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainView extends Application {
    public static final Logger logger = LogManager.getLogger(MainView.class);

    public static void main(String[] args) {
        logger.info("Initialisation de l'interface utilisateur principale (MainView)");
        launch(args);
        logger.info("Fermeture de l'interface utilisateur principale (MainView)");
    }

    @Override
    public void start(Stage stage) {
        AnchorPane root = createInterface();
        Scene scene = new Scene(root, 320, 240);

        stage.setScene(scene);
        stage.setTitle("AntennaPod");
        stage.setMaximized(true);
        stage.show();
        logger.info("Interface utilisateur initialisée avec succès");
    }

    private AnchorPane createInterface() {
        AnchorPane root = new AnchorPane();

        VBox menuContainer = createNavigationMenu();
        VBox mainContainer = createMainContainer();

        root.getChildren().addAll(menuContainer, mainContainer);
        root.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        return root;
    }

    private VBox createNavigationMenu() {
        VBox menu = new VBox();
        menu.setPrefWidth(240.0);

        menu.getChildren().add(NavigationContainer.createMenu());

        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setTopAnchor(menu, 0.0);

        return menu;
    }

    private VBox createMainContainer() {
        VBox mainContainer = new VBox();
        mainContainer.getChildren().add(HomeContainer.getHomeContainer());
        HBox.setHgrow(mainContainer, Priority.ALWAYS);

        AnchorPane.setLeftAnchor(mainContainer, 240.0);
        AnchorPane.setTopAnchor(mainContainer, 0.0);
        AnchorPane.setRightAnchor(mainContainer, 0.0);
        return mainContainer;
    }
}