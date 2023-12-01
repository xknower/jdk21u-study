package com.sun.hotspot.igv.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class OrSelector implements Selector {

    private Selector[] selectors;

    public OrSelector(Selector[] selectors) {
        this.selectors = selectors;
    }

    @Override
    public List<Figure> selected(Diagram d) {
        List<Figure> result = new ArrayList<>();
        for (Selector s : selectors) {
            for (Figure f : s.selected(d)) {
                if (!result.contains(f)) {
                    result.add(f);
                }
            }
        }
        return result;
    }
}
