package com.podcast.antennapod.util.opml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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

    private static void writeOpml(List<ItemOpml> list) {
        Document xml = DocumentHelper.createDocument();
        xml.setXMLEncoding("UTF-8");
        Element opml = xml.addElement("opml");
        Element header = opml.addElement("head");
        Element body = opml.addElement("body");

        opml.addAttribute("version", "2.0");
        addHeader(header);

        for(ItemOpml itemOpml : list) {
            addOutline(body, itemOpml);
        }

        logger.info(xml.asXML());


        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(System.out, format);
            writer.write( xml );
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void addHeader(Element head) {
        head.addElement("title").addText("AntennaPod Subscriptions");
        head.addElement("dateCreated").addText("29 Jun 24 14:45:48 +0200");
    }

    private static void addOutline(Element body, ItemOpml itemOpml) {
        body.addElement("outline")
                .addAttribute("text", itemOpml.getText())
                .addAttribute("title", itemOpml.getText())
                .addAttribute("type", itemOpml.getType())
                .addAttribute("xmlUrl", itemOpml.getXmlUrl())
                .addAttribute("htmlUrl", itemOpml.getHtmlUrl());
    }

    public static void main(String[] args) {
        String filePath = "/tmp/antennapod-feeds-2024-06-29.opml";
        List<ItemOpml> list = OpmlReader.read(filePath);
        logger.info("Found {} OPML items", list.size());
        for (ItemOpml e : list) {
            logger.info(e);
        }

        writeOpml(list);
    }
}