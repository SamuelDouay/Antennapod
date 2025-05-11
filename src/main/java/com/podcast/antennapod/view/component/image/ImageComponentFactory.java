package com.podcast.antennapod.view.component.image;

import javafx.scene.Node;

public class ImageComponentFactory {
    private final ImageComponentService imageComponentService;

    public ImageComponentFactory(ImageComponentService imageComponentService) {
        this.imageComponentService = imageComponentService;
    }

    public ImageComponentFactory() {
        this(ImageComponentServiceImpl.getInstance());
    }

    public Node createImageCard(String imageUrl) {
        return imageComponentService.createImageCard(imageUrl);
    }

    public Node createImageCard(String imageUrl, String title, int episodeCount) {
        return imageComponentService.createImageCard(imageUrl, title, episodeCount);
    }

    public Node createImageCard(String imageUrl, String title, String date) {
        return imageComponentService.createImageCard(imageUrl, title, date);
    }
}
