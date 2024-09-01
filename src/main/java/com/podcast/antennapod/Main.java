package com.podcast.antennapod;

import com.podcast.antennapod.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        logger.info("Start application");
        MainView.main(args);
        logger.info("Close application");
    }
}