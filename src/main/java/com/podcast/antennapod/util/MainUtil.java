package com.podcast.antennapod.util;

import com.podcast.antennapod.util.opml.ItemOpml;
import com.podcast.antennapod.util.opml.OpmlReader;
import com.podcast.antennapod.util.opml.OpmlWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    }
}
