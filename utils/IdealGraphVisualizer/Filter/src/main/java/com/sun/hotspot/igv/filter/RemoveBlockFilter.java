package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.Block;
import com.sun.hotspot.igv.graph.BlockSelector;
import com.sun.hotspot.igv.graph.Diagram;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveBlockFilter extends AbstractFilter {

    private final List<RemoveBlockRule> rules;
    private final String name;

    public RemoveBlockFilter(String name) {
        this.name = name;
        rules = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void apply(Diagram diagram) {
        for (RemoveBlockRule r : rules) {
            List<Block> selected = r.getBlockSelector().selected(diagram);
            Set<Block> toRemove = new HashSet<>(selected);
            diagram.removeAllBlocks(toRemove);
        }
    }

    public void addRule(RemoveBlockRule rule) {
        rules.add(rule);
    }

    public static class RemoveBlockRule {

        private final BlockSelector selector;

        public RemoveBlockRule(BlockSelector selector) {
            this.selector = selector;
        }

        public BlockSelector getBlockSelector() {
            return selector;
        }
    }
}
