package com.sun.hotspot.igv.graph;

import java.util.List;

public interface BlockSelector {
    List<Block> selected(Diagram d);
}
