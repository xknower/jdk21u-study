package jdk.internal.net.http.hpack;

import java.nio.ByteBuffer;
import java.util.Iterator;

import static java.util.Objects.requireNonNull;

final class BulkSizeUpdateWriter implements BinaryRepresentationWriter {

    private final SizeUpdateWriter writer = new SizeUpdateWriter();
    private Iterator<Integer> maxSizes;
    private boolean writing;
    private boolean configured;

    BulkSizeUpdateWriter maxHeaderTableSizes(Iterable<Integer> sizes) {
        if (configured) {
            throw new IllegalStateException("Already configured");
        }
        requireNonNull(sizes, "sizes");
        maxSizes = sizes.iterator();
        configured = true;
        return this;
    }

    @Override
    public boolean write(HeaderTable table, ByteBuffer destination) {
        if (!configured) {
            throw new IllegalStateException("Configure first");
        }
        while (true) {
            if (writing) {
                if (!writer.write(table, destination)) {
                    return false;
                }
                writing = false;
            } else if (maxSizes.hasNext()) {
                writing = true;
                writer.reset();
                writer.maxHeaderTableSize(maxSizes.next());
            } else {
                configured = false;
                return true;
            }
        }
    }

    @Override
    public BulkSizeUpdateWriter reset() {
        maxSizes = null;
        writing = false;
        configured = false;
        return this;
    }
}
