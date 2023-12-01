package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.*;
import static javax.lang.model.SourceVersion.*;

/**
 * A visitor of types based on their {@linkplain TypeKind kind} with
 * default behavior appropriate for the {@link SourceVersion#RELEASE_8
 * RELEASE_8} source version.  For {@linkplain
 * TypeMirror types} <code><i>Xyz</i></code> that may have more than one
 * kind, the <code>visit<i>Xyz</i></code> methods in this class delegate
 * to the <code>visit<i>Xyz</i>As<i>Kind</i></code> method corresponding to the
 * first argument's kind.  The <code>visit<i>Xyz</i>As<i>Kind</i></code> methods
 * call {@link #defaultAction defaultAction}, passing their arguments
 * to {@code defaultAction}'s corresponding parameters.
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
 * @see <a href="TypeKindVisitor6.html#note_for_subclasses">
 * <strong>Compatibility note for subclasses</strong></a>
 * @see TypeKindVisitor6
 * @see TypeKindVisitor7
 * @see TypeKindVisitor9
 * @see TypeKindVisitor14
 * @since 1.8
 */
@SupportedSourceVersion(RELEASE_8)
public class TypeKindVisitor8<R, P> extends TypeKindVisitor7<R, P> {
    /**
     * Constructor for concrete subclasses to call; uses {@code null}
     * for the default value.
     */
    @SuppressWarnings("deprecation") // Superclass constructor deprecated
    protected TypeKindVisitor8() {
        super(null);
    }

    /**
     * Constructor for concrete subclasses to call; uses the argument
     * for the default value.
     *
     * @param defaultValue the value to assign to {@link #DEFAULT_VALUE}
     */
    @SuppressWarnings("deprecation") // Superclass constructor deprecated
    protected TypeKindVisitor8(R defaultValue) {
        super(defaultValue);
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
    public R visitIntersection(IntersectionType t, P p) {
        return defaultAction(t, p);
    }
}
