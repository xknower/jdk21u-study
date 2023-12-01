package com.sun.source.doctree;

/**
 * A tree node for plain text.
 *
 * @since 1.8
 */
public interface TextTree extends DocTree {
    /**
     * Returns the text.
     * @return the text
     */
    String getBody();
}
