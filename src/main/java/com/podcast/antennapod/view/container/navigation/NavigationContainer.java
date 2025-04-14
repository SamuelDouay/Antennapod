package com.podcast.antennapod.view.container.navigation;

import com.podcast.antennapod.view.item.NavigationItem;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.material2.Material2MZ;

public class NavigationContainer {
    private static final Logger LOGGER = LogManager.getLogger(NavigationContainer.class);

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


        NavigationItem sperator = new NavigationItem();

        NavigationItem podcast1 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/zerl.jpg")), "Zack en Roue Libre by Zack Nani", 12);
        NavigationItem podcast2 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/heure_du_monde.png")), "L'heure du monde", 12);
        NavigationItem podcast3 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/small_talk.jpg")), "Small Tallk - Kobini", 12);
        NavigationItem podcast4 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/underscore.jpeg")), "Underscore_", 12);
        NavigationItem podcast5 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/ex.jpeg")), "Ex...", 12);

        listView.getItems().addAll(
                homeItem, playlistItem, inboxItem, episodesItem,
                subscriptionsItem, downloadsItem, historyItem, addPodcastItem,
                sperator,
                podcast1, podcast2, podcast3, podcast4, podcast5
        );

        listView.setCellFactory(param -> new NavigationCellItem());

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                LOGGER.info("Element sélectioné {}", newValue.getTitle());
                // Ici, vous pouvez exécuter n'importe quelle action en fonction de l'élément sélectionné
                // Par exemple, changer de vue ou charger du contenu
            }
        });

        listView.getSelectionModel().selectFirst();

        return listView;
    }
}
