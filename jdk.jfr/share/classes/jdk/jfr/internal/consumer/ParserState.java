package jdk.jfr.internal.consumer;

public final class ParserState {
    private volatile boolean closed;

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = true;
    }
}
