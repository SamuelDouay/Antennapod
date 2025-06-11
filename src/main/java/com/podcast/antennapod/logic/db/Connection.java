package com.podcast.antennapod.logic.db;

public class Connection {
    private static Connection instance;

    Connection() {
        // no parameters
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }
}
