package com.podcast.antennapod.util.opml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OpmlReader {
    private static final Logger logger = LogManager.getLogger(OpmlReader.class);
    private static final String FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    private static final boolean FEATURE_BOOL = true;
    private static final String OUTLINE = "//body/outline";

    public static List<ItemOpml> read() {
        try {
            List<ItemOpml> itemOpmls = new ArrayList<>();

            SAXReader saxReader = new SAXReader();
            saxReader.setFeature(FEATURE, FEATURE_BOOL);
            Document document = saxReader.read(OpmlReader.class.getResourceAsStream("/tmp/antennapod-feeds-2024-06-29.opml"));

            List<Node> list = document.selectNodes(OUTLINE);

            for (Node node : list) {
                DefaultElement attribute = (DefaultElement) node;
                String title = attribute.attribute("text").getValue();
                String type = attribute.attribute("type").getValue();
                String xmlUrl = attribute.attribute("xmlUrl").getValue();
                String htmlUrl = attribute.attribute("htmlUrl").getValue();


                itemOpmls.add(new ItemOpml(title, type, xmlUrl, htmlUrl));
            }

            return itemOpmls;

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        logger.info(OpmlReader.read());
    }

}
