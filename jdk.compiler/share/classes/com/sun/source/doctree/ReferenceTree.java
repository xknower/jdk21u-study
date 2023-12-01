package com.sun.source.doctree;

/**
 * A tree node for a reference to a Java language element.
 *
 * <pre>
 *    package.class#field
 *    package.class#method(<i>arg-types</i>)
 * </pre>
 *
 * @since 1.8
 */
public interface ReferenceTree extends DocTree {
    /**
     * Returns the signature of the Java language element being referenced,
     * as found in {@code @see} and similar nodes.
     * @return the signature
     */
    String getSignature();
}
