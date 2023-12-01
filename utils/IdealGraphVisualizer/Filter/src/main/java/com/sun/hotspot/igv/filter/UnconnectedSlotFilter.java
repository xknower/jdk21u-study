package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter that hides slots with no connections.
 */
public class UnconnectedSlotFilter extends AbstractFilter {

    private final boolean removeInputs;
    private final boolean removeOutputs;

    public UnconnectedSlotFilter(boolean inputs, boolean outputs) {
        this.removeInputs = inputs;
        this.removeOutputs = outputs;
    }

    @Override
    public String getName() {
        return "Unconnected Slot Filter";
    }

    @Override
    public void apply(Diagram d) {
        if (!removeInputs && !removeOutputs) {
            return;
        }

        List<Figure> figures = d.getFigures();
        for (Figure f : figures) {
            List<Slot> remove = new ArrayList<>();
            if (removeInputs) {
                for (InputSlot is : f.getInputSlots()) {
                    if (is.getConnections().isEmpty()) {
                        remove.add(is);
                    }
                }
            }
            if (removeOutputs) {
                for (OutputSlot os : f.getOutputSlots()) {
                    if (os.getConnections().isEmpty()) {
                        remove.add(os);
                    }
                }
            }
            for (Slot s : remove) {
                f.removeSlot(s);
            }
        }
    }
}
