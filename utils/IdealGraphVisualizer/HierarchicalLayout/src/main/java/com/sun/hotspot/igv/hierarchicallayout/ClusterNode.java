package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.Cluster;
import com.sun.hotspot.igv.layout.Link;
import com.sun.hotspot.igv.layout.Port;
import com.sun.hotspot.igv.layout.Vertex;
import java.awt.Dimension;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ClusterNode implements Vertex {

    private Cluster cluster;
    private Port inputSlot;
    private final Set<Vertex> subNodes;
    private Dimension size;
    private Point position;
    private final Set<Link> subEdges;
    private boolean root;
    private final String name;
    private final int border;
    private final Dimension nodeOffset;
    private final int headerVerticalSpace;
    private final Dimension emptySize;

    public ClusterNode(Cluster cluster, String name, int border,
                       Dimension nodeOffset, int headerVerticalSpace,
                       Dimension emptySize) {
        this.subNodes = new HashSet<>();
        this.subEdges = new HashSet<>();
        this.cluster = cluster;
        this.position = new Point(0, 0);
        this.name = name;
        this.border = border;
        this.nodeOffset = nodeOffset;
        this.headerVerticalSpace = headerVerticalSpace;
        this.emptySize = emptySize;
        if (emptySize.width > 0 || emptySize.height > 0) {
            updateSize();
        }
    }

    public ClusterNode(Cluster cluster, String name) {
        this(cluster, name, 20, new Dimension(0, 0), 0, new Dimension(0, 0));
    }

    public String getName() {
        return name;
    }

    public void addSubNode(Vertex v) {
        subNodes.add(v);
    }

    public void addSubEdge(Link l) {
        subEdges.add(l);
    }

    public Set<Link> getSubEdges() {
        return Collections.unmodifiableSet(subEdges);
    }

    public void updateSize() {
        calculateSize();

        final ClusterNode widget = this;
        inputSlot = new Port() {

            public Point getRelativePosition() {
                return new Point(size.width / 2, 0);
            }

            public Vertex getVertex() {
                return widget;
            }

            @Override
            public String toString() {
                return "ClusterInput(" + name + ")";
            }
        };
    }

    private void calculateSize() {

        if (subNodes.isEmpty()) {
            size = emptySize;
            return;
        }

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;


        for (Vertex n : subNodes) {
            Point p = n.getPosition();
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
            maxX = Math.max(maxX, p.x + n.getSize().width);
            maxY = Math.max(maxY, p.y + n.getSize().height);
        }

        for (Link l : subEdges) {
            List<Point> points = l.getControlPoints();
            for (Point p : points) {
                if (p != null) {
                    minX = Math.min(minX, p.x);
                    maxX = Math.max(maxX, p.x);
                    minY = Math.min(minY, p.y);
                    maxY = Math.max(maxY, p.y);
                }
            }
        }

        size = new Dimension(maxX - minX, maxY - minY + headerVerticalSpace);

        // Normalize coordinates
        for (Vertex n : subNodes) {
            n.setPosition(new Point(n.getPosition().x - minX + nodeOffset.width,
                                    n.getPosition().y - minY + nodeOffset.height + headerVerticalSpace));
        }

        for (Link l : subEdges) {
            List<Point> points = new ArrayList<>(l.getControlPoints());
            for (Point p : points) {
                p.x -= minX;
                p.y -= minY;
            }
            l.setControlPoints(points);

        }

        size.width += 2 * border;
        size.height += 2 * border;
    }

    public Port getInputSlot() {
        return inputSlot;

    }

    public Dimension getSize() {
        return size;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point pos) {

        this.position = pos;
        for (Vertex n : subNodes) {
            Point cur = new Point(n.getPosition());
            cur.translate(pos.x + border, pos.y + border);
            n.setPosition(cur);
        }

        for (Link e : subEdges) {
            List<Point> arr = e.getControlPoints();
            ArrayList<Point> newArr = new ArrayList<>(arr.size());
            for (Point p : arr) {
                if (p != null) {
                    Point p2 = new Point(p);
                    p2.translate(pos.x + border, pos.y + border);
                    newArr.add(p2);
                } else {
                    newArr.add(null);
                }
            }

            e.setControlPoints(newArr);
        }
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster c) {
        cluster = c;
    }

    public void setRoot(boolean b) {
        root = b;
    }

    public boolean isRoot() {
        return root;
    }

    public int getBorder() {
        return border;
    }

    public int compareTo(Vertex o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<? extends Vertex> getSubNodes() {
        return subNodes;
    }
}
