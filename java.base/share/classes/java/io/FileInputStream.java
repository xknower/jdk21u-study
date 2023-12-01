package java.io;

import java.nio.channels.FileChannel;
import java.util.Arrays;
import jdk.internal.misc.Blocker;
import jdk.internal.util.ArraysSupport;
import sun.nio.ch.FileChannelImpl;

/**
 * A {@code FileInputStream} obtains input bytes
 * from a file in a file system. What files
 * are  available depends on the host environment.
 *
 * <p>{@code FileInputStream} is meant for reading streams of raw bytes
 * such as image data. For reading streams of characters, consider using
 * {@code FileReader}.
 *
 * @apiNote
 * The {@link #close} method should be called to release resources used by this
 * stream, either directly, or with the {@code try}-with-resources statement.
 *
 * @implSpec
 * Subclasses are responsible for the cleanup of resources acquired by the subclass.
 * Subclasses requiring that resource cleanup take place after a stream becomes
 * unreachable should use {@link java.lang.ref.Cleaner} or some other mechanism.
 *
 * @author  Arthur van Hoff
 * @see     java.io.File
 * @see     java.io.FileDescriptor
 * @see     java.io.FileOutputStream
 * @see     java.nio.file.Files#newInputStream
 * @since   1.0
 */
public class FileInputStream extends InputStream
{
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /* File Descriptor - handle to the open file */
    private final FileDescriptor fd;

    /**
     * The path of the referenced file
     * (null if the stream is created with a file descriptor)
     */
    private final String path;

    private volatile FileChannel channel;

    private final Object closeLock = new Object();

    private volatile boolean closed;

    /**
     * Creates a {@code FileInputStream} by
     * opening a connection to an actual file,
     * the file named by the path name {@code name}
     * in the file system.  A new {@code FileDescriptor}
     * object is created to represent this file
     * connection.
     * <p>
     * First, if there is a security
     * manager, its {@code checkRead} method
     * is called with the {@code name} argument
     * as its argument.
     * <p>
     * If the named file does not exist, is a directory rather than a regular
     * file, or for some other reason cannot be opened for reading then a
     * {@code FileNotFoundException} is thrown.
     *
     * @param      name   the system-dependent file name.
     * @throws     FileNotFoundException  if the file does not exist,
     *             is a directory rather than a regular file,
     *             or for some other reason cannot be opened for
     *             reading.
     * @throws     SecurityException      if a security manager exists and its
     *             {@code checkRead} method denies read access
     *             to the file.
     * @see        java.lang.SecurityManager#checkRead(java.lang.String)
     */
    public FileInputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null);
    }

    /**
     * Creates a {@code FileInputStream} by
     * opening a connection to an actual file,
     * the file named by the {@code File}
     * object {@code file} in the file system.
     * A new {@code FileDescriptor} object
     * is created to represent this file connection.
     * <p>
     * First, if there is a security manager,
     * its {@code checkRead} method  is called
     * with the path represented by the {@code file}
     * argument as its argument.
     * <p>
     * If the named file does not exist, is a directory rather than a regular
     * file, or for some other reason cannot be opened for reading then a
     * {@code FileNotFoundException} is thrown.
     *
     * @param      file   the file to be opened for reading.
     * @throws     FileNotFoundException  if the file does not exist,
     *             is a directory rather than a regular file,
     *             or for some other reason cannot be opened for
     *             reading.
     * @throws     SecurityException      if a security manager exists and its
     *             {@code checkRead} method denies read access to the file.
     * @see        java.io.File#getPath()
     * @see        java.lang.SecurityManager#checkRead(java.lang.String)
     */
    public FileInputStream(File file) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        @SuppressWarnings("removal")
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
        }
        if (name == null) {
            throw new NullPointerException();
        }
        if (file.isInvalid()) {
            throw new FileNotFoundException("Invalid file path");
        }
        fd = new FileDescriptor();
        fd.attach(this);
        path = name;
        open(name);
        FileCleanable.register(fd);       // open set the fd, register the cleanup
    }

    /**
     * Creates a {@code FileInputStream} by using the file descriptor
     * {@code fdObj}, which represents an existing connection to an
     * actual file in the file system.
     * <p>
     * If there is a security manager, its {@code checkRead} method is
     * called with the file descriptor {@code fdObj} as its argument to
     * see if it's ok to read the file descriptor. If read access is denied
     * to the file descriptor a {@code SecurityException} is thrown.
     * <p>
     * If {@code fdObj} is null then a {@code NullPointerException}
     * is thrown.
     * <p>
     * This constructor does not throw an exception if {@code fdObj}
     * is {@link java.io.FileDescriptor#valid() invalid}.
     * However, if the methods are invoked on the resulting stream to attempt
     * I/O on the stream, an {@code IOException} is thrown.
     *
     * @param      fdObj   the file descriptor to be opened for reading.
     * @throws     SecurityException      if a security manager exists and its
     *             {@code checkRead} method denies read access to the
     *             file descriptor.
     * @see        SecurityManager#checkRead(java.io.FileDescriptor)
     */
    public FileInputStream(FileDescriptor fdObj) {
        @SuppressWarnings("removal")
        SecurityManager security = System.getSecurityManager();
        if (fdObj == null) {
            throw new NullPointerException();
        }
        if (security != null) {
            security.checkRead(fdObj);
        }
        fd = fdObj;
        path = null;

        /*
         * FileDescriptor is being shared by streams.
         * Register this stream with FileDescriptor tracker.
         */
        fd.attach(this);
    }

    /**
     * Opens the specified file for reading.
     * @param name the name of the file
     */
    private native void open0(String name) throws FileNotFoundException;

    // wrap native call to allow instrumentation
    /**
     * Opens the specified file for reading.
     * @param name the name of the file
     */
    private void open(String name) throws FileNotFoundException {
        long comp = Blocker.begin();
        try {
            open0(name);
        } finally {
            Blocker.end(comp);
        }
    }

    /**
     * Reads a byte of data from this input stream. This method blocks
     * if no input is yet available.
     *
     * @return     the next byte of data, or {@code -1} if the end of the
     *             file is reached.
     * @throws     IOException {@inheritDoc}
     */
    @Override
    public int read() throws IOException {
        long comp = Blocker.begin();
        try {
            return read0();
        } finally {
            Blocker.end(comp);
        }
    }

    private native int read0() throws IOException;

    /**
     * Reads a subarray as a sequence of bytes.
     * @param     b the data to be written
     * @param     off the start offset in the data
     * @param     len the number of bytes that are written
     * @throws    IOException If an I/O error has occurred.
     */
    private native int readBytes(byte[] b, int off, int len) throws IOException;

    /**
     * Reads up to {@code b.length} bytes of data from this input
     * stream into an array of bytes. This method blocks until some input
     * is available.
     *
     * @param      b   {@inheritDoc}
     * @return     the total number of bytes read into the buffer, or
     *             {@code -1} if there is no more data because the end of
     *             the file has been reached.
     * @throws     IOException  if an I/O error occurs.
     */
    @Override
    public int read(byte[] b) throws IOException {
        long comp = Blocker.begin();
        try {
            return readBytes(b, 0, b.length);
        } finally {
            Blocker.end(comp);
        }
    }

    /**
     * Reads up to {@code len} bytes of data from this input stream
     * into an array of bytes. If {@code len} is not zero, the method
     * blocks until some input is available; otherwise, no
     * bytes are read and {@code 0} is returned.
     *
     * @param      b     {@inheritDoc}
     * @param      off   {@inheritDoc}
     * @param      len   {@inheritDoc}
     * @return     {@inheritDoc}
     * @throws     NullPointerException {@inheritDoc}
     * @throws     IndexOutOfBoundsException {@inheritDoc}
     * @throws     IOException  if an I/O error occurs.
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        long comp = Blocker.begin();
        try {
            return readBytes(b, off, len);
        } finally {
            Blocker.end(comp);
        }
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        long length = length();
        long position = position();
        long size = length - position;

        if (length <= 0 || size <= 0)
            return super.readAllBytes();

        if (size > (long) Integer.MAX_VALUE) {
            String msg =
                String.format("Required array size too large for %s: %d = %d - %d",
                    path, size, length, position);
            throw new OutOfMemoryError(msg);
        }

        int capacity = (int)size;
        byte[] buf = new byte[capacity];

        int nread = 0;
        int n;
        for (;;) {
            // read to EOF which may read more or less than initial size, e.g.,
            // file is truncated while we are reading
            while ((n = read(buf, nread, capacity - nread)) > 0)
                nread += n;

            // if last call to read() returned -1, we are done; otherwise,
            // try to read one more byte and if that fails we're done too
            if (n < 0 || (n = read()) < 0)
                break;

            // one more byte was read; need to allocate a larger buffer
            capacity = Math.max(ArraysSupport.newLength(capacity,
                                                        1,         // min growth
                                                        capacity), // pref growth
                                DEFAULT_BUFFER_SIZE);
            buf = Arrays.copyOf(buf, capacity);
            buf[nread++] = (byte)n;
        }
        return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        if (len < 0)
            throw new IllegalArgumentException("len < 0");
        if (len == 0)
            return new byte[0];

        long length = length();
        long position = position();
        long size = length - position;

        if (length <= 0 || size <= 0)
            return super.readNBytes(len);

        int capacity = (int)Math.min(len, size);
        byte[] buf = new byte[capacity];

        int remaining = capacity;
        int nread = 0;
        int n;
        do {
            n = read(buf, nread, remaining);
            if (n > 0) {
                nread += n;
                remaining -= n;
            } else if (n == 0) {
                // Block until a byte is read or EOF is detected
                byte b = (byte)read();
                if (b == -1 )
                    break;
                buf[nread++] = b;
                remaining--;
            }
        } while (n >= 0 && remaining > 0);
        return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long transferTo(OutputStream out) throws IOException {
        long transferred = 0L;
        if (out instanceof FileOutputStream fos) {
            FileChannel fc = getChannel();
            long pos = fc.position();
            transferred = fc.transferTo(pos, Long.MAX_VALUE, fos.getChannel());
            long newPos = pos + transferred;
            fc.position(newPos);
            if (newPos >= fc.size()) {
                return transferred;
            }
        }
        try {
            return Math.addExact(transferred, super.transferTo(out));
        } catch (ArithmeticException ignore) {
            return Long.MAX_VALUE;
        }
    }

    private long length() throws IOException {
        long comp = Blocker.begin();
        try {
            return length0();
        } finally {
            Blocker.end(comp);
        }
    }
    private native long length0() throws IOException;

    private long position() throws IOException {
        long comp = Blocker.begin();
        try {
            return position0();
        } finally {
            Blocker.end(comp);
        }
    }
    private native long position0() throws IOException;

    /**
     * Skips over and discards {@code n} bytes of data from the
     * input stream.
     *
     * <p>The {@code skip} method may, for a variety of
     * reasons, end up skipping over some smaller number of bytes,
     * possibly {@code 0}. If {@code n} is negative, the method
     * will try to skip backwards. In case the backing file does not support
     * backward skip at its current position, an {@code IOException} is
     * thrown. The actual number of bytes skipped is returned. If it skips
     * forwards, it returns a positive value. If it skips backwards, it
     * returns a negative value.
     *
     * <p>This method may skip more bytes than what are remaining in the
     * backing file. This produces no exception and the number of bytes skipped
     * may include some number of bytes that were beyond the EOF of the
     * backing file. Attempting to read from the stream after skipping past
     * the end will result in -1 indicating the end of the file.
     *
     * @param      n   {@inheritDoc}
     * @return     the actual number of bytes skipped.
     * @throws     IOException  if n is negative, if the stream does not
     *             support seek, or if an I/O error occurs.
     */
    @Override
    public long skip(long n) throws IOException {
        long comp = Blocker.begin();
        try {
            return skip0(n);
        } finally {
            Blocker.end(comp);
        }
    }

    private native long skip0(long n) throws IOException;

    /**
     * Returns an estimate of the number of remaining bytes that can be read (or
     * skipped over) from this input stream without blocking by the next
     * invocation of a method for this input stream. Returns 0 when the file
     * position is beyond EOF. The next invocation might be the same thread
     * or another thread. A single read or skip of this many bytes will not
     * block, but may read or skip fewer bytes.
     *
     * <p> In some cases, a non-blocking read (or skip) may appear to be
     * blocked when it is merely slow, for example when reading large
     * files over slow networks.
     *
     * @return     an estimate of the number of remaining bytes that can be read
     *             (or skipped over) from this input stream without blocking.
     * @throws     IOException  if this file input stream has been closed by calling
     *             {@code close} or an I/O error occurs.
     */
    @Override
    public int available() throws IOException {
        long comp = Blocker.begin();
        try {
            return available0();
        } finally {
            Blocker.end(comp);
        }
    }

    private native int available0() throws IOException;

    /**
     * Closes this file input stream and releases any system resources
     * associated with the stream.
     *
     * <p> If this stream has an associated channel then the channel is closed
     * as well.
     *
     * @apiNote
     * Overriding {@link #close} to perform cleanup actions is reliable
     * only when called directly or when called by try-with-resources.
     *
     * @implSpec
     * Subclasses requiring that resource cleanup take place after a stream becomes
     * unreachable should use the {@link java.lang.ref.Cleaner} mechanism.
     *
     * <p>
     * If this stream has an associated channel then this method will close the
     * channel, which in turn will close this stream. Subclasses that override
     * this method should be prepared to handle possible reentrant invocation.
     *
     * @throws     IOException  {@inheritDoc}
     *
     * @revised 1.4
     */
    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }
        synchronized (closeLock) {
            if (closed) {
                return;
            }
            closed = true;
        }

        FileChannel fc = channel;
        if (fc != null) {
            // possible race with getChannel(), benign since
            // FileChannel.close is final and idempotent
            fc.close();
        }

        fd.closeAll(new Closeable() {
            public void close() throws IOException {
               fd.close();
           }
        });
    }

    /**
     * Returns the {@code FileDescriptor}
     * object  that represents the connection to
     * the actual file in the file system being
     * used by this {@code FileInputStream}.
     *
     * @return     the file descriptor object associated with this stream.
     * @throws     IOException  if an I/O error occurs.
     * @see        java.io.FileDescriptor
     */
    public final FileDescriptor getFD() throws IOException {
        if (fd != null) {
            return fd;
        }
        throw new IOException();
    }

    /**
     * Returns the unique {@link java.nio.channels.FileChannel FileChannel}
     * object associated with this file input stream.
     *
     * <p> The initial {@link java.nio.channels.FileChannel#position()
     * position} of the returned channel will be equal to the
     * number of bytes read from the file so far.  Reading bytes from this
     * stream will increment the channel's position.  Changing the channel's
     * position, either explicitly or by reading, will change this stream's
     * file position.
     *
     * @return  the file channel associated with this file input stream
     *
     * @since 1.4
     */
    public FileChannel getChannel() {
        FileChannel fc = this.channel;
        if (fc == null) {
            synchronized (this) {
                fc = this.channel;
                if (fc == null) {
                    this.channel = fc = FileChannelImpl.open(fd, path, true,
                        false, false, this);
                    if (closed) {
                        try {
                            // possible race with close(), benign since
                            // FileChannel.close is final and idempotent
                            fc.close();
                        } catch (IOException ioe) {
                            throw new InternalError(ioe); // should not happen
                        }
                    }
                }
            }
        }
        return fc;
    }

    private static native void initIDs();

    static {
        initIDs();
    }
}
