package javax.lang.model.element;

import java.util.Map;
import javax.lang.model.type.DeclaredType;

/**
 * Represents an annotation.  An annotation associates a value with
 * each element of an annotation interface.
 *
 * <p> Annotations should be compared using the {@code equals}
 * method.  There is no guarantee that any particular annotation will
 * always be represented by the same object.
 *
 * @since 1.6
 */
public interface AnnotationMirror {

    /**
     * {@return the type of this annotation}
     */
    DeclaredType getAnnotationType();

    /**
     * Returns the values of this annotation's elements.
     * This is returned in the form of a map that associates elements
     * with their corresponding values.
     * Only those elements with values explicitly present in the
     * annotation are included, not those that are implicitly assuming
     * their default values.
     * The order of the map matches the order in which the
     * values appear in the annotation's source.
     *
     * @apiNote
     * An annotation mirror of a marker annotation interface
     * will by definition have an empty map.
     *
     * <p>To fill in default values, use {@link
     * javax.lang.model.util.Elements#getElementValuesWithDefaults
     * getElementValuesWithDefaults}.
     *
     * @return the values of this annotation's elements,
     *          or an empty map if there are none
     */
    Map<? extends ExecutableElement, ? extends AnnotationValue> getElementValues();
}
