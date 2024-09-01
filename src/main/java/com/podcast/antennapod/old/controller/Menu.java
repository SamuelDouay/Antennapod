package com.podcast.antennapod.old.controller;

import javafx.scene.image.Image;

public class Menu {
    private String name;
    private int nbEpisode;
    public Image image;
    private boolean isActive;

    public Menu(String name, int nbEpisode, Image image, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
        this.nbEpisode = nbEpisode;
        this.image = image;
    }

    public void setNbEpisode(int nbEpisode) {
        this.nbEpisode = nbEpisode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getNbEpisode() {
        return nbEpisode;
    }
}
