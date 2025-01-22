package com.podcast.antennapod.logic.database;

import com.podcast.antennapod.logic.config.ConfigProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Connect {
    private static Connect instance;
    private Connection connection;
    private static final Logger logger = LogManager.getLogger();

    private Connect(){
        try {
            this.connection = DriverManager.getConnection(ConfigProperties.getInstance().getProperty("jdbc.database"));
        } catch ( Exception e ) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
            System.exit(0);
        }
        logger.info("Opened database successfully");

    }

    public static Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        Statement statement = null;
        
        try {
            statement = this.connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
            logger.info("Requête exécutée avec succès : {}", query);
            return resultSet;
    
        } catch (SQLException e) {
            logger.error("Erreur d'exécution de la requête : {} - {}", e.getClass().getName(), e.getMessage());
            throw new RuntimeException("Erreur lors de l'exécution de la requête SQL", e);
    
        } catch (Exception e) {
            logger.error("Erreur inattendue : {} - {}", e.getClass().getName(), e.getMessage());
            throw new RuntimeException("Erreur inattendue lors de l'exécution de la requête SQL", e);
    
        } finally {
            // Si l'exécution échoue, on ferme les ressources
            if (resultSet != null) {
                try {
                    resultSet.close();
                    logger.debug("ResultSet fermé avec succès");
                } catch (SQLException e) {
                    logger.error("Erreur lors de la fermeture du ResultSet : {}", e.getMessage());
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                    logger.debug("Statement fermé avec succès");
                } catch (SQLException e) {
                    logger.error("Erreur lors de la fermeture du Statement : {}", e.getMessage());
                }
            }
            // Si l'exécution réussit, le ResultSet et le Statement doivent rester ouverts
            // pour être utilisés par l'appelant
        }
    }
}
