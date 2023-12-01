package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.Cluster;
import com.sun.hotspot.igv.layout.Link;
import com.sun.hotspot.igv.layout.Port;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ClusterEdge implements Link {

    private final ClusterNode from;
    private final ClusterNode to;
    private List<Point> points;

    public ClusterEdge(ClusterNode from, ClusterNode to) {
        assert from != null;
        assert to != null;
        this.from = from;
        this.to = to;
    }

    public Port getTo() {
        return to.getInputSlot();
    }

    public Port getFrom() {
        return from.getInputSlot();
    }

    public Cluster getFromCluster() {
        return from.getCluster();
    }

    public Cluster getToCluster() {
        return to.getCluster();
    }

    public void setControlPoints(List<Point> p) {
        this.points = p;
    }

    public List<Point> getControlPoints() {
        return points;
    }

    public boolean isVIP() {
        return false;
    }

    @Override
    public String toString() {
        return from + "->" + to;
    }
}
