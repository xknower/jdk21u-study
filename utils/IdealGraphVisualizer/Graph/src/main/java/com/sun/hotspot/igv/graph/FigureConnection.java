package com.sun.hotspot.igv.graph;

import com.sun.hotspot.igv.layout.Cluster;
import com.sun.hotspot.igv.layout.Port;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class FigureConnection implements Connection {

    private final InputSlot inputSlot;
    private final OutputSlot outputSlot;
    private Color color;
    private ConnectionStyle style;
    private List<Point> controlPoints;
    private String label;

    protected FigureConnection(InputSlot inputSlot, OutputSlot outputSlot, String label) {
        this.inputSlot = inputSlot;
        this.outputSlot = outputSlot;
        this.label = label;
        this.inputSlot.connections.add(this);
        this.outputSlot.connections.add(this);
        controlPoints = new ArrayList<>();
        Figure sourceFigure = this.outputSlot.getFigure();
        Figure destFigure = this.inputSlot.getFigure();
        sourceFigure.addSuccessor(destFigure);
        destFigure.addPredecessor(sourceFigure);

        this.color = Color.BLACK;
        this.style = ConnectionStyle.NORMAL;
    }

    public InputSlot getInputSlot() {
        return inputSlot;
    }

    public OutputSlot getOutputSlot() {
        return outputSlot;
    }

    public Color getColor() {
        return color;
    }

    public ConnectionStyle getStyle() {
        return style;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void setStyle(ConnectionStyle s) {
        style = s;
    }

    public String getLabel() {
        return label;
    }

    public void remove() {
        inputSlot.getFigure().removePredecessor(outputSlot.getFigure());
        inputSlot.connections.remove(this);
        outputSlot.getFigure().removeSuccessor(inputSlot.getFigure());
        outputSlot.connections.remove(this);
    }

    public String getToolTipText() {
        StringBuilder builder = new StringBuilder();
        if (label != null) {
            builder.append(label).append(": ");
        }
        // Resolve strings lazily every time the tooltip is shown, instead of
        // eagerly as for node labels, for efficiency.
        String shortNodeText = getInputSlot().getFigure().getDiagram().getShortNodeText();
        builder.append(getOutputSlot().getFigure().getProperties().resolveString(shortNodeText));
        builder.append(" → ");
        builder.append(getInputSlot().getFigure().getProperties().resolveString(shortNodeText));
        builder.append(" [")
               .append(getInputSlot().getOriginalIndex())
               .append("]");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "FigureConnection('" + label + "', " + getFrom().getVertex() + " to " + getTo().getVertex() + ")";
    }

    @Override
    public Port getFrom() {
        return outputSlot;
    }

    @Override
    public Cluster getFromCluster() {
        return getFrom().getVertex().getCluster();
    }

    @Override
    public Port getTo() {
        return inputSlot;
    }

    @Override
    public Cluster getToCluster() {
        return getTo().getVertex().getCluster();
    }

    @Override
    public boolean isVIP() {
        return style == ConnectionStyle.BOLD;
    }

    @Override
    public List<Point> getControlPoints() {
        return controlPoints;
    }

    @Override
    public void setControlPoints(List<Point> list) {
        controlPoints = list;
    }

    @Override
    public boolean hasSlots() {
        return true;
    }

}

