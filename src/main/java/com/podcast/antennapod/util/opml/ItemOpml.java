package com.podcast.antennapod.util.opml;

public class ItemOpml {
    private String text;
    private String title;
    private String type;
    private String xmlUrl;
    private String htmlUrl;

    public ItemOpml(String text, String title, String type, String xmlUrl, String htmlUrl) {
        this.text = text;
        this.title = title;
        this.type = type;
        this.xmlUrl = xmlUrl;
        this.htmlUrl = htmlUrl;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getXmlUrl() {
        return xmlUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public String toString() {
        return "ItemOpml{" +
                "text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", xmlUrl='" + xmlUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}';
    }
}
