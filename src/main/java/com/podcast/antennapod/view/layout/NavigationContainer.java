package com.podcast.antennapod.view.layout;

import com.podcast.antennapod.item.ItemManager;
import com.podcast.antennapod.item.NavigationItem;
import com.podcast.antennapod.service.NavigationService;
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
    private final NavigationService navigationService;

    public NavigationContainer() {
        this.manager = new ItemManager();
        this.listNav = new ArrayList<>();
        this.navigationService = new NavigationService();
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
        NavigationItem inboxItem = new NavigationItem(new FontIcon(MaterialDesignI.INBOX), "Inbox", navigationService.getNumberOfInbox());
        NavigationItem episodesItem = new NavigationItem(new FontIcon(MaterialDesignR.RSS), "Episodes");
        NavigationItem subscriptionsItem = new NavigationItem(new FontIcon(MaterialDesignV.VIEW_GRID_OUTLINE), "Subscription", navigationService.getNumberOfInbox());
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

        for (NavigationItem navigationItem : navigationService.getList()) {
            box.getChildren().add(createNavigationComponent(navigationItem, LayoutType.FEED));
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