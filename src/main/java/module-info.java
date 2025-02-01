module com.podcast.antennapod {
    requires javafx.controls;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires java.net.http;
    requires org.dom4j;

    exports com.podcast.antennapod;
    exports com.podcast.antennapod.view;
    exports com.podcast.antennapod.test;
}