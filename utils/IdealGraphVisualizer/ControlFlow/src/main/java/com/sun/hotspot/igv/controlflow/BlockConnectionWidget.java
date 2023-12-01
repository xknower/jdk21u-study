package com.sun.hotspot.igv.controlflow;

import com.sun.hotspot.igv.data.InputBlockEdge;
import com.sun.hotspot.igv.layout.Cluster;
import com.sun.hotspot.igv.layout.Link;
import com.sun.hotspot.igv.layout.Port;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.visual.widget.ConnectionWidget;

/**
 *
 * @author Thomas Wuerthinger
 */
public class BlockConnectionWidget extends ConnectionWidget implements Link {

    private static final Stroke NORMAL_STROKE = new BasicStroke(1.0f);
    private static final Stroke BOLD_STROKE = new BasicStroke(2.5f);
    private static final Stroke DASHED_STROKE = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[]{5, 5}, 0);
    private static final Stroke BOLD_DASHED_STROKE = new BasicStroke(2.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[]{5, 5}, 0);

    private final BlockWidget from;
    private final BlockWidget to;
    private final Port inputSlot;
    private final Port outputSlot;
    private List<Point> points;
    private boolean isDashed = false;
    private boolean isBold = false;

    public BlockConnectionWidget(ControlFlowScene scene, InputBlockEdge edge) {
        super(scene);

        this.from = (BlockWidget) scene.findWidget(edge.getFrom());
        this.to = (BlockWidget) scene.findWidget(edge.getTo());
        inputSlot = to.getInputSlot();
        outputSlot = from.getOutputSlot();
        points = new ArrayList<>();
    }

    public Port getTo() {
        return inputSlot;
    }

    public Port getFrom() {
        return outputSlot;
    }

    public Cluster getFromCluster() {
        return null;
    }

    public Cluster getToCluster() {
        return null;
    }

    public void setBold(boolean bold) {
        this.isBold = bold;
        updateStroke();
    }

    public void setDashed(boolean dashed) {
        this.isDashed = dashed;
        updateStroke();
    }

    private void updateStroke() {
        Stroke stroke = NORMAL_STROKE;
        if (isBold) {
            if (isDashed) {
                stroke = BOLD_DASHED_STROKE;
            } else {
                stroke = BOLD_STROKE;
            }
        } else if (isDashed) {
            stroke = DASHED_STROKE;
        }
        setStroke(stroke);
    }

    public void setControlPoints(List<Point> p) {
        this.points = p;
    }

    @Override
    public List<Point> getControlPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Connection[ " + from.toString() + " - " + to.toString() + "]";
    }

    @Override
    public boolean isVIP() {
        return isBold;
    }
}
