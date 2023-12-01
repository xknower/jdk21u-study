package com.sun.source.doctree;

import javax.lang.model.element.Name;

/**
 * An identifier in a documentation comment.
 *
 * <pre>
 *    name
 * </pre>
 *
 * @since 1.8
 */
public interface IdentifierTree extends DocTree {
    /**
     * Returns the name of the identifier.
     * @return the name
     */
    Name getName();
}
