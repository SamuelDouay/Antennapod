module com.podcast.antennapod {
    requires javafx.controls;
    requires java.sql;
    requires org.apache.logging.log4j;

    exports com.podcast.antennapod;
    exports com.podcast.antennapod.view;
    exports com.podcast.antennapod.old.navigation;
    exports com.podcast.antennapod.old.controller;
    exports com.podcast.antennapod.old;
}