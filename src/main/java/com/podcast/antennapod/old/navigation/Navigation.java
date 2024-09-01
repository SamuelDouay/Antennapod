package com.podcast.antennapod.old.navigation;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

public class Navigation {
    VBox menuItem;

    public Navigation(double size) {
        this.menuItem = new VBox(0);
        this.menuItem.setMinWidth(size);
        this.menuItem.setPadding(new Insets(8.0));
    }

    public void setBorder(Border border) {
        this.menuItem.setBorder(border);
    }

    public void addItem(Node item) {
        menuItem.getChildren().add(item);
    }

    public void setBackground(Background background) {
        menuItem.setBackground(background);
    }

    public VBox getMenuItem() {
        return menuItem;
    }
}
