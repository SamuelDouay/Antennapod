package com.podcast.antennapod.view;

import com.podcast.antennapod.logic.nav.ItemNav;
import javafx.scene.control.ListView;

public class NavView {
    private ListView<ItemNav> pageList;
    private ListView<ItemNav> podcastList;

    public NavView() {
        this.pageList = new ListView<>();
        this.podcastList = new ListView<>();
    }
}
