package com.podcast.antennapod.view.component.button;

import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.TypeButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import static com.podcast.antennapod.view.util.Constant.*;

public class ButtonBuilder {
    // Button properties
    private String text;
    private FontIcon icon;
    private TypeButton typeButton;
    private boolean iconOnly;

    // Customization properties
    private Color backgroundColor;
    private Color textColor;
    private Color iconColor;
    private Color borderColor;

    /**
     * Private constructor to enforce builder pattern usage
     */
    ButtonBuilder() {
    }

    /**
     * Set the button text
     *
     * @param text The text to display on the button
     * @return This builder instance for chaining
     */
    public ButtonBuilder withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Set the button icon
     *
     * @param icon The FontIcon to display on the button
     * @return This builder instance for chaining
     */
    public ButtonBuilder withIcon(FontIcon icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Set the button type
     *
     * @param typeButton The type of button (PRIMARY, SECONDARY, TERTIARY)
     * @return This builder instance for chaining
     */
    public ButtonBuilder withType(TypeButton typeButton) {
        this.typeButton = typeButton;
        return this;
    }

    /**
     * Set the button to display only an icon (circular button)
     *
     * @param iconOnly Whether the button should display only an icon
     * @return This builder instance for chaining
     */
    public ButtonBuilder setIconOnly(boolean iconOnly) {
        this.iconOnly = iconOnly;
        return this;
    }

    /**
     * Set a custom background color for the button
     *
     * @param backgroundColor The background color to use
     * @return This builder instance for chaining
     */
    public ButtonBuilder withBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * Set a custom text color for the button
     *
     * @param textColor The text color to use
     * @return This builder instance for chaining
     */
    public ButtonBuilder withTextColor(Color textColor) {
        this.textColor = textColor;
        return this;
    }

    /**
     * Set a custom icon color for the button
     *
     * @param iconColor The icon color to use
     * @return This builder instance for chaining
     */
    public ButtonBuilder withIconColor(Color iconColor) {
        this.iconColor = iconColor;
        return this;
    }

    /**
     * Set a custom border color for the button
     *
     * @param borderColor The border color to use
     * @return This builder instance for chaining
     */
    public ButtonBuilder withBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }


    /**
     * Build and return the button
     *
     * @return The constructed button
     */
    public Button build() {
        if (typeButton == null) {
            throw new IllegalStateException("Button type must be specified");
        }

        Button button;

        if (iconOnly && icon != null) {
            // Icon-only button (circular)
            button = new Button();
            button.setFocusTraversable(true);
            button.setGraphic(icon);
            button.setAlignment(Pos.CENTER);
            button.setPrefSize(ICON_BUTTON_SIZE, ICON_BUTTON_SIZE);
            button.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            button.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

            applyIconButtonStyle(button);
        } else {
            // Text button (with optional icon)
            button = new Button(text != null ? text : "");
            button.setFocusTraversable(true);
            button.setAlignment(Pos.CENTER);

            if (icon != null) {
                icon.setIconSize(ICON_SIZE);
                button.setGraphic(icon);
            }

            applyButtonStyle(button);
        }

        return button;
    }

    private void applyButtonStyle(Button button) {
        // Apply base styling based on button type
        switch (typeButton) {
            case PRIMARY:
                applyPrimaryButtonStyle(button);
                break;
            case SECONDARY:
                applySecondaryButtonStyle(button);
                break;
            case TERTIARY:
                applyTertiaryButtonStyle(button);
                break;
        }

        // Apply custom styling if specified
        if (textColor != null) {
            button.textFillProperty().set(textColor);
        }

        if (backgroundColor != null) {
            button.setBackground(createBackground(backgroundColor));
        }

        if (icon != null && iconColor != null) {
            icon.setIconColor(iconColor);
        }
    }

    private void applyIconButtonStyle(Button button) {
        // Apply base styling based on button type
        switch (typeButton) {
            case PRIMARY:
                applyPrimaryIconButtonStyle(button);
                break;
            case SECONDARY:
                applySecondaryIconButtonStyle(button);
                break;
            case TERTIARY:
                applyTertiaryIconButtonStyle(button);
                break;
        }

        // Apply custom styling if specified
        if (backgroundColor != null) {
            button.setBackground(createCircleBackground(backgroundColor));
        }

        if (icon != null && iconColor != null) {
            icon.setIconColor(iconColor);
        }
    }

    private void applyPrimaryButtonStyle(Button button) {
        // Base styling
        button.textFillProperty().set(textColor != null ? textColor : ColorThemeConstants.getMain950());
        button.setBackground(createBackground(backgroundColor != null ? backgroundColor : ColorThemeConstants.getMain500()));
        button.setBorder(null);
        button.setPadding(PRIMARY_TERTIARY_PADDING);

        setIconParameter();

        button.setOnMouseEntered(_ -> button.setBackground(createBackground(ColorThemeConstants.getMain400())));
        button.setOnMouseExited(_ -> button.setBackground(createBackground(ColorThemeConstants.getMain500())));
        button.setOnMousePressed(_ -> button.setBackground(createBackground(ColorThemeConstants.getMain600())));
        button.setOnMouseReleased(_ -> {
            if (button.isHover()) {
                button.setBackground(createBackground(ColorThemeConstants.getMain400()));
            } else {
                button.setBackground(createBackground(ColorThemeConstants.getMain500()));
            }
        });
    }

    private void setIconParameter() {
        if (icon != null) {
            icon.setIconColor(iconColor != null ? iconColor : ColorThemeConstants.getGrey950());
            icon.setIconSize(ICON_SIZE);
        }
    }

    private void applySecondaryButtonStyle(Button button) {
        // Base styling
        button.textFillProperty().set(textColor != null ? textColor : ColorThemeConstants.getGrey950());
        button.setBackground(null);
        button.setPadding(SECONDARY_PADDING);
        button.setBorder(createBorderSecondaryButton());

        setIconParameter();

        button.setOnMouseEntered(_ -> button.setBackground(createBackground(ColorThemeConstants.getMain050())));
        button.setOnMouseExited(_ -> button.setBackground(null));
        button.setOnMousePressed(_ -> button.setBackground(createBackground(ColorThemeConstants.getMain100())));
        button.setOnMouseReleased(_ -> {
            if (button.isHover()) {
                button.setBackground(createBackground(ColorThemeConstants.getMain050()));
            } else {
                button.setBackground(null);
            }
        });
    }

    private void applyTertiaryButtonStyle(Button button) {
        // Base styling
        button.textFillProperty().set(textColor != null ? textColor : ColorThemeConstants.getGrey950());
        button.setBackground(null);
        button.setBorder(null);
        button.setPadding(PRIMARY_TERTIARY_PADDING);

        setIconParameter();

        button.setOnMouseEntered(_ -> button.setBackground(createBackground(ColorThemeConstants.getMain050())));
        button.setOnMouseExited(_ -> button.setBackground(null));
        button.setOnMousePressed(_ -> button.setBackground(createBackground(ColorThemeConstants.getMain100())));
        button.setOnMouseReleased(_ -> {
            if (button.isHover()) {
                button.setBackground(createBackground(ColorThemeConstants.getMain050()));
            } else {
                button.setBackground(null);
            }
        });
    }

    private void applyPrimaryIconButtonStyle(Button button) {
        // Base styling
        setIconParameter();

        button.setBackground(createCircleBackground(backgroundColor != null ? backgroundColor : ColorThemeConstants.getMain400()));
        button.setBorder(null);
        button.setPadding(ICON_BUTTON_PADDING);

        button.setOnMouseEntered(_ -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain500())));
        button.setOnMouseExited(_ -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain400())));
        button.setOnMousePressed(_ -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain600())));
        button.setOnMouseReleased(_ -> {
            if (button.isHover()) {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain500()));
            } else {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain400()));
            }
        });
    }

    private void applySecondaryIconButtonStyle(Button button) {
        // Base styling
        setIconParameter();

        button.setBackground(null);
        button.setBorder(this.createCircleBorderSecondaryButton());
        button.setPadding(ICON_BUTTON_PADDING);


        button.setOnMouseEntered(_ -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain100())));
        button.setOnMouseExited(_ -> button.setBackground(null));
        button.setOnMousePressed(_ -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain300())));
        button.setOnMouseReleased(_ -> {
            if (button.isHover()) {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain100()));
            } else {
                button.setBackground(null);
            }
        });

    }

    private void applyTertiaryIconButtonStyle(Button button) {
        // Base styling
        setIconParameter();

        button.setBackground(null);
        button.setBorder(null);
        button.setPadding(ICON_BUTTON_PADDING);

        // Mouse event handlers

        button.setOnMouseEntered(_ -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain100())));
        button.setOnMouseExited(_ -> button.setBackground(null));
        button.setOnMousePressed(_ -> button.setBackground(createCircleBackground(ColorThemeConstants.getMain300())));
        button.setOnMouseReleased(_ -> {
            if (button.isHover()) {
                button.setBackground(createCircleBackground(ColorThemeConstants.getMain100()));
            } else {
                button.setBackground(null);
            }
        });

    }

    private Background createBackground(Color color) {
        return new Background(new BackgroundFill(color, CORNER_RADII, Insets.EMPTY));
    }

    private Background createCircleBackground(Color color) {
        return new Background(new BackgroundFill(color, CIRCLE_RADII, Insets.EMPTY));
    }

    private Border createBorderSecondaryButton() {
        Color color = borderColor != null ? borderColor : ColorThemeConstants.getGrey950();
        return new Border(new BorderStroke(
                color,
                BorderStrokeStyle.SOLID,
                CORNER_RADII,
                BORDER_WIDTH
        ));
    }

    private Border createCircleBorderSecondaryButton() {
        Color color = borderColor != null ? borderColor : ColorThemeConstants.getGrey950();
        return new Border(new BorderStroke(
                color,
                BorderStrokeStyle.SOLID,
                CIRCLE_RADII,
                BORDER_WIDTH
        ));
    }
}