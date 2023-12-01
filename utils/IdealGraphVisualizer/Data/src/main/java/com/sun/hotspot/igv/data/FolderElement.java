package com.sun.hotspot.igv.data;

public interface FolderElement {
    ChangedEvent<? extends FolderElement> getDisplayNameChangedEvent();
    void setName(String name);
    String getName();
    String getDisplayName();
    void setParent(Folder parent);
    Folder getParent();
}
