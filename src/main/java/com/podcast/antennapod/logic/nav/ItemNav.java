package com.podcast.antennapod.logic.nav;

public class ItemNav {
    private String name;
    private String urlImage;
    private int numberNew;

    public ItemNav(String name, String urlImage, int numberNew) {
        this.name = name;
        this.urlImage = urlImage;
        this.numberNew = numberNew;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public int getNumberNew() {
        return numberNew;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
