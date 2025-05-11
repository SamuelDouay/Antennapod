package com.podcast.antennapod.view.component.image;

import javafx.scene.Node;

public class ImageComponentServiceImpl implements ImageComponentService{
    private static ImageComponentServiceImpl instance;

    private ImageComponentServiceImpl() {
        // Constructeur privé
    }

    public static synchronized ImageComponentServiceImpl getInstance() {
        if (instance == null) {
            instance = new ImageComponentServiceImpl();
        }
        return instance;
    }

    @Override
    public Node createImageCard(String imageUrl) {
        return ImageComponent.createImageCard(imageUrl);
    }

    @Override
    public Node createImageCard(String imageUrl, String title, int episodeCount) {
        return ImageComponent.createImageCard(imageUrl, title, episodeCount);
    }

    @Override
    public Node createImageCard(String imageUrl, String title, String date) {
        return ImageComponent.createImageCard(imageUrl, title, date);
    }
}
