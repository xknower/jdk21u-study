package com.sun.hotspot.igv.bytecodes;

import com.sun.hotspot.igv.data.InputNode;
import java.util.Collections;
import java.util.Set;
import org.openide.nodes.Node;

/**
 *
 * @author Thomas Wuerthinger
 */
public class SelectBytecodesCookie implements Node.Cookie {

    private final Set<InputNode> nodes;

    /** Creates a new instance of SelectBytecodesCookie */
    public SelectBytecodesCookie(Set<InputNode> nodes) {
        this.nodes = nodes;
    }

    public Set<InputNode> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }
}
