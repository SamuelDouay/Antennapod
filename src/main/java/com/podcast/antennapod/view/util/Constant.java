package com.podcast.antennapod.view.util;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Constant {

    private Constant(){

    }

    // Constantes pour les couleurs (facilite la maintenance et la cohérence)
    public static final Color PRIMARY_COLOR = Color.web("#285E61");
    public static final Color PRIMARY_HOVER_COLOR = Color.web("#164F52");
    public static final Color PRIMARY_PRESSED_COLOR = Color.web("#004044");

    public static final Color SECONDARY_TEXT_COLOR = Color.web("#164F52");
    public static final Color SECONDARY_HOVER_COLOR = Color.web("#C5E6E8");
    public static final Color SECONDARY_PRESSED_COLOR = Color.web("#D3EDEF");

    public static final Color WHITE = Color.WHITE;

    // Constantes pour la mise en page
    public static final double CORNER_RADIUS = 2.0;
    public static final Insets PRIMARY_TERTIARY_PADDING = new Insets(8.0, 16.0, 8.0, 16.0);
    public static final Insets SECONDARY_PADDING = new Insets(6.0, 14.0, 6.0, 14.0);
    public static final BorderWidths BORDER_WIDTH = new BorderWidths(2);
    public static final CornerRadii CORNER_RADII = new CornerRadii(CORNER_RADIUS);
}
