package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an {@code @return} block tag.
 *
 * <pre>{@code
 *    @return description
 *    {@return description}
 * }</pre>
 *
 * @since 1.8
 */
public interface ReturnTree extends BlockTagTree, InlineTagTree {
    /**
     * Returns whether this instance is an inline tag.
     *
     * @return {@code true} if this instance is an inline tag, and {@code false} otherwise
     * @implSpec this implementation returns {@code false}.
     * @since 16
     */
    default boolean isInline() {
        return false;
    }

    /**
     * Returns the description of the return value of a method.
     * @return the description
     */
    List<? extends DocTree> getDescription();
}
