package jdk.javadoc.internal.doclets.formats.html.markup;

/**
 * A type-safe wrapper around a {@code String}, for use as an "id"
 * in {@code HtmlTree} objects.
 *
 * @see HtmlTree#setId(HtmlId)
 */
public interface HtmlId {
    /**
     * Creates an id with the given name.
     *
     * @param name the name
     * @return the id
     */
    static HtmlId of(String name) {
        assert name.indexOf(' ') == -1;
        return () -> name;
    }

    /**
     * {@return the name of the id}
     */
    String name();
}
