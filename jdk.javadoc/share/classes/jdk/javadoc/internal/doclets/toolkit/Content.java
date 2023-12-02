package jdk.javadoc.internal.doclets.toolkit;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.function.Function;

/**
 * A content tree for javadoc output pages.
 */
public abstract class Content {

    /**
     * Returns a string representation of the content.
     * Newlines are represented by {@code \n}.
     *
     * @return string representation of the content
     */
    @Override
    public String toString() {
        StringWriter out = new StringWriter();
        try {
            write(out, "\n", true);
        } catch (IOException e) {
            // cannot happen from StringWriter
            throw new AssertionError(e);
        }
        return out.toString();
    }

    /**
     * Adds content to the existing content.
     * This is an optional operation.
     *
     * @implSpec This implementation throws {@linkplain UnsupportedOperationException}.
     *
     * @param content content to be added
     * @return this object
     * @throws UnsupportedOperationException if this operation is not supported by
     *                                       a particular implementation
     * @throws IllegalArgumentException      if the content is not suitable to be added
     */
    public Content add(Content content) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a string content to the existing content.
     * This is an optional operation.
     *
     * @implSpec
     * This implementation throws {@linkplain UnsupportedOperationException}.
     *
     * @param stringContent the string content to be added
     * @return this object
     * @throws UnsupportedOperationException if this operation is not supported by
     *                                       a particular implementation
     * @throws IllegalArgumentException      if the content is not suitable to be added
     */
    public Content add(CharSequence stringContent) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds content to the existing content, generated from a collection of items
     * This is an optional operation.
     *
     * @implSpec This implementation delegates to {@link #add(Content)}.
     *
     * @param items  the items to be added
     * @param mapper the function to create content for each item
     *
     * @return this object
     * @throws UnsupportedOperationException if this operation is not supported by
     *                                       a particular implementation
     * @throws IllegalArgumentException      if the content is not suitable to be added
     */
    public <T> Content addAll(Collection<T> items, Function<T, Content> mapper) {
        items.forEach(item -> add(mapper.apply(item)));
        return this;
    }

    /**
     * Writes content to a writer, using a given newline sequence, which should be
     * either {@code \n} or the platform line separator.
     *
     * @param writer the writer
     * @param newline the newline sequence to use
     * @param atNewline whether the writer has just written a newline
     * @return whether the writer has just written a newline
     * @throws IOException if an error occurs while writing the output
     */
    public abstract boolean write(Writer writer, String newline, boolean atNewline) throws IOException;

    /**
     * Returns true if the content is empty.
     *
     * @return true if no content to be displayed else return false
     */
    public abstract boolean isEmpty();

    /**
     * Returns true if this content does not affect the output and can be discarded.
     * The default implementation considers empty content as discardable.
     *
     * @return true if this content can be discarded without affecting the output
     */
    public boolean isDiscardable() {
        return isEmpty();
    }

    /**
     * {@return the number of characters of plain text content in this object}
     */
    public int charCount() {
        return 0;
    }
}
