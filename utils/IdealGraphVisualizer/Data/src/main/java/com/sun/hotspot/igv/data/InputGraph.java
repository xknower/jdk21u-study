package com.sun.hotspot.igv.data;

import java.util.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class InputGraph extends Properties.Entity implements FolderElement {

    private final Map<Integer, InputNode> nodes;
    private final List<InputEdge> edges;
    private Folder parent;
    private Group parentGroup;
    private final Map<String, InputBlock> blocks;
    private final List<InputBlockEdge> blockEdges;
    private final Map<Integer, InputBlock> nodeToBlock;
    private final boolean isDiffGraph;
    private final InputGraph firstGraph;
    private final InputGraph secondGraph;
    private final ChangedEvent<InputGraph> displayNameChangedEvent = new ChangedEvent<>(this);

    public InputGraph(InputGraph firstGraph, InputGraph secondGraph) {
        this(firstGraph.getName() + " Δ " + secondGraph.getName(), firstGraph, secondGraph);
        assert !firstGraph.isDiffGraph() && !secondGraph.isDiffGraph();

    }

    public InputGraph(String name) {
        this(name, null, null);
    }

    private InputGraph(String name, InputGraph firstGraph, InputGraph secondGraph) {
        setName(name);
        nodes = new LinkedHashMap<>();
        edges = new ArrayList<>();
        blocks = new LinkedHashMap<>();
        blockEdges = new ArrayList<>();
        nodeToBlock = new LinkedHashMap<>();
        isDiffGraph = firstGraph != null && secondGraph != null;
        this.firstGraph = firstGraph;
        this.secondGraph = secondGraph;
        if (isDiffGraph) {
            this.firstGraph.getDisplayNameChangedEvent().addListener(l -> displayNameChangedEvent.fire());
            this.secondGraph.getDisplayNameChangedEvent().addListener(l -> displayNameChangedEvent.fire());
        }
    }

    public boolean isDiffGraph() {
        return isDiffGraph;
    }

    public InputGraph getFirstGraph() {
        return firstGraph;
    }

    public InputGraph getSecondGraph() {
        return secondGraph;
    }

    @Override
    public void setParent(Folder parent) {
        this.parent = parent;
        if (parent instanceof Group) {
            assert this.parentGroup == null;
            this.parentGroup = (Group) parent;
            assert displayNameChangedEvent != null;
            assert this.parentGroup.getDisplayNameChangedEvent() != null;
            this.parentGroup.getDisplayNameChangedEvent().addListener(l -> displayNameChangedEvent.fire());
        }
    }

    public InputBlockEdge addBlockEdge(InputBlock left, InputBlock right) {
        return addBlockEdge(left, right, null);
    }

    public InputBlockEdge addBlockEdge(InputBlock left, InputBlock right, String label) {
        InputBlockEdge edge = new InputBlockEdge(left, right, label);
        blockEdges.add(edge);
        left.addSuccessor(right);
        return edge;
    }

    public List<InputNode> findRootNodes() {
        List<InputNode> result = new ArrayList<>();
        Set<Integer> nonRoot = new HashSet<>();
        for(InputEdge curEdges : getEdges()) {
            nonRoot.add(curEdges.getTo());
        }

        for(InputNode node : getNodes()) {
            if(!nonRoot.contains(node.getId())) {
                result.add(node);
            }
        }

        return result;
    }

    public Map<InputNode, List<InputEdge>> findAllOutgoingEdges() {
        Map<InputNode, List<InputEdge>> result = new HashMap<>(getNodes().size());
        for(InputNode n : this.getNodes()) {
            result.put(n, new ArrayList<>());
        }

        for(InputEdge e : this.edges) {
            int from = e.getFrom();
            InputNode fromNode = this.getNode(from);
            List<InputEdge> fromList = result.get(fromNode);
            assert fromList != null;
            fromList.add(e);
        }

        for(InputNode n : this.getNodes()) {
            List<InputEdge> list = result.get(n);
            list.sort(InputEdge.OUTGOING_COMPARATOR);
        }

        return result;
    }

    public Map<InputNode, List<InputEdge>> findAllIngoingEdges() {
        Map<InputNode, List<InputEdge>> result = new HashMap<>(getNodes().size());
        for(InputNode n : this.getNodes()) {
            result.put(n, new ArrayList<InputEdge>());
        }

        for(InputEdge e : this.edges) {
            int to = e.getTo();
            InputNode toNode = this.getNode(to);
            List<InputEdge> toList = result.get(toNode);
            assert toList != null;
            toList.add(e);
        }

        for(InputNode n : this.getNodes()) {
            List<InputEdge> list = result.get(n);
            list.sort(InputEdge.INGOING_COMPARATOR);
        }

        return result;
    }

    public List<InputEdge> findOutgoingEdges(InputNode n) {
        List<InputEdge> result = new ArrayList<>();

        for(InputEdge e : this.edges) {
            if(e.getFrom() == n.getId()) {
                result.add(e);
            }
        }

        result.sort(InputEdge.OUTGOING_COMPARATOR);

        return result;
    }

    public void clearBlocks() {
        blocks.clear();
        blockEdges.clear();
        nodeToBlock.clear();
    }

    public void ensureNodesInBlocks() {
        InputBlock noBlock = null;
        Set<InputNode> scheduledNodes = new HashSet<>();

        for (InputBlock b : getBlocks()) {
            for (InputNode n : b.getNodes()) {
                assert !scheduledNodes.contains(n);
                scheduledNodes.add(n);
            }
        }

        for (InputNode n : this.getNodes()) {
            assert nodes.get(n.getId()) == n;
            if (!scheduledNodes.contains(n)) {
                if (noBlock == null) {
                    noBlock = addArtificialBlock();
                }
                noBlock.addNode(n.getId());
            }
            assert this.getBlock(n) != null;
        }
    }

    public void setBlock(InputNode node, InputBlock block) {
        nodeToBlock.put(node.getId(), block);
    }

    public InputBlock getBlock(int nodeId) {
        return nodeToBlock.get(nodeId);
    }

    public InputBlock getBlock(InputNode node) {
        assert nodes.containsKey(node.getId());
        assert nodes.get(node.getId()).equals(node);
        return getBlock(node.getId());
    }

    public InputGraph getNext() {
        return parentGroup.getNext(this);
    }

    public InputGraph getPrev() {
        return parentGroup.getPrev(this);
    }

    @Override
    public ChangedEvent<InputGraph> getDisplayNameChangedEvent() {
        return displayNameChangedEvent;
    }

    @Override
    public void setName(String name) {
        getProperties().setProperty("name", name);
        displayNameChangedEvent.fire();
    }

    @Override
    public String getName() {
        return getProperties().get("name");
    }

    @Override
    public String getDisplayName() {
        if (isDiffGraph) {
            return firstGraph.getDisplayName() + " Δ " + secondGraph.getDisplayName();
        } else {
            return getIndex()+1 + ". " + getName();
        }
    }

    public int getIndex() {
        Group group = getGroup();
        if (group != null) {
            return group.getGraphs().indexOf(this);
        } else {
            return -1;
        }
    }

    public Collection<InputNode> getNodes() {
        return Collections.unmodifiableCollection(nodes.values());
    }

    public Set<Integer> getNodesAsSet() {
        return Collections.unmodifiableSet(nodes.keySet());
    }

    public Collection<InputBlock> getBlocks() {
        return Collections.unmodifiableCollection(blocks.values());
    }

    public void addNode(InputNode node) {
        nodes.put(node.getId(), node);
    }

    public InputNode getNode(int id) {
        return nodes.get(id);
    }

    public InputNode removeNode(int index) {
        return nodes.remove(index);
    }

    public Collection<InputEdge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public void removeEdge(InputEdge c) {
        boolean removed = edges.remove(c);
        assert removed;
    }

    public void addEdge(InputEdge c) {
        edges.add(c);
    }

    public Group getGroup() {
        return parentGroup;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph ").append(getName()).append(" ").append(getProperties().toString()).append("\n");
        for (InputNode n : nodes.values()) {
            sb.append(n.toString());
            sb.append("\n");
        }

        for (InputEdge c : edges) {
            sb.append(c.toString());
            sb.append("\n");
        }

        for (InputBlock b : getBlocks()) {
            sb.append(b.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public InputBlock addArtificialBlock() {
        InputBlock b = addBlock("(no block)");
        b.setArtificial();
        return b;
    }

    public InputBlock addBlock(String name) {
        final InputBlock b = new InputBlock(this, name);
        blocks.put(b.getName(), b);
        return b;
    }

    public InputBlock getBlock(String s) {
        return blocks.get(s);
    }

    public Collection<InputBlockEdge> getBlockEdges() {
        return Collections.unmodifiableList(blockEdges);
    }

    @Override
    public Folder getParent() {
        return parent;
    }
}
