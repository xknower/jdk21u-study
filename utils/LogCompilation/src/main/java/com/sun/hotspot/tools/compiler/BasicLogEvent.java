package com.sun.hotspot.tools.compiler;

import java.io.PrintStream;

/**
 * Provide basic data structures and behaviour for {@link LogEvent}s.
 */
public abstract class BasicLogEvent implements LogEvent {

    /**
     * The event's ID. This is a number; we represent it as a string for
     * convenience.
     */
    protected final String id;

    /**
     * The event's start time.
     */
    protected final double start;

    /**
     * The event's end time.
     */
    protected double end;

    /**
     * The compilation during which this event was signalled.
     */
    protected Compilation compilation;

    BasicLogEvent(double start, String id) {
        this.start = start;
        this.end = start;
        this.id = id;
    }

    public final double getStart() {
        return start;
    }

    public final double getEnd() {
        return end;
    }

    public final void setEnd(double end) {
        this.end = end;
    }

    public final double getElapsedTime() {
        return ((int) ((getEnd() - getStart()) * 1000)) / 1000.0;
    }

    public final String getId() {
        return id;
    }

    public final Compilation getCompilation() {
        return compilation;
    }

    /**
     * Set the compilation for this event. This is not a {@code final} method
     * as it is overridden in {@link UncommonTrapEvent}.
     */
    public void setCompilation(Compilation compilation) {
        this.compilation = compilation;
    }

    public abstract void print(PrintStream stream, boolean printID);
}
