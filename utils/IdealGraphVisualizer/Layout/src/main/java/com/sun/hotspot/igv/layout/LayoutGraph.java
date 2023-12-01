package com.sun.hotspot.igv.layout;

import java.util.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class LayoutGraph {

    private final Set<? extends Link> links;
    private final SortedSet<Vertex> vertices;
    private final HashMap<Vertex, Set<Port>> inputPorts;
    private final HashMap<Vertex, Set<Port>> outputPorts;
    private final HashMap<Port, Set<Link>> portLinks;

    public LayoutGraph(Set<? extends Link> links) {
        this(links, new HashSet<>());
    }

    public LayoutGraph(Set<? extends Link> links, Set<? extends Vertex> additionalVertices) {
        this.links = links;
        assert verify();

        vertices = new TreeSet<>();
        portLinks = new HashMap<>(links.size());
        inputPorts = new HashMap<>(links.size());
        outputPorts = new HashMap<>(links.size());

        for (Link l : links) {
            if (l.getFrom() == null || l.getTo() == null) {
                continue;
            }
            Port p = l.getFrom();
            Port p2 = l.getTo();
            Vertex v1 = p.getVertex();
            Vertex v2 = p2.getVertex();

            if (!vertices.contains(v1)) {

                outputPorts.put(v1, new HashSet<>(1));
                inputPorts.put(v1, new HashSet<>(3));
                vertices.add(v1);
                assert vertices.contains(v1);
            }

            if (!vertices.contains(v2)) {
                vertices.add(v2);
                assert vertices.contains(v2);
                outputPorts.put(v2, new HashSet<>(1));
                inputPorts.put(v2, new HashSet<>(3));
            }

            if (!portLinks.containsKey(p)) {
                HashSet<Link> hashSet = new HashSet<>(3);
                portLinks.put(p, hashSet);
            }

            if (!portLinks.containsKey(p2)) {
                portLinks.put(p2, new HashSet<>(3));
            }

            outputPorts.get(v1).add(p);
            inputPorts.get(v2).add(p2);

            portLinks.get(p).add(l);
            portLinks.get(p2).add(l);
        }

        for (Vertex v : additionalVertices) {
            if (!vertices.contains(v)) {
                outputPorts.put(v, new HashSet<>(1));
                inputPorts.put(v, new HashSet<>(3));
                vertices.add(v);
            }
        }
    }

    public Set<Port> getInputPorts(Vertex v) {
        return this.inputPorts.get(v);
    }

    public Set<Port> getOutputPorts(Vertex v) {
        return this.outputPorts.get(v);
    }

    public Set<Link> getPortLinks(Port p) {
        return portLinks.get(p);
    }

    public Set<? extends Link> getLinks() {
        return links;
    }

    public boolean verify() {
        return true;
    }

    public SortedSet<Vertex> getVertices() {
        return vertices;
    }

    private void markNotRoot(Set<Vertex> notRootSet, Vertex v, Vertex startingVertex) {

        if (notRootSet.contains(v)) {
            return;
        }
        if (v != startingVertex) {
            notRootSet.add(v);
        }
        Set<Port> outPorts = getOutputPorts(v);
        for (Port p : outPorts) {
            Set<Link> portLinks = getPortLinks(p);
            for (Link l : portLinks) {
                Port other = l.getTo();
                Vertex otherVertex = other.getVertex();
                if (otherVertex != startingVertex) {
                    markNotRoot(notRootSet, otherVertex, startingVertex);
                }
            }
        }
    }

    // Returns a set of vertices with the following properties:
    // - All Vertices in the set startingRoots are elements of the set.
    // - When starting a DFS at every vertex in the set, every vertex of the
    //   whole graph is visited.
    public Set<Vertex> findRootVertices(Set<Vertex> startingRoots) {

        Set<Vertex> notRootSet = new HashSet<>();
        for (Vertex v : startingRoots) {
            if (!notRootSet.contains(v)) {
                markNotRoot(notRootSet, v, v);
            }
        }

        Set<Vertex> tmpVertices = getVertices();
        for (Vertex v : tmpVertices) {
            if (!notRootSet.contains(v)) {
                if (this.getInputPorts(v).size() == 0) {
                    markNotRoot(notRootSet, v, v);
                }
            }
        }

        for (Vertex v : tmpVertices) {
            if (!notRootSet.contains(v)) {
                markNotRoot(notRootSet, v, v);
            }
        }

        Set<Vertex> result = new HashSet<>();
        for (Vertex v : tmpVertices) {
            if (!notRootSet.contains(v)) {
                result.add(v);
            }
        }
        assert tmpVertices.size() == 0 || result.size() > 0;
        return result;
    }

    public Set<Vertex> findRootVertices() {
        return findRootVertices(new HashSet<>());
    }

    public SortedSet<Cluster> getClusters() {

        SortedSet<Cluster> clusters = new TreeSet<>();
        for (Vertex v : getVertices()) {
            if (v.getCluster() != null) {
                clusters.add(v.getCluster());
            }
        }

        return clusters;
    }

    @Override
    public String toString() {
        return "LayoutGraph(" + vertices + ", " + links + ", " + getClusters() + ")";
    }
}
