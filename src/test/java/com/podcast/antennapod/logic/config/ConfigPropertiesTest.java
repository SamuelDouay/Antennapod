package com.podcast.antennapod.logic.config;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class Config Properties")
class ConfigPropertiesTest {
    private static ConfigProperties configProperties;

    @BeforeAll
    static void init() {
        configProperties = ConfigProperties.getInstance();
    }

    @Test
    @DisplayName("Properties")
    void configProperties() {
        assertEquals("org.sqlite.JDBC", configProperties.getProperty("jdbc.pilote"), "Pilote jdbc");
        assertEquals("#1A2B2C", configProperties.getProperty("light.theme.grey.200"), "Test style properties");
    }

    @Test
    @DisplayName("Properties null")
    void firstTest() {
        assertEquals(null, configProperties.getProperty("light"), "Null properties");
    }
}