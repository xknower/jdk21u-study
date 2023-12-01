package com.sun.hotspot.tools.compiler;

import java.io.PrintStream;

/**
 * Representation of a compilation phase as a log event.
 */
public class Phase extends BasicLogEvent {

    /**
     * The number of nodes in the compilation at the beginning of this phase.
     */
    private final int startNodes;

    /**
     * The number of nodes in the compilation at the end of this phase.
     */
    private int endNodes;

    /**
     * The number of live nodes in the compilation at the beginning of this
     * phase.
     */
    private final int startLiveNodes;

    /**
     * The number of live nodes in the compilation at the end of this phase.
     */
    private int endLiveNodes;

    Phase(String n, double s, int nodes, int live) {
        super(s, n);
        startNodes = nodes;
        startLiveNodes = live;
    }

    int getNodes() {
        return getEndNodes() - getStartNodes();
    }

    void setEndNodes(int n) {
        endNodes = n;
    }

    public String getName() {
        return getId();
    }

    public int getStartNodes() {
        return startNodes;
    }

    public int getEndNodes() {
        return endNodes;
    }

    /**
     * The number of live nodes added by this phase.
     */
    int getAddedLiveNodes() {
        return getEndLiveNodes() - getStartLiveNodes();
    }

    void setEndLiveNodes(int n) {
        endLiveNodes = n;
    }

    public int getStartLiveNodes() {
        return startLiveNodes;
    }

    public int getEndLiveNodes() {
        return endLiveNodes;
    }

    @Override
    public void print(PrintStream stream, boolean printID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
