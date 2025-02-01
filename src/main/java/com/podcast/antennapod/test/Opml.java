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
                logger.info( attribute.attribute("text").getValue() +" | " +attribute.attribute("xmlUrl").getValue());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            document = saxReader.read("https://feeds.acast.com/public/shows/5e6a404cd22bfc26784b114c");

            List<Node> list = document.selectNodes("//channel/title");

            for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
                DefaultElement element = (DefaultElement) iter.next();
                logger.info(element + " | " + element.getName() + " | " + element.getText());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            document = saxReader.read("https://feeds.acast.com/public/shows/5e6a404cd22bfc26784b114c");

            List<Node> list = document.selectNodes("//channel/item/title");

            for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
                DefaultElement element = (DefaultElement) iter.next();
                logger.info(element + " | " + element.getName() + " | " + element.getText());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
