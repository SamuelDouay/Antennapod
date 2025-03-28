package com.podcast.antennapod.util.opml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.util.List;

public class OpmlWriter {
    private static final Logger logger = LogManager.getLogger(OpmlWriter.class);

    public static void writeOpml(List<ItemOpml> list) {
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
        try {
            XMLWriter writer = new XMLWriter(System.out, format);
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
}
