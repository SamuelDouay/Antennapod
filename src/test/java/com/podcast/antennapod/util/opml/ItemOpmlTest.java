package com.podcast.antennapod.util.opml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Item Opml Test")
public class ItemOpmlTest {
    final String text = "TEXT";
    final String title = "TITLE";
    final String type = "RSS";
    final String xmlUrl = "XML";
    final String htmlUrl = "HTML";

    @DisplayName("Test Constructor")
    @Test
    public void testConstructor() {

        ItemOpml test = new ItemOpml(text, title, type, xmlUrl, htmlUrl);

        assertEquals(text, test.getText());
        assertEquals(title, test.getTitle());
        assertEquals(type, test.getType());
        assertEquals(xmlUrl, test.getXmlUrl());
        assertEquals(htmlUrl, test.getHtmlUrl());
    }

    @DisplayName("Test string")
    @Test
    public void testString() {
        ItemOpml test = new ItemOpml(text, title, type, xmlUrl, htmlUrl);

        assertEquals(test.toString(), "ItemOpml{" +
                "text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", xmlUrl='" + xmlUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}');
    }
}
