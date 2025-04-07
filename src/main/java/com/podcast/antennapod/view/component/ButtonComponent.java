package com.podcast.antennapod.view.component;

import com.podcast.antennapod.view.util.TypeButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.podcast.antennapod.view.util.Constant.*;

public class ButtonComponent {

    private ButtonComponent(){

    }

    public static Button createButton(String text, TypeButton typeButton) {
        if (typeButton == null) {
            return null;
        }

        Button button = createButtonText(text);

        switch (typeButton) {
            case PRIMARY:
                createPrimaryButton(button);
                break;
            case SECONDARY:
                createSecondaryButton(button);
                break;
            case TERTIARY:
                createTertiaryButton(button);
                break;
            default:
                return null;
        }

        return button;
    }

    private static Button createButtonText(String text) {
        Button button = new Button(text);
        button.setFocusTraversable(true);
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }

    private static void createPrimaryButton(Button button) {
        // Configuration initiale
        button.textFillProperty().set(WHITE);
        button.setBackground(createBackground(PRIMARY_COLOR));
        button.setBorder(null);
        button.setPadding(PRIMARY_TERTIARY_PADDING);

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createBackground(PRIMARY_HOVER_COLOR)));
        button.setOnMouseExited(e -> button.setBackground(createBackground(PRIMARY_COLOR)));
        button.setOnMousePressed(e -> button.setBackground(createBackground(PRIMARY_PRESSED_COLOR)));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createBackground(PRIMARY_HOVER_COLOR));
            } else {
                button.setBackground(createBackground(PRIMARY_COLOR));
            }
        });
    }

    private static void createSecondaryButton(Button button) {
        // Configuration initiale
        button.textFillProperty().set(SECONDARY_TEXT_COLOR);
        button.setBackground(null);
        button.setPadding(SECONDARY_PADDING);
        button.setBorder(createBorderSecondaryButton());

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createBackground(SECONDARY_HOVER_COLOR)));
        button.setOnMouseExited(e -> button.setBackground(null));
        button.setOnMousePressed(e -> button.setBackground(createBackground(SECONDARY_PRESSED_COLOR)));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createBackground(SECONDARY_HOVER_COLOR));
            } else {
                button.setBackground(null);
            }
        });
    }

    private static void createTertiaryButton(Button button) {
        // Configuration initiale
        button.textFillProperty().set(SECONDARY_TEXT_COLOR);
        button.setBackground(null);
        button.setBorder(null);
        button.setPadding(PRIMARY_TERTIARY_PADDING);

        // Événements de souris
        button.setOnMouseEntered(e -> button.setBackground(createBackground(SECONDARY_HOVER_COLOR)));
        button.setOnMouseExited(e -> button.setBackground(null));
        button.setOnMousePressed(e -> button.setBackground(createBackground(SECONDARY_PRESSED_COLOR)));
        button.setOnMouseReleased(e -> {
            if (button.isHover()) {
                button.setBackground(createBackground(SECONDARY_HOVER_COLOR));
            } else {
                button.setBackground(null);
            }
        });
    }

    private static Background createBackground(Color color) {
        return new Background(new BackgroundFill(color, CORNER_RADII, Insets.EMPTY));
    }

    private static Border createBorderSecondaryButton() {
        return new Border(new BorderStroke(
                SECONDARY_TEXT_COLOR,
                BorderStrokeStyle.SOLID,
                CORNER_RADII,
                BORDER_WIDTH
        ));
    }
}
