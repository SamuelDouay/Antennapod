package com.podcast.antennapod.view.component.image;

import javafx.scene.Node;

public class ImageComponentFactory {
    private final ImageComponentService service;

    public ImageComponentFactory(ImageComponentService service) {
        this.service = service;
    }

    public ImageComponentFactory() {
        this(new ImageComponentService());
    }

    public Node createImageCard(String imageUrl) {
        return service.createImageCard(imageUrl);
    }

    public Node createImageCard(String imageUrl, String title, int episodeCount) {
        return service.createImageCard(imageUrl, title, episodeCount);
    }

    public Node createImageCard(String imageUrl, String title, String date) {
        return service.createImageCard(imageUrl, title, date);
    }
}
