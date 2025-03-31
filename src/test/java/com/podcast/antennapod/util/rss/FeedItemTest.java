package com.podcast.antennapod.util.rss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FeedItemTest {

    private static FeedItem feedItemAllFields;
    private static FeedItem feedItemNullFields;

    @BeforeAll
    public static void setUp() {
        // Initialisation d'un FeedItem avec toutes les valeurs
        feedItemAllFields = new FeedItem(
                "Test Title",
                "01:30:00",
                "2025-03-31T10:00:00Z",
                "This is a test description"
        );

        // Initialisation d'un FeedItem avec des valeurs null
        feedItemNullFields = new FeedItem(null, null, null, null);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Test Title", feedItemAllFields.getTitle());
        assertEquals("01:30:00", feedItemAllFields.getDuration());
        assertEquals("2025-03-31T10:00:00Z", feedItemAllFields.getPubDate());
        assertEquals("This is a test description", feedItemAllFields.getDescription());

        assertNull(feedItemNullFields.getTitle());
        assertNull(feedItemNullFields.getDuration());
        assertNull(feedItemNullFields.getPubDate());
        assertNull(feedItemNullFields.getDescription());
    }

    @Test
    public void testSetters() {
        // Test des setters avec des valeurs non-null
        FeedItem item = new FeedItem(null, null, null, null);

        item.setTitle("New Title");
        assertEquals("New Title", item.getTitle());

        item.setDuration("00:45:30");
        assertEquals("00:45:30", item.getDuration());

        item.setPubDate("2025-04-01T12:00:00Z");
        assertEquals("2025-04-01T12:00:00Z", item.getPubDate());

        item.setDescription("New description text");
        assertEquals("New description text", item.getDescription());

        // Test des setters avec null
        item.setTitle(null);
        assertNull(item.getTitle());

        item.setDuration(null);
        assertNull(item.getDuration());

        item.setPubDate(null);
        assertNull(item.getPubDate());

        item.setDescription(null);
        assertNull(item.getDescription());
    }

    @Test
    public void testToString() {
        // Test de toString avec des valeurs non-null
        String expected = "FeedItem{" +
                "title='Test Title'" +
                ", duration='01:30:00'" +
                ", pubdate='2025-03-31T10:00:00Z'" +
                ", description='This is a test description'" +
                '}';
        assertEquals(expected, feedItemAllFields.toString());

        // Test de toString avec des valeurs null
        String expectedWithNulls = "FeedItem{" +
                "title=''" +
                ", duration=''" +
                ", pubdate=''" +
                ", description=''" +
                '}';
        assertEquals(expectedWithNulls, feedItemNullFields.toString());
    }

    @Test
    public void testToStringWithMixedNullValues() {
        // Test avec certaines valeurs null et d'autres non
        FeedItem mixedItem = new FeedItem(
                "Mixed Title",
                null,
                "2025-03-31T10:00:00Z",
                null
        );

        String expected = "FeedItem{" +
                "title='Mixed Title'" +
                ", duration=''" +
                ", pubdate='2025-03-31T10:00:00Z'" +
                ", description=''" +
                '}';
        assertEquals(expected, mixedItem.toString());
    }
}