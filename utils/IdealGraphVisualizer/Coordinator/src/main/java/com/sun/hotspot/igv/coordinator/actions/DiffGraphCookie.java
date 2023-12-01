package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.services.GraphViewer;
import com.sun.hotspot.igv.data.services.InputGraphProvider;
import com.sun.hotspot.igv.difference.Difference;
import com.sun.hotspot.igv.util.LookupHistory;
import com.sun.hotspot.igv.view.EditorTopComponent;
import com.sun.hotspot.igv.view.GraphViewerImplementation;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Thomas Wuerthinger
 */
public class DiffGraphCookie implements Node.Cookie {

    private final InputGraph graph;

    public DiffGraphCookie(InputGraph graph) {
        this.graph = graph;
    }

    private InputGraph getCurrentGraph() {
        InputGraphProvider graphProvider = LookupHistory.getLast(InputGraphProvider.class);
        if (graphProvider != null) {
            return graphProvider.getGraph();
        }
        return null;
    }

    public boolean isPossible() {
        InputGraph currentGraph = getCurrentGraph();
        return currentGraph != null && !currentGraph.isDiffGraph() && currentGraph != graph;
    }

    public void openDiff() {
        InputGraph other = getCurrentGraph();
        final GraphViewer viewer = Lookup.getDefault().lookup(GraphViewer.class);
        if (viewer != null && other != null) {
            viewer.viewDifference(other, graph);
        }
    }
}
