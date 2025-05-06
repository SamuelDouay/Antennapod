package com.podcast.antennapod.view.container.home;

import com.podcast.antennapod.view.MainView;
import com.podcast.antennapod.view.item.EpisodeItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class HomeContainer {
    private HomeContainer() {

    }

    public static Node getHomeContainer() {
        VBox box = new VBox();

        box.getChildren().add(getNews());
        return box;
    }

    private static VBox getNews() {
        VBox box = new VBox(12);

        box.getChildren().add(getTitleSection("Nouvautés"));
        box.getChildren().add(getNewsTable());
        return box;
    }

    private static Node getNewsTable() {
        TableView<EpisodeItem> table = new TableView<>();

        TableColumn<EpisodeItem, String> name = new TableColumn<>("name");
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));


        table.getColumns().add(name);


        List<EpisodeItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EpisodeItem episodeItem = new EpisodeItem(String.valueOf(MainView.class.getResource("/images/heure_du_monde.png")),
                    false,
                    "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]",
                    "00:20:40",
                    "28/10/2024",
                    "18 Mo");

            EpisodeItem episodeItem1 = new EpisodeItem(String.valueOf(MainView.class.getResource("/images/small_talk.jpg")),
                    true,
                    "Lil Nas X, une icône noire, et gay et flamboyante [REDIF]",
                    "00:20:40",
                    "28/10/2024",
                    "18 Mo");

            list.add(episodeItem);
            list.add(episodeItem1);
        }
        ObservableList<EpisodeItem> episodeItems = FXCollections.observableList(list);
        table.setItems(episodeItems);

        return table;
    }

    private static Label getTitleSection(String title) {
        Label label = new Label(title);
        label.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        label.setTextFill(ColorThemeConstants.getMain950());
        return label;
    }
}
