package com.podcast.antennapod.old.navigation;

import com.podcast.antennapod.old.style.ButtonStyle;
import com.podcast.antennapod.old.style.LabelStyle;
import com.podcast.antennapod.old.controller.Menu;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class MenuItem {
    private Menu menu;

    public MenuItem (Menu menu) {
        this.menu = menu;
    }

    public Node toView() {
        HBox main = new HBox();
        Label name = new Label(this.menu.getName());
        ImageView imageView = new ImageView(this.menu.getImage());
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        Label number = new Label(Integer.toString(this.menu.getNbEpisode()));
        name.setMaxWidth(140);

        LabelStyle.setTextOnDefault(name);
        LabelStyle.setNbEpisodes(number);


        HBox inside = new HBox(14);
        inside.getChildren().addAll(imageView, name);
        inside.setAlignment(Pos.CENTER_LEFT);


        number.setAlignment(Pos.CENTER_RIGHT);

        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        main.getChildren().addAll(inside, r, number);
        ButtonStyle.setButtonStyle(main);
        return main;
    }
}