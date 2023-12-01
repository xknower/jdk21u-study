package com.sun.hotspot.igv.graph;

import com.sun.hotspot.igv.data.InputBlock;
import com.sun.hotspot.igv.layout.Cluster;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Block implements Cluster {

    private InputBlock inputBlock;
    private Rectangle bounds;
    private Diagram diagram;

    public Block(InputBlock inputBlock, Diagram diagram) {
        this.inputBlock = inputBlock;
        this.diagram = diagram;
    }

    public InputBlock getInputBlock() {
        return inputBlock;
    }

    public Set<? extends Cluster> getSuccessors() {
        Set<Block> succs = new HashSet<Block>();
        for (InputBlock b : inputBlock.getSuccessors()) {
            if (diagram.hasBlock(b)) {
                succs.add(diagram.getBlock(b));
            }
        }
        return succs;
    }

    public Dimension getNodeOffset() {
        return new Dimension(0, -Figure.getVerticalOffset());
    }

    public void setBounds(Rectangle r) {
        this.bounds = r;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int compareTo(Cluster o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return inputBlock.getName();
    }
}

