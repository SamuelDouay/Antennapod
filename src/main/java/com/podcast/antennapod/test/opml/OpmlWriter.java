package com.podcast.antennapod.test.opml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * Utilitaire pour l'exportation de données au format OPML.
 * Cette classe permet de générer des fichiers OPML à partir d'une liste d'éléments ItemOpml.
 */
public final class OpmlWriter {
    private static final Logger LOGGER = LogManager.getLogger(OpmlWriter.class);
    private static final String OPML_VERSION = "2.0";
    private static final String ENCODING = "UTF-8";
    private static final String TITLE = "AntennaPod Subscriptions";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yy HH:mm:ss Z");

    private OpmlWriter() {
        // Constructeur privé pour empêcher l'instanciation
    }

    /**
     * Écrit le contenu OPML dans le flux de sortie standard.
     *
     * @param items Liste d'éléments à inclure dans le fichier OPML
     * @throws OpmlWriterException si une erreur survient lors de l'écriture
     */
    public static void writeOpml(List<ItemOpml> items) {
        writeOpml(items, System.out);
    }

    /**
     * Écrit le contenu OPML dans le flux de sortie spécifié.
     *
     * @param items        Liste d'éléments à inclure dans le fichier OPML
     * @param outputStream Flux de sortie où écrire le contenu
     * @throws OpmlWriterException si une erreur survient lors de l'écriture
     */
    public static void writeOpml(List<ItemOpml> items, OutputStream outputStream) {
        Objects.requireNonNull(items, "La liste d'items ne peut pas être null");
        Objects.requireNonNull(outputStream, "Le flux de sortie ne peut pas être null");

        Document document = createDocument(items);
        LOGGER.debug("Document OPML généré: {}", document.asXML());

        writeDocumentToStream(document, outputStream);
    }

    /**
     * Crée le document OPML à partir de la liste d'éléments.
     *
     * @param items Liste d'éléments à inclure dans le document
     * @return Le document OPML généré
     */
    private static Document createDocument(List<ItemOpml> items) {
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding(ENCODING);

        Element opmlElement = document.addElement("opml");
        opmlElement.addAttribute("version", OPML_VERSION);

        Element headerElement = opmlElement.addElement("head");
        addHeader(headerElement);

        Element bodyElement = opmlElement.addElement("body");
        items.forEach(item -> addOutline(bodyElement, item));

        return document;
    }

    /**
     * Ajoute les éléments d'en-tête au document OPML.
     *
     * @param headerElement Élément d'en-tête à compléter
     */
    private static void addHeader(Element headerElement) {
        headerElement.addElement("title").addText(TITLE);

        // Utilisation de l'API de date moderne au lieu d'une date codée en dur
        String currentDate = ZonedDateTime.now().format(DATE_FORMATTER);
        headerElement.addElement("dateCreated").addText(currentDate);
    }

    /**
     * Ajoute un élément outline au document OPML.
     *
     * @param bodyElement Élément body parent
     * @param item        Item OPML à ajouter
     */
    private static void addOutline(Element bodyElement, ItemOpml item) {
        Element outlineElement = bodyElement.addElement("outline");
        outlineElement.addAttribute("text", item.getText());
        outlineElement.addAttribute("title", item.getText());
        outlineElement.addAttribute("type", item.getType());

        if (item.getXmlUrl() != null) {
            outlineElement.addAttribute("xmlUrl", item.getXmlUrl());
        }

        if (item.getHtmlUrl() != null) {
            outlineElement.addAttribute("htmlUrl", item.getHtmlUrl());
        }
    }

    /**
     * Écrit le document dans le flux de sortie.
     *
     * @param document     Document à écrire
     * @param outputStream Flux de sortie où écrire le document
     * @throws OpmlWriterException si une erreur survient lors de l'écriture
     */
    private static void writeDocumentToStream(Document document, OutputStream outputStream) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(ENCODING);

        XMLWriter writer = null;
        try {
            writer = new XMLWriter(outputStream, format);
            writer.write(document);
        } catch (IOException e) {
            LOGGER.error("Erreur lors de l'écriture du document OPML", e);
            throw new OpmlWriterException("Impossible d'écrire le document OPML", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    LOGGER.warn("Erreur lors de la fermeture du XMLWriter", e);
                }
            }
        }
    }

    /**
     * Exception spécifique pour les erreurs d'écriture OPML.
     */
    public static class OpmlWriterException extends RuntimeException {
        public OpmlWriterException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}