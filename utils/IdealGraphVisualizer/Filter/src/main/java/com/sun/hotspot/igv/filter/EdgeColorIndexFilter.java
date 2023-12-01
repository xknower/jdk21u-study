package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.*;
import java.awt.Color;
import java.util.List;

public class EdgeColorIndexFilter extends AbstractFilter {

    public static final String INPUTS = "INPUTS";
    public static final String OUTPUTS = "OUTPUTS";
    private final String applyTo;
    private final Color[] colors;

    public EdgeColorIndexFilter(String applyTo, Color... color) {
        if (!applyTo.equals(INPUTS) && !applyTo.equals(OUTPUTS)) {
            throw new IllegalArgumentException("applyTo");
        }

        this.applyTo = applyTo;
        this.colors = color;
    }

    @Override
    public String getName() {
        return "Edge Color Index Filter";
    }

    @Override
    public void apply(Diagram d) {
        List<Figure> figures = d.getFigures();
        for (Figure f : figures) {
            Slot[] slots;
            if (applyTo.equals(INPUTS)) {
                List<InputSlot> inputSlots = f.getInputSlots();
                slots = inputSlots.toArray(new Slot[inputSlots.size()]);
            } else {
                List<OutputSlot> outputSlots = f.getOutputSlots();
                slots = outputSlots.toArray(new Slot[outputSlots.size()]);
            }
            int index = 0;
            for (Slot slot : slots) {
                if (index < colors.length && colors[index] != null) {
                    slot.setColor(colors[index]);
                    for (FigureConnection c : slot.getConnections()) {

                        c.setColor(colors[index]);
                    }
                }
                index++;
            }

        }
    }
}
