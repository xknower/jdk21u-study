package com.sun.hotspot.igv.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class GraphDocument extends Properties.Entity implements ChangedEventProvider<GraphDocument>, Folder {

    private final List<FolderElement> elements;
    private final ChangedEvent<GraphDocument> changedEvent;
    private String name;

    public GraphDocument() {
        elements = new ArrayList<>();
        changedEvent = new ChangedEvent<>(this);
        setName("GraphDocument");
    }

    public void clear() {
        elements.clear();
        getChangedEvent().fire();
    }

    @Override
    public ChangedEvent<GraphDocument> getChangedEvent() {
        return changedEvent;
    }

    public void addGraphDocument(GraphDocument document) {
        if (document != this) {
            for (FolderElement e : document.elements) {
                e.setParent(this);
                this.addElement(e);
            }
            document.clear();
        }
        getChangedEvent().fire();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return getName();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GraphDocument: ").append(getProperties().toString()).append(" \n\n");
        for (FolderElement g : getElements()) {
            sb.append(g.toString());
            sb.append("\n\n");
        }

        return sb.toString();
    }

    @Override
    public List<? extends FolderElement> getElements() {
        return elements;
    }

    @Override
    public void removeElement(FolderElement element) {
        if (elements.remove(element)) {
            getChangedEvent().fire();
        }
        for (FolderElement folderElement : elements) {
            folderElement.getDisplayNameChangedEvent().fire();
        }
    }

    @Override
    public void addElement(FolderElement element) {
        elements.add(element);
        getChangedEvent().fire();
    }
}
