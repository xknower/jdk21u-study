package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.Cluster;
import com.sun.hotspot.igv.layout.Link;
import com.sun.hotspot.igv.layout.Port;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ClusterOutgoingConnection implements Link {

    private List<Point> intermediatePoints;
    private final Port inputSlot;
    private final Port outputSlot;

    public ClusterOutgoingConnection(ClusterOutputSlotNode outputSlotNode, Link c) {
        this.intermediatePoints = new ArrayList<>();
        this.outputSlot = c.getFrom();
        this.inputSlot = outputSlotNode.getInputSlot();
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

    public void setControlPoints(List<Point> p) {
        this.intermediatePoints = p;
    }

    public List<Point> getControlPoints() {
        return intermediatePoints;
    }

    public boolean isVIP() {
        return false;
    }
}
