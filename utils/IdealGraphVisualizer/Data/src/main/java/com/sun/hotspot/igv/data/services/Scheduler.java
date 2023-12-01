package com.sun.hotspot.igv.data.services;

import com.sun.hotspot.igv.data.InputBlock;
import com.sun.hotspot.igv.data.InputGraph;
import java.util.Collection;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Scheduler {

    public Collection<InputBlock> schedule(InputGraph graph);
}
