package com.podcast.antennapod.view;

import com.podcast.antennapod.view.component.BadgeComponent;
import com.podcast.antennapod.view.component.ButtonComponent;
import com.podcast.antennapod.view.component.PodcastComponent;
import com.podcast.antennapod.view.container.navigation.NavigationContainer;
import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.BadgeType;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.TypeButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

public class MainView extends Application {
    public static final Logger logger = LogManager.getLogger(MainView.class);

    public static void main(String[] args) {
        logger.info("Initialisation de l'interface utilisateur principale (MainView)");
        launch(args);
        logger.info("Fermeture de l'interface utilisateur principale (MainView)");
    }

    private static Node mainContent() {
        VBox box = new VBox(10.0);

        box.setPadding(new Insets(4.0, 16.0, 4.0, 16.0));

        HBox hBox1 = new HBox(15.0);

        hBox1.getChildren().add(ButtonComponent.createButton(TypeButton.PRIMARY.name(), TypeButton.PRIMARY));
        hBox1.getChildren().add(ButtonComponent.createButton(TypeButton.SECONDARY.name(), TypeButton.SECONDARY));
        hBox1.getChildren().add(ButtonComponent.createButton(TypeButton.TERTIARY.name(), TypeButton.TERTIARY));

        hBox1.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignP.PLUS), BadgeType.RED));
        hBox1.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignI.INBOX), BadgeType.BLUE));
        hBox1.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignI.INBOX), BadgeType.GREEN));
        hBox1.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignP.PLAYLIST_PLAY), BadgeType.PURPLE));

        hBox1.getChildren().add(BadgeComponent.createBadge("PLUS", new FontIcon(MaterialDesignP.PLUS), BadgeType.RED));
        hBox1.getChildren().add(BadgeComponent.createBadge("MAIL", new FontIcon(MaterialDesignI.INBOX), BadgeType.BLUE));
        hBox1.getChildren().add(BadgeComponent.createBadge("MAIL", new FontIcon(MaterialDesignI.INBOX), BadgeType.GREEN));
        hBox1.getChildren().add(BadgeComponent.createBadge("PLAY", new FontIcon(MaterialDesignP.PLAYLIST_PLAY), BadgeType.PURPLE));

        HBox hBox2 = new HBox(15.0);


        hBox2.getChildren().add(ButtonComponent.createButton(new FontIcon(MaterialDesignP.PLAY), TypeButton.PRIMARY));
        hBox2.getChildren().add(ButtonComponent.createButton(new FontIcon(MaterialDesignP.PLAY), TypeButton.SECONDARY));
        hBox2.getChildren().add(ButtonComponent.createButton(new FontIcon(MaterialDesignP.PLAY), TypeButton.TERTIARY));

        hBox2.getChildren().add(ButtonComponent.createButton("PLAY", new FontIcon(MaterialDesignP.PLAY), TypeButton.PRIMARY));
        hBox2.getChildren().add(ButtonComponent.createButton("PLAY", new FontIcon(MaterialDesignP.PLAY), TypeButton.SECONDARY));
        hBox2.getChildren().add(ButtonComponent.createButton("PLAY", new FontIcon(MaterialDesignP.PLAY), TypeButton.TERTIARY));


        HBox hBox = new HBox(15.0);

        hBox.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/ex.jpeg"))));
        hBox.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/heure_du_monde.png"))));
        hBox.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/small_talk.jpg"))));
        hBox.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/underscore.jpeg"))));
        hBox.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/zerl.jpg"))));

        HBox hBox3 = new HBox(15.0);

        hBox3.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/ex.jpeg")), "EX...", 10));
        hBox3.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/heure_du_monde.png")), "L'heure du monde", 0));
        hBox3.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/small_talk.jpg")), "Small Talk", 125));
        hBox3.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/underscore.jpeg")), "Undersore", 25));
        hBox3.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainView.class.getResource("/images/zerl.jpg")), "Zack en roue libre", 5));


        HBox hBox4 = new HBox(15.0);

        hBox4.getChildren().add(BadgeComponent.createBadge("Download", BadgeType.GREEN));
        hBox4.getChildren().add(BadgeComponent.createBadge("Sans media", BadgeType.RED));
        hBox4.getChildren().add(BadgeComponent.createBadge("Téléchargé", BadgeType.BLUE));
        hBox4.getChildren().add(BadgeComponent.createBadge("Téléchargé", BadgeType.PURPLE));

        box.getChildren().add(hBox);
        box.getChildren().add(hBox1);
        box.getChildren().add(hBox2);
        box.getChildren().add(hBox3);
        box.getChildren().add(hBox4);

        return box;
    }

    @Override
    public void start(Stage stage) {
        AnchorPane root = new AnchorPane();

        VBox menu = new VBox();
        menu.setPrefWidth(240.0);

        ListView<NavigationItem> listView = NavigationContainer.createMenu();
        menu.getChildren().add(listView);

        Label currentSelectionLabel = new Label("Sélection actuelle : Accueil");
        currentSelectionLabel.setFont(new Font(36));
        currentSelectionLabel.setTextFill(ColorThemeConstants.getMain950());
        currentSelectionLabel.setPadding(new Insets(10, 0, 10, 0));

        // Ajouter un écouteur pour mettre à jour le label lors de la sélection
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.getTitle() != null) {
                currentSelectionLabel.setText("Sélection actuelle : " + newValue.getTitle());
            }
        });

        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setTopAnchor(menu, 0.0);

        Node main = mainContent();

        // Positionner le label en haut du contenu principal
        VBox mainContainer = new VBox(10);
        mainContainer.getChildren().addAll(currentSelectionLabel, main);
        mainContainer.setPadding(new Insets(10));

        AnchorPane.setLeftAnchor(mainContainer, 240.0);
        AnchorPane.setTopAnchor(mainContainer, 0.0);
        AnchorPane.setRightAnchor(mainContainer, 0.0);

        root.getChildren().addAll(menu, mainContainer);

        stage.setScene(new Scene(root, 320, 240));

        stage.setTitle("AntennaPod");
        stage.setMaximized(true);
        stage.show();
        logger.info("Interface utilisateur initialisée avec succès");
    }
}
