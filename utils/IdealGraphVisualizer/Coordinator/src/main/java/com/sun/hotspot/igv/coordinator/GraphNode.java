package com.sun.hotspot.igv.coordinator;

import com.sun.hotspot.igv.coordinator.actions.*;
import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.Properties;
import com.sun.hotspot.igv.data.services.GraphViewer;
import com.sun.hotspot.igv.util.PropertiesSheet;
import com.sun.hotspot.igv.util.StringUtils;
import java.awt.Image;
import javax.swing.Action;
import org.openide.actions.OpenAction;
import org.openide.actions.RenameAction;
import org.openide.nodes.*;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Thomas Wuerthinger
 */
public class GraphNode extends AbstractNode {

    private final InputGraph graph;
    private boolean selected = false;

    /** Creates a new instance of GraphNode */
    public GraphNode(InputGraph graph) {
        this(graph, new InstanceContent());
    }

    @Override
    public boolean canRename() {
        return true;
    }

    @Override
    public void setName(String name) {
        graph.setName(name);
        fireDisplayNameChange(null, null);
    }

    @Override
    public String getName() {
        return graph.getName();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        fireDisplayNameChange(null, null);
        fireIconChange();
    }

    @Override
    public String getHtmlDisplayName() {
        String htmlDisplayName = StringUtils.escapeHTML(getDisplayName());
        if (selected) {
            htmlDisplayName = "<b>" + htmlDisplayName + "</b>";
        }
        return htmlDisplayName;
    }

    @Override
    public String getDisplayName() {
        return graph.getDisplayName();
    }

    private GraphNode(InputGraph graph, InstanceContent content) {
        super(Children.LEAF, new AbstractLookup(content));
        this.graph = graph;
        this.setDisplayName(graph.getName());
        content.add(graph);

        final GraphViewer viewer = Lookup.getDefault().lookup(GraphViewer.class);

        if (viewer != null) {
            // Action for opening the graph
            content.add(new GraphOpenCookie(viewer, graph));
        }

        // Action for removing a graph
        content.add(new GraphRemoveCookie(graph));

        // Action for diffing to the current graph
        content.add(new DiffGraphCookie(graph));

        // Action for cloning to the current graph
        content.add(new NewGraphTabCookie(viewer, graph));
    }

    @Override
    protected Sheet createSheet() {
        Sheet s = super.createSheet();
        Properties p = new Properties();
        p.add(graph.getProperties());
        p.setProperty("nodeCount", Integer.toString(graph.getNodes().size()));
        p.setProperty("edgeCount", Integer.toString(graph.getEdges().size()));
        PropertiesSheet.initializeSheet(p, s);
        return s;
    }

    @Override
    public Image getIcon(int i) {
        if (selected) {
            return ImageUtilities.loadImage("com/sun/hotspot/igv/coordinator/images/graph_selected.png");
        } else {
            return ImageUtilities.loadImage("com/sun/hotspot/igv/coordinator/images/graph.png");
        }
    }

    @Override
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }

    @Override
    public Action[] getActions(boolean b) {
        return new Action[]{
                RenameAction.findObject(RenameAction.class, true),
                DiffGraphAction.findObject(DiffGraphAction.class, true),
                NewGraphTabAction.findObject(NewGraphTabAction.class, true),
                OpenAction.findObject(OpenAction.class, true)
        };
    }

    @Override
    public Action getPreferredAction() {
        return OpenAction.findObject(OpenAction.class, true);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GraphNode) {
            return (graph == ((GraphNode) obj).graph);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return graph.hashCode();
    }
}
