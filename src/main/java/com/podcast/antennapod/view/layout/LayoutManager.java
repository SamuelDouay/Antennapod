package com.podcast.antennapod.view.layout;

import com.podcast.antennapod.view.util.LayoutType;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class LayoutManager {
    private final ScrollPane scrollPane;

    public LayoutManager(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void setLayout(LayoutType layoutType) {
        Node layout;
        if (layoutType.equals(LayoutType.HOME)) {
            layout = new HomeLayout().getLayout();
        } else {
            layout = new SubscriptionLayout().getLayout();
        }
        scrollPane.setContent(layout);
    }
}
