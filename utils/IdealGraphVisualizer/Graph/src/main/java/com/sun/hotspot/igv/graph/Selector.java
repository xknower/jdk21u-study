package com.sun.hotspot.igv.graph;

import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Selector {

    List<Figure> selected(Diagram d);
}
