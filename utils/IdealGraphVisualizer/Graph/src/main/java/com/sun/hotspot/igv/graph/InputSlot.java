package com.sun.hotspot.igv.graph;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class InputSlot extends Slot {

    private int originalIndex;

    protected InputSlot(Figure figure, int wantedIndex) {
        super(figure, wantedIndex);
        this.originalIndex = -1;
    }

    @Override
    public int getPosition() {
        return getFigure().getInputSlots().indexOf(this);
    }

    @Override
    public void setPosition(int position) {
        List<InputSlot> inputSlots = getFigure().inputSlots;
        InputSlot s = inputSlots.remove(position);
        inputSlots.add(position, s);
    }

    public int getOriginalIndex() {
        return originalIndex;
    }

    public void setOriginalIndex(int originalIndex) {
        this.originalIndex = originalIndex;
    }

    public int gapSize() {
        int index = getPosition();
        int originalIndex = getOriginalIndex();
        InputSlot prevSlot = index > 0 ? getFigure().getInputSlots().get(index - 1) : null;
        int prevOriginalIndex = index > 0 ? prevSlot.getOriginalIndex() : -1;
        return originalIndex - prevOriginalIndex - 1;
    }

    @Override
    public Point getRelativePosition() {
        int gap = getFigure().getWidth() - Figure.getSlotsWidth(getFigure().getInputSlots());
        double gapRatio = (double)gap / (double)(getFigure().getInputSlots().size() + 1);
        int gapAmount = (int)((getPosition() + 1)*gapRatio);
        return new Point(gapAmount + Figure.getSlotsWidth(Figure.getAllBefore(getFigure().getInputSlots(), this)) + getWidth()/2, -Figure.SLOT_START);
    }

    @Override
    public String getToolTipText() {
        return super.getToolTipText() + " [" + originalIndex + "]";
    }

    @Override
    public String toString() {
        return "InputSlot[figure=" + this.getFigure().toString() + ", position=" + getPosition() + "]";
    }
}
