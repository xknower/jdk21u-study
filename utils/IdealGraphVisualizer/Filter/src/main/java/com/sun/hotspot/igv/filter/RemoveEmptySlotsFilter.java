package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveEmptySlotsFilter extends AbstractFilter {

    private String name;
    private Selector selector;

    public RemoveEmptySlotsFilter(String name, Selector selector) {
        this.name = name;
        this.selector = selector;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void apply(Diagram diagram) {
        List<Figure> list = selector.selected(diagram);
        for (Figure f : list) {
            List<InputSlot> empty = new ArrayList<>();
            for (InputSlot is : f.getInputSlots()) {
                if (is.getConnections().isEmpty()) {
                    empty.add(is);
                }
            }
            for (InputSlot is : empty) {
                f.removeSlot(is);
            }
        }
    }
}
