package com.podcast.antennapod.view.component.image;

import javafx.scene.Node;

public class ImageComponent {
    private ImageComponent() {

    }

    public static Node createImageCard(String imageUrl) {
        return new ImageBuilder(imageUrl).build();
    }

    public static Node createImageCard(String imageUrl, String title, int episodeCount) {
        return new ImageBuilder(imageUrl).withTitle(title).withEpisodeCount(episodeCount).build();
    }

    public static Node createImageCard(String url, String title, String date) {
        return new ImageBuilder(url).withTitle(title).withDate(date).build();

    }
}