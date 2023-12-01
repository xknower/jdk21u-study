package com.sun.hotspot.igv.data;

import java.util.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class InputBlock {

    private List<InputNode> nodes;
    private final String name;
    private final InputGraph graph;
    private final Set<InputBlock> successors;
    private boolean artificial;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if ((!(o instanceof InputBlock))) {
            return false;
        }

        final InputBlock b = (InputBlock)o;
        final boolean result = b.nodes.equals(nodes) && b.name.equals(name) && b.successors.size() == successors.size();
        if (!result) {
            return false;
        }

        final HashSet<String> s = new HashSet<>();
        for (InputBlock succ : successors) {
            s.add(succ.name);
        }

        for (InputBlock succ : b.successors) {
            if (!s.contains(succ.name)) {
                return false;
            }
        }

        return true;
    }

    InputBlock(InputGraph graph, String name) {
        this.graph = graph;
        this.name = name;
        nodes = new ArrayList<>();
        successors = new LinkedHashSet<>(2);
        artificial = false;
    }

    public String getName() {
        return name;
    }

    public List<InputNode> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public void addNode(int id) {
        InputNode node = graph.getNode(id);
        assert node != null;
        // nodes.contains(node) is too expensive for large graphs so
        // just make sure the Graph doesn't know it yet.
        assert graph.getBlock(id) == null : "duplicate : " + node;
        graph.setBlock(node, this);
        nodes.add(node);
    }

    public Set<InputBlock> getSuccessors() {
        return Collections.unmodifiableSet(successors);
    }

    public void setNodes(List<InputNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Block " + this.getName();
    }

    void addSuccessor(InputBlock b) {
        successors.add(b);
    }

    void setArtificial() {
        this.artificial = true;
    }

    public boolean isArtificial() {
        return artificial;
    }
}
