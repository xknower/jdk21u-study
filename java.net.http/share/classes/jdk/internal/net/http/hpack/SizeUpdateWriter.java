package jdk.internal.net.http.hpack;

import java.nio.ByteBuffer;

final class SizeUpdateWriter implements BinaryRepresentationWriter {

    private final IntegerWriter intWriter = new IntegerWriter();
    private int maxSize;
    private boolean tableUpdated;

    SizeUpdateWriter() { }

    SizeUpdateWriter maxHeaderTableSize(int size) {
        intWriter.configure(size, 5, 0b0010_0000);
        this.maxSize = size;
        return this;
    }

    @Override
    public boolean write(HeaderTable table, ByteBuffer destination) {
        if (!tableUpdated) {
            table.setMaxSize(maxSize);
            tableUpdated = true;
        }
        return intWriter.write(destination);
    }

    @Override
    public BinaryRepresentationWriter reset() {
        intWriter.reset();
        maxSize = -1;
        tableUpdated = false;
        return this;
    }
}
