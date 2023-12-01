package javax.annotation.processing;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

/**
 * An annotation used to indicate what annotation interfaces an
 * annotation processor supports.  The {@link
 * Processor#getSupportedAnnotationTypes} method can construct its
 * result from the value of this annotation, as done by {@link
 * AbstractProcessor#getSupportedAnnotationTypes}.  Only {@linkplain
 * Processor#getSupportedAnnotationTypes strings conforming to the
 * grammar} should be used as values.
 *
 * @since 1.6
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface SupportedAnnotationTypes {
    /**
     * {@return the names of the supported annotation interfaces}
     */
    String [] value();
}
