package com.sun.org.apache.bcel.internal.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Utility class that implements a sequence of bytes which can be read via the 'readByte()' method. This is used to
 * implement a wrapper for the Java byte code stream to gain some more readability.
 */
public final class ByteSequence extends DataInputStream {

    private static final class ByteArrayStream extends ByteArrayInputStream {

        ByteArrayStream(final byte[] bytes) {
            super(bytes);
        }

        int getPosition() {
            // pos is protected in ByteArrayInputStream
            return pos;
        }

        void unreadByte() {
            if (pos > 0) {
                pos--;
            }
        }
    }

    private final ByteArrayStream byteStream;

    public ByteSequence(final byte[] bytes) {
        super(new ByteArrayStream(bytes));
        byteStream = (ByteArrayStream) in;
    }

    public int getIndex() {
        return byteStream.getPosition();
    }

    void unreadByte() {
        byteStream.unreadByte();
    }
}
