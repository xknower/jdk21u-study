package com.sun.hotspot.igv.view.widgets;

import com.sun.hotspot.igv.graph.Figure;
import com.sun.hotspot.igv.graph.OutputSlot;
import com.sun.hotspot.igv.view.DiagramScene;
import java.util.List;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Thomas Wuerthinger
 */
public class OutputSlotWidget extends SlotWidget {

    private OutputSlot outputSlot;

    public OutputSlotWidget(OutputSlot slot, DiagramScene scene, Widget parent, FigureWidget fw) {
        super(slot, scene, parent, fw);
        outputSlot = slot;
    }

    public OutputSlot getOutputSlot() {
        return outputSlot;
    }

    @Override
    protected int calculateSlotWidth() {
        List<OutputSlot> slots = getSlot().getFigure().getOutputSlots();
        assert slots.contains(getSlot());
        return calculateWidth(slots.size());

    }

    @Override
    protected int yOffset() {
        int overlap = getFigureWidget().getFigure().getDiagram().isCFG() ?
            calculateClientArea().height : Figure.SLOT_START;
        return getSlot().getFigure().getHeight() - overlap;
    }
}
