package com.podcast.antennapod.view.container.navigation;

import com.podcast.antennapod.view.component.NavigationComponent;
import com.podcast.antennapod.view.item.ItemManager;
import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.*;

import java.util.ArrayList;
import java.util.List;

public class NavigationContainer {
    private static final ItemManager manager = new ItemManager();
    private static final List<HBox> listNav = new ArrayList<>();

    private NavigationContainer() {

    }

    public static VBox createMenu() {
        VBox mainContainer = new VBox();
        mainContainer.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        VBox.setVgrow(mainContainer, Priority.ALWAYS);
        mainContainer.getChildren().addAll(createFixedList(), createScrollList());
        return mainContainer;
    }

    private static VBox createList() {
        VBox box = new VBox();
        box.setPadding(new Insets(8.0));
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        box.setMinWidth(Region.USE_PREF_SIZE);
        return box;
    }

    private static VBox createFixedList() {
        VBox box = createList();

        NavigationItem homeItem = new NavigationItem(new FontIcon(MaterialDesignH.HOME), "Accueil");
        homeItem.setSelected(true);
        NavigationItem playlistItem = new NavigationItem(new FontIcon(MaterialDesignP.PLAYLIST_PLAY), "Liste de lecture");
        NavigationItem inboxItem = new NavigationItem(new FontIcon(MaterialDesignI.INBOX), "Boîte de reception", 12);
        NavigationItem episodesItem = new NavigationItem(new FontIcon(MaterialDesignR.RSS), "Episodes");
        NavigationItem subscriptionsItem = new NavigationItem(new FontIcon(MaterialDesignV.VIEW_GRID_OUTLINE), "Abonnements");
        NavigationItem downloadsItem = new NavigationItem(new FontIcon(MaterialDesignD.DOWNLOAD), "Téléchargement", 123);
        NavigationItem historyItem = new NavigationItem(new FontIcon(MaterialDesignH.HISTORY), "Journal de lecture");
        NavigationItem addPodcastItem = new NavigationItem(new FontIcon(MaterialDesignP.PLUS), "Ajouter un podcast");

        listNav.add(createNavigationComponent(homeItem));
        listNav.add(createNavigationComponent(playlistItem));
        listNav.add(createNavigationComponent(inboxItem));
        listNav.add(createNavigationComponent(episodesItem));
        listNav.add(createNavigationComponent(subscriptionsItem));
        listNav.add(createNavigationComponent(downloadsItem));
        listNav.add(createNavigationComponent(historyItem));
        listNav.add(createNavigationComponent(addPodcastItem));

        box.getChildren().addAll(listNav);

        return box;
    }

    private static ScrollPane createScrollList() {
        VBox box = createList();

        for (int i = 0; i < 2; i++) {
            NavigationItem podcast1 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/zerl.jpg")), "Zack en Roue Libre by Zack Nani", 12);
            NavigationItem podcast2 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/heure_du_monde.png")), "L'heure du monde");
            NavigationItem podcast3 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/small_talk.jpg")), "Small Tallk - Kobini", 12);
            NavigationItem podcast4 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/underscore.jpeg")), "Underscore_", 12);
            NavigationItem podcast5 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/ex.jpeg")), "Ex...", 12);
            NavigationItem podcast6 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/ex.jpeg")), "Ex...");

            HBox box1 = createNavigationComponent(podcast1);
            HBox box2 = createNavigationComponent(podcast2);
            HBox box3 = createNavigationComponent(podcast3);
            HBox box4 = createNavigationComponent(podcast4);
            HBox box5 = createNavigationComponent(podcast5);
            HBox box6 = createNavigationComponent(podcast6);

            listNav.add(box1);
            listNav.add(box2);
            listNav.add(box3);
            listNav.add(box4);
            listNav.add(box5);
            listNav.add(box6);

            box.getChildren().addAll(box1, box2, box3, box4, box5, box6);

        }

        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        scrollPane.setBorder(new Border(new BorderStroke(ColorThemeConstants.getGrey950(), BorderStrokeStyle.SOLID, null, new BorderWidths(1.0, 0, 0, 0))));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return scrollPane;
    }

    private static HBox createNavigationComponent(NavigationItem item) {
        manager.addItem(item);
        HBox box = NavigationComponent.createNavigation(item);
        box.setOnMouseClicked(_ -> {
            manager.setItemState(true, item.getUuid());
            for (HBox hBox : listNav) {
                NavigationComponent.updateAppearance(hBox, hBox.equals(box));
            }
        });
        return box;
    }
}
