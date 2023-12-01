package com.sun.source.doctree;

/**
 * A tree node used as the base class for the different types of
 * block tags.
 *
 * @since 1.8
 */
public interface BlockTagTree extends DocTree {
    /**
     * Returns the name of the tag.
     * @return the name of the tag
     */
    String getTagName();
}
