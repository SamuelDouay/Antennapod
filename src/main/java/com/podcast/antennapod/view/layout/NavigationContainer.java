package com.podcast.antennapod.view.layout;

import com.podcast.antennapod.item.ItemManager;
import com.podcast.antennapod.item.NavigationItem;
import com.podcast.antennapod.view.component.navigation.NavigationComponent;
import com.podcast.antennapod.view.layout.context.FeedContext;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.LayoutType;
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

    private final ItemManager manager;
    private final List<HBox> listNav;
    private LayoutManager layoutManager;

    public NavigationContainer() {
        this.manager = new ItemManager();
        this.listNav = new ArrayList<>();
    }

    public NavigationContainer(LayoutManager layoutManager) {
        this();
        this.layoutManager = layoutManager;
    }

    public VBox createMenu() {
        VBox mainContainer = new VBox();
        mainContainer.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        VBox.setVgrow(mainContainer, Priority.ALWAYS);
        mainContainer.getChildren().addAll(createFixedList(), createScrollList());
        return mainContainer;
    }

    private VBox createList() {
        VBox box = new VBox();
        box.setPadding(new Insets(8.0));
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        box.setMinWidth(Region.USE_PREF_SIZE);
        return box;
    }

    private VBox createFixedList() {
        VBox box = createList();

        NavigationItem homeItem = new NavigationItem(new FontIcon(MaterialDesignH.HOME), "Home");
        homeItem.setSelected(true);
        NavigationItem playlistItem = new NavigationItem(new FontIcon(MaterialDesignP.PLAYLIST_PLAY), "Queue");
        NavigationItem inboxItem = new NavigationItem(new FontIcon(MaterialDesignI.INBOX), "Inbox", 12);
        NavigationItem episodesItem = new NavigationItem(new FontIcon(MaterialDesignR.RSS), "Episodes");
        NavigationItem subscriptionsItem = new NavigationItem(new FontIcon(MaterialDesignV.VIEW_GRID_OUTLINE), "Subscription");
        NavigationItem downloadsItem = new NavigationItem(new FontIcon(MaterialDesignD.DOWNLOAD), "Downloads", 123);
        NavigationItem historyItem = new NavigationItem(new FontIcon(MaterialDesignH.HISTORY), "Playback history");
        NavigationItem addPodcastItem = new NavigationItem(new FontIcon(MaterialDesignP.PLUS), "Add podcast");

        listNav.add(createNavigationComponent(homeItem, LayoutType.HOME));
        listNav.add(createNavigationComponent(playlistItem, LayoutType.QUEUE));
        listNav.add(createNavigationComponent(inboxItem, LayoutType.INBOX));
        listNav.add(createNavigationComponent(episodesItem, LayoutType.EPISODES));
        listNav.add(createNavigationComponent(subscriptionsItem, LayoutType.SUBSCRIPTION));
        listNav.add(createNavigationComponent(downloadsItem, LayoutType.DOWNLOAD));
        listNav.add(createNavigationComponent(historyItem, LayoutType.HISTORY));
        listNav.add(createNavigationComponent(addPodcastItem, LayoutType.ADD));

        box.getChildren().addAll(listNav);

        return box;
    }

    private ScrollPane createScrollList() {
        VBox box = createList();

        for (int i = 0; i < 5; i++) {
            NavigationItem podcast1 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/zerl.jpg")), "Zack en Roue Libre by Zack Nani", 12);
            NavigationItem podcast2 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/heure_du_monde.png")), "L'heure du monde");
            NavigationItem podcast3 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/small_talk.jpg")), "Small Tallk - Kobini", 142);
            NavigationItem podcast4 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/underscore.jpeg")), "Underscore_", 5);
            NavigationItem podcast5 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/ex.jpeg")), "Ex...", 200);
            NavigationItem podcast6 = new NavigationItem(String.valueOf(NavigationContainer.class.getResource("/images/ex.jpeg")), "Ex...");

            HBox box1 = createNavigationComponent(podcast1, LayoutType.FEED);
            HBox box2 = createNavigationComponent(podcast2, LayoutType.FEED);
            HBox box3 = createNavigationComponent(podcast3, LayoutType.FEED);
            HBox box4 = createNavigationComponent(podcast4, LayoutType.FEED);
            HBox box5 = createNavigationComponent(podcast5, LayoutType.FEED);
            HBox box6 = createNavigationComponent(podcast6, LayoutType.FEED);

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

    private ScrollPane getScrollPane(VBox box) {
        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain000(), null, null)));
        scrollPane.setBorder(new Border(new BorderStroke(ColorThemeConstants.getGrey950(), BorderStrokeStyle.SOLID, null, new BorderWidths(1.0, 0, 0, 0))));
        return scrollPane;
    }

    private HBox createNavigationComponent(NavigationItem item, LayoutType layoutType) {
        manager.addItem(item);
        NavigationComponent container = new NavigationComponent();
        HBox box = container.createNavigationCard(item);

        box.setOnMouseClicked(_ -> {
            manager.setItemState(true, item.getUuid());
            for (HBox hBox : listNav) {
                updateAppearance(hBox, hBox.equals(box));
            }
            if (layoutType != null && layoutManager != null) {
                if (layoutType.equals(LayoutType.FEED)) {
                    FeedContext context = new FeedContext(item.getTitle(), item.getUuid().toString(), item.getNumber());
                    layoutManager.setLayout(layoutType, context);

                } else {
                    layoutManager.setLayout(layoutType);
                }
            }
        });
        box.setOnMouseEntered(_ -> updateAppearance(box, true));
        box.setOnMouseExited(_ -> updateAppearance(box, item.isSelected()));
        box.setOnMousePressed(_ -> box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain050(), null, null))));
        box.setOnMouseReleased(_ -> updateAppearance(box, item.isSelected()));

        return box;
    }

    private void updateAppearance(HBox mainBox, boolean isSelected) {
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