package com.sun.hotspot.igv.graph;

import com.sun.hotspot.igv.layout.Link;
import java.awt.Color;

public interface Connection extends Link {

    public enum ConnectionStyle {
        NORMAL,
        DASHED,
        BOLD,
        INVISIBLE
    }

    public ConnectionStyle getStyle();

    public Color getColor();

    public String getToolTipText();

    public boolean hasSlots();

}
