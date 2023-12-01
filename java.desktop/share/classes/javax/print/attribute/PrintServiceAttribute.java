package javax.print.attribute;

/**
 * Interface {@code PrintServiceAttribute} is a tagging interface which a
 * printing attribute class implements to indicate the attribute describes the
 * status of a Print Service or some other characteristic of a Print Service. A
 * Print Service instance adds a number of {@code PrintServiceAttributes} to a
 * Print service's attribute set to report the Print Service's status.
 *
 * @author Alan Kaminsky
 * @see PrintServiceAttributeSet
 */
public interface PrintServiceAttribute extends Attribute {
}
