package sun.print;

import java.awt.print.PrinterJob;
import java.io.Serial;

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintRequestAttribute;

public class PrinterJobWrapper implements PrintRequestAttribute {

    /**
     * Use serialVersionUID from JDK 1.8 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -8792124426995707237L;

    @SuppressWarnings("serial") // Type of field is not Serializable
    private PrinterJob job;

    public PrinterJobWrapper(PrinterJob job) {
        this.job = job;
    }

    public PrinterJob getPrinterJob() {
        return job;
    }

    public final Class<? extends Attribute> getCategory() {
        return PrinterJobWrapper.class;
    }

    public final String getName() {
        return "printerjob-wrapper";
    }

    public String toString() {
       return "printerjob-wrapper: " + job.toString();
    }

    public int hashCode() {
        return job.hashCode();
    }
}
