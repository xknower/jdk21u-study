package javax.print.attribute.standard;

import java.io.Serial;
import java.util.Locale;

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.TextSyntax;

/**
 * Class {@code JobMessageFromOperator} is a printing attribute class, a text
 * attribute, that provides a message from an operator, system administrator, or
 * "intelligent" process to indicate to the end user the reasons for
 * modification or other management action taken on a job.
 * <p>
 * A Print Job's attribute set includes zero instances or one instance of a
 * {@code JobMessageFromOperator} attribute, not more than one instance. A new
 * {@code JobMessageFromOperator} attribute replaces an existing
 * {@code JobMessageFromOperator} attribute, if any. In other words,
 * {@code JobMessageFromOperator} is not intended to be a history log. If it
 * wishes, the client can detect changes to a Print Job's
 * {@code JobMessageFromOperator} attribute and maintain the client's own
 * history log of the {@code JobMessageFromOperator} attribute values.
 * <p>
 * <b>IPP Compatibility:</b> The string value gives the IPP name value. The
 * locale gives the IPP natural language. The category name returned by
 * {@code getName()} gives the IPP attribute name.
 *
 * @author Alan Kaminsky
 */
public final class JobMessageFromOperator extends TextSyntax
        implements PrintJobAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -4620751846003142047L;

    /**
     * Constructs a new job message from operator attribute with the given
     * message and locale.
     *
     * @param  message the message
     * @param  locale Natural language of the text string. {@code null} is
     *         interpreted to mean the default locale as returned by
     *         {@code Locale.getDefault()}
     * @throws NullPointerException if {@code message} is {@code null}
     */
    public JobMessageFromOperator(String message, Locale locale) {
        super (message, locale);
    }

    /**
     * Returns whether this job message from operator attribute is equivalent to
     * the passed in object. To be equivalent, all of the following conditions
     * must be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class
     *   {@code JobMessageFromOperator}.
     *   <li>This job message from operator attribute's underlying string and
     *   {@code object}'s underlying string are equal.
     *   <li>This job message from operator attribute's locale and
     *   {@code object}'s locale are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this job message
     *         from operator attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals (object) &&
                object instanceof JobMessageFromOperator);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code JobMessageFromOperator}, the category is class
     * {@code JobMessageFromOperator} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return JobMessageFromOperator.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code JobMessageFromOperator}, the category name is
     * {@code "job-message-from-operator"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "job-message-from-operator";
    }
}
