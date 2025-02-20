package com.podcast.antennapod.util.opml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.util.ArrayList;
import java.util.List;

public class OpmlReader {
    private static final Logger logger = LogManager.getLogger(OpmlReader.class);
    private static final String OUTLINE = "//body/outline";

    public static List<ItemOpml> read() {
        List<ItemOpml> itemOpmls = new ArrayList<>();
        try {

            SAXReader saxReader = new SAXReader();
            saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document document = saxReader.read(OpmlReader.class.getResourceAsStream("/tmp/antennapod-feeds-2024-06-29.opml"));

            List<Node> list = document.selectNodes(OUTLINE);

            for (Node node : list) {

                DefaultElement attribute = (DefaultElement) node;
                String text = attribute.attribute("text").getValue();
                String title = attribute.attribute("title").getValue();
                String type = attribute.attribute("type").getValue();
                String xmlUrl = attribute.attribute("xmlUrl").getValue();
                String htmlUrl = attribute.attribute("htmlUrl").getValue();
                itemOpmls.add(new ItemOpml(text, title, type, xmlUrl, htmlUrl));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return itemOpmls;
    }

    public static void main(String[] args) {
        List<ItemOpml> list = OpmlReader.read();
        for(ItemOpml e : list) {
            logger.info(e);
        }
    }

}
