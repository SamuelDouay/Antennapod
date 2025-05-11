package com.podcast.antennapod.view.component.image;

import com.podcast.antennapod.view.util.ColorThemeConstants;
import com.podcast.antennapod.view.util.Constant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.concurrent.ConcurrentHashMap;

public class ImageBuilder {
    private static final double SCALE_FACTOR = 1.2;
    private static final double PADDING = Constant.PODCAST_CARD_DEFAULT_PADDING;
    private static final double IMAGE_SIZE = Constant.PODCAST_CARD_DEFAULT_IMAGE_WIDTH_HEIGHT;
    public static final double WIDTH = calculateTotalWidth();

    // Effets et styles préchargés
    private static final BoxBlur BACKGROUND_BLUR = new BoxBlur(200, 200, 5);
    private static final Color OVERLAY_COLOR = Color.hsb(230.0, 0.17, 0.14, 0.2);
    private static final Font TITLE_FONT = Font.font("Inter", FontWeight.BOLD, 15);
    private static final Font DATE_FONT = Font.font("Inter", FontPosture.REGULAR, 10);
    private static final Font COUNT_FONT = Font.font("Inter", FontWeight.MEDIUM, 12);
    private static final CornerRadii BADGE_CORNER = new CornerRadii(99.0);
    private static final Insets BADGE_PADDING = new Insets(2.0, 7.0, 2.0, 7.0);
    private static final Insets BADGE_MARGIN = new Insets(10, 0, 0, 10);


    // Cache d'images
    private static final ConcurrentHashMap<String, Image> IMAGE_CACHE = new ConcurrentHashMap<>();

    private final String imageUrl;
    private String title;
    private String date;
    private int episodeCount;

    public ImageBuilder(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private static double calculateTotalWidth() {
        return IMAGE_SIZE + 2 * PADDING;
    }

    private static double calculateTotalHeight(double contentHeight) {
        return contentHeight + 2 * PADDING;
    }

    private static Image getOrLoadImage(String url) {
        return IMAGE_CACHE.computeIfAbsent(url, Image::new);
    }

    public ImageBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ImageBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public ImageBuilder withEpisodeCount(int count) {
        this.episodeCount = count;
        return this;
    }

    public Node build() {
        // Charger l'image (avec cache)
        Image image = getOrLoadImage(imageUrl);

        // Calculer la hauteur du contenu
        double contentHeight = IMAGE_SIZE;
        if (title != null) contentHeight += 25.0;
        if (date != null) contentHeight += 25.0;

        // Créer le conteneur principal
        StackPane container = createContainer(contentHeight);

        // Ajouter les composants dans l'ordre z-index
        container.getChildren().addAll(createBlurredBackground(image, contentHeight), createColorOverlay(contentHeight), createContent(image));

        // Ajouter le badge de compteur d'épisodes si nécessaire
        if (episodeCount > 0) {
            container.getChildren().add(createEpisodeCountBadge(episodeCount));
        }

        return container;
    }

    private StackPane createContainer(double contentHeight) {
        double containerWidth = WIDTH;
        double containerHeight = calculateTotalHeight(contentHeight);

        StackPane stackPane = new StackPane();
        stackPane.setMinSize(containerWidth, containerHeight);
        stackPane.setPrefSize(containerWidth, containerHeight);

        // Utiliser un clip pour assurer que le contenu ne déborde pas
        Rectangle clip = new Rectangle(containerWidth, containerHeight);
        stackPane.setClip(clip);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(PADDING));

        return stackPane;
    }

    private ImageView createBlurredBackground(Image image, double height) {
        double totalWidth = WIDTH;
        double totalHeight = calculateTotalHeight(height);

        ImageView blurredBackground = new ImageView(image);

        // Dimensionner l'image d'arrière-plan pour qu'elle couvre entièrement
        blurredBackground.setFitWidth(totalWidth * SCALE_FACTOR);
        blurredBackground.setFitHeight(totalHeight * SCALE_FACTOR);

        // Centrer l'image agrandie
        blurredBackground.setTranslateX((totalWidth * SCALE_FACTOR - totalWidth) / -2);
        blurredBackground.setTranslateY((totalHeight * SCALE_FACTOR - totalHeight) / -2);

        // Appliquer l'effet de flou
        blurredBackground.setEffect(BACKGROUND_BLUR);

        return blurredBackground;
    }

    private Rectangle createColorOverlay(double height) {
        Rectangle overlay = new Rectangle(WIDTH, calculateTotalHeight(height));
        overlay.setFill(OVERLAY_COLOR);
        return overlay;
    }

    private VBox createContent(Image image) {
        VBox content = new VBox(5);
        content.setAlignment(Pos.TOP_LEFT);

        // Ajouter l'image principale
        content.getChildren().add(createMainImage(image));

        // Ajouter le texte si nécessaire
        if (title != null || date != null) {
            content.getChildren().add(createTextContent());
        }

        return content;
    }

    private ImageView createMainImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);  // Meilleure qualité d'image
        imageView.setCache(true);   // Activer le cache pour de meilleures performances
        return imageView;
    }

    private VBox createTextContent() {
        VBox textContainer = new VBox(2.0);
        textContainer.setAlignment(Pos.BASELINE_LEFT);

        if (title != null) {
            textContainer.getChildren().add(createLabel(title, TITLE_FONT, ColorThemeConstants.getGrey100()));
        }

        if (date != null) {
            textContainer.getChildren().add(createLabel(date, DATE_FONT, ColorThemeConstants.getGrey100()));
        }

        return textContainer;
    }

    private Label createLabel(String text, Font font, Color color) {
        Label label = new Label(text);
        label.setTextFill(color);
        label.setFont(font);
        label.setWrapText(true);
        label.setMaxWidth(IMAGE_SIZE);
        label.setAlignment(Pos.BASELINE_LEFT);

        // Optimisation pour éviter le recalcul de mise en page
        Text helper = new Text(text);
        helper.setFont(font);
        double preferredWidth = Math.min(helper.getLayoutBounds().getWidth(), IMAGE_SIZE);
        label.setPrefWidth(preferredWidth);

        return label;
    }

    private Node createEpisodeCountBadge(int count) {
        Label countLabel = new Label(String.valueOf(count));
        countLabel.setFont(COUNT_FONT);
        countLabel.setTextFill(ColorThemeConstants.getMain900());

        HBox badgeBox = new HBox();
        badgeBox.setBackground(new Background(new BackgroundFill(ColorThemeConstants.getMain100(), BADGE_CORNER, Insets.EMPTY)));
        badgeBox.setPadding(BADGE_PADDING);
        badgeBox.setAlignment(Pos.CENTER);
        badgeBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        badgeBox.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        badgeBox.getChildren().add(countLabel);

        // Positionner le badge en haut à gauche
        StackPane.setAlignment(badgeBox, Pos.TOP_LEFT);
        StackPane.setMargin(badgeBox, BADGE_MARGIN);

        return badgeBox;
    }


}
