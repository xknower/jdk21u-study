package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.Cluster;
import com.sun.hotspot.igv.layout.Port;
import com.sun.hotspot.igv.layout.Vertex;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ClusterInputSlotNode implements Vertex {

    private Point position;
    private final Port inputSlot;
    private final Port outputSlot;
    private final ClusterNode blockNode;

    private final String id;

    @Override
    public String toString() {
        return id;
    }

    public ClusterInputSlotNode(ClusterNode n, String id) {
        this.blockNode = n;
        this.id = id;

        n.addSubNode(this);

        final Vertex thisNode = this;
        final ClusterNode thisBlockNode = blockNode;

        outputSlot = new Port() {

            public Point getRelativePosition() {
                return new Point(0, 0);
            }

            public Vertex getVertex() {
                return thisNode;
            }

            @Override
            public String toString() {
                return "OutPort of " + thisNode;
            }
        };

        inputSlot = new Port() {

            public Point getRelativePosition() {
                Point p = new Point(thisNode.getPosition());
                p.x += blockNode.getBorder();
                p.y = 0;
                return p;
            }

            public Vertex getVertex() {
                return thisBlockNode;
            }

            @Override
            public String toString() {
                return "InPort of " + thisNode;
            }
        };
    }

    public Port getInputSlot() {
        return inputSlot;
    }

    public Port getOutputSlot() {
        return outputSlot;
    }

    public Dimension getSize() {
        return new Dimension(0, 0);
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    public Point getPosition() {
        return position;
    }

    public Cluster getCluster() {
        return null;
    }

    public boolean isRoot() {
        return true;
    }

    public int compareTo(Vertex o) {
        return toString().compareTo(o.toString());
    }

}
