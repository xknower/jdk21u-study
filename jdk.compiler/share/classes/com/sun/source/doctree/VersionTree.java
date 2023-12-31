package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an {@code @version} block tag.
 *
 * <pre>
 *    &#064;version version-text
 * </pre>
 *
 * @since 1.8
 */
public interface VersionTree extends BlockTagTree {
    /**
     * Returns the body of the tag.
     * @return the body
     */
    List<? extends DocTree> getBody();
}
