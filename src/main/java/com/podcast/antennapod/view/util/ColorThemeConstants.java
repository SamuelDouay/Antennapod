package com.podcast.antennapod.view.util;

import com.podcast.antennapod.logic.config.ConfigProperties;
import javafx.scene.paint.Color;

public class ColorThemeConstants {
    private static final ConfigProperties configProperties = ConfigProperties.getInstance();
    private ColorThemeConstants() {
        // Private constructor to prevent instantiation
    }
    public static Color getColor(String key) {
        String colorValue = configProperties.getProperty(key);
        if (colorValue != null && !colorValue.isEmpty()) {
            return Color.web(colorValue);
        }
        return null;
    }

    // Utility methods for accessing colors by their component names

    // Dark theme - interactive components
    public static Color getDarkIc01() {
        return getColor("dark.theme.ic.01");
    }

    public static Color getDarkIc02() {
        return getColor("dark.theme.ic.02");
    }

    public static Color getDarkIc03() {
        return getColor("dark.theme.ic.03");
    }

    public static Color getDarkIc04() {
        return getColor("dark.theme.ic.04");
    }

    public static Color getDarkIc05() {
        return getColor("dark.theme.ic.05");
    }

    public static Color getDarkIc06() {
        return getColor("dark.theme.ic.06");
    }

    public static Color getDarkIc07() {
        return getColor("dark.theme.ic.07");
    }

    public static Color getDarkIc08() {
        return getColor("dark.theme.ic.08");
    }

    public static Color getDarkIc09() {
        return getColor("dark.theme.ic.09");
    }

    public static Color getDarkIc10() {
        return getColor("dark.theme.ic.10");
    }

    public static Color getDarkIc11() {
        return getColor("dark.theme.ic.11");
    }

    public static Color getDarkIc12() {
        return getColor("dark.theme.ic.12");
    }

    // Light theme - interactive components
    public static Color getLightIc01() {
        return getColor("light.theme.ic.01");
    }

    public static Color getLightIc02() {
        return getColor("light.theme.ic.02");
    }

    public static Color getLightIc03() {
        return getColor("light.theme.ic.03");
    }

    public static Color getLightIc04() {
        return getColor("light.theme.ic.04");
    }

    public static Color getLightIc05() {
        return getColor("light.theme.ic.05");
    }

    public static Color getLightIc06() {
        return getColor("light.theme.ic.06");
    }

    public static Color getLightIc07() {
        return getColor("light.theme.ic.07");
    }

    public static Color getLightIc08() {
        return getColor("light.theme.ic.08");
    }

    public static Color getLightIc09() {
        return getColor("light.theme.ic.09");
    }

    public static Color getLightIc10() {
        return getColor("light.theme.ic.10");
    }

    public static Color getLightIc11() {
        return getColor("light.theme.ic.11");
    }

    public static Color getLightIc12() {
        return getColor("light.theme.ic.12");
    }

    // Dark theme - solid colors
    public static Color getDarkSc01() {
        return getColor("dark.theme.sc.01");
    }

    public static Color getDarkSc02() {
        return getColor("dark.theme.sc.02");
    }

    public static Color getDarkSc03() {
        return getColor("dark.theme.sc.03");
    }

    public static Color getDarkSc04() {
        return getColor("dark.theme.sc.04");
    }

    public static Color getDarkSc05() {
        return getColor("dark.theme.sc.05");
    }

    public static Color getDarkSc06() {
        return getColor("dark.theme.sc.06");
    }

    public static Color getDarkSc07() {
        return getColor("dark.theme.sc.07");
    }

    public static Color getDarkSc08() {
        return getColor("dark.theme.sc.08");
    }

    // Light theme - solid colors
    public static Color getLightCs01() {
        return getColor("light.theme.cs.01");
    }

    public static Color getLightCs02() {
        return getColor("light.theme.cs.02");
    }

    public static Color getLightCs03() {
        return getColor("light.theme.cs.03");
    }

    public static Color getLightCs04() {
        return getColor("light.theme.cs.04");
    }

    public static Color getLightCs05() {
        return getColor("light.theme.cs.05");
    }

    public static Color getLightCs06() {
        return getColor("light.theme.cs.06");
    }

    public static Color getLightCs07() {
        return getColor("light.theme.cs.07");
    }

    public static Color getLightCs08() {
        return getColor("light.theme.cs.08");
    }

    // Dark theme - border separators
    public static Color getDarkBs01() {
        return getColor("dark.theme.bs.01");
    }

    public static Color getDarkBs02() {
        return getColor("dark.theme.bs.02");
    }

    public static Color getDarkBs03() {
        return getColor("dark.theme.bs.03");
    }

    public static Color getDarkBs04() {
        return getColor("dark.theme.bs.04");
    }

    public static Color getDarkBs05() {
        return getColor("dark.theme.bs.05");
    }

    public static Color getDarkBs06() {
        return getColor("dark.theme.bs.06");
    }

    public static Color getDarkBs07() {
        return getColor("dark.theme.bs.07");
    }

    public static Color getDarkBs08() {
        return getColor("dark.theme.bs.08");
    }

    public static Color getDarkBs09() {
        return getColor("dark.theme.bs.09");
    }

    public static Color getDarkBs10() {
        return getColor("dark.theme.bs.10");
    }

    public static Color getDarkBs11() {
        return getColor("dark.theme.bs.11");
    }

    public static Color getDarkBs12() {
        return getColor("dark.theme.bs.12");
    }

    // Light theme - border separators
    public static Color getLightBs01() {
        return getColor("light.theme.bs.01");
    }

    public static Color getLightBs02() {
        return getColor("light.theme.bs.02");
    }

    public static Color getLightBs03() {
        return getColor("light.theme.bs.03");
    }

    public static Color getLightBs04() {
        return getColor("light.theme.bs.04");
    }

    public static Color getLightBs05() {
        return getColor("light.theme.bs.05");
    }

    public static Color getLightBs06() {
        return getColor("light.theme.bs.06");
    }

    public static Color getLightBs07() {
        return getColor("light.theme.bs.07");
    }

    public static Color getLightBs08() {
        return getColor("light.theme.bs.08");
    }

    public static Color getLightBs09() {
        return getColor("light.theme.bs.09");
    }

    public static Color getLightBs10() {
        return getColor("light.theme.bs.10");
    }

    public static Color getLightBs11() {
        return getColor("light.theme.bs.11");
    }

    public static Color getLightBs12() {
        return getColor("light.theme.bs.12");
    }

    // Dark theme - backgrounds
    public static Color getDarkBg01() {
        return getColor("dark.theme.bg.01");
    }

    public static Color getDarkBg02() {
        return getColor("dark.theme.bg.02");
    }

    public static Color getDarkBg03() {
        return getColor("dark.theme.bg.03");
    }

    public static Color getDarkBg04() {
        return getColor("dark.theme.bg.04");
    }

    public static Color getDarkBg05() {
        return getColor("dark.theme.bg.05");
    }

    public static Color getDarkBg06() {
        return getColor("dark.theme.bg.06");
    }

    public static Color getDarkBg07() {
        return getColor("dark.theme.bg.07");
    }

    public static Color getDarkBg08() {
        return getColor("dark.theme.bg.08");
    }

    // Light theme - backgrounds
    public static Color getLightBg01() {
        return getColor("light.theme.bg.01");
    }

    public static Color getLightBg02() {
        return getColor("light.theme.bg.02");
    }

    public static Color getLightBg03() {
        return getColor("light.theme.bg.03");
    }

    public static Color getLightBg04() {
        return getColor("light.theme.bg.04");
    }

    public static Color getLightBg05() {
        return getColor("light.theme.bg.05");
    }

    public static Color getLightBg06() {
        return getColor("light.theme.bg.06");
    }

    public static Color getLightBg07() {
        return getColor("light.theme.bg.07");
    }

    public static Color getLightBg08() {
        return getColor("light.theme.bg.08");
    }

    // Light theme - accessible text
    public static Color getLightAc01() {
        return getColor("light.theme.ac.01");
    }

    public static Color getLightAc02() {
        return getColor("light.theme.ac.02");
    }

    public static Color getLightAc03() {
        return getColor("light.theme.ac.03");
    }

    public static Color getLightAc04() {
        return getColor("light.theme.ac.04");
    }

    public static Color getLightAc05() {
        return getColor("light.theme.ac.05");
    }

    public static Color getLightAc06() {
        return getColor("light.theme.ac.06");
    }

    public static Color getLightAc07() {
        return getColor("light.theme.ac.07");
    }

    public static Color getLightAc08() {
        return getColor("light.theme.ac.08");
    }

    // Dark theme - accessible text
    public static Color getDarkAt01() {
        return getColor("dark.theme.at.01");
    }

    public static Color getDarkAt02() {
        return getColor("dark.theme.at.02");
    }

    public static Color getDarkAt03() {
        return getColor("dark.theme.at.03");
    }

    public static Color getDarkAt04() {
        return getColor("dark.theme.at.04");
    }

    public static Color getDarkAt05() {
        return getColor("dark.theme.at.05");
    }

    public static Color getDarkAt06() {
        return getColor("dark.theme.at.06");
    }

    public static Color getDarkAt07() {
        return getColor("dark.theme.at.07");
    }

    public static Color getDarkAt08() {
        return getColor("dark.theme.at.08");
    }
}