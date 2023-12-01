package javax.lang.model.element;

import java.util.List;

/**
 * A mixin interface for an element that has type parameters.
 *
 * @since 1.7
 */
public interface Parameterizable extends Element {
    /**
     * Returns the formal type parameters of an element in
     * declaration order.
     *
     * @return the formal type parameters, or an empty list
     * if there are none
     */
    List<? extends TypeParameterElement> getTypeParameters();
}
