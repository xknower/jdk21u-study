package com.sun.hotspot.igv.layout;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Set;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Cluster extends Comparable<Cluster> {

    void setBounds(Rectangle r);

    Set<? extends Cluster> getSuccessors();

    Dimension getNodeOffset();
}
