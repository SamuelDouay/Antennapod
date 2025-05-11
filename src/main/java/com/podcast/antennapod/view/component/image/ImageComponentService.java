package com.podcast.antennapod.view.component.image;

import javafx.scene.Node;

public class ImageComponentService {
    private final ImageComponent component;

    public ImageComponentService() {
        this.component = new ImageComponent();
    }

    public Node createImageCard(String imageUrl) {
        return component.createImageCard(imageUrl);
    }

    public Node createImageCard(String imageUrl, String title, int episodeCount) {
        return component.createImageCard(imageUrl, title, episodeCount);
    }

    public Node createImageCard(String imageUrl, String title, String date) {
        return component.createImageCard(imageUrl, title, date);
    }
}
