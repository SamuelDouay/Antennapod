package com.podcast.antennapod.util.rss;

import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;
import com.apptasticsoftware.rssreader.module.itunes.ItunesRssReader;
import com.podcast.antennapod.util.MainUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class RssReader {
    private static final Logger logger = LogManager.getLogger(RssReader.class);

    public static void main(String[] args) {
        String fileFeed = "/tmp/ex_01022025_feed.xml";

        try (InputStream inputStream = MainUtil.class.getResourceAsStream(fileFeed)) {
            if (inputStream == null) {
                logger.error("Could not find resource: {}", fileFeed);
            } else {
                ItunesRssReader itunesRssReader = new ItunesRssReader();

                List<ItunesItem> list1 = itunesRssReader.read(inputStream).toList();

                for(ItunesItem item : list1) {
                    logger.info("{}|{}|{}|{}|", item.getTitle(), item.getItunesDuration(), item.getPubDate(), item.getDescription());
                }
            }
        } catch (Exception e) {
            logger.error("Error reading OPML file: {}", e.getMessage());
        }
    }
}
