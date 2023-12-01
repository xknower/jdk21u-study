package jdk.internal.net.http.hpack;

import java.nio.ByteBuffer;

interface BinaryRepresentationWriter {

    boolean write(HeaderTable table, ByteBuffer destination);

    BinaryRepresentationWriter reset();
}
