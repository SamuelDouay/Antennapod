package com.podcast.antennapod.view.layout.context;

public interface ContextualLayout {
    void updateContext(LayoutContext context);
    boolean acceptsContext(Class<? extends LayoutContext> contextType);
}
