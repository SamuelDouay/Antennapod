package com.podcast.antennapod.view.util;

import com.podcast.antennapod.logic.config.ConfigProperties;
import javafx.scene.paint.Color;

public class ColorThemeConstants {
    private static final ConfigProperties configProperties = ConfigProperties.getInstance();

    // Default theme
    private static ThemeType currentTheme = ThemeType.LIGHT;

    private ColorThemeConstants() {
        // Private constructor to prevent instantiation
    }

    public static void setTheme(ThemeType theme) {
        currentTheme = theme;
    }

    public static ThemeType getCurrentTheme() {
        return currentTheme;
    }

    public static Color getColor(String key) {
        String colorValue = configProperties.getProperty(key);
        if (colorValue != null && !colorValue.isEmpty()) {
            return Color.web(colorValue);
        }
        return null;
    }

    public static Color getIc01() {
        return currentTheme == ThemeType.DARK ? getDarkIc01() : getLightIc01();
    }

    public static Color getIc02() {
        return currentTheme == ThemeType.DARK ? getDarkIc02() : getLightIc02();
    }

    public static Color getIc03() {
        return currentTheme == ThemeType.DARK ? getDarkIc03() : getLightIc03();
    }

    public static Color getIc04() {
        return currentTheme == ThemeType.DARK ? getDarkIc04() : getLightIc04();
    }

    public static Color getIc05() {
        return currentTheme == ThemeType.DARK ? getDarkIc05() : getLightIc05();
    }

    public static Color getIc06() {
        return currentTheme == ThemeType.DARK ? getDarkIc06() : getLightIc06();
    }

    public static Color getIc07() {
        return currentTheme == ThemeType.DARK ? getDarkIc07() : getLightIc07();
    }

    public static Color getIc08() {
        return currentTheme == ThemeType.DARK ? getDarkIc08() : getLightIc08();
    }

    public static Color getIc09() {
        return currentTheme == ThemeType.DARK ? getDarkIc09() : getLightIc09();
    }

    public static Color getIc10() {
        return currentTheme == ThemeType.DARK ? getDarkIc10() : getLightIc10();
    }

    public static Color getIc11() {
        return currentTheme == ThemeType.DARK ? getDarkIc11() : getLightIc11();
    }

    public static Color getIc12() {
        return currentTheme == ThemeType.DARK ? getDarkIc12() : getLightIc12();
    }

    // Solid colors (SC/CS)
    public static Color getCs01() {
        return currentTheme == ThemeType.DARK ? getDarKCs01() : getLightCs01();
    }

    public static Color getCs02() {
        return currentTheme == ThemeType.DARK ? getDarKCs02() : getLightCs02();
    }

    public static Color getCs03() {
        return currentTheme == ThemeType.DARK ? getDarKCs03() : getLightCs03();
    }

    public static Color getCs04() {
        return currentTheme == ThemeType.DARK ? getDarKCs04() : getLightCs04();
    }

    public static Color getCs05() {
        return currentTheme == ThemeType.DARK ? getDarKCs05() : getLightCs05();
    }

    public static Color getCs06() {
        return currentTheme == ThemeType.DARK ? getDarKCs06() : getLightCs06();
    }

    public static Color getCs07() {
        return currentTheme == ThemeType.DARK ? getDarKCs07() : getLightCs07();
    }

    public static Color getCs08() {
        return currentTheme == ThemeType.DARK ? getDarKCs08() : getLightCs08();
    }

    // Border separators (BS)
    public static Color getBs01() {
        return currentTheme == ThemeType.DARK ? getDarkBs01() : getLightBs01();
    }

    public static Color getBs02() {
        return currentTheme == ThemeType.DARK ? getDarkBs02() : getLightBs02();
    }

    public static Color getBs03() {
        return currentTheme == ThemeType.DARK ? getDarkBs03() : getLightBs03();
    }

    public static Color getBs04() {
        return currentTheme == ThemeType.DARK ? getDarkBs04() : getLightBs04();
    }

    public static Color getBs05() {
        return currentTheme == ThemeType.DARK ? getDarkBs05() : getLightBs05();
    }

    public static Color getBs06() {
        return currentTheme == ThemeType.DARK ? getDarkBs06() : getLightBs06();
    }

    public static Color getBs07() {
        return currentTheme == ThemeType.DARK ? getDarkBs07() : getLightBs07();
    }

    public static Color getBs08() {
        return currentTheme == ThemeType.DARK ? getDarkBs08() : getLightBs08();
    }

    public static Color getBs09() {
        return currentTheme == ThemeType.DARK ? getDarkBs09() : getLightBs09();
    }

    public static Color getBs10() {
        return currentTheme == ThemeType.DARK ? getDarkBs10() : getLightBs10();
    }

    public static Color getBs11() {
        return currentTheme == ThemeType.DARK ? getDarkBs11() : getLightBs11();
    }

    public static Color getBs12() {
        return currentTheme == ThemeType.DARK ? getDarkBs12() : getLightBs12();
    }

    // Backgrounds (BG)
    public static Color getBg01() {
        return currentTheme == ThemeType.DARK ? getDarkBg01() : getLightBg01();
    }

    public static Color getBg02() {
        return currentTheme == ThemeType.DARK ? getDarkBg02() : getLightBg02();
    }

    public static Color getBg03() {
        return currentTheme == ThemeType.DARK ? getDarkBg03() : getLightBg03();
    }

    public static Color getBg04() {
        return currentTheme == ThemeType.DARK ? getDarkBg04() : getLightBg04();
    }

    public static Color getBg05() {
        return currentTheme == ThemeType.DARK ? getDarkBg05() : getLightBg05();
    }

    public static Color getBg06() {
        return currentTheme == ThemeType.DARK ? getDarkBg06() : getLightBg06();
    }

    public static Color getBg07() {
        return currentTheme == ThemeType.DARK ? getDarkBg07() : getLightBg07();
    }

    public static Color getBg08() {
        return currentTheme == ThemeType.DARK ? getDarkBg08() : getLightBg08();
    }

    // Accessible text (AT/AC)
    public static Color getAt01() {
        return currentTheme == ThemeType.DARK ? getDarkAt01() : getLightAt01();
    }

    public static Color getAt02() {
        return currentTheme == ThemeType.DARK ? getDarkAt02() : getLightAt02();
    }

    public static Color getAt03() {
        return currentTheme == ThemeType.DARK ? getDarkAt03() : getLightAt03();
    }

    public static Color getAt04() {
        return currentTheme == ThemeType.DARK ? getDarkAt04() : getLightAt04();
    }

    public static Color getAt05() {
        return currentTheme == ThemeType.DARK ? getDarkAt05() : getLightAt05();
    }

    public static Color getAt06() {
        return currentTheme == ThemeType.DARK ? getDarkAt06() : getLightAt06();
    }

    public static Color getAt07() {
        return currentTheme == ThemeType.DARK ? getDarkAt07() : getLightAt07();
    }

    public static Color getAt08() {
        return currentTheme == ThemeType.DARK ? getDarkAt08() : getLightAt08();
    }

    // Utility methods for accessing colors by their component names

    // Dark theme - interactive components
    private static Color getDarkIc01() {
        return getColor("dark.theme.ic.01");
    }

    private static Color getDarkIc02() {
        return getColor("dark.theme.ic.02");
    }

    private static Color getDarkIc03() {
        return getColor("dark.theme.ic.03");
    }

    private static Color getDarkIc04() {
        return getColor("dark.theme.ic.04");
    }

    private static Color getDarkIc05() {
        return getColor("dark.theme.ic.05");
    }

    private static Color getDarkIc06() {
        return getColor("dark.theme.ic.06");
    }

    private static Color getDarkIc07() {
        return getColor("dark.theme.ic.07");
    }

    private static Color getDarkIc08() {
        return getColor("dark.theme.ic.08");
    }

    private static Color getDarkIc09() {
        return getColor("dark.theme.ic.09");
    }

    private static Color getDarkIc10() {
        return getColor("dark.theme.ic.10");
    }

    private static Color getDarkIc11() {
        return getColor("dark.theme.ic.11");
    }

    private static Color getDarkIc12() {
        return getColor("dark.theme.ic.12");
    }

    // Light theme - interactive components
    private static Color getLightIc01() {
        return getColor("light.theme.ic.01");
    }

    private static Color getLightIc02() {
        return getColor("light.theme.ic.02");
    }

    private static Color getLightIc03() {
        return getColor("light.theme.ic.03");
    }

    private static Color getLightIc04() {
        return getColor("light.theme.ic.04");
    }

    private static Color getLightIc05() {
        return getColor("light.theme.ic.05");
    }

    private static Color getLightIc06() {
        return getColor("light.theme.ic.06");
    }

    private static Color getLightIc07() {
        return getColor("light.theme.ic.07");
    }

    private static Color getLightIc08() {
        return getColor("light.theme.ic.08");
    }

    private static Color getLightIc09() {
        return getColor("light.theme.ic.09");
    }

    private static Color getLightIc10() {
        return getColor("light.theme.ic.10");
    }

    private static Color getLightIc11() {
        return getColor("light.theme.ic.11");
    }

    private static Color getLightIc12() {
        return getColor("light.theme.ic.12");
    }

    // Dark theme - solid colors
    private static Color getDarKCs01() {
        return getColor("dark.theme.cs.01");
    }

    private static Color getDarKCs02() {
        return getColor("dark.theme.cs.02");
    }

    private static Color getDarKCs03() {
        return getColor("dark.theme.cs.03");
    }

    private static Color getDarKCs04() {
        return getColor("dark.theme.cs.04");
    }

    private static Color getDarKCs05() {
        return getColor("dark.theme.cs.05");
    }

    private static Color getDarKCs06() {
        return getColor("dark.theme.cs.06");
    }

    private static Color getDarKCs07() {
        return getColor("dark.theme.cs.07");
    }

    private static Color getDarKCs08() {
        return getColor("dark.theme.cs.08");
    }

    // Light theme - solid colors
    private static Color getLightCs01() {
        return getColor("light.theme.cs.01");
    }

    private static Color getLightCs02() {
        return getColor("light.theme.cs.02");
    }

    private static Color getLightCs03() {
        return getColor("light.theme.cs.03");
    }

    private static Color getLightCs04() {
        return getColor("light.theme.cs.04");
    }

    private static Color getLightCs05() {
        return getColor("light.theme.cs.05");
    }

    private static Color getLightCs06() {
        return getColor("light.theme.cs.06");
    }

    private static Color getLightCs07() {
        return getColor("light.theme.cs.07");
    }

    private static Color getLightCs08() {
        return getColor("light.theme.cs.08");
    }

    // Dark theme - border separators
    private static Color getDarkBs01() {
        return getColor("dark.theme.bs.01");
    }

    private static Color getDarkBs02() {
        return getColor("dark.theme.bs.02");
    }

    private static Color getDarkBs03() {
        return getColor("dark.theme.bs.03");
    }

    private static Color getDarkBs04() {
        return getColor("dark.theme.bs.04");
    }

    private static Color getDarkBs05() {
        return getColor("dark.theme.bs.05");
    }

    private static Color getDarkBs06() {
        return getColor("dark.theme.bs.06");
    }

    private static Color getDarkBs07() {
        return getColor("dark.theme.bs.07");
    }

    private static Color getDarkBs08() {
        return getColor("dark.theme.bs.08");
    }

    private static Color getDarkBs09() {
        return getColor("dark.theme.bs.09");
    }

    private static Color getDarkBs10() {
        return getColor("dark.theme.bs.10");
    }

    private static Color getDarkBs11() {
        return getColor("dark.theme.bs.11");
    }

    private static Color getDarkBs12() {
        return getColor("dark.theme.bs.12");
    }

    // Light theme - border separators
    private static Color getLightBs01() {
        return getColor("light.theme.bs.01");
    }

    private static Color getLightBs02() {
        return getColor("light.theme.bs.02");
    }

    private static Color getLightBs03() {
        return getColor("light.theme.bs.03");
    }

    private static Color getLightBs04() {
        return getColor("light.theme.bs.04");
    }

    private static Color getLightBs05() {
        return getColor("light.theme.bs.05");
    }

    private static Color getLightBs06() {
        return getColor("light.theme.bs.06");
    }

    private static Color getLightBs07() {
        return getColor("light.theme.bs.07");
    }

    private static Color getLightBs08() {
        return getColor("light.theme.bs.08");
    }

    private static Color getLightBs09() {
        return getColor("light.theme.bs.09");
    }

    private static Color getLightBs10() {
        return getColor("light.theme.bs.10");
    }

    private static Color getLightBs11() {
        return getColor("light.theme.bs.11");
    }

    private static Color getLightBs12() {
        return getColor("light.theme.bs.12");
    }

    // Dark theme - backgrounds
    private static Color getDarkBg01() {
        return getColor("dark.theme.bg.01");
    }

    private static Color getDarkBg02() {
        return getColor("dark.theme.bg.02");
    }

    private static Color getDarkBg03() {
        return getColor("dark.theme.bg.03");
    }

    private static Color getDarkBg04() {
        return getColor("dark.theme.bg.04");
    }

    private static Color getDarkBg05() {
        return getColor("dark.theme.bg.05");
    }

    private static Color getDarkBg06() {
        return getColor("dark.theme.bg.06");
    }

    private static Color getDarkBg07() {
        return getColor("dark.theme.bg.07");
    }

    private static Color getDarkBg08() {
        return getColor("dark.theme.bg.08");
    }

    // Light theme - backgrounds
    private static Color getLightBg01() {
        return getColor("light.theme.bg.01");
    }

    private static Color getLightBg02() {
        return getColor("light.theme.bg.02");
    }

    private static Color getLightBg03() {
        return getColor("light.theme.bg.03");
    }

    private static Color getLightBg04() {
        return getColor("light.theme.bg.04");
    }

    private static Color getLightBg05() {
        return getColor("light.theme.bg.05");
    }

    private static Color getLightBg06() {
        return getColor("light.theme.bg.06");
    }

    private static Color getLightBg07() {
        return getColor("light.theme.bg.07");
    }

    private static Color getLightBg08() {
        return getColor("light.theme.bg.08");
    }

    // Light theme - accessible text
    private static Color getLightAt01() {
        return getColor("light.theme.at.01");
    }

    private static Color getLightAt02() {
        return getColor("light.theme.at.02");
    }

    private static Color getLightAt03() {
        return getColor("light.theme.at.03");
    }

    private static Color getLightAt04() {
        return getColor("light.theme.at.04");
    }

    private static Color getLightAt05() {
        return getColor("light.theme.at.05");
    }

    private static Color getLightAt06() {
        return getColor("light.theme.at.06");
    }

    private static Color getLightAt07() {
        return getColor("light.theme.at.07");
    }

    private static Color getLightAt08() {
        return getColor("light.theme.at.08");
    }

    // Dark theme - accessible text
    private static Color getDarkAt01() {
        return getColor("dark.theme.at.01");
    }

    private static Color getDarkAt02() {
        return getColor("dark.theme.at.02");
    }

    private static Color getDarkAt03() {
        return getColor("dark.theme.at.03");
    }

    private static Color getDarkAt04() {
        return getColor("dark.theme.at.04");
    }

    private static Color getDarkAt05() {
        return getColor("dark.theme.at.05");
    }

    private static Color getDarkAt06() {
        return getColor("dark.theme.at.06");
    }

    private static Color getDarkAt07() {
        return getColor("dark.theme.at.07");
    }

    private static Color getDarkAt08() {
        return getColor("dark.theme.at.08");
    }
}