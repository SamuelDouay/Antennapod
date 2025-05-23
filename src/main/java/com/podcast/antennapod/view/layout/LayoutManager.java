package com.podcast.antennapod.view.layout;

import com.podcast.antennapod.view.util.LayoutType;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class LayoutManager {
    private ScrollPane scrollPane;

    public LayoutManager(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void setLayout(LayoutType layoutType) {
        Node layout = null;
        switch (layoutType) {
            case HOME:
                layout = new HomeLayout().getLayout();
                break;
            case SUBSCRIPTION:
                layout = new SubscriptionLayout().getLayout();
                break;
            // Ajoutez d'autres layouts ici
        }
        scrollPane.setContent(layout);
    }
}
