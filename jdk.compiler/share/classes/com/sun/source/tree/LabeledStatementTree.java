package com.sun.source.tree;

import javax.lang.model.element.Name;

/**
 * A tree node for a labeled statement.
 *
 * For example:
 * <pre>
 *   <em>label</em> : <em>statement</em>
 * </pre>
 *
 * @jls 14.7 Labeled Statements
 *
 * @author Peter von der Ah&eacute;
 * @author Jonathan Gibbons
 * @since 1.6
 */
public interface LabeledStatementTree extends StatementTree {
    /**
     * Returns the label.
     * @return the label
     */
    Name getLabel();

    /**
     * Returns the statement that is labeled.
     * @return the statement
     */
    StatementTree getStatement();
}
