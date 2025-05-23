package com.podcast.antennapod.view.layout;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SubscriptionLayout extends Layout {

    public SubscriptionLayout() {
        super("Subscription");// no param
    }

    @Override
    public VBox getLayout() {
        VBox box = getContainer();

        box.getChildren().add(getTitle());

        return box;
    }

    private GridPane getSubscription() {
        GridPane gridPane = new GridPane();

        return gridPane;
    }
}
