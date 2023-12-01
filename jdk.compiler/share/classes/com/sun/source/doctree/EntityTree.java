package com.sun.source.doctree;

import javax.lang.model.element.Name;

/**
 * A tree node for an HTML entity.
 *
 * <pre>
 *    &amp;name;
 *    &amp;#digits;
 *    &amp;#Xhex-digits;
 * </pre>
 *
 * @since 1.8
 */
public interface EntityTree extends DocTree {
    /**
     * Returns the name or value of the entity.
     * @return the name or value of the entity
     */
    Name getName();
}
