package javax.lang.model.element;

/**
 * Represents a value of an annotation interface element.
 * A value is of one of the following types:
 * <ul><li> a wrapper class (such as {@link Integer}) for a primitive type
 *     <li> {@code String}
 *     <li> {@code TypeMirror}
 *     <li> {@code VariableElement} (representing an enum constant)
 *     <li> {@code AnnotationMirror}
 *     <li> {@code List<? extends AnnotationValue>}
 *              (representing the elements, in declared order, if the value is an array)
 * </ul>
 *
 * @since 1.6
 */
public interface AnnotationValue {

    /**
     * {@return the value}
     */
    Object getValue();

    /**
     * {@return a string representation of this value}
     * This is returned in a form suitable for representing this value
     * in the source code of an annotation.
     */
    String toString();

    /**
     * Applies a visitor to this value.
     *
     * @param <R> the return type of the visitor's methods
     * @param <P> the type of the additional parameter to the visitor's methods
     * @param v   the visitor operating on this value
     * @param p   additional parameter to the visitor
     * @return a visitor-specified result
     */
    <R, P> R accept(AnnotationValueVisitor<R, P> v, P p);
}
