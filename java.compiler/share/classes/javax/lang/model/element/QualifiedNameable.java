package javax.lang.model.element;

/**
 * A mixin interface for an element that has a qualified name.
 *
 * @since 1.7
 */
public interface QualifiedNameable extends Element {
    /**
     * {@return the fully qualified name of an element}
     */
    Name getQualifiedName();
}
