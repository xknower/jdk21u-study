package javax.lang.model.type;

import java.util.List;

/**
 * Represents a union type.
 *
 * Union types can appear as the type of a multi-catch exception
 * parameter.
 *
 * @jls 14.20 The try statement
 * @since 1.7
 */
public interface UnionType extends TypeMirror {

    /**
     * {@return the alternatives comprising this union type}
     */
    List<? extends TypeMirror> getAlternatives();
}
