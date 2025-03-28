package com.podcast.antennapod.util.opml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OpmlReader {
    private static final Logger logger = LogManager.getLogger(OpmlReader.class);
    private static final String OUTLINE = "//body/outline";

    public static List<ItemOpml> read(String filePath) {
        List<ItemOpml> itemOpmls = new ArrayList<>();

        Document document = getDocument(filePath);
        if (document == null) {
            logger.error("Failed to parse OPML document");
            return itemOpmls;
        }

        List<Node> list = document.selectNodes(OUTLINE);

        for (Node node : list) {
            if (node instanceof DefaultElement element) {
                ItemOpml item = extractElement(element);
                if (item != null) {
                    itemOpmls.add(item);
                }
            }
        }

        return itemOpmls;
    }

    private static Document getDocument(String filePath) {
        try (InputStream inputStream = OpmlReader.class.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                logger.error("Could not find resource: {}", filePath);
                return null;
            }

            SAXReader saxReader = new SAXReader();
            saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            return saxReader.read(inputStream);
        } catch (Exception e) {
            logger.error("Error reading OPML file: {}", e.getMessage());
            return null;
        }
    }

    private static ItemOpml extractElement(DefaultElement attribute) {
        try {
            String text = attribute.attributeValue("text", "");
            String title = attribute.attributeValue("title", "");
            String type = attribute.attributeValue("type", "");
            String xmlUrl = attribute.attributeValue("xmlUrl", "");
            String htmlUrl = attribute.attributeValue("htmlUrl", "");

            // Vérification des attributs obligatoires
            if (xmlUrl.isEmpty()) {
                logger.warn("Missing required attribute 'xmlUrl' in element");
                return null;
            }

            return new ItemOpml(text, title, type, xmlUrl, htmlUrl);
        } catch (Exception e) {
            logger.error("Error extracting element attributes: {}", e.getMessage());
            return null;
        }
    }
}