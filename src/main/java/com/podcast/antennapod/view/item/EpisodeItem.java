package com.podcast.antennapod.view.item;

public class EpisodeItem extends Item {
    private final String urlImage;
    private final boolean favorite;
    private final String name;
    private final String duration;
    private final String date;
    private final String size;

    public EpisodeItem(String urlImage, boolean favorite, String name, String duration, String date, String size) {
        super();
        this.urlImage = urlImage;
        this.favorite = favorite;
        this.name = name;
        this.duration = duration;
        this.date = date;
        this.size = size;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public String getSize() {
        return size;
    }
}
