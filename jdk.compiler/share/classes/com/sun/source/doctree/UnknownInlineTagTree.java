package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an unrecognized inline tag.
 *
 * <pre>
 *    {&#064;name content}
 * </pre>
 *
 * @since 1.8
 */
public interface UnknownInlineTagTree extends InlineTagTree {
    /**
     * Returns the content of an unrecognized inline tag.
     * @return the content
     */
    List<? extends DocTree> getContent();
}
