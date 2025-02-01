package com.podcast.antennapod.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.util.Iterator;
import java.util.List;

public class Opml {
    private static final Logger logger = LogManager.getLogger(Opml.class);

    public static void main(String[] args) {
        Document document;

        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            document = saxReader.read(Opml.class.getResourceAsStream("/tmp/antennapod-feeds-2024-06-29.opml"));

            List<Node> list = document.selectNodes("//body/outline");

            for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
                DefaultElement attribute = (DefaultElement) iter.next();
                logger.info(attribute.attribute("xmlUrl").getValue());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
