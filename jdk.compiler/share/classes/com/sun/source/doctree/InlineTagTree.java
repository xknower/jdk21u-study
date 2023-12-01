package com.sun.source.doctree;

/**
 * A tree node used as the base class for the different types of
 * inline tags.
 *
 * @since 1.8
 */
public interface InlineTagTree extends DocTree {
    /**
     * Returns the name of the tag.
     * @return the name of the tag
     */
    String getTagName();
}
