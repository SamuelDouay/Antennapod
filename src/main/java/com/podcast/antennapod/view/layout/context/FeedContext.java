package com.podcast.antennapod.view.layout.context;

public record FeedContext(String podcastTitle, String podcastId, int unreadCount) implements LayoutContext {
}
