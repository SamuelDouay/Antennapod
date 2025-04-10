package com.podcast.antennapod.view.util;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

public class Constant {

    private Constant(){

    }

    // Constantes pour la mise en page
    public static final double CORNER_RADIUS = 2.0;
    public static final Insets PRIMARY_TERTIARY_PADDING = new Insets(8.0, 16.0, 8.0, 16.0);
    public static final Insets SECONDARY_PADDING = new Insets(6.0, 14.0, 6.0, 14.0);
    public static final BorderWidths BORDER_WIDTH = new BorderWidths(2);
    public static final CornerRadii CORNER_RADII = new CornerRadii(CORNER_RADIUS);

    // Constantes de dimensions
    public static final double BADGE_DEFAULT_WIDTH = 100.0;
    public static final Insets BADGE_DEFAULT_PADDING = new Insets(4.0, 16.0, 4.0, 16.0);
    public static final Insets BADGE_ICON_PADDING = new Insets(4.0);
    public static final int BADGE_ICON_SIZE = 15;
}
