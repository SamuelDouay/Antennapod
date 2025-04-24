package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.TypeButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import static com.podcast.antennapod.view.util.Constant.*;

public class ButtonComponent {

    private ButtonComponent() {
        // Constructeur privé pour éviter l'instanciation
    }

    public static Button createButton(String text, FontIcon icon, TypeButton typeButton, boolean iconOnly) {
        if (typeButton == null) {
            return null;
        }

        Button button;

        if (iconOnly && icon != null) {
            // Bouton avec uniquement une icône (circulaire)
            button = new Button();
            button.setFocusTraversable(true);
            button.setGraphic(icon);
            button.setAlignment(Pos.CENTER);
            button.setPrefSize(ICON_BUTTON_SIZE, ICON_BUTTON_SIZE);
            button.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            button.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

            applyIconButtonStyle(button, icon, typeButton);
        } else {
            // Bouton avec texte (et éventuellement une icône)
            button = new Button(text);
            button.setFocusTraversable(true);
            button.setAlignment(Pos.CENTER_LEFT);

            if (icon != null) {
                icon.setIconSize(BUTTON_ICON_SIZE);
                button.setGraphic(icon);
                button.setGraphicTextGap(8); // Espace entre l'icône et le texte
            }

            applyButtonStyle(button, icon, typeButton);
        }

        return button;
    }

    public static Button createButton(String text, TypeButton typeButton) {
        return createButton(text, null, typeButton, false);
    }

    public static Button createButton(String text, FontIcon icon, TypeButton typeButton) {
        return createButton(text, icon, typeButton, false);
    }

    public static Button createButton(FontIcon icon, TypeButton typeButton) {
        return createButton(null, icon, typeButton, true);
    }

    private static void applyButtonStyle(Button button, FontIcon icon, TypeButton typeButton) {
        switch (typeButton) {
            case PRIMARY:
                createPrimaryButton(button);
                if (icon != null) {
                    icon.setIconColor(ColorThemeConstants.getGrey950());
                }
                break;
            case SECONDARY:
                createSecondaryButton(button);
                if (icon != null) {
                    icon.setIconColor(ColorThemeConstants.getGrey950());
                }
                break;
            case TERTIARY:
                createTertiaryButton(button);
                if (icon != null) {
                    icon.setIconColor(ColorThemeConstants.getGrey950());
                }
                break;
        }
    }

    private static void applyIconButtonStyle(Button button, FontIcon icon, TypeButton typeButton) {
        switch (typeButton) {
            case PRIMARY:
                createPrimaryIconButton(button, icon);
                break;
            case SECONDARY:
                createSecondaryIconButton(button, icon);
                break;
            case TERTIARY:
                createTertiaryIconButton(button, icon);
                break;
        }
    }

    private static void createPrimaryButton(Button button) {
        // Configuration initiale
        button.textFillProperty().set(ColorThemeConstants.getMain950());
        button.setBackground(createBackground(ColorThemeConstants.getMain500()));
        button.setBorder(null);
        button.setPadding(PRIMARY_TERTIARY_PADDING);

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createBackground(ColorThemeConstants.getMain400())));
        button.setOnMouseExited(e -> button.setBackground(createBackground(ColorThemeConstants.getMain500())));
        button.setOnMousePressed(e -> button.setBackground(createBackground(ColorThemeConstants.getMain600())));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createBackground(ColorThemeConstants.getMain400()));
            } else {
                button.setBackground(createBackground(ColorThemeConstants.getMain500()));
            }
        });
    }

    private static void createSecondaryButton(Button button) {
        // Configuration initiale
        button.textFillProperty().set(ColorThemeConstants.getGrey950());
        button.setBackground(null);
        button.setPadding(SECONDARY_PADDING);
        button.setBorder(createBorderSecondaryButton());

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createBackground(ColorThemeConstants.getMain050())));
        button.setOnMouseExited(e -> button.setBackground(null));
        button.setOnMousePressed(e -> button.setBackground(createBackground(ColorThemeConstants.getMain100())));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createBackground(ColorThemeConstants.getMain050()));
            } else {
                button.setBackground(null);
            }
        });
    }

    private static void createTertiaryButton(Button button) {
        // Configuration initiale
        button.textFillProperty().set(ColorThemeConstants.getGrey950());
        button.setBackground(null);
        button.setBorder(null);
        button.setPadding(PRIMARY_TERTIARY_PADDING);

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createBackground(ColorThemeConstants.getMain050())));
        button.setOnMouseExited(e -> button.setBackground(null));
        button.setOnMousePressed(e -> button.setBackground(createBackground(ColorThemeConstants.getMain100())));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createBackground(ColorThemeConstants.getMain050()));
            } else {
                button.setBackground(null);
            }
        });
    }

    private static void createPrimaryIconButton(Button button, FontIcon icon) {
        // Configuration pour bouton icône primaire (rond plein)
        icon.setIconColor(ColorThemeConstants.getMain950());
        icon.setIconSize(ICON_SIZE);

        button.setBackground(createCircleBackground(ColorThemeConstants.getMain400()));
        button.setBorder(null);
        button.setPadding(ICON_BUTTON_PADDING);

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain500())));
        button.setOnMouseExited(e -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain400())));
        button.setOnMousePressed(e -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain600())));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain500()));
            } else {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain400()));
            }
        });
    }

    private static void createSecondaryIconButton(Button button, FontIcon icon) {
        // Configuration pour bouton icône secondaire (rond avec contour)
        icon.setIconColor(ColorThemeConstants.getGrey950());
        icon.setIconSize(ICON_SIZE);

        button.setBackground(null);
        button.setBorder(createCircleBorderSecondaryButton());
        button.setPadding(ICON_BUTTON_PADDING);

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain100())));
        button.setOnMouseExited(e -> button.setBackground(null));
        button.setOnMousePressed(e -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain300())));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain100()));
            } else {
                button.setBackground(null);
            }
        });
    }

    private static void createTertiaryIconButton(Button button, FontIcon icon) {
        // Configuration pour bouton icône tertiaire (avec fond clair)
        icon.setIconColor(ColorThemeConstants.getGrey950());
        icon.setIconSize(ICON_SIZE);

        button.setBackground(null);
        button.setBorder(null);
        button.setPadding(ICON_BUTTON_PADDING);

        // Événements de souris
        button.setOnMouseEntered(e -> {
            button.setOpacity(0.8);
            button.setBackground(createCircleBackground(ColorThemeConstants.getMain100()));
        });
        button.setOnMouseExited(e -> {
            button.setOpacity(1.0);
            button.setBackground(null);
        });
        button.setOnMousePressed(e -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain300())));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain100()));
                button.setOpacity(0.8);
            } else {
                button.setBackground(null);
                button.setOpacity(1.0);
            }
        });
    }

    private static Background createBackground(Color color) {
        return new Background(new BackgroundFill(color, CORNER_RADII, Insets.EMPTY));
    }

    private static Background createCircleBackground(Color color) {
        return new Background(new BackgroundFill(color, CIRCLE_RADII, Insets.EMPTY));
    }

    private static Border createBorderSecondaryButton() {
        return new Border(new BorderStroke(
                ColorThemeConstants.getGrey950(),
                BorderStrokeStyle.SOLID,
                CORNER_RADII,
                BORDER_WIDTH
        ));
    }

    private static Border createCircleBorderSecondaryButton() {
        return new Border(new BorderStroke(
                ColorThemeConstants.getGrey950(),
                BorderStrokeStyle.SOLID,
                CIRCLE_RADII,
                BORDER_WIDTH
        ));
    }
}