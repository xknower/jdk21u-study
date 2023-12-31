package sun.awt.X11;

import java.awt.*;
import java.awt.peer.SystemTrayPeer;
import sun.awt.SunToolkit;
import sun.awt.AppContext;
import sun.awt.AWTAccessor;
import sun.util.logging.PlatformLogger;

public class XSystemTrayPeer implements SystemTrayPeer, XMSelectionListener {
    private static final PlatformLogger log = PlatformLogger.getLogger("sun.awt.X11.XSystemTrayPeer");

    SystemTray target;
    static XSystemTrayPeer peerInstance; // there is only one SystemTray peer per application

    private volatile boolean available;
    private final XMSelection selection = new XMSelection("_NET_SYSTEM_TRAY");

    private static final int SCREEN = 0;
    private static final String SYSTEM_TRAY_PROPERTY_NAME = "systemTray";
    private static final XAtom _NET_SYSTEM_TRAY = XAtom.get("_NET_SYSTEM_TRAY_S" + SCREEN);
    private static final XAtom _XEMBED_INFO = XAtom.get("_XEMBED_INFO");
    private static final XAtom _NET_SYSTEM_TRAY_OPCODE = XAtom.get("_NET_SYSTEM_TRAY_OPCODE");
    private static final XAtom _NET_WM_ICON = XAtom.get("_NET_WM_ICON");
    private static final long SYSTEM_TRAY_REQUEST_DOCK = 0;

    XSystemTrayPeer(SystemTray target) {
        this.target = target;
        peerInstance = this;

        selection.addSelectionListener(this);

        long selection_owner = selection.getOwner(SCREEN);
        available = (selection_owner != XConstants.None);

        if (log.isLoggable(PlatformLogger.Level.FINE)) {
            log.fine(" check if system tray is available. selection owner: " + selection_owner);
        }
    }

    public void ownerChanged(int screen, XMSelection sel, long newOwner, long data, long timestamp) {
        if (screen != SCREEN) {
            return;
        }
        if (!available) {
            available = true;
            firePropertyChange(SYSTEM_TRAY_PROPERTY_NAME, null, target);
        } else {
            removeTrayPeers();
        }
        createTrayPeers();
    }

    public void ownerDeath(int screen, XMSelection sel, long deadOwner) {
        if (screen != SCREEN) {
            return;
        }
        if (available) {
            available = false;
            firePropertyChange(SYSTEM_TRAY_PROPERTY_NAME, target, null);
            removeTrayPeers();
        }
    }

    public void selectionChanged(int screen, XMSelection sel, long owner, XPropertyEvent event) {
    }

    public Dimension getTrayIconSize() {
        return new Dimension(XTrayIconPeer.TRAY_ICON_HEIGHT, XTrayIconPeer.TRAY_ICON_WIDTH);
    }

    boolean isAvailable() {
        return available;
    }

    void dispose() {
        selection.removeSelectionListener(this);
    }

    // ***********************************************************************
    // ***********************************************************************

    void addTrayIcon(XTrayIconPeer tiPeer) throws AWTException {
        long selection_owner = selection.getOwner(SCREEN);

        if (log.isLoggable(PlatformLogger.Level.FINE)) {
            log.fine(" send SYSTEM_TRAY_REQUEST_DOCK message to owner: " + selection_owner);
        }

        if (selection_owner == XConstants.None) {
            throw new AWTException("TrayIcon couldn't be displayed.");
        }

        long tray_window = tiPeer.getWindow();
        long[] data = new long[] {XEmbedHelper.XEMBED_VERSION, XEmbedHelper.XEMBED_MAPPED};
        long data_ptr = Native.card32ToData(data);

        _XEMBED_INFO.setAtomData(tray_window, data_ptr, data.length);

        sendMessage(selection_owner, SYSTEM_TRAY_REQUEST_DOCK, tray_window, 0, 0);
    }

    void sendMessage(long win, long msg, long data1, long data2, long data3) {
        XClientMessageEvent xev = new XClientMessageEvent();

        try {
            xev.set_type(XConstants.ClientMessage);
            xev.set_window(win);
            xev.set_format(32);
            xev.set_message_type(_NET_SYSTEM_TRAY_OPCODE.getAtom());
            xev.set_data(0, 0);
            xev.set_data(1, msg);
            xev.set_data(2, data1);
            xev.set_data(3, data2);
            xev.set_data(4, data3);

            XToolkit.awtLock();
            try {
                XlibWrapper.XSendEvent(XToolkit.getDisplay(), win, false,
                                       XConstants.NoEventMask, xev.pData);
            } finally {
                XToolkit.awtUnlock();
            }
        } finally {
            xev.dispose();
        }
    }

    static XSystemTrayPeer getPeerInstance() {
        return peerInstance;
    }

    private void firePropertyChange(final String propertyName,
                                    final Object oldValue,
                                    final Object newValue) {
        Runnable runnable = new Runnable() {
                public void run() {
                    AWTAccessor.getSystemTrayAccessor()
                        .firePropertyChange(target, propertyName, oldValue, newValue);
                }
            };
        invokeOnEachAppContext(runnable);
    }

    private void createTrayPeers() {
        Runnable runnable = new Runnable() {
                public void run() {
                    TrayIcon[] icons = target.getTrayIcons();
                    try {
                        for (TrayIcon ti : icons) {
                            AWTAccessor.getTrayIconAccessor().addNotify(ti);
                        }
                    } catch (AWTException e) {
                    }
                }
            };
        invokeOnEachAppContext(runnable);
    }

    private void removeTrayPeers() {
        Runnable runnable = new Runnable() {
                public void run() {
                    TrayIcon[] icons = target.getTrayIcons();
                    for (TrayIcon ti : icons) {
                        AWTAccessor.getTrayIconAccessor().removeNotify(ti);
                    }
                }
            };
        invokeOnEachAppContext(runnable);
    }

    private void invokeOnEachAppContext(Runnable runnable) {
        for (AppContext appContext : AppContext.getAppContexts()) {
            SunToolkit.invokeLaterOnAppContext(appContext, runnable);
        }
    }

}
