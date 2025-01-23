package com.podcast.antennapod.old.style;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelStyle {
    private static final Sting FONT = "Inter";

    private LabelStyle() {

    }

    public static void setTextOnDefault(Label label) {
        label.setFont(Font.font(FONT, FontWeight.MEDIUM, 12));
        label.setTextFill(Paint.valueOf(Color.web("#1A2B2C").toString()));
    }

    public static void setNbEpisodes(Label label) {
        label.setFont(Font.font(FONT, FontWeight.BOLD, 10));
        label.setTextFill(Paint.valueOf(Color.web("#388CFA").toString()));
    }

    public static void setPodcastName(Label label) {
        label.setFont(Font.font(FONT, FontWeight.BOLD, 15));
        label.setTextFill(Paint.valueOf(Color.web("#FEFEFF").toString()));
    }

    public static void setTitleSection(Label label) {
        label.setFont(Font.font(FONT, FontWeight.BOLD, 20));
        label.setTextFill(Paint.valueOf(Color.web("#1A2B2C").toString()));
    }
}
