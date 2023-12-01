package com.sun.hotspot.igv.graph;

import java.util.ArrayList;
import java.util.List;

// Selects blocks where any node is selected.
public class AnySelector implements BlockSelector {

    private final Selector selector;

    public AnySelector(Selector s) {
        this.selector = s;
    }

    @Override
    public List<Block> selected(Diagram d) {
        List<Block> l = new ArrayList<>();
        for (Figure f : selector.selected(d)) {
            l.add(f.getBlock());
        }
        return l;
    }
}
