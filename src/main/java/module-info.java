module com.podcast.antennapod {
    requires org.apache.logging.log4j;
    requires org.dom4j;
    requires com.fasterxml.jackson.databind;
    requires com.apptasticsoftware.rssreader;
    requires java.net.http;
    requires org.kordamp.ikonli.javafx;
    requires javafx.controls;
    requires org.kordamp.ikonli.materialdesign2;
    requires java.sql;

    exports com.podcast.antennapod;
    exports com.podcast.antennapod.view;
    exports com.podcast.antennapod.test;
    exports com.podcast.antennapod.test.opml;
    exports com.podcast.antennapod.test.gppoder;
}