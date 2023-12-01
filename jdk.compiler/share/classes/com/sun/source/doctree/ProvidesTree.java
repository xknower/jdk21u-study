package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for a {@code @provides} block tag.
 *
 * <pre>
 *    &#064;provides service-type description
 * </pre>
 *
 * @since 9
 */
public interface ProvidesTree extends BlockTagTree {
    /**
     * Returns the name of the service type being documented.
     * @return the name of the service type
     */
    ReferenceTree getServiceType();

    /**
     * Returns a description of the service type being provided by the module.
     * @return the description
     */
    List<? extends DocTree> getDescription();
}
