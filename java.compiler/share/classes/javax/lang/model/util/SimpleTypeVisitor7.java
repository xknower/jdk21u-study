package javax.lang.model.util;

import javax.lang.model.type.*;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import static javax.lang.model.SourceVersion.*;

/**
 * A simple visitor of types with default behavior appropriate for the
 * {@link SourceVersion#RELEASE_7 RELEASE_7} source version.
 *
 * Visit methods corresponding to {@code RELEASE_7} and earlier
 * language constructs call {@link #defaultAction defaultAction},
 * passing their arguments to {@code defaultAction}'s corresponding
 * parameters.
 *
 * @apiNote
 * Methods in this class may be overridden subject to their general
 * contract.
 *
 * @param <R> the return type of this visitor's methods.  Use {@link
 *            Void} for visitors that do not need to return results.
 * @param <P> the type of the additional parameter to this visitor's
 *            methods.  Use {@code Void} for visitors that do not need an
 *            additional parameter.
 *
 * @see <a href="SimpleTypeVisitor6.html#note_for_subclasses">
 * <strong>Compatibility note for subclasses</strong></a>
 * @see SimpleTypeVisitor6
 * @see SimpleTypeVisitor8
 * @see SimpleTypeVisitor9
 * @see SimpleTypeVisitor14
 * @since 1.7
 */
@SupportedSourceVersion(RELEASE_7)
public class SimpleTypeVisitor7<R, P> extends SimpleTypeVisitor6<R, P> {
    /**
     * Constructor for concrete subclasses; uses {@code null} for the
     * default value.
     *
     * @deprecated Release 7 is obsolete; update to a visitor for a newer
     * release level.
     */
    @Deprecated(since="12")
    protected SimpleTypeVisitor7(){
        super(null); // Superclass constructor deprecated too
    }

    /**
     * Constructor for concrete subclasses; uses the argument for the
     * default value.
     *
     * @param defaultValue the value to assign to {@link #DEFAULT_VALUE}
     *
     * @deprecated Release 7 is obsolete; update to a visitor for a newer
     * release level.
     */
    @Deprecated(since="12")
    protected SimpleTypeVisitor7(R defaultValue){
        super(defaultValue); // Superclass constructor deprecated too
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation calls {@code defaultAction}.
     *
     * @param t  {@inheritDoc}
     * @param p  {@inheritDoc}
     * @return the result of {@code defaultAction}
     */
    @Override
    public R visitUnion(UnionType t, P p) {
        return defaultAction(t, p);
    }
}
