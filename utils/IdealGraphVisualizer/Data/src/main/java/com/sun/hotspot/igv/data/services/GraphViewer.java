package com.sun.hotspot.igv.data.services;

import com.sun.hotspot.igv.data.InputGraph;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface GraphViewer {

    void view(InputGraph graph, boolean newTab);

    void viewDifference(InputGraph firstGraph, InputGraph secondGraph);
}
