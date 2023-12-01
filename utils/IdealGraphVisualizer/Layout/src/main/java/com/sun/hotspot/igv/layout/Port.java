package com.sun.hotspot.igv.layout;

import java.awt.Point;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Port {

    Vertex getVertex();

    Point getRelativePosition();
}
