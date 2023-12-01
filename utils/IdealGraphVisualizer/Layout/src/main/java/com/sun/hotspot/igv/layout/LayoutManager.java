package com.sun.hotspot.igv.layout;

import java.util.Set;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface LayoutManager {

    void doLayout(LayoutGraph graph);

    void doLayout(LayoutGraph graph, Set<? extends Link> importantLinks);
}
