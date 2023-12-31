package com.sun.source.doctree;

import javax.lang.model.element.Name;

/**
 * A tree node for an {@code @systemProperty} inline tag.
 *
 * <pre>
 *    {&#064;systemProperty property-name}
 * </pre>
 *
 * @since 12
 */
public interface SystemPropertyTree extends InlineTagTree {
    /**
     * Returns the specified system property name.
     * @return the system property name
     */
    Name getPropertyName();
}
