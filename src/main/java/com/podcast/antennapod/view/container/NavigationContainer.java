package com.podcast.antennapod.view.container;

import com.podcast.antennapod.view.component.NavigationComponent;
import com.podcast.antennapod.view.item.NavigationItem;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.material2.Material2MZ;

public class NavigationContainer {
    private NavigationContainer() {

    }

    public static ListView<NavigationItem> createMenu() {
        ListView<NavigationItem> listView = new ListView<>();

        // Configurer le padding
        listView.setPadding(new Insets(8.0));
        listView.setBackground(null);
        listView.setBorder(null);
        listView.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        listView.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        NavigationItem homeItem = new NavigationItem(new FontIcon(Material2AL.HOME), "Accueil",0);
        NavigationItem playlistItem = new NavigationItem(new FontIcon(Material2MZ.PLAYLIST_PLAY), "Liste de lecture",0);
        NavigationItem inboxItem = new NavigationItem(new FontIcon(Material2AL.INBOX), "Boîte de reception",0);
        NavigationItem episodesItem = new NavigationItem(new FontIcon(Material2MZ.RSS_FEED), "Episodes",0);
        NavigationItem subscriptionsItem = new NavigationItem(new FontIcon(Material2AL.APPS), "Abonnements",0);
        NavigationItem downloadsItem = new NavigationItem(new FontIcon(Material2AL.CLOUD_DOWNLOAD), "Téléchargement",0);
        NavigationItem historyItem = new NavigationItem(new FontIcon(Material2AL.HISTORY), "Journal de lecture",0);
        NavigationItem addPodcastItem = new NavigationItem(new FontIcon(Material2AL.ADD), "Ajouter un podcast",0);


        listView.getItems().addAll(
                homeItem, playlistItem, inboxItem, episodesItem,
                subscriptionsItem, downloadsItem, historyItem, addPodcastItem
        );

        listView.setCellFactory(param -> new ListCell<NavigationItem>() {
            @Override
            protected void updateItem(NavigationItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setGraphic(NavigationComponent.createNavigation(item));

                    setStyle(
                            "-fx-background-color: transparent;" + "-fx-padding: 0px;"
                    );

                    if (isSelected()) {
                        setStyle("-fx-background-color: #e0e0e0;");
                    }
                }
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Élément sélectionné: " + newValue.getTitle());
                // Ici, vous pouvez exécuter n'importe quelle action en fonction de l'élément sélectionné
                // Par exemple, changer de vue ou charger du contenu
            }
        });

        return listView;
    }
}
