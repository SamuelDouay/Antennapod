package com.podcast.antennapod.logic.database;

import com.podcast.antennapod.logic.config.ConfigProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

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
        ResultSet resultSet;
        try {
            resultSet =  this.connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Requête succes : %s".formatted(query));
        return resultSet;
    }
}
