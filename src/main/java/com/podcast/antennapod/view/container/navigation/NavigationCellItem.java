package com.podcast.antennapod.view.container.navigation;

import com.podcast.antennapod.view.component.NavigationComponent;
import com.podcast.antennapod.view.item.NavigationItem;
import com.podcast.antennapod.view.util.ColorThemeConstants;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

import static com.podcast.antennapod.view.util.Constant.CORNER_RADII;

public class NavigationCellItem extends ListCell<NavigationItem> {
    private HBox cellContent;

    @Override
    protected void updateItem(NavigationItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            cellContent = null;
            setStyle("-fx-background-color: transparent;");
        }
        else if (item.isSeparator()) {
            // Afficher un séparateur
            Region separator = new Region();
            separator.setPrefHeight(1);
            separator.setMaxWidth(Double.MAX_VALUE);
            separator.setStyle("-fx-background-color: #e0e0e0;");

            VBox container = new VBox();
            container.setPadding(new Insets(8, 0, 8, 0));
            container.getChildren().add(separator);

            setGraphic(container);
            setText(null);
            setStyle("-fx-background-color: transparent;");
            setDisable(true); // Empêcher la sélection du séparateur
        }
        else {
            cellContent = NavigationComponent.createNavigation(item);
            setGraphic(cellContent);
            setStyle("-fx-background-color: transparent; -fx-padding: 0px;");
            updateCellAppearance(isSelected());
            setupCellBehaviors();
        }
    }

    @Override
    public void updateSelected(boolean selected) {
        super.updateSelected(selected);

        if (cellContent != null) {
            updateCellAppearance(selected);
        }
    }

    private void updateCellAppearance(boolean selected) {
        if (selected) {
            cellContent.setBackground(new Background(new BackgroundFill(
                    ColorThemeConstants.getIc07(),
                    CORNER_RADII,
                    Insets.EMPTY)));
            updateComponentColors(ColorThemeConstants.getAt08());
        } else {
            cellContent.setBackground(null);

            updateComponentColors(ColorThemeConstants.getAt02());
        }
    }

    private void setupCellBehaviors() {
        cellContent.setOnMouseEntered(e -> {
            if (!isSelected()) {
                cellContent.setBackground(new Background(new BackgroundFill(
                        ColorThemeConstants.getIc07(),
                        CORNER_RADII,
                        Insets.EMPTY)));
                updateComponentColors(ColorThemeConstants.getAt08());
            }
        });

        cellContent.setOnMouseExited(e -> {
            if (!isSelected()) {
                cellContent.setBackground(null);
                updateComponentColors(ColorThemeConstants.getAt02());
            }
        });

        cellContent.setOnMousePressed(e -> {
            cellContent.setBackground(new Background(new BackgroundFill(
                    ColorThemeConstants.getIc12(),
                    CORNER_RADII,
                    Insets.EMPTY)));
        });

        cellContent.setOnMouseReleased(e -> {
            if (!isSelected()) {
                if (cellContent.isHover()) {
                    cellContent.setBackground(new Background(new BackgroundFill(
                            ColorThemeConstants.getIc07(),
                            CORNER_RADII,
                            Insets.EMPTY)));
                } else {
                    cellContent.setBackground(null);
                }
            }
        });
    }

    private void updateComponentColors(Paint color) {
        for (Node node : cellContent.getChildren()) {
            if (node instanceof HBox) {
                for (Node child : ((HBox) node).getChildren()) {
                    if (child instanceof Label) {
                        ((Label) child).setTextFill(color);
                    } else if (child instanceof FontIcon) {
                        ((FontIcon) child).setIconColor(color);
                    }
                }
            }
        }

        Node graphic = (Node) cellContent.getUserData();
        if (graphic instanceof FontIcon) {
            ((FontIcon) graphic).setIconColor(color);
        }
    }
}

