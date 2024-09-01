package com.podcast.antennapod.logic.database;

import com.podcast.antennapod.logic.config.ConfigProperties;
import com.podcast.antennapod.old.abonnement.Episode;
import com.podcast.antennapod.old.abonnement.Podcast;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLQuery {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        logger.info("Hello World !");
        logger.trace("Entering method processOrder().");
        logger.debug("Received order with ID 12345.");
        logger.info("Order shipped successfully.");
        logger.warn("Potential security vulnerability detected in user input: '...'");
        logger.error("Failed to process order. Error: {. . .}");
        logger.fatal("System crashed. Shutting down...");
        System.out.println(getPodcast());

        ConfigProperties configProperties = ConfigProperties.getInstance();

        logger.info(configProperties.getProperty("page.name"));
        String page_name = configProperties.getProperty("page.name");
        String[]  page = page_name.split(",");

        for(String s : page) {
            logger.info(configProperties.getProperty("page."+s+".name"));

            logger.info(configProperties.getProperty("page."+s+".icon"));
        }


    }

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
