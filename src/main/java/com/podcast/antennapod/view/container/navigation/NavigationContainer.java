package com.podcast.antennapod.view.container.navigation;

import com.podcast.antennapod.view.item.NavigationItem;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.*;

public class NavigationContainer {

    private NavigationContainer() {

    }

    public static ListView<NavigationItem> createMenu() {
        ListView<NavigationItem> listView = new ListView<>();

        // Configurer le padding
        listView.setPadding(new Insets(8.0));
        listView.setBackground(null);
        listView.setBorder(null);
        listView.setMinWidth(Region.USE_PREF_SIZE);

        NavigationItem homeItem = new NavigationItem(new FontIcon(MaterialDesignH.HOME), "Accueil", 0);
        NavigationItem playlistItem = new NavigationItem(new FontIcon(MaterialDesignP.PLAYLIST_PLAY), "Liste de lecture", 0);
        NavigationItem inboxItem = new NavigationItem(new FontIcon(MaterialDesignI.INBOX), "Boîte de reception", 0);
        NavigationItem episodesItem = new NavigationItem(new FontIcon(MaterialDesignR.RSS), "Episodes", 0);
        NavigationItem subscriptionsItem = new NavigationItem(new FontIcon(MaterialDesignV.VIEW_GRID_OUTLINE), "Abonnements", 0);
        NavigationItem downloadsItem = new NavigationItem(new FontIcon(MaterialDesignD.DOWNLOAD), "Téléchargement", 0);
        NavigationItem historyItem = new NavigationItem(new FontIcon(MaterialDesignH.HISTORY), "Journal de lecture", 0);
        NavigationItem addPodcastItem = new NavigationItem(new FontIcon(MaterialDesignP.PLUS), "Ajouter un podcast", 0);


        NavigationItem separator = new NavigationItem();

        NavigationItem podcast1 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/zerl.jpg")), "Zack en Roue Libre by Zack Nani", 12);
        NavigationItem podcast2 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/heure_du_monde.png")), "L'heure du monde", 12);
        NavigationItem podcast3 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/small_talk.jpg")), "Small Tallk - Kobini", 12);
        NavigationItem podcast4 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/underscore.jpeg")), "Underscore_", 12);
        NavigationItem podcast5 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/others/ex.jpeg")), "Ex...", 12);

        listView.getItems().addAll(
                homeItem, playlistItem, inboxItem, episodesItem,
                subscriptionsItem, downloadsItem, historyItem, addPodcastItem,
                separator,
                podcast1, podcast2, podcast3, podcast4, podcast5
        );

        listView.setCellFactory(_ -> new NavigationCellItem());

        listView.getSelectionModel().selectFirst();
        return listView;
    }
}
