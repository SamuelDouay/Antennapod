package com.podcast.antennapod.view.item;

import org.kordamp.ikonli.javafx.FontIcon;

public class NavigationItem {
    private FontIcon icon;
    private String title;
    private int number;
    private String imageUrl;
    private final boolean separator;

    public NavigationItem(FontIcon icon, String title, int number) {
        this.icon = icon;
        this.title = title;
        this.number = number;
        this.imageUrl = null;
        this.separator = false;
    }

    public NavigationItem(String imageUrl, String title, int number) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.number = number;
        this.icon = null;
        this.separator = false;
    }

    public NavigationItem() {
        this.separator = true;
        this.icon = null;
        this.number = 0;
        this.imageUrl = null;
        this.title = null;
    }

    public FontIcon getIcon() {
        return icon;
    }

    public void setIcon(FontIcon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isSeparator() {
        return separator;
    }
}
