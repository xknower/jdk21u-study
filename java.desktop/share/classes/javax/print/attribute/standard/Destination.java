package javax.print.attribute.standard;

import java.io.File;
import java.io.Serial;
import java.net.URI;

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.URISyntax;

/**
 * Class {@code Destination} is a printing attribute class, a {@code URI}, that
 * is used to indicate an alternate destination for the spooled printer
 * formatted data. Many {@code PrintServices} will not support the notion of a
 * destination other than the printer device, and so will not support this
 * attribute.
 * <p>
 * A common use for this attribute will be applications which want to redirect
 * output to a local disk file : eg."file:out.prn". Note that proper
 * construction of "file:" scheme {@code URI} instances should be performed
 * using the {@code toURI()} method of class {@link File File}. See the
 * documentation on that class for more information.
 * <p>
 * If a destination {@code URI} is specified in a PrintRequest and it is not
 * accessible for output by the {@code PrintService}, a {@code PrintException}
 * will be thrown. The {@code PrintException} may implement {@code URIException}
 * to provide a more specific cause.
 * <p>
 * <b>IPP Compatibility:</b> Destination is not an IPP attribute.
 *
 * @author Phil Race
 */
public final class Destination extends URISyntax
        implements PrintJobAttribute, PrintRequestAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 6776739171700415321L;

    /**
     * Constructs a new destination attribute with the specified {@code URI}.
     *
     * @param  uri {@code URI}
     * @throws NullPointerException if {@code uri} is {@code null}
     */
    public Destination(URI uri) {
        super (uri);
    }

    /**
     * Returns whether this destination attribute is equivalent to the passed in
     * object. To be equivalent, all of the following conditions must be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code Destination}.
     *   <li>This destination attribute's {@code URI} and {@code object}'s
     *   {@code URI} are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this destination
     *         attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals(object) &&
                object instanceof Destination);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code Destination}, the category is class {@code Destination}
     * itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return Destination.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code Destination}, the category name is
     * {@code "spool-data-destination"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "spool-data-destination";
    }
}
