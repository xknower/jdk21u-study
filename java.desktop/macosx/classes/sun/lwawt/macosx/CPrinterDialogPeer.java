package sun.lwawt.macosx;

import java.awt.*;
import java.awt.dnd.*;

import sun.lwawt.*;

public class CPrinterDialogPeer extends LWWindowPeer {
    static {
        // AWT has to be initialized for the native code to function correctly.
        Toolkit.getDefaultToolkit();
    }

    Component fTarget;

    public CPrinterDialogPeer(CPrinterDialog target, PlatformComponent platformComponent,
                              PlatformWindow platformWindow)
    {
        super(target, platformComponent, platformWindow, LWWindowPeer.PeerType.DIALOG);
        //super(target);
        fTarget = target;
        super.initialize();
    }

    protected void disposeImpl() {
        LWCToolkit.targetDisposedPeer(fTarget, this);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            Runnable task = () -> {
                CPrinterDialog printerDialog = (CPrinterDialog)fTarget;
                printerDialog.setRetVal(printerDialog.showDialog());
                printerDialog.setVisible(false);
            };
            new Thread(null, task, "PrintDialog", 0, false).start();
        }
    }

    // unused methods.
    public void toFront() {}
    public void toBack() {}
    public void setResizable(boolean resizable) {}
    public void setEnabled(boolean enable) {}
    public void setBounds(int x, int y, int width, int height) {}
    @SuppressWarnings("deprecation")
    public boolean handleEvent(Event e) { return false; }
    public void setForeground(Color c) {}
    public void setBackground(Color c) {}
    public void setFont(Font f) {}
    public boolean requestFocus(boolean temporary, boolean focusedWindowChangeAllowed) {
        return false;
    }
    void start() {}
    void invalidate(int x, int y, int width, int height) {}
    public void addDropTarget(DropTarget dt) {}
    public void removeDropTarget(DropTarget dt) {}

    // 1.5 peer method
    public boolean isRestackSupported() {
        return false;
    }

    // 1.6 peer method
    public void updateAlwaysOnTopState() {
        // no-op, since we just show the native print dialog
    }

    // 1.6 peer method
    public void updateMinimumSize() {}

    // 1.6 peer method
    public void setModalBlocked(Dialog blocker, boolean blocked) {
        // I don't think we care since this is a native dialog
    }

    // 1.6 peer method
    public void updateFocusableWindowState() {}
}
