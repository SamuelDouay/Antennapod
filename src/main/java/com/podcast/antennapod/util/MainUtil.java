package com.podcast.antennapod.util;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.podcast.antennapod.util.opml.ItemOpml;
import com.podcast.antennapod.util.opml.OpmlReader;
import com.podcast.antennapod.util.opml.OpmlWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class MainUtil {
    private static final Logger logger = LogManager.getLogger(MainUtil.class);

    public static void main(String[] args) {

        String filePath = "/tmp/antennapod-feeds-2024-06-29.opml";
        List<ItemOpml> list = OpmlReader.read(filePath);
        logger.info("Found {} OPML items", list.size());
        for (ItemOpml e : list) {
            logger.info(e);
        }

        OpmlWriter.writeOpml(list);



        String fileFeed = "/tmp/ex_01022025_feed.xml";

        try (InputStream inputStream = MainUtil.class.getResourceAsStream(fileFeed)) {
            if (inputStream == null) {
                logger.error("Could not find resource: {}", fileFeed);
            } else {
                RssReader rssReader = new RssReader();

                List<Item> list1 = rssReader.read(inputStream).toList();

                for(Item item : list1) {
                    logger.info(item.getTitle());
                    logger.info(item.getDescription());
                }
            }
        } catch (Exception e) {
            logger.error("Error reading OPML file: {}", e.getMessage());
        }
    }
}
