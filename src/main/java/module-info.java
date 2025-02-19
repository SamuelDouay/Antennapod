module com.podcast.antennapod {
    requires javafx.controls;
    requires org.apache.logging.log4j;
    requires java.net.http;
    requires org.dom4j;
    requires com.fasterxml.jackson.databind;

    exports com.podcast.antennapod;
    exports com.podcast.antennapod.view;
    exports com.podcast.antennapod.test;
    exports com.podcast.antennapod.util.opml;
    exports com.podcast.antennapod.test.gppoder;
}