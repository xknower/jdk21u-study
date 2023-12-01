package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for an intersection type in a cast expression.
 *
 * @author Maurizio Cimadamore
 *
 * @since 1.8
 */
public interface IntersectionTypeTree extends Tree {
    /**
     * Returns the bounds of the type.
     * @return the bounds
     */
    List<? extends Tree> getBounds();
}
