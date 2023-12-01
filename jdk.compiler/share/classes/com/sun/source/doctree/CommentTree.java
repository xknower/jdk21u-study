package com.sun.source.doctree;

/**
 * An embedded HTML comment.
 *
 * <pre>
 *    &lt;!-- text --&gt;
 * </pre>
 *
 * @since 1.8
 */
public interface CommentTree extends DocTree {
    /**
     * Returns the text of the comment.
     * @return the comment text
     */
    String getBody();
}

