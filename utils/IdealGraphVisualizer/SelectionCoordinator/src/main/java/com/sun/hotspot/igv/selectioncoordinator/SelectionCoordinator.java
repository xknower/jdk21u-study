package com.sun.hotspot.igv.selectioncoordinator;

import com.sun.hotspot.igv.data.ChangedEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thomas
 */
public class SelectionCoordinator {

    private static final SelectionCoordinator singleInstance = new SelectionCoordinator();
    private final Set<Integer> selectedObjects;
    private final Set<Integer> highlightedObjects;
    private final ChangedEvent<SelectionCoordinator> selectedChangedEvent;
    private final ChangedEvent<SelectionCoordinator> highlightedChangedEvent;

    public static SelectionCoordinator getInstance() {
        return singleInstance;
    }

    private SelectionCoordinator() {
        selectedChangedEvent = new ChangedEvent<>(this);
        highlightedChangedEvent = new ChangedEvent<>(this);
        selectedObjects = new HashSet<>();
        highlightedObjects = new HashSet<>();
    }

    public Set<Integer> getSelectedObjects() {
        return Collections.unmodifiableSet(selectedObjects);
    }

    public Set<Integer> getHighlightedObjects() {
        return Collections.unmodifiableSet(highlightedObjects);
    }

    public ChangedEvent<SelectionCoordinator> getHighlightedChangedEvent() {
        return highlightedChangedEvent;
    }

    public ChangedEvent<SelectionCoordinator> getSelectedChangedEvent() {
        return selectedChangedEvent;
    }


    public void setSelectedObjects(Set<Integer> s) {
        assert s != null;
        selectedObjects.clear();
        selectedObjects.addAll(s);
        getSelectedChangedEvent().fire();
    }

    public void setHighlightedObjects(Set<Integer> s) {
        assert s != null;
        highlightedObjects.clear();
        highlightedObjects.addAll(s);
        getHighlightedChangedEvent().fire();
    }
}
