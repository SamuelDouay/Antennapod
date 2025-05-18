package com.podcast.antennapod.view.util;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private static final Map<String, Image> imageCache = new HashMap<>();
    private static final int MAX_CACHE_SIZE = 1000; // Ajuste selon tes besoins

    public static Image getImage(String url) {
        if (imageCache.containsKey(url)) {
            return imageCache.get(url);
        }

        // Mécanisme de contrôle de taille du cache
        if (imageCache.size() >= MAX_CACHE_SIZE) {
            // Stratégie simple: supprimer une entrée aléatoire
            String keyToRemove = imageCache.keySet().iterator().next();
            imageCache.remove(keyToRemove);
        }

        Image image = new Image(url, true); // true pour chargement en background
        imageCache.put(url, image);
        return image;
    }

    public static void clearCache() {
        imageCache.clear();
    }
}
