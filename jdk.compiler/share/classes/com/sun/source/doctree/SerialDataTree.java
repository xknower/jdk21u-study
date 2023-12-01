package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an {@code @serialData} block tag.
 *
 * <pre>
 *    &#064;serialData data-description
 * </pre>
 *
 * @since 1.8
 */
public interface SerialDataTree extends BlockTagTree {
    /**
     * Returns the description of the serial data.
     * @return the description
     */
    List<? extends DocTree> getDescription();
}
