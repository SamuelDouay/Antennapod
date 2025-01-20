package com.podcast.antennapod.old.abonnement;

import java.util.Date;

public class Episode {
    private String title;
    private Date date;
    private String imageUrl;

    public Episode(String title, Date date, String imageUrl) {
        this.title = title;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}


/*

SELECT fi.title as title, fi.pubDate  as date, f.title as podcast_name, f.image_url as podcast_img FROM Queue
INNER JOIN FeedItems fi ON fi.id = Queue.feeditem
INNER JOIN Feeds f ON f.id = Queue.feed
LIMIT 10;
 */