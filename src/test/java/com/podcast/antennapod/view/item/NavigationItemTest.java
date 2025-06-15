package com.podcast.antennapod.view.item;

import com.podcast.antennapod.item.NavigationItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kordamp.ikonli.javafx.FontIcon;

import static org.junit.jupiter.api.Assertions.*;

class NavigationItemTest {

    @Test
    @DisplayName("Test constructeur avec icône et titre")
    void testConstructorWithIconAndTitle() {
        FontIcon icon = new FontIcon();
        String title = "Podcasts";

        NavigationItem item = new NavigationItem(icon, title);

        assertSame(icon, item.getIcon());
        assertEquals(title, item.getTitle());
        assertEquals(0, item.getNumber());
        assertNull(item.getImageUrl());
    }

    @Test
    @DisplayName("Test constructeur avec icône, titre et nombre")
    void testConstructorWithIconTitleAndNumber() {
        FontIcon icon = new FontIcon();
        String title = "Playlists";
        int number = 5;

        NavigationItem item = new NavigationItem(icon, title, number);

        assertSame(icon, item.getIcon());
        assertEquals(title, item.getTitle());
        assertEquals(number, item.getNumber());
        assertNull(item.getImageUrl());
    }

    @Test
    @DisplayName("Test constructeur avec URL d'image et titre")
    void testConstructorWithImageUrlAndTitle() {
        String imageUrl = "https://example.com/image.jpg";
        String title = "Chaîne";

        NavigationItem item = new NavigationItem(imageUrl, title);

        assertEquals(imageUrl, item.getImageUrl());
        assertEquals(title, item.getTitle());
        assertEquals(0, item.getNumber());
        assertNull(item.getIcon());
    }

    @Test
    @DisplayName("Test constructeur avec URL d'image, titre et nombre")
    void testConstructorWithImageUrlTitleAndNumber() {
        String imageUrl = "https://example.com/image.jpg";
        String title = "Non lus";
        int number = 10;

        NavigationItem item = new NavigationItem(imageUrl, title, number);

        assertEquals(imageUrl, item.getImageUrl());
        assertEquals(title, item.getTitle());
        assertEquals(number, item.getNumber());
        assertNull(item.getIcon());
    }

    @Test
    @DisplayName("Test setters et getters")
    void testSettersAndGetters() {
        NavigationItem item = new NavigationItem(new FontIcon(), "test");

        FontIcon icon = new FontIcon();
        item.setIcon(icon);
        assertSame(icon, item.getIcon());

        String title = "Nouveau titre";
        item.setTitle(title);
        assertEquals(title, item.getTitle());

        int number = 15;
        item.setNumber(number);
        assertEquals(number, item.getNumber());

        String imageUrl = "https://example.com/new-image.jpg";
        item.setImageUrl(imageUrl);
        assertEquals(imageUrl, item.getImageUrl());
    }
}