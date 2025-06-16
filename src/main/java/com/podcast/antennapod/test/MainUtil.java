package com.podcast.antennapod.test;

import com.podcast.antennapod.test.opml.ItemOpml;
import com.podcast.antennapod.test.opml.OpmlReader;
import com.podcast.antennapod.test.opml.OpmlWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MainUtil {
    private static final Logger LOGGER = LogManager.getLogger(MainUtil.class);
    private static final String FILE = "/tmp/antennapod-feeds-2024-06-29.opml";

    public static void main(String[] args) {
        List<ItemOpml> list = OpmlReader.read(FILE);
        LOGGER.info("Found {} OPML items", list.size());
        for (ItemOpml e : list) {
            LOGGER.info(e);
        }

        OpmlWriter.writeOpml(list);
    }
}
