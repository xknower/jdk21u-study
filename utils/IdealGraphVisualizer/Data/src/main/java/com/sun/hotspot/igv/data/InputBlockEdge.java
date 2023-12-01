package com.sun.hotspot.igv.data;

/**
 *
 * @author Thomas Wuerthinger
 */
public class InputBlockEdge {

    public enum State {
        SAME,
        NEW,
        DELETED
    }

    private final InputBlock from;
    private final InputBlock to;
    private State state = State.SAME;
    private final String label;

    public InputBlockEdge(InputBlock from, InputBlock to, String label) {
        assert from != null;
        assert to != null;
        this.from = from;
        this.to = to;
        this.label = label;
    }

    public InputBlock getFrom() {
        return from;
    }

    public InputBlock getTo() {
        return to;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InputBlockEdge) {
            InputBlockEdge e = (InputBlockEdge) obj;
            return e.from.equals(from) && e.to.equals(to);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = from.hashCode();
        hash = 59 * hash + to.hashCode();
        return hash;
    }
}
