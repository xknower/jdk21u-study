package com.sun.hotspot.igv.view;

import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.services.GraphViewer;
import com.sun.hotspot.igv.difference.Difference;
import com.sun.hotspot.igv.graph.Diagram;
import com.sun.hotspot.igv.settings.Settings;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Thomas Wuerthinger
 */
@ServiceProvider(service=GraphViewer.class)
public class GraphViewerImplementation implements GraphViewer {

    @Override
    public void viewDifference(InputGraph firstGraph, InputGraph secondGraph) {
        if (firstGraph.getGroup() != secondGraph.getGroup()) {
            InputGraph diffGraph = Difference.createDiffGraph(firstGraph, secondGraph);
            view(diffGraph, true);
        } else {
            view(firstGraph, true);
            EditorTopComponent etc = EditorTopComponent.findEditorForGraph(firstGraph);
            if (etc != null) {
                etc.getModel().selectDiffGraph(secondGraph);
                etc.requestActive();
            }
        }
    }

    @Override
    public void view(InputGraph graph, boolean newTab) {
        if (!newTab) {
            EditorTopComponent etc = EditorTopComponent.findEditorForGraph(graph);
            if (etc != null) {
                etc.getModel().selectGraph(graph);
                etc.requestActive();
                return;
            }
        }
        DiagramViewModel model = new DiagramViewModel(graph);
        EditorTopComponent etc = new EditorTopComponent(model);
        etc.open();
        etc.requestActive();
    }
}
