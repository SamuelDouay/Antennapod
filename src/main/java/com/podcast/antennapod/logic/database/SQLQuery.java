package com.podcast.antennapod.logic.database;

import com.podcast.antennapod.old.abonnement.Episode;
import com.podcast.antennapod.old.abonnement.Podcast;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLQuery {
    public static HashMap<String, String> getPodcast() throws SQLException {
        ResultSet resultSet = Connect.getInstance().executeQuery("SELECT * from FEEDS LIMIT 10;");
        HashMap<String, String> res = new HashMap<>();

        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String img = resultSet.getString("image_url");
            res.put(title, img);
        }

        return res;
    }

    public static List<Episode> getQueueTop10() throws SQLException {

        ResultSet resultSet = Connect.getInstance().executeQuery("SELECT fi.title as title, fi.pubDate  as date, f.title as podcast_name, f.image_url as podcast_img FROM Queue\n" +
                "INNER JOIN FeedItems fi ON fi.id = Queue.feeditem\n" +
                "INNER JOIN Feeds f ON f.id = Queue.feed LIMIT 10;");

        List<Episode> res = new ArrayList<>();

        while (resultSet.next()) {
            String title = resultSet.getString("title");
            Date date = resultSet.getDate("date");
            String podcast_name = resultSet.getString("podcast_name");
            String podcast_img = resultSet.getString("podcast_img");
            res.add(new Episode(title, date, podcast_img));
        }

        return res;

    }

    public static List<Podcast> getPodcastName() throws SQLException {
        ResultSet resultSet = Connect.getInstance().executeQuery("SELECT Feeds.title, Feeds.image_url FROM Feeds;");

        List<Podcast> res = new ArrayList<>();

        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String img = resultSet.getString("image_url");
            int nb = 0;
            res.add(new Podcast(title, img, nb));
        }

        return res;
    }
}
