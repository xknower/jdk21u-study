package javax.print.attribute.standard;

import java.io.Serial;
import java.util.Locale;

import javax.print.attribute.Attribute;
import javax.print.attribute.DocAttribute;
import javax.print.attribute.TextSyntax;

/**
 * Class {@code DocumentName} is a printing attribute class, a text attribute,
 * that specifies the name of a document. {@code DocumentName} is an attribute
 * of the print data (the doc), not of the Print Job. A document's name is an
 * arbitrary string defined by the client. However if a JobName is not
 * specified, the {@code DocumentName} should be used instead, which implies
 * that supporting specification of {@code DocumentName} requires reporting of
 * JobName and vice versa. See {@link JobName JobName} for more information.
 * <p>
 * <b>IPP Compatibility:</b> The string value gives the IPP name value. The
 * locale gives the IPP natural language. The category name returned by
 * {@code getName()} gives the IPP attribute name.
 *
 * @author Alan Kaminsky
 */
public final class DocumentName extends TextSyntax implements DocAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 7883105848533280430L;

    /**
     * Constructs a new document name attribute with the given document name and
     * locale.
     *
     * @param  documentName document name
     * @param  locale natural language of the text string. {@code null} is
     *         interpreted to mean the default locale as returned by
     *         {@code Locale.getDefault()}
     * @throws NullPointerException if {@code documentName} is {@code null}
     */
    public DocumentName(String documentName, Locale locale) {
        super (documentName, locale);
    }

    /**
     * Returns whether this document name attribute is equivalent to the passed
     * in object. To be equivalent, all of the following conditions must be
     * true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code DocumentName}.
     *   <li>This document name attribute's underlying string and
     *   {@code object}'s underlying string are equal.
     *   <li>This document name attribute's locale and {@code object}'s locale
     *   are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this document
     *         name attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals (object) && object instanceof DocumentName);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code DocumentName}, the category is class
     * {@code DocumentName} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return DocumentName.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code DocumentName}, the category name is
     * {@code "document-name"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "document-name";
    }
}
