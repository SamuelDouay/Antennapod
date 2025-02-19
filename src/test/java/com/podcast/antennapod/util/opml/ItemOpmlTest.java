package com.podcast.antennapod.util.opml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Item Opml Test")
public class ItemOpmlTest {
    final String title = "Test";
    final String type = "RSS";
    final String xmlUrl = "Xml";
    final String htmlUrl = "HTML";

    @DisplayName("Test Constructor")
    @Test
    public void testConstructor() {

        ItemOpml test = new ItemOpml(title, type, xmlUrl, htmlUrl);

        assertEquals(title, test.getTitle());
        assertEquals(type, test.getType());
        assertEquals(xmlUrl, test.getXmlUrl());
        assertEquals(htmlUrl, test.getHtmlUrl());
    }

    @DisplayName("Test string")
    @Test
    public void testString() {
        ItemOpml test = new ItemOpml(title, type, xmlUrl, htmlUrl);

        assertEquals(test.toString(), "ItemOpml{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", xmlUrl='" + xmlUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}');
    }
}
