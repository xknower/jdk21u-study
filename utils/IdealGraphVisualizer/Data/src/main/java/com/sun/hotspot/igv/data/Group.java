package com.sun.hotspot.igv.data;

import java.util.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Group extends Properties.Entity implements ChangedEventProvider<Group>, Folder, FolderElement {

    private final List<InputGraph> graphs;
    private InputMethod method;
    private final transient ChangedEvent<Group> changedEvent;
    private final ChangedEvent<Group> displayNameChangedEvent = new ChangedEvent<>(this);

    private Folder parent;

    public Group(Folder parent) {
        graphs = new ArrayList<>();
        changedEvent = new ChangedEvent<>(this);
        this.parent = parent;

        // Ensure that name is never null
        getProperties().setProperty("name", "");
    }

    public void setMethod(InputMethod method) {
        this.method = method;
    }

    public InputMethod getMethod() {
        return method;
    }

    @Override
    public ChangedEvent<Group> getChangedEvent() {
        return changedEvent;
    }

    @Override
    public void addElement(FolderElement element) {
        assert element instanceof InputGraph;
        graphs.add((InputGraph) element);
        element.setParent(this);
        getChangedEvent().fire();
    }

    @Override
    public void removeElement(FolderElement element) {
        assert element instanceof InputGraph;
        if (graphs.remove((InputGraph) element)) {
            getChangedEvent().fire();
        }
        for (InputGraph inputGraph : graphs) {
            assert inputGraph.getDisplayNameChangedEvent() != null;
            inputGraph.getDisplayNameChangedEvent().fire();
        }
    }

    @Override
    public List<FolderElement> getElements() {
        return Collections.unmodifiableList(graphs);
    }

    public List<InputGraph> getGraphs() {
        return Collections.unmodifiableList(graphs);
    }

    public Set<Integer> getAllNodes() {
        Set<Integer> result = new HashSet<>();
        for (InputGraph g : graphs) {
            result.addAll(g.getNodesAsSet());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group ").append(getProperties()).append("\n");
        for (FolderElement g : getElements()) {
            sb.append(g.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public ChangedEvent<Group> getDisplayNameChangedEvent() {
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
        String displayName = (getParent() == null ? "" : getParent().getElements().indexOf(this) + 1 + " - ") + getName();
        if (getProperties().get("osr") != null) {
            displayName += " [OSR]";
        }
        return displayName;
    }

    public String getType() {
        return getProperties().get("type");
    }

    InputGraph getPrev(InputGraph graph) {
        InputGraph lastGraph = null;
        for (FolderElement e : getElements()) {
            if (e == graph) {
                return lastGraph;
            }
            if (e instanceof InputGraph) {
                lastGraph = (InputGraph) e;
            }
        }
        return null;
    }

    InputGraph getNext(InputGraph graph) {
        boolean found = false;
        for (FolderElement e : getElements()) {
            if (e == graph) {
                found = true;
            } else if (found && e instanceof InputGraph) {
                return (InputGraph) e;
            }
        }
        return null;
    }

    @Override
    public Folder getParent() {
         return parent;
    }
    @Override
    public void setParent(Folder parent) {
        this.parent = parent;
    }
}
