package com.podcast.antennapod;

import com.podcast.antennapod.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private final static Logger logger = LogManager.getLogger();

    // test
    public static void main(String[] args) {
        try {
            logger.info("Start application");
            MainView.main(args);
            logger.info("Close application");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}