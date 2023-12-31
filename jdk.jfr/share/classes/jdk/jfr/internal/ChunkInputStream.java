package jdk.jfr.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class ChunkInputStream extends InputStream {
    private final Iterator<RepositoryChunk> chunks;
    private long unstreamedSize = 0;
    private RepositoryChunk currentChunk;
    private InputStream stream;

    ChunkInputStream(List<RepositoryChunk> chunks) throws IOException {
        List<RepositoryChunk> l = new ArrayList<>(chunks.size());
        for (RepositoryChunk c : chunks) {
            c.use(); // keep alive while we're reading.
            l.add(c);
            unstreamedSize += c.getSize();
        }

        this.chunks = l.iterator();
        nextStream();
    }

    @Override
    public int available() throws IOException {
        long total = unstreamedSize;
        if (stream != null) {
            total += stream.available();
        }
        return total <= Integer.MAX_VALUE ? (int) total : Integer.MAX_VALUE;
    }

    private boolean nextStream() throws IOException {
        if (!nextChunk()) {
            return false;
        }

        stream = new BufferedInputStream(SecuritySupport.newFileInputStream(currentChunk.getFile()));
        unstreamedSize -= currentChunk.getSize();
        return true;
    }

    private boolean nextChunk() {
        if (!chunks.hasNext()) {
            return false;
        }
        currentChunk = chunks.next();
        return true;
    }

    @Override
    public int read() throws IOException {
        while (true) {
            if (stream != null) {
                int r = stream.read();
                if (r != -1) {
                    return r;
                }
                stream.close();
                currentChunk.release();
                stream = null;
                currentChunk = null;
            }
            if (!nextStream()) {
                return -1;
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (stream != null) {
            stream.close();
            stream = null;
        }
        while (currentChunk != null) {
            currentChunk.release();
            currentChunk = null;
            if (!nextChunk()) {
                return;
            }
        }
    }
}
