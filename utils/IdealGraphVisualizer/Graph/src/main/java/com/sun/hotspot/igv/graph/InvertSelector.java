package com.sun.hotspot.igv.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class InvertSelector implements Selector {

    private Selector selector;

    public InvertSelector(Selector selector) {
        this.selector = selector;
    }

    @Override
    public List<Figure> selected(Diagram d) {

        List<Figure> result = new ArrayList<>();
        List<Figure> otherResult = selector.selected(d);
        for (Figure f : d.getFigures()) {
            if (!otherResult.contains(f)) {
                result.add(f);
            }
        }

        return result;
    }
}
