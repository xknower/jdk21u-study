package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.view.EditorTopComponent;
import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.services.GraphViewer;
import org.openide.nodes.Node;

public class NewGraphTabCookie implements Node.Cookie {

    private final GraphViewer viewer;
    private final InputGraph graph;

    public NewGraphTabCookie(GraphViewer viewer, InputGraph graph) {
        this.viewer = viewer;
        this.graph = graph;
    }

    public boolean isActive() {
        return EditorTopComponent.findEditorForGraph(graph) != null;
    }

    public void openNewTab() {
        viewer.view(graph, true);
    }
}
