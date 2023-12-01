package com.sun.hotspot.igv.graph;

import java.awt.Point;

/**
 *
 * @author Thomas Wuerthinger
 */
public class OutputSlot extends Slot {

    protected OutputSlot(Figure figure, int wantedIndex) {
        super(figure, wantedIndex);
    }

    @Override
    public int getPosition() {
        return getFigure().getOutputSlots().indexOf(this);
    }

    @Override
    public void setPosition(int position) {
        OutputSlot s = getFigure().outputSlots.remove(position);
        getFigure().outputSlots.add(position, s);
    }

    @Override
    public Point getRelativePosition() {
        int gap = getFigure().getWidth() - Figure.getSlotsWidth(getFigure().getOutputSlots());
        if(gap < 0) {
            gap = 0;
        }
        double gapRatio = (double)gap / (double)(getFigure().getOutputSlots().size() + 1);
        int gapAmount = (int)((getPosition() + 1)*gapRatio);
        return new Point(gapAmount + Figure.getSlotsWidth(Figure.getAllBefore(getFigure().getOutputSlots(), this)) + getWidth()/2, Figure.SLOT_START);
    }

    @Override
    public String toString() {
        return "OutputSlot[figure=" + this.getFigure().toString() + ", position=" + getPosition() + "]";
    }
}
