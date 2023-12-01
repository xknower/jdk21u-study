package java.awt;

/**
 * An abstract class which provides a print graphics context for a page.
 *
 * @author      Amy Fowler
 */
public interface PrintGraphics {

    /**
     * Returns the PrintJob object from which this PrintGraphics
     * object originated.
     *
     * @return the print job for this object
     */
    public PrintJob getPrintJob();

}
