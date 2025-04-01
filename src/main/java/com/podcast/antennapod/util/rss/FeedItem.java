package com.podcast.antennapod.util.rss;

public class FeedItem {
    private String title;
    private String duration;
    private String pubDate;
    private String description;

    public FeedItem(String title, String duration, String pubDate, String description) {
        this.title = title;
        this.duration = duration;
        this.pubDate = pubDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "title='" + (title != null ? title : "") + '\'' +
                ", duration='" + (duration != null ? duration : "") + '\'' +
                ", pubdate='" + (pubDate != null ? pubDate : "") + '\'' +
                ", description='" + (description != null ? description : "") + '\'' +
                '}';
    }
}
