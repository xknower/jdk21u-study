package javax.print.attribute.standard;

import java.io.Serial;
import java.util.Locale;

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.TextSyntax;

/**
 * Class {@code PrinterMessageFromOperator} is a printing attribute class, a
 * text attribute, that provides a message from an operator, system
 * administrator, or "intelligent" process to indicate to the end user
 * information about or status of the printer, such as why it is unavailable or
 * when it is expected to be available.
 * <p>
 * A Print Service's attribute set includes zero instances or one instance of a
 * {@code PrinterMessageFromOperator} attribute, not more than one instance. A
 * new {@code PrinterMessageFromOperator} attribute replaces an existing
 * {@code PrinterMessageFromOperator} attribute, if any. In other words,
 * {@code PrinterMessageFromOperator} is not intended to be a history log. If it
 * wishes, the client can detect changes to a Print Service's
 * {@code PrinterMessageFromOperator} attribute and maintain the client's own
 * history log of the {@code PrinterMessageFromOperator} attribute values.
 * <p>
 * <b>IPP Compatibility:</b> The string value gives the IPP name value. The
 * locale gives the IPP natural language. The category name returned by
 * {@code getName()} gives the IPP attribute name.
 *
 * @author Alan Kaminsky
 */
public final class PrinterMessageFromOperator   extends TextSyntax
    implements PrintServiceAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -4486871203218629318L;

    /**
     * Constructs a new printer message from operator attribute with the given
     * message and locale.
     *
     * @param  message the message
     * @param  locale natural language of the text string. {@code null} is
     *         interpreted to mean the default locale as returned by
     *         {@code Locale.getDefault()}
     * @throws NullPointerException if {@code message} is {@code null}
     */
    public PrinterMessageFromOperator(String message, Locale locale) {
        super (message, locale);
    }

    /**
     * Returns whether this printer message from operator attribute is
     * equivalent to the passed in object. To be equivalent, all of the
     * following conditions must be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class
     *   {@code PrinterMessageFromOperator}.
     *   <li>This printer message from operator attribute's underlying string
     *   and {@code object}'s underlying string are equal.
     *   <li>This printer message from operator attribute's locale and
     *   {@code object}'s locale are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this printer
     *         message from operator attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals(object) &&
                object instanceof PrinterMessageFromOperator);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code PrinterMessageFromOperator}, the category is class
     * {@code PrinterMessageFromOperator} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return PrinterMessageFromOperator.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code PrinterMessageFromOperator}, the category name is
     * {@code "printer-message-from-operator"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "printer-message-from-operator";
    }
}
