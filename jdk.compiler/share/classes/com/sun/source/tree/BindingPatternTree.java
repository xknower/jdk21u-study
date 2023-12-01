package com.sun.source.tree;

/**
 * A binding pattern tree
 *
 * @since 16
 */
public interface BindingPatternTree extends PatternTree {

    /**
     * Returns the binding variable.
     * @return the binding variable
     */
    VariableTree getVariable();

}
