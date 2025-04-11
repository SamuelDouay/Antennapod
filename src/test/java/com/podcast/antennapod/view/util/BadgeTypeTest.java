package com.podcast.antennapod.view.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

import static org.junit.jupiter.api.Assertions.*;

 @DisplayName("Badge Type test")
class BadgeTypeTest {

    @Test
    void testGreenBadgeProperties() {
        BadgeType badge = BadgeType.GREEN;
        assertEquals("Green", badge.getTypeName());
        assertEquals(ColorThemeConstants.getLightIc07(), badge.getBackgroundColor());
        assertEquals(ColorThemeConstants.getLightAt06(), badge.getTextColor());
    }

    @Test
    void testRedBadgeProperties() {
        BadgeType badge = BadgeType.RED;
        assertEquals("Red", badge.getTypeName());
        assertEquals(Color.hsb(1.0, 0.24, 1, 1), badge.getBackgroundColor());
        assertEquals(Color.hsb(2.0, 1, 0.62, 0.7294), badge.getTextColor());
    }

    @Test
    void testBlueBadgeProperties() {
        BadgeType badge = BadgeType.BLUE;
        assertEquals("Blue", badge.getTypeName());
        assertEquals(Color.hsb(233.0, 0.16, 1, 1), badge.getBackgroundColor());
        assertEquals(Color.hsb(251.0, 0.787, 0.98, 1), badge.getTextColor());
    }

    @Test
    void testPurpleBadgeProperties() {
        BadgeType badge = BadgeType.PURPLE;
        assertEquals("Purple", badge.getTypeName());
        assertEquals(Color.hsb(251.0, 0.16, 1, 1), badge.getBackgroundColor());
        assertEquals(Color.hsb(255.0, 0.48, 0.64, 1), badge.getTextColor());
    }

    @Test
    void testEnumValues() {
        BadgeType[] values = BadgeType.values();
        assertEquals(4, values.length);
        assertArrayEquals(new BadgeType[] {
                BadgeType.GREEN,
                BadgeType.RED,
                BadgeType.BLUE,
                BadgeType.PURPLE
        }, values);
    }

    @Test
    void testValueOf() {
        assertEquals(BadgeType.GREEN, BadgeType.valueOf("GREEN"));
        assertEquals(BadgeType.RED, BadgeType.valueOf("RED"));
        assertEquals(BadgeType.BLUE, BadgeType.valueOf("BLUE"));
        assertEquals(BadgeType.PURPLE, BadgeType.valueOf("PURPLE"));
    }

    @Test
    void testValueOfThrowsExceptionForInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> BadgeType.valueOf("YELLOW"));
    }
}