package com.podcast.antennapod.test.rss;

import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;
import com.apptasticsoftware.rssreader.module.itunes.ItunesRssReader;
import com.podcast.antennapod.test.MainUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class RssReader {
    private static final Logger logger = LogManager.getLogger(RssReader.class);

    private RssReader() {

    }

    public static void main(String[] args) {
        String fileFeed = "/tmp/ex_01022025_feed.xml";

        List<FeedItem> feedItems = RssReader.readRssFeed(fileFeed);

        for (FeedItem feedItem : feedItems) {
            logger.info(feedItem);
        }


    }

    public static List<FeedItem> readRssFeed(String filePath) {
        try (InputStream inputStream = MainUtil.class.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                logger.error("Could not find resource: {}", filePath);
                return List.of();
            } else {
                ItunesRssReader itunesRssReader = new ItunesRssReader();

                List<ItunesItem> items = itunesRssReader.read(inputStream).toList();

                return items.stream()
                        .map(RssReader::getFeedItem)
                        .toList();
            }
        } catch (Exception e) {
            logger.error("Error reading RSS file: {}", e.getMessage());
            return List.of();
        }
    }

    private static FeedItem getFeedItem(ItunesItem item) {
        String title = item.getTitle().orElse("");
        String duration = item.getItunesDuration().orElse("");
        String pubDate = item.getPubDate().orElse("");
        String description = item.getDescription().orElse("");
        return new FeedItem(title, duration, pubDate, description);
    }
}
