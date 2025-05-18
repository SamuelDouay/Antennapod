package com.podcast.antennapod.view.component.episode;

import com.podcast.antennapod.view.component.badge.BadgeComponent;
import com.podcast.antennapod.view.component.button.ButtonComponent;
import com.podcast.antennapod.view.item.EpisodeItem;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;

public class EpisodeComponent {
    public EpisodeComponent() {
        // no parameters
    }

    public HBox createInboxEpisode(EpisodeItem item) {
        return new EpisodeBuilder().withEpisodeItem(item)
                .withButton(new ButtonComponent().createPrimaryButton("Télécharger"))
                .withBadge(new BadgeComponent().createBlueBadge(new FontIcon(MaterialDesignI.INBOX)))
                .build();
    }
}
