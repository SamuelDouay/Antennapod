package com.podcast.antennapod.view.component.image;

import javafx.scene.Node;

public interface ImageComponentService {
    Node createImageCard(String imageUrl);
    Node createImageCard(String imageUrl, String title, int episodeCount);
    Node createImageCard(String imageUrl, String title, String date);
}
