package com.sun.source.tree;

/**
 * A tree node for a wildcard type argument.
 * Use {@link #getKind getKind} to determine the kind of bound.
 *
 * For example:
 * <pre>
 *   ?
 *
 *   ? extends <em>bound</em>
 *
 *   ? super <em>bound</em>
 * </pre>
 *
 * @jls 4.5.1 Type Arguments of Parameterized Types
 *
 * @author Peter von der Ah&eacute;
 * @author Jonathan Gibbons
 * @since 1.6
 */
public interface WildcardTree extends Tree {
    /**
     * Returns the bound of the wildcard.
     * @return the bound
     */
    Tree getBound();
}
