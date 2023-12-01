package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.LayoutGraph;
import com.sun.hotspot.igv.layout.LayoutManager;
import com.sun.hotspot.igv.layout.Link;
import com.sun.hotspot.igv.layout.Vertex;
import java.awt.Point;
import java.util.*;

public class LinearLayoutManager implements LayoutManager {

    // Ranking determining the vertical node ordering.
    private final Map<? extends Vertex, Integer> vertexRank;

    public LinearLayoutManager(Map<? extends Vertex, Integer> vertexRank) {
        this.vertexRank = vertexRank;
    }

    @Override
    public void doLayout(LayoutGraph graph) {
        doLayout(graph, new HashSet<>());
    }

    @Override
    public void doLayout(LayoutGraph graph, Set<? extends Link> importantLinks) {

        assert (graph.getLinks().isEmpty());

        // Sort vertices according to given rank.
        List<Vertex> vertices = new ArrayList<>(graph.getVertices());
        vertices.sort(Comparator.comparingInt((Vertex v) -> vertexRank.getOrDefault(v, Integer.MAX_VALUE)));

        // Assign vertical coordinates in rank order.
        assignVerticalCoordinates(vertices);
    }

    private void assignVerticalCoordinates(List<Vertex> vertices) {
        int curY = 0;
        for (Vertex v : vertices) {
            v.setPosition(new Point(0, curY));
            curY += v.getSize().getHeight();
        }
    }
}
