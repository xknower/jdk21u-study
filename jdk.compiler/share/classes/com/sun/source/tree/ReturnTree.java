package com.sun.source.tree;

/**
 * A tree node for a {@code return} statement.
 *
 * For example:
 * <pre>
 *   return;
 *   return <em>expression</em>;
 * </pre>
 *
 * @jls 14.17 The return Statement
 *
 * @author Peter von der Ah&eacute;
 * @author Jonathan Gibbons
 * @since 1.6
 */
public interface ReturnTree extends StatementTree {
    /**
     * Returns the expression to be returned.
     * @return the expression
     */
    ExpressionTree getExpression();
}
