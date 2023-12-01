package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an {@code @author} block tag.
 *
 * <pre>
 *    &#064;author name-text
 * </pre>
 *
 * @since 1.8
 */
public interface AuthorTree extends BlockTagTree {
    /**
     * Returns the name of the author.
     * @return the name
     */
    List<? extends DocTree> getName();
}
