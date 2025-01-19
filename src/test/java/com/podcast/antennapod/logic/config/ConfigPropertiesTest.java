package com.podcast.antennapod.logic.config;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class Config Properties")
public class ConfigPropertiesTest {
    private static ConfigProperties configProperties;

    @BeforeAll
    public static void init() {
        configProperties = ConfigProperties.getInstance();
    }

    @Test
    @DisplayName("Test Database Config")
    void firstTest() {
        assertEquals("org.sqlite.JDBC", configProperties.getProperty("jdbc.pilote"), "Pilote jdbc");
    }
}
