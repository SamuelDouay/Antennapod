package com.podcast.antennapod;

import com.podcast.antennapod.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Start application");
        try {
            MainView.main(args);
        } catch (Exception e) {
            logger.error("Une erreur est survenue lors de l'exécution", e);
            System.exit(1);  // Quitte l'application avec un code d'erreur
        } finally {
            logger.info("Close application");
        }
    }
}