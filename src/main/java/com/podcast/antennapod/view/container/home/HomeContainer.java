package com.podcast.antennapod.view.container.home;

import com.podcast.antennapod.view.component.episode.EpisodeComponent;
import com.podcast.antennapod.view.component.episode.SurpriseComponent;
import com.podcast.antennapod.view.component.image.ImageComponent;
import com.podcast.antennapod.view.item.EpisodeItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeContainer {

    public static final ImageComponent IMAGE_COMPONENT = new ImageComponent();
    public static final String IMAGE_EX = String.valueOf(HomeContainer.class.getResource("/images/ex.jpeg"));
    public static final String IMAGE_HDM = String.valueOf(HomeContainer.class.getResource("/images/heure_du_monde.png"));
    public static final String IMAGE_SMLTLK = String.valueOf(HomeContainer.class.getResource("/images/small_talk.jpg"));
    public static final String IMAGE_UNDERSCORE = String.valueOf(HomeContainer.class.getResource("/images/underscore.jpeg"));
    public static final String IMAGE_ZERL = String.valueOf(HomeContainer.class.getResource("/images/zerl.jpg"));
    public static final String TITLE_EXAMPLE = "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]";
    public static final String TIME_EXAMPLE = "00:20:40";
    public static final String DATE_EXAMPLE = "28/10/2024";
    public static final String MO_EXAMPLE = "18 Mo";

    private HomeContainer() {

    }

    public static Node getHomeContainer() {
        VBox box = new VBox();

        box.getChildren().add(getTitle());
        box.getChildren().add(getListeningSection());
        box.getChildren().add(getNewsSection());
        box.getChildren().add(getSurpriseSection());
        box.getChildren().add(getClassicsSection());
        box.getChildren().add(getDownloadSection());

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

    private static VBox getNewsSection() {
        VBox box = new VBox(12);
        HBox.setHgrow(box, Priority.ALWAYS);
        box.getChildren().add(getTitleSection("Nouveautés"));
        box.getChildren().add(getNewsTable());
        return box;
    }

    private static VBox getSurpriseSection() {
        VBox box = new VBox(12);
        HBox.setHgrow(box, Priority.ALWAYS);
        box.getChildren().add(getTitleSection("Soyez surpris"));
        box.getChildren().add(getSurpriseTable());
        return box;
    }

    private static Node getSurpriseTable() {
        GridPane box = new GridPane();
        box.setVgap(15.0);
        box.setHgap(15.0);
        box.setPrefWidth(Region.USE_PREF_SIZE);
        box.setMaxHeight(Region.USE_PREF_SIZE);

        int numColumns = 3;
        for (int i = 0; i < numColumns; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            column.setPercentWidth(100.0 / numColumns);
            box.getColumnConstraints().add(column);
        }

        EpisodeItem episodeItem = new EpisodeItem(IMAGE_HDM,
                false,
                TITLE_EXAMPLE,
                TIME_EXAMPLE,
                DATE_EXAMPLE,
                MO_EXAMPLE);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Node surpriseComponent = SurpriseComponent.createSurprise(episodeItem);
                GridPane.setHalignment(surpriseComponent, HPos.CENTER);
                box.add(surpriseComponent, i, j, 1, 1);
            }
        }
        return box;
    }

    private static VBox getDownloadSection() {
        VBox box = new VBox(12);
        HBox.setHgrow(box, Priority.ALWAYS);
        box.getChildren().add(getTitleSection("Vos téléchargements"));
        box.getChildren().add(getNewsTable());
        return box;
    }

    private static Node getNewsTable() {
        VBox box = new VBox();

        for (int i = 0; i < 4; i++) {
            EpisodeItem episodeItem = new EpisodeItem(IMAGE_HDM,
                    false,
                    TITLE_EXAMPLE,
                    TIME_EXAMPLE,
                    DATE_EXAMPLE,
                    MO_EXAMPLE);

            EpisodeItem episodeItem1 = new EpisodeItem(String.valueOf(HomeContainer.class.getResource("/images/ex.jpeg")),
                    true,
                    TITLE_EXAMPLE,
                    TIME_EXAMPLE,
                    DATE_EXAMPLE,
                    MO_EXAMPLE);

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

    private static VBox getListeningSection() {
        VBox box = new VBox(12);
        box.getChildren().add(getTitleSection("Continuer d'écouter"));
        box.getChildren().add(getListening());
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

        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_EX));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_HDM));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_SMLTLK));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_UNDERSCORE));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_ZERL));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_UNDERSCORE));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_ZERL));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_SMLTLK));

        scrollPane.setContent(box);
        return scrollPane;
    }

    private static ScrollPane getListening() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        HBox.setHgrow(scrollPane, Priority.ALWAYS);


        HBox box = new HBox(15);
        box.setPadding(new Insets(0.0, 1.0, 0.0, 1.0));
        box.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getGrey000(), null, null)));
        HBox.setHgrow(box, Priority.ALWAYS);

        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_EX, TITLE_EXAMPLE, "24/05/25"));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_HDM, TITLE_EXAMPLE, "24/12/24"));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_SMLTLK, TITLE_EXAMPLE, "11/05/25"));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_UNDERSCORE, TITLE_EXAMPLE, "15/05/24"));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_ZERL, TITLE_EXAMPLE, "09/03/24"));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_UNDERSCORE, TITLE_EXAMPLE, "12/12/25"));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_ZERL, TITLE_EXAMPLE, "16/11/24"));
        box.getChildren().add(IMAGE_COMPONENT.createImageCard(IMAGE_SMLTLK, TITLE_EXAMPLE, "12/03/24"));

        scrollPane.setContent(box);
        return scrollPane;
    }
}
