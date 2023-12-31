package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an {@code @serial} block tag.
 *
 * <pre>
 *    &#064;serial field-description | include | exclude
 * </pre>
 *
 * @since 1.8
 */
public interface SerialTree extends BlockTagTree {
    /**
     * Returns the description of the field, or the word
     * "include" or "exclude".
     * @return the description of the field
     */
    List<? extends DocTree> getDescription();
}
