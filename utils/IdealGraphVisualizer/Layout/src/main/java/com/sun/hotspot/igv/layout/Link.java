package com.sun.hotspot.igv.layout;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Link {

    Port getFrom();

    Cluster getFromCluster();

    Port getTo();

    Cluster getToCluster();

    boolean isVIP();

    List<Point> getControlPoints();

    void setControlPoints(List<Point> list);
}
