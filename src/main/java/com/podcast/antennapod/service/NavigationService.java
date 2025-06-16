package com.podcast.antennapod.service;

import com.podcast.antennapod.item.NavigationItem;
import com.podcast.antennapod.logic.sql.dao.NavigationDao;

import java.util.List;

public class NavigationService {
    private final NavigationDao navigationDao;

    public NavigationService() {
        this.navigationDao = new NavigationDao();
    }

    public List<NavigationItem> getList() {
        return navigationDao.getList();
    }

    public int getNumberOfInbox() {
        return navigationDao.getNumberOfInbox();
    }
}
