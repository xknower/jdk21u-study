package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.services.GraphViewer;
import org.openide.cookies.OpenCookie;

public class GraphOpenCookie implements OpenCookie {

    private final GraphViewer viewer;
    private final InputGraph graph;

    public GraphOpenCookie(GraphViewer viewer, InputGraph graph) {
        this.viewer = viewer;
        this.graph = graph;
    }

    @Override
    public void open() {
        viewer.view(graph, false);
    }
}
