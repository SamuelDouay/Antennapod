package com.podcast.antennapod.util.opml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.util.Iterator;
import java.util.List;

public class OpmlReader {
    private static final Logger logger = LogManager.getLogger(OpmlReader.class);

    public static void main(String[] args) {
        Document document;

        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            document = saxReader.read(OpmlReader.class.getResourceAsStream("/tmp/antennapod-feeds-2024-06-29.opml"));

            List<Node> list = document.selectNodes("//body/outline");

            for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
                DefaultElement attribute = (DefaultElement) iter.next();
                String title = attribute.attribute("text").getValue();
                String type = attribute.attribute("type").getValue();
                String xmlUrl = attribute.attribute("xmlUrl").getValue();
                String htmlUrl = attribute.attribute("htmlUrl").getValue();
                logger.info(new ItemOpml(title, type, xmlUrl, htmlUrl));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
