package javax.print.attribute.standard;

import java.io.Serial;
import java.util.Locale;

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.TextSyntax;

/**
 * Class {@code PrinterLocation} is a printing attribute class, a text
 * attribute, that identifies the location of the device. This could include
 * things like: {@code "in Room 123A, second floor of building XYZ"}.
 * <p>
 * <b>IPP Compatibility:</b> The string value gives the IPP name value. The
 * locale gives the IPP natural language. The category name returned by
 * {@code getName()} gives the IPP attribute name.
 *
 * @author Alan Kaminsky
 */
public final class PrinterLocation extends TextSyntax
    implements PrintServiceAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -1598610039865566337L;

    /**
     * Constructs a new printer location attribute with the given location and
     * locale.
     *
     * @param  location printer location
     * @param  locale natural language of the text string. {@code null} is
     *         interpreted to mean the default locale as returned by
     *         {@code Locale.getDefault()}
     * @throws NullPointerException if {@code location} is {@code null}
     */
    public PrinterLocation(String location, Locale locale) {
        super (location, locale);
    }

    /**
     * Returns whether this printer location attribute is equivalent to the
     * passed in object. To be equivalent, all of the following conditions must
     * be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code PrinterLocation}.
     *   <li>This printer location attribute's underlying string and
     *   {@code object}'s underlying string are equal.
     *   <li>This printer location attribute's locale and {@code object}'s
     *   locale are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this printer
     *         location attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals(object) && object instanceof PrinterLocation);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code PrinterLocation}, the category is class
     * {@code PrinterLocation} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return PrinterLocation.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code PrinterLocation}, the category name is
     * {@code "printer-location"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "printer-location";
    }
}
