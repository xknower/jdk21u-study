package com.sun.hotspot.igv.layout;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Vertex extends Comparable<Vertex> {

    Dimension getSize();

    Point getPosition();

    void setPosition(Point p);

    boolean isRoot();

    Cluster getCluster();
}
