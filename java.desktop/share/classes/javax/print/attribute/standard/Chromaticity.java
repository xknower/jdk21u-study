package javax.print.attribute.standard;

import java.io.Serial;

import javax.print.attribute.Attribute;
import javax.print.attribute.DocAttribute;
import javax.print.attribute.EnumSyntax;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.PrintRequestAttribute;

/**
 * Class {@code Chromaticity} is a printing attribute class, an enumeration,
 * that specifies monochrome or color printing. This is used by a print client
 * to specify how the print data should be generated or processed. It is not
 * descriptive of the color capabilities of the device. Query the service's
 * {@link ColorSupported ColorSupported} attribute to determine if the device
 * can be verified to support color printing.
 * <p>
 * The table below shows the effects of specifying a Chromaticity attribute of
 * {@link #MONOCHROME MONOCHROME} or {@link #COLOR COLOR} for a monochrome or
 * color document.
 *
 * <table class="striped">
 * <caption>Shows effects of specifying {@code MONOCHROME} or {@code COLOR}
 * Chromaticity attributes</caption>
 * <thead>
 *   <tr>
 *     <th scope="col">Chromaticity<br>Attribute
 *     <th scope="col">Effect on<br>Monochrome Document
 *     <th scope="col">Effect on<br>Color Document
 * </thead>
 * <tbody>
 *   <tr>
 *     <th scope="row">{@link #MONOCHROME MONOCHROME}
 *     <td>Printed as is, in monochrome
 *     <td>Printed in monochrome, with colors converted to shades of gray
 *   <tr>
 *     <th scope="row">{@link #COLOR COLOR}
 *     <td>Printed as is, in monochrome
 *     <td>Printed as is, in color
 * </tbody>
 * </table>
 * <p>
 * <b>IPP Compatibility:</b> Chromaticity is not an IPP attribute at present.
 *
 * @author Alan Kaminsky
 */
public final class Chromaticity extends EnumSyntax
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 4660543931355214012L;

    /**
     * Monochrome printing.
     */
    public static final Chromaticity MONOCHROME = new Chromaticity(0);

    /**
     * Color printing.
     */
    public static final Chromaticity COLOR = new Chromaticity(1);

    /**
     * Construct a new chromaticity enumeration value with the given integer
     * value.
     *
     * @param  value Integer value
     */
    protected Chromaticity(int value) {
        super(value);
    }

    /**
     * The string table for class {@code Chromaticity}.
     */
    private static final String[] myStringTable = {"monochrome",
                                                   "color"};

    /**
     * The enumeration value table for class {@code Chromaticity}.
     */
    private static final Chromaticity[] myEnumValueTable = {MONOCHROME,
                                                            COLOR};

    /**
     * Returns the string table for class {@code Chromaticity}.
     */
    protected String[] getStringTable() {
        return myStringTable;
    }

    /**
     * Returns the enumeration value table for class {@code Chromaticity}.
     */
    protected EnumSyntax[] getEnumValueTable() {
        return myEnumValueTable;
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code Chromaticity}, the category is the class
     * {@code Chromaticity} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return Chromaticity.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code Chromaticity}, the category name is
     * {@code "chromaticity"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "chromaticity";
    }
}
