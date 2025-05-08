package com.podcast.antennapod.view;

import com.podcast.antennapod.view.component.SearchComponent;
import com.podcast.antennapod.view.container.home.HomeContainer;
import com.podcast.antennapod.view.container.navigation.NavigationContainer;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

    private static HBox createFooter() {
        HBox box = new HBox();

        HBox.setHgrow(box, Priority.ALWAYS);
        box.setPrefHeight(72.0);
        box.setPadding(new Insets(12.0,32.0,12.0,32.0));
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        box.setBorder(new Border(new BorderStroke(ColorThemeConstants.getMain950(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1,0,0,0), null)));

        box.getChildren().add(SearchComponent.createSearchComponent());

        AnchorPane.setBottomAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);

        return box;
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
        root.getChildren().addAll(createMainContainer(), createHeader(), createNavigationMenu(), createFooter());
        root.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        return root;
    }

    private VBox createNavigationMenu() {
        VBox menu = new VBox();
        menu.setPrefWidth(240.0);

        menu.getChildren().add(NavigationContainer.createMenu());
        menu.setBorder(new Border(new BorderStroke(ColorThemeConstants.getMain950(), BorderStrokeStyle.SOLID, null, new BorderWidths(0,1,0,0), null)));
        menu.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain500(), null, null)));

        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setTopAnchor(menu, 0.0);
        AnchorPane.setBottomAnchor(menu, 72.0);

        return menu;
    }

    private Node createMainContainer() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(Background.EMPTY);
        scrollPane.setFitToWidth(true);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        scrollPane.setContent(HomeContainer.getHomeContainer());

        AnchorPane.setLeftAnchor(scrollPane, 240.0);
        AnchorPane.setTopAnchor(scrollPane, 72.0);
        AnchorPane.setBottomAnchor(scrollPane, 72.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        return scrollPane;
    }

    private HBox createHeader() {
        HBox box = new HBox();

        HBox.setHgrow(box, Priority.ALWAYS);
        box.setPrefHeight(72.0);
        box.setPadding(new Insets(12.0,32.0,12.0,32.0));
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        box.setBorder(new Border(new BorderStroke(ColorThemeConstants.getMain950(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0,0,1,0), null)));

        box.getChildren().add(SearchComponent.createSearchComponent());

        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 240.0);
        AnchorPane.setRightAnchor(box, 0.0);

        return box;
    }
}