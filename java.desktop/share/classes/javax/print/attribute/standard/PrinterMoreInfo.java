package javax.print.attribute.standard;

import java.io.Serial;
import java.net.URI;

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.URISyntax;

/**
 * Class {@code PrinterMoreInfo} is a printing attribute class, a {@code URI},
 * that is used to obtain more information about this specific printer. For
 * example, this could be an HTTP type {@code URI} referencing an HTML page
 * accessible to a web browser. The information obtained from this {@code URI}
 * is intended for end user consumption. Features outside the scope of the Print
 * Service API can be accessed from this {@code URI}. The information is
 * intended to be specific to this printer instance and site specific services
 * (e.g. job pricing, services offered, end user assistance).
 * <p>
 * In contrast, the
 * {@link PrinterMoreInfoManufacturer PrinterMoreInfoManufacturer} attribute is
 * used to find out more information about this general kind of printer rather
 * than this specific printer.
 * <p>
 * <b>IPP Compatibility:</b> The string form returned by {@code toString()}
 * gives the IPP uri value. The category name returned by {@code getName()}
 * gives the IPP attribute name.
 *
 * @author Alan Kaminsky
 */
public final class PrinterMoreInfo extends URISyntax
        implements PrintServiceAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 4555850007675338574L;

    /**
     * Constructs a new printer more info attribute with the specified
     * {@code URI}.
     *
     * @param  uri {@code URI}
     * @throws NullPointerException if {@code uri} is {@code null}
     */
    public PrinterMoreInfo(URI uri) {
        super (uri);
    }

    /**
     * Returns whether this printer more info attribute is equivalent to the
     * passed in object. To be equivalent, all of the following conditions must
     * be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code PrinterMoreInfo}.
     *   <li>This printer more info attribute's {@code URI} and {@code object}'s
     *   {@code URI} are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this printer more
     *         info attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals(object) &&
                object instanceof PrinterMoreInfo);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code PrinterMoreInfo}, the category is class
     * {@code PrinterMoreInfo} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return PrinterMoreInfo.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code PrinterMoreInfo}, the category name is
     * {@code "printer-more-info"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "printer-more-info";
    }
}
