package sun.print;

import javax.print.DocFlavor;
import javax.print.FlavorException;
import javax.print.PrintException;

@SuppressWarnings("serial") // JDK implementation class
class PrintJobFlavorException extends PrintException
    implements FlavorException {

    private DocFlavor flavor;

    PrintJobFlavorException(String s, DocFlavor f) {
        super(s);
        flavor = f;
        }

    public DocFlavor[] getUnsupportedFlavors() {
        DocFlavor [] flavors = { flavor};
            return flavors;
    }
}
