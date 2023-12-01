package com.sun.hotspot.igv.data;

import java.util.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Source {

    private final List<InputNode> sourceNodes;
    private final Set<Integer> set;

    public Source() {
        sourceNodes = new ArrayList<>(1);
        set = new LinkedHashSet<>(1);
    }

    public List<InputNode> getSourceNodes() {
        return Collections.unmodifiableList(sourceNodes);
    }

    public Set<Integer> getSourceNodesAsSet() {
        return Collections.unmodifiableSet(set);
    }

    public void addSourceNode(InputNode n) {
        if (!set.contains(n.getId())) {
            sourceNodes.add(n);
            set.add(n.getId());
        }
    }

    public interface Provider {

        Source getSource();
    }

}
