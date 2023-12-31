package sun.lwawt.macosx;

import java.awt.*;

@SuppressWarnings("serial") // JDK implementation class
public abstract class CPrinterDialog extends Dialog {
    private final CPrinterJob fPrinterJob; // used from native

    CPrinterDialog(Frame parent, CPrinterJob printerJob) {
        super(parent, true);
        fPrinterJob = printerJob;
        setLayout(null);
    }

    private boolean retval = false;

    public void setRetVal(boolean ret) {
        retval = ret;
    }

    public boolean getRetVal() {
        return retval;
    }

    protected abstract boolean showDialog();
}
