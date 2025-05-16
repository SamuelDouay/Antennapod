package com.podcast.antennapod.view.container.navigation;

import com.podcast.antennapod.view.component.navigation.NavigationComponent;
import com.podcast.antennapod.view.item.ItemManager;
import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.*;

import java.util.ArrayList;
import java.util.List;

public class NavigationContainer {
    private static final String FONT = "Inter";
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

        for (int i = 0; i < 5; i++) {
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

        ScrollPane scrollPane = getScrollPane(box);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return scrollPane;
    }

    private static ScrollPane getScrollPane(VBox box) {
        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        scrollPane.setBorder(new Border(new BorderStroke(ColorThemeConstants.getGrey950(), BorderStrokeStyle.SOLID, null, new BorderWidths(1.0, 0, 0, 0))));
        return scrollPane;
    }

    private static HBox createNavigationComponent(NavigationItem item) {
        manager.addItem(item);
        NavigationComponent container = new NavigationComponent();
        HBox box = container.createNavigationCard(item);
        
        box.setOnMouseClicked(_ -> {
            manager.setItemState(true, item.getUuid());
            for (HBox hBox : listNav) {
                updateAppearance(hBox, hBox.equals(box));
            }
        });
        box.setOnMouseEntered(_ -> updateAppearance(box, true));
        box.setOnMouseExited(_ -> updateAppearance(box, item.isSelected()));
        box.setOnMousePressed(e -> box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain050(), null, null))));
        box.setOnMouseReleased(_ -> updateAppearance(box, item.isSelected()));

        return box;
    }

    private static void updateAppearance(HBox mainBox, boolean isSelected) {
        Label titleLabel = (Label) ((HBox) mainBox.getChildren().getFirst()).getChildren().get(1);
        Node icon = ((HBox) mainBox.getChildren().getFirst()).getChildren().get(0);

        if (isSelected) {
            titleLabel.setTextFill(ColorThemeConstants.getMain950());
            titleLabel.setFont(Font.font(FONT, FontWeight.BOLD, 12));
            mainBox.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain100(), new CornerRadii(2.0), null)));
            if (!(icon instanceof FontIcon)) {
                return;
            }
            ((FontIcon) icon).setIconColor(ColorThemeConstants.getMain950());
        } else {
            titleLabel.setTextFill(ColorThemeConstants.getGrey800());
            titleLabel.setFont(Font.font(FONT, FontPosture.REGULAR, 12));
            mainBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
            if (!(icon instanceof FontIcon)) {
                return;
            }
            ((FontIcon) icon).setIconColor(ColorThemeConstants.getGrey800());
        }
    }
}
