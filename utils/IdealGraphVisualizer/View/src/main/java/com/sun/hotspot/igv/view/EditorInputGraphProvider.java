package com.sun.hotspot.igv.view;

import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.InputNode;
import com.sun.hotspot.igv.data.services.InputGraphProvider;
import java.util.Collection;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Thomas Wuerthinger
 */
@ServiceProvider(service=InputGraphProvider.class)
public class EditorInputGraphProvider implements InputGraphProvider {

    private final EditorTopComponent editor;

    public EditorInputGraphProvider() {
        editor = null;
    }

    public EditorInputGraphProvider(EditorTopComponent editor) {
        this.editor = editor;
    }

    @Override
    public InputGraph getGraph() {
        if (editor != null && EditorTopComponent.isOpen(editor)) {
            return editor.getModel().getGraph();
        } else {
            return null;
        }
    }

    @Override
    public void centerSelectedNodes() {
        if (editor != null && EditorTopComponent.isOpen(editor)) {
            editor.centerSelectedNodes();
            editor.requestActive();
        }
    }

    @Override
    public void addSelectedNodes(Collection<InputNode> nodes, boolean showIfHidden) {
        if (editor != null && EditorTopComponent.isOpen(editor)) {
            editor.addSelectedNodes(nodes, showIfHidden);
            editor.requestActive();
        }
    }

    @Override
    public void clearSelectedNodes() {
        if (editor != null && EditorTopComponent.isOpen(editor)) {
            editor.clearSelectedNodes();
            editor.requestActive();
        }
    }

    @Override
    public Iterable<InputGraph> searchBackward() {
        if (editor != null && EditorTopComponent.isOpen(editor)) {
            return editor.getModel().getGraphsBackward();
        } else {
            return null;
        }
    }

    @Override
    public Iterable<InputGraph> searchForward() {
        if (editor != null && EditorTopComponent.isOpen(editor)) {
            return editor.getModel().getGraphsForward();
        } else {
            return null;
        }
    }
}
