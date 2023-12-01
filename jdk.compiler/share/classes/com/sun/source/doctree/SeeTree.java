package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an {@code @see} block tag.
 *
 * <pre>
 *    &#064;see "string"
 *    &#064;see &lt;a href="URL#value"&gt; label &lt;/a&gt;
 *    &#064;see reference
 * </pre>
 *
 * @since 1.8
 */
public interface SeeTree extends BlockTagTree {
    /**
     * Returns the reference.
     * @return the reference
     */
    List<? extends DocTree> getReference();
}
