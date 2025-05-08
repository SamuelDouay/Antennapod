package com.podcast.antennapod.view.container.home;

import com.podcast.antennapod.view.MainTest;
import com.podcast.antennapod.view.MainView;
import com.podcast.antennapod.view.component.EpisodeComponent;
import com.podcast.antennapod.view.component.PodcastComponent;
import com.podcast.antennapod.view.item.EpisodeItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeContainer {
    private HomeContainer() {

    }

    public static Node getHomeContainer() {
        VBox box = new VBox();

        box.getChildren().add(getTitle());
        box.getChildren().add(getNews());
        box.getChildren().add(getClassicsSection());

        box.setPadding(new Insets(32.0, 64.0, 32.0, 64.0));
        box.setSpacing(35.0);
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        HBox.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    private static Label getTitle() {
        Label label = new Label("Accueil");
        label.setFont(Font.font("Inter", FontWeight.BOLD, 36));
        label.setTextFill(ColorThemeConstants.getMain950());
        return label;
    }

    private static VBox getNews() {
        VBox box = new VBox(12);
        HBox.setHgrow(box, Priority.ALWAYS);
        box.getChildren().add(getTitleSection("Nouveautés"));
        box.getChildren().add(getNewsTable());
        return box;
    }

    private static Node getNewsTable() {
        VBox box = new VBox();

        for (int i = 0; i < 4; i++) {
            EpisodeItem episodeItem = new EpisodeItem(String.valueOf(MainView.class.getResource("/images/heure_du_monde.png")),
                    false,
                    "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]",
                    "00:20:40",
                    "28/10/2024",
                    "18 Mo");

            EpisodeItem episodeItem1 = new EpisodeItem(String.valueOf(MainView.class.getResource("/images/ex.jpeg")),
                    true,
                    "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]",
                    "00:20:40",
                    "28/10/2024",
                    "18 Mo");

            box.getChildren().add(EpisodeComponent.createNewEpisode(episodeItem));
            box.getChildren().add(EpisodeComponent.createNewEpisode(episodeItem1));
        }

        return box;
    }

    private static Label getTitleSection(String title) {
        Label label = new Label(title);
        label.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        label.setTextFill(ColorThemeConstants.getMain950());
        return label;
    }

    private static VBox getClassicsSection() {
        VBox box = new VBox(12);
        box.getChildren().add(getTitleSection("Vos classiques"));
        box.getChildren().add(getClassic());
        return box;
    }

    private static ScrollPane getClassic() {
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        HBox.setHgrow(scrollPane, Priority.ALWAYS);


        HBox box = new HBox(15);
        box.setPadding(new Insets(0.0, 1.0, 0.0, 1.0));
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        HBox.setHgrow(box, Priority.ALWAYS);

        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/ex.jpeg"))));
        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/heure_du_monde.png"))));
        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/small_talk.jpg"))));
        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/underscore.jpeg"))));
        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/zerl.jpg"))));
        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/underscore.jpeg"))));
        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/zerl.jpg"))));
        box.getChildren().add(PodcastComponent.createPodcastCard(String.valueOf(MainTest.class.getResource("/images/small_talk.jpg"))));

        scrollPane.setContent(box);
        return scrollPane;
    }
}
