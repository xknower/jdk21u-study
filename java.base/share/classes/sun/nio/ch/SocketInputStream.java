package sun.nio.ch;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.IntSupplier;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * An InputStream that reads bytes from a socket channel.
 */
class SocketInputStream extends InputStream {
    private final SocketChannelImpl sc;
    private final IntSupplier timeoutSupplier;

    /**
     * Initialize a SocketInputStream that reads from the given socket channel.
     * @param sc the socket channel
     * @param timeoutSupplier supplies the read timeout, in milliseconds
     */
    SocketInputStream(SocketChannelImpl sc, IntSupplier timeoutSupplier) {
        this.sc = sc;
        this.timeoutSupplier = timeoutSupplier;
    }

    /**
     * Initialize a SocketInputStream that reads from the given socket channel.
     */
    SocketInputStream(SocketChannelImpl sc) {
        this(sc, () -> 0);
    }

    @Override
    public int read() throws IOException {
        byte[] a = new byte[1];
        int n = read(a, 0, 1);
        return (n > 0) ? (a[0] & 0xff) : -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int timeout = timeoutSupplier.getAsInt();
        if (timeout > 0) {
            long nanos = MILLISECONDS.toNanos(timeout);
            return sc.blockingRead(b, off, len, nanos);
        } else {
            return sc.blockingRead(b, off, len, 0);
        }
    }

    @Override
    public int available() throws IOException {
        return sc.available();
    }

    @Override
    public void close() throws IOException {
        sc.close();
    }
}
