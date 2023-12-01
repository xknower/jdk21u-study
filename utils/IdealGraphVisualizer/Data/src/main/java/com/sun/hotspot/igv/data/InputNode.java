package com.sun.hotspot.igv.data;

/**
 *
 * @author Thomas Wuerthinger
 */
public class InputNode extends Properties.Entity {

    private int id;

    public InputNode(InputNode n) {
        super(n);
        setId(n.id);
    }

    public InputNode(int id) {
        setId(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InputNode)) {
            return false;
        }
        InputNode n = (InputNode) o;
        return n.id == id;
    }

    @Override
    public int hashCode() {
        return id * 13;
    }

    @Override
    public String toString() {
        return "Node " + id + " " + getProperties().toString();
    }
}
