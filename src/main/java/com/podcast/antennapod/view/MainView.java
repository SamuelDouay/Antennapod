package com.podcast.antennapod.view;

import com.podcast.antennapod.view.component.ButtonComponent;
import com.podcast.antennapod.view.component.EpisodeComponent;
import com.podcast.antennapod.view.container.navigation.NavigationContainer;
import com.podcast.antennapod.view.item.EpisodeItem;
import com.podcast.antennapod.view.item.NavigationItem;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainView extends Application {
    public static final Logger logger = LogManager.getLogger(MainView.class);
    private Scene scene;
    private ListView<NavigationItem> navigationListView;
    private Label currentSelectionLabel;

    public static void main(String[] args) {
        logger.info("Initialisation de l'interface utilisateur principale (MainView)");
        launch(args);
        logger.info("Fermeture de l'interface utilisateur principale (MainView)");
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
        navigationListView = NavigationContainer.createMenu();
        menu.getChildren().add(navigationListView);

        // Configurer les écouteurs
        navigationListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.getTitle() != null) {
                currentSelectionLabel.setText(newValue.getTitle());
            }
        });

        // Configurer le positionnement
        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setTopAnchor(menu, 0.0);

        return menu;
    }

    private VBox createMainContainer() {
        // Créer le label de sélection
        currentSelectionLabel = new Label("Accueil");
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


        EpisodeItem episodeItem = new EpisodeItem(String.valueOf(MainView.class.getResource("/images/heure_du_monde.png")),
                false,
                "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]",
                "00:20:40",
                "28/10/2024",
                "18 Mo");

        EpisodeItem episodeItem1 = new EpisodeItem(String.valueOf(MainView.class.getResource("/images/heure_du_monde.png")),
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

        return box;
    }
}