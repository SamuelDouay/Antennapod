package com.podcast.antennapod.service;

import com.podcast.antennapod.item.EpisodeItem;
import com.podcast.antennapod.logic.sql.dao.EpisodeDao;

import java.util.List;

public class EpisodeService {
    private final EpisodeDao episodeDao;

    public EpisodeService() {
        this.episodeDao = new EpisodeDao();
    }

    public List<EpisodeItem> getTop8Queue() {
        return episodeDao.getTop8InQueue();
    }
}
