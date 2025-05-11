package com.podcast.antennapod.view;

import com.podcast.antennapod.view.component.BadgeComponent;
import com.podcast.antennapod.view.component.ButtonComponent;
import com.podcast.antennapod.view.component.EpisodeComponent;
import com.podcast.antennapod.view.component.image.ImageComponentFactory;
import com.podcast.antennapod.view.item.EpisodeItem;
import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.BadgeType;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.ThemeType;
import com.podcast.antennapod.view.util.TypeButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

public class MainTest extends Application {
    public static final Logger logger = LogManager.getLogger(MainTest.class);
    private Scene scene;
    private ListView<NavigationItem> navigationListView;
    private Label currentSelectionLabel;

    public static void main(String[] args) {
        logger.info("Initialisation de l'interface utilisateur principale (MainTest)");
        launch(args);
        logger.info("Fermeture de l'interface utilisateur principale (MainTest)");
    }

    @Override
    public void start(Stage stage) {
        // Créer l'interface initiale
        AnchorPane root = createInterface();

        // Configurer la scène
        scene = new Scene(root, 320, 240);

        // Ajouter un écouteur pour mettre à jour l'interface lors du changement de thème
        ColorThemeConstants.addThemeChangeListener(newTheme -> refreshInterface());

        // Finaliser la configuration du stage
        stage.setScene(scene);
        stage.setTitle("AntennaPod");
        stage.setMaximized(true);
        stage.show();
        logger.info("Interface utilisateur initialisée avec succès");
    }

    private void refreshInterface() {
        // Sauvegarder l'état actuel (si nécessaire)
        String currentSelection = currentSelectionLabel.getText();
        int selectedIndex = navigationListView.getSelectionModel().getSelectedIndex();

        // Créer une nouvelle interface
        AnchorPane newRoot = createInterface();

        // Restaurer l'état précédent
        if (selectedIndex >= 0) {
            navigationListView.getSelectionModel().select(selectedIndex);
        }
        currentSelectionLabel.setText(currentSelection);

        // Mettre à jour la scène
        scene.setRoot(newRoot);
        logger.info("Interface utilisateur mise à jour avec le thème:  {}", ColorThemeConstants.getCurrentTheme());
    }

    private AnchorPane createInterface() {
        AnchorPane root = new AnchorPane();

        // Créer les composants principaux
        VBox menuContainer = createNavigationMenu();
        VBox mainContainer = createMainContainer();

        // Ajouter les composants au root
        root.getChildren().addAll(menuContainer, mainContainer);

        root.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));

        return root;
    }

    private VBox createNavigationMenu() {
        VBox menu = new VBox();
        menu.setPrefWidth(240.0);

        // Créer la liste de navigation
        //navigationListView = NavigationContainer.createMenu();
        menu.getChildren().add(navigationListView);

        // Configurer les écouteurs
        navigationListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.getTitle() != null) {
                currentSelectionLabel.setText("Sélection actuelle : " + newValue.getTitle());
            }
        });

        // Configurer le positionnement
        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setTopAnchor(menu, 0.0);

        return menu;
    }

    private VBox createMainContainer() {
        // Créer le label de sélection
        currentSelectionLabel = new Label("Sélection actuelle : Accueil");
        currentSelectionLabel.setFont(new Font(36));
        currentSelectionLabel.setTextFill(ColorThemeConstants.getMain950());
        currentSelectionLabel.setPadding(new Insets(10, 0, 10, 0));

        // Créer le bouton de mode
        Button themeToggleBtn = createThemeToggleButton();

        // Créer le contenu principal
        Node mainContent = createMainContent();

        // Assembler dans un conteneur
        VBox mainContainer = new VBox(10);
        mainContainer.getChildren().addAll(currentSelectionLabel, themeToggleBtn, mainContent);
        mainContainer.setPadding(new Insets(10));


        EpisodeItem episodeItem = new EpisodeItem(String.valueOf(MainTest.class.getResource("/images/heure_du_monde.png")),
                false,
                "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]",
                "00:20:40",
                "28/10/2024",
                "18 Mo");

        EpisodeItem episodeItem1 = new EpisodeItem(String.valueOf(MainTest.class.getResource("/images/heure_du_monde.png")),
                true,
                "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]",
                "00:20:40",
                "28/10/2024",
                "18 Mo");

        mainContainer.getChildren().addAll(EpisodeComponent.createNewEpisode(episodeItem), EpisodeComponent.createNewEpisode(episodeItem1));

        // Configurer le positionnement
        AnchorPane.setLeftAnchor(mainContainer, 240.0);
        AnchorPane.setTopAnchor(mainContainer, 0.0);
        AnchorPane.setRightAnchor(mainContainer, 0.0);

        return mainContainer;
    }

    private Button createThemeToggleButton() {
        Button btn = ButtonComponent.createButton("MODE", TypeButton.PRIMARY);

        btn.setOnAction(_ -> {
            if (ColorThemeConstants.getCurrentTheme().equals(ThemeType.LIGHT)) {
                ColorThemeConstants.setTheme(ThemeType.DARK);
            } else {
                ColorThemeConstants.setTheme(ThemeType.LIGHT);
            }
        });

        return btn;
    }

    private Node createMainContent() {
        VBox box = new VBox(10.0);
        box.setPadding(new Insets(4.0, 16.0, 4.0, 16.0));

        // Ligne de boutons et badges
        HBox buttonRow = createButtonRow();

        // Ligne de boutons avec icônes
        HBox iconButtonRow = createIconButtonRow();

        // Ligne de cartes de podcast simples
        HBox podcastRow = createPodcastRow();

        // Ligne de cartes de podcast avec descriptions
        HBox podcastWithInfoRow = createPodcastWithInfoRow();

        // Ligne de badges texte
        HBox textBadgeRow = createTextBadgeRow();

        // Ajouter toutes les lignes au conteneur principal
        box.getChildren().addAll(podcastRow, buttonRow, iconButtonRow, podcastWithInfoRow, textBadgeRow);

        return box;
    }

    private HBox createButtonRow() {
        HBox hBox = new HBox(15.0);

        // Ajouter les boutons
        hBox.getChildren().add(ButtonComponent.createButton(TypeButton.PRIMARY.name(), TypeButton.PRIMARY));
        hBox.getChildren().add(ButtonComponent.createButton(TypeButton.SECONDARY.name(), TypeButton.SECONDARY));
        hBox.getChildren().add(ButtonComponent.createButton(TypeButton.TERTIARY.name(), TypeButton.TERTIARY));

        // Ajouter les badges avec icônes
        hBox.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignP.PLUS), BadgeType.RED));
        hBox.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignI.INBOX), BadgeType.BLUE));
        hBox.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignI.INBOX), BadgeType.GREEN));
        hBox.getChildren().add(BadgeComponent.createBadge(new FontIcon(MaterialDesignP.PLAYLIST_PLAY), BadgeType.PURPLE));

        // Ajouter les badges avec texte et icônes
        hBox.getChildren().add(BadgeComponent.createBadge("PLUS", new FontIcon(MaterialDesignP.PLUS), BadgeType.RED));
        hBox.getChildren().add(BadgeComponent.createBadge("MAIL", new FontIcon(MaterialDesignI.INBOX), BadgeType.BLUE));
        hBox.getChildren().add(BadgeComponent.createBadge("MAIL", new FontIcon(MaterialDesignI.INBOX), BadgeType.GREEN));
        hBox.getChildren().add(BadgeComponent.createBadge("PLAY", new FontIcon(MaterialDesignP.PLAYLIST_PLAY), BadgeType.PURPLE));

        return hBox;
    }

    private HBox createIconButtonRow() {
        HBox hBox = new HBox(15.0);

        // Ajouter les boutons avec icônes
        hBox.getChildren().add(ButtonComponent.createButton(new FontIcon(MaterialDesignP.PLAY), TypeButton.PRIMARY));
        hBox.getChildren().add(ButtonComponent.createButton(new FontIcon(MaterialDesignP.PLAY), TypeButton.SECONDARY));
        hBox.getChildren().add(ButtonComponent.createButton(new FontIcon(MaterialDesignP.PLAY), TypeButton.TERTIARY));

        // Ajouter les boutons avec texte et icônes
        hBox.getChildren().add(ButtonComponent.createButton("PLAY", new FontIcon(MaterialDesignP.PLAY), TypeButton.PRIMARY));
        hBox.getChildren().add(ButtonComponent.createButton("PLAY", new FontIcon(MaterialDesignP.PLAY), TypeButton.SECONDARY));
        hBox.getChildren().add(ButtonComponent.createButton("PLAY", new FontIcon(MaterialDesignP.PLAY), TypeButton.TERTIARY));

        return hBox;
    }

    private HBox createPodcastRow() {
        HBox hBox = new HBox(15.0);
        ImageComponentFactory factory = new ImageComponentFactory();

        // Ajouter les cartes de podcast
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/ex.jpeg"))));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/heure_du_monde.png"))));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/small_talk.jpg"))));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/underscore.jpeg"))));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/zerl.jpg"))));

        return hBox;
    }

    private HBox createPodcastWithInfoRow() {
        HBox hBox = new HBox(15.0);
        ImageComponentFactory factory = new ImageComponentFactory();

        // Ajouter les cartes de podcast avec titre et nombre d'épisodes
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/ex.jpeg")), "EX...", 10));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/heure_du_monde.png")), "L'heure du monde", 0));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/small_talk.jpg")), "Small Talk", 125));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/underscore.jpeg")), "Undersore", 25));
        hBox.getChildren().add(factory.createImageCard(String.valueOf(MainTest.class.getResource("/images/zerl.jpg")), "Zack en roue libre", 5));

        return hBox;
    }

    private HBox createTextBadgeRow() {
        HBox hBox = new HBox(15.0);

        // Ajouter les badges texte
        hBox.getChildren().add(BadgeComponent.createBadge("Download", BadgeType.GREEN));
        hBox.getChildren().add(BadgeComponent.createBadge("Sans media", BadgeType.RED));
        hBox.getChildren().add(BadgeComponent.createBadge("Téléchargé", BadgeType.BLUE));
        hBox.getChildren().add(BadgeComponent.createBadge("Téléchargé", BadgeType.PURPLE));

        return hBox;
    }
}
