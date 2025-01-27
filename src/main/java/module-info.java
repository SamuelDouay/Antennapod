module com.podcast.antennapod {
    requires javafx.controls;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires java.net.http;

    exports com.podcast.antennapod;
    exports com.podcast.antennapod.view;
}