package javax.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.util.Objects;

/**
 * Forwards calls to a given file object.  Subclasses of this class
 * might override some of these methods and might also provide
 * additional fields and methods.
 *
 * <p>Unless stated otherwise, references in this class to "<em>this file object</em>"
 * should be interpreted as referring indirectly to the {@link #fileObject delegate file object}.
 *
 * @param <F> the kind of file object forwarded to by this object
 * @since 1.6
 */
public class ForwardingFileObject<F extends FileObject> implements FileObject {

    /**
     * The file object to which all methods are delegated.
     */
    protected final F fileObject;

    /**
     * Creates a new instance of {@code ForwardingFileObject}.
     * @param fileObject delegate to this file object
     */
    protected ForwardingFileObject(F fileObject) {
        this.fileObject = Objects.requireNonNull(fileObject);
    }

    @Override
    public URI toUri() {
        return fileObject.toUri();
    }

    @Override
    public String getName() {
        return fileObject.getName();
    }

    /**
     * @throws IllegalStateException {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IOException {@inheritDoc}
     */
    @Override
    public InputStream openInputStream() throws IOException {
        return fileObject.openInputStream();
    }

    /**
     * @throws IllegalStateException {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IOException {@inheritDoc}
     */
    @Override
    public OutputStream openOutputStream() throws IOException {
        return fileObject.openOutputStream();
    }

    /**
     * @throws IllegalStateException {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IOException {@inheritDoc}
     */
    @Override
    public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
        return fileObject.openReader(ignoreEncodingErrors);
    }

    /**
     * @throws IllegalStateException {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IOException {@inheritDoc}
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return fileObject.getCharContent(ignoreEncodingErrors);
    }

    /**
     * @throws IllegalStateException {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IOException {@inheritDoc}
     */
    @Override
    public Writer openWriter() throws IOException {
        return fileObject.openWriter();
    }

    @Override
    public long getLastModified() {
        return fileObject.getLastModified();
    }

    @Override
    public boolean delete() {
        return fileObject.delete();
    }
}
