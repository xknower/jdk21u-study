package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.LayoutManager;
import com.sun.hotspot.igv.layout.*;
import java.awt.*;
import java.util.*;

public class HierarchicalCFGLayoutManager implements LayoutManager {

    private static final int BLOCK_BORDER = 5;
    private final FontMetrics fontMetrics;
    // Lays out nodes within a single cluster (basic block).
    private LayoutManager subManager;
    // Lays out clusters in the CFG.
    private LayoutManager manager;
    private Set<Cluster> clusters;

    public HierarchicalCFGLayoutManager() {
        // Anticipate block label sizes to dimension blocks appropriately.
        Canvas canvas = new Canvas();
        Font font = new Font("Arial", Font.BOLD, 14);
        fontMetrics = canvas.getFontMetrics(font);
    }

    public void setSubManager(LayoutManager manager) {
        this.subManager = manager;
    }

    public void setManager(LayoutManager manager) {
        this.manager = manager;
    }

    public void setClusters(Set<Cluster> clusters) {
        this.clusters = clusters;
    }

    public void doLayout(LayoutGraph graph, Set<? extends Link> importantLinks) {
        doLayout(graph);
    }

    public void doLayout(LayoutGraph graph) {

        // Create cluster-level nodes and edges.
        Map<Cluster, ClusterNode> clusterNode = createClusterNodes(graph);
        Set<ClusterEdge> clusterEdges = createClusterEdges(clusterNode);
        markRootClusters(clusterEdges);

        // Compute layout for each cluster.
        for (Cluster c : clusters) {
            ClusterNode n = clusterNode.get(c);
            subManager.doLayout(new LayoutGraph(n.getSubEdges(), n.getSubNodes()), new HashSet<>());
            n.updateSize();
        }

        // Compute inter-cluster layout.
        manager.doLayout(new LayoutGraph(clusterEdges, new HashSet<>(clusterNode.values())), new HashSet<>());

        // Write back results.
        writeBackClusterBounds(clusterNode);
        writeBackClusterEdgePoints(graph, clusterEdges);
    }

    private Map<Cluster, ClusterNode> createClusterNodes(LayoutGraph graph) {
        Map<Cluster, ClusterNode> clusterNode = new HashMap<>();
        for (Cluster c : clusters) {
            String blockLabel = "B" + c;
            Dimension emptySize = new Dimension(fontMetrics.stringWidth(blockLabel) + BLOCK_BORDER * 2,
                                                fontMetrics.getHeight() + BLOCK_BORDER);
            ClusterNode cn = new ClusterNode(c, c.toString(), BLOCK_BORDER, c.getNodeOffset(),
                                             fontMetrics.getHeight(), emptySize);
            clusterNode.put(c, cn);
        }

        for (Vertex v : graph.getVertices()) {
            Cluster c = v.getCluster();
            assert c != null : "Cluster of vertex " + v + " is null!";
            clusterNode.get(c).addSubNode(v);
        }
        return clusterNode;
    }

    private Set<ClusterEdge> createClusterEdges(Map<Cluster, ClusterNode> clusterNode) {
        Set<ClusterEdge> clusterEdges = new HashSet<>();
        for (Cluster c : clusters) {
            ClusterNode start = clusterNode.get(c);
            for (Cluster succ : c.getSuccessors()) {
                ClusterNode end = clusterNode.get(succ);
                if (end != null) {
                    ClusterEdge e = new ClusterEdge(start, end);
                    clusterEdges.add(e);
                }
            }
        }
        return clusterEdges;
    }

    private void markRootClusters(Set<ClusterEdge> clusterEdges) {
        Set<Vertex> roots = new LayoutGraph(clusterEdges).findRootVertices();
        for (Vertex v : roots) {
            assert v instanceof ClusterNode;
            ((ClusterNode) v).setRoot(true);
        }
    }

    private void writeBackClusterBounds(Map<Cluster, ClusterNode> clusterNode) {
        for (Cluster c : clusters) {
            ClusterNode n = clusterNode.get(c);
            c.setBounds(new Rectangle(n.getPosition(), n.getSize()));
        }
    }

    private void writeBackClusterEdgePoints(LayoutGraph graph, Set<ClusterEdge> clusterEdges) {
        // Map from "primitive" cluster edges to their input links.
        Map<AbstractMap.SimpleEntry<Cluster, Cluster>, Link> inputLink = new HashMap<>();
        for (Link l : graph.getLinks()) {
            inputLink.put(new AbstractMap.SimpleEntry<>(l.getFromCluster(), l.getToCluster()), l);
        }
        for (ClusterEdge ce : clusterEdges) {
            assert (ce.getControlPoints() != null);
            Link l = inputLink.get(new AbstractMap.SimpleEntry<>(ce.getFromCluster(), ce.getToCluster()));
            l.setControlPoints(ce.getControlPoints());
        }
    }
}
