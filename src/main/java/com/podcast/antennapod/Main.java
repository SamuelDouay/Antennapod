package com.podcast.antennapod;

import com.podcast.antennapod.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.now();
        
        logger.info("=== Démarrage de l'application AntennaPod ===");
        logger.info("Heure de démarrage: {}", 
            startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        
        if (args.length > 0) {
            logger.info("Arguments de démarrage reçus: {}", String.join(", ", args));
        }
        
        try {

            MainView.main(args);
            

            
        } catch (Exception e) {
            logger.error("Erreur fatale lors de l'initialisation de l'application", e);
            logger.error("Détails de l'erreur: {}", e.getMessage());
            
            if (e.getCause() != null) {
                logger.error("Cause racine: {}", e.getCause().getMessage());
            }
            
            logger.info("Arrêt de l'application suite à une erreur");
            System.exit(1);
        } finally {
            LocalDateTime endTime = LocalDateTime.now();
            logger.info("Durée d'exécution: {} secondes", 
                (endTime.toEpochSecond(java.time.ZoneOffset.UTC) - 
                 startTime.toEpochSecond(java.time.ZoneOffset.UTC)));
            logger.info("=== Fermeture de l'application AntennaPod ===");
        }
    }
}