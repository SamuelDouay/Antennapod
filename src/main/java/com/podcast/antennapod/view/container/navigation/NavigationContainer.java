package com.podcast.antennapod.view.container.navigation;

import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.*;

public class NavigationContainer {

    private NavigationContainer() {

    }

    public static VBox createMenu() {
        VBox mainContainer = new VBox();
        mainContainer.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        VBox.setVgrow(mainContainer, Priority.ALWAYS);

        // Partie fixe
        ListView<NavigationItem> fixedListView = getFixedList();
        fixedListView.getSelectionModel().selectFirst();

        // Partie scrollable
        ListView<NavigationItem> scrollableListView = getScrollList();

        updateSelected(fixedListView, scrollableListView);

        ScrollPane scrollPane = new ScrollPane(scrollableListView);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));

        mainContainer.getChildren().addAll(fixedListView, scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        return mainContainer;
    }

    private static void updateSelected(ListView<NavigationItem> fixedListView, ListView<NavigationItem> scrollableListView) {
        // Synchronisation des sélections
        fixedListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                scrollableListView.getSelectionModel().clearSelection();
            }
        });

        scrollableListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fixedListView.getSelectionModel().clearSelection();
            }
        });
    }

    private static ListView<NavigationItem> createListView() {
        ListView<NavigationItem> listView = new ListView<>();
        listView.setPadding(new Insets(8.0));
        listView.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        listView.setBorder(null);
        listView.setMinWidth(Region.USE_PREF_SIZE);
        listView.setCellFactory(_ -> new NavigationCellItem());
        return listView;
    }

    private static ListView<NavigationItem> getScrollList() {
        ListView<NavigationItem> scrollableListView = createListView();

        for (int i = 0; i < 2; i++) {
            NavigationItem podcast1 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/zerl.jpg")), "Zack en Roue Libre by Zack Nani", 12);
            NavigationItem podcast2 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/heure_du_monde.png")), "L'heure du monde");
            NavigationItem podcast3 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/small_talk.jpg")), "Small Tallk - Kobini", 12);
            NavigationItem podcast4 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/underscore.jpeg")), "Underscore_", 12);
            NavigationItem podcast5 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/ex.jpeg")), "Ex...", 12);
            NavigationItem podcast6 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/ex.jpeg")), "Ex...");

            scrollableListView.getItems().addAll(
                    podcast1, podcast2, podcast3, podcast4, podcast5, podcast6
            );

        }
        return scrollableListView;
    }

    private static ListView<NavigationItem> getFixedList() {
        ListView<NavigationItem> fixedListView = createListView();

        NavigationItem homeItem = new NavigationItem(new FontIcon(MaterialDesignH.HOME), "Accueil");
        NavigationItem playlistItem = new NavigationItem(new FontIcon(MaterialDesignP.PLAYLIST_PLAY), "Liste de lecture");
        NavigationItem inboxItem = new NavigationItem(new FontIcon(MaterialDesignI.INBOX), "Boîte de reception", 12);
        NavigationItem episodesItem = new NavigationItem(new FontIcon(MaterialDesignR.RSS), "Episodes");
        NavigationItem subscriptionsItem = new NavigationItem(new FontIcon(MaterialDesignV.VIEW_GRID_OUTLINE), "Abonnements");
        NavigationItem downloadsItem = new NavigationItem(new FontIcon(MaterialDesignD.DOWNLOAD), "Téléchargement", 123);
        NavigationItem historyItem = new NavigationItem(new FontIcon(MaterialDesignH.HISTORY), "Journal de lecture");
        NavigationItem addPodcastItem = new NavigationItem(new FontIcon(MaterialDesignP.PLUS), "Ajouter un podcast");
        NavigationItem separator = new NavigationItem();

        fixedListView.getItems().addAll(
                homeItem, playlistItem, inboxItem, episodesItem,
                subscriptionsItem, downloadsItem, historyItem, addPodcastItem,
                separator
        );

        fixedListView.setCellFactory(_ -> new NavigationCellItem());
        return fixedListView;
    }
}
