package sun.awt.X11;

import java.awt.*;

import java.util.ArrayList;

import sun.util.logging.PlatformLogger;

import sun.awt.EmbeddedFrame;
import sun.awt.SunToolkit;

import static sun.awt.X11.XConstants.*;

public class XEmbeddedFramePeer extends XFramePeer {

    private static final PlatformLogger xembedLog = PlatformLogger.getLogger("sun.awt.X11.xembed.XEmbeddedFramePeer");

    private ArrayList<AWTKeyStroke> strokes;

    XEmbedClientHelper embedder; // Caution - can be null if XEmbed is not supported
    public XEmbeddedFramePeer(EmbeddedFrame target) {
        // Don't specify PARENT_WINDOW param here. Instead we reparent
        // this embedded frame peer to the proper parent window after
        // an XEventDispatcher is registered to handle XEmbed events
        super(new XCreateWindowParams(new Object[] {
            TARGET, target,
            VISIBLE, Boolean.TRUE,
            EMBEDDED, Boolean.TRUE}));
    }

    public void preInit(XCreateWindowParams params) {
        super.preInit(params);
        strokes = new ArrayList<>();
        if (supportsXEmbed()) {
            embedder = new XEmbedClientHelper();
        }
    }
    void postInit(XCreateWindowParams params) {
        super.postInit(params);
        if (embedder != null) {
            // install X11 event dispatcher
            embedder.setClient(this);
            // reparent to XEmbed server
            embedder.install();
        } else if (getParentWindowHandle() != 0) {
            XToolkit.awtLock();
            try {
                XlibWrapper.XReparentWindow(XToolkit.getDisplay(),
                                            getWindow(),
                                            getParentWindowHandle(),
                                            0, 0);
            } finally {
                XToolkit.awtUnlock();
            }
        }
    }

    @Override
    public void dispose() {
        if (embedder != null) {
            // uninstall X11 event dispatcher
            embedder.setClient(null);
        }
        super.dispose();
    }

    public void updateMinimumSize() {
    }

    protected String getWMName() {
        return "JavaEmbeddedFrame";
    }

    final long getParentWindowHandle() {
        return ((XEmbeddedFrame)target).handle;
    }

    boolean supportsXEmbed() {
        return ((EmbeddedFrame)target).supportsXEmbed();
    }

    public boolean requestWindowFocus(long time, boolean timeProvided) {
        // Should check for active state of host application
        if (embedder != null && embedder.isActive()) {
            xembedLog.fine("Requesting focus from embedding host");
            return embedder.requestFocus();
        } else {
            xembedLog.fine("Requesting focus from X");
            return super.requestWindowFocus(time, timeProvided);
        }
    }

    protected void requestInitialFocus() {
        if (embedder != null && supportsXEmbed()) {
            embedder.requestFocus();
        } else {
            super.requestInitialFocus();
        }
    }

    protected boolean isEventDisabled(XEvent e) {
        if (embedder != null && embedder.isActive()) {
            switch (e.get_type()) {
              case XConstants.FocusIn:
              case XConstants.FocusOut:
                  return true;
            }
        }
        return super.isEventDisabled(e);
    }

    public void handleConfigureNotifyEvent(XEvent xev)
    {
        assert (SunToolkit.isAWTLockHeldByCurrentThread());
        XConfigureEvent xe = xev.get_xconfigure();
        if (xembedLog.isLoggable(PlatformLogger.Level.FINE)) {
            xembedLog.fine(xe.toString());
        }

        // fix for 5063031
        // if we use super.handleConfigureNotifyEvent() we would get wrong
        // size and position because embedded frame really is NOT a decorated one
        checkIfOnNewScreen(toGlobal(new Rectangle(scaleDown(xe.get_x()),
                                                  scaleDown(xe.get_y()),
                                                  scaleDown(xe.get_width()),
                                                  scaleDown(xe.get_height()))));

        Rectangle oldBounds = getBounds();

        synchronized (getStateLock()) {
            x = scaleDown(xe.get_x());
            y = scaleDown(xe.get_y());
            width = scaleDown(xe.get_width());
            height = scaleDown(xe.get_height());

            dimensions.setClientSize(width, height);
            dimensions.setLocation(x, y);
        }

        if (!getLocation().equals(oldBounds.getLocation())) {
            handleMoved(dimensions);
        }
        reconfigureContentWindow(dimensions);
    }

    protected void traverseOutForward() {
        if (embedder != null && embedder.isActive()) {
            if (embedder.isApplicationActive()) {
                xembedLog.fine("Traversing out Forward");
                embedder.traverseOutForward();
            }
        }
    }

    protected void traverseOutBackward() {
        if (embedder != null && embedder.isActive()) {
            if (embedder.isApplicationActive()) {
                xembedLog.fine("Traversing out Backward");
                embedder.traverseOutBackward();
            }
        }
    }

    // don't use getLocationOnScreen() inherited from XDecoratedPeer
    public Point getLocationOnScreen() {
        XToolkit.awtLock();
        try {
            return toGlobal(0, 0);
        } finally {
            XToolkit.awtUnlock();
        }
    }

    // don't use getBounds() inherited from XDecoratedPeer
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setBoundsPrivate(int x, int y, int width, int height) {
        setBounds(x, y, width, height, SET_BOUNDS | NO_EMBEDDED_CHECK);
    }

    public Rectangle getBoundsPrivate() {
        int x = 0, y = 0;
        int w = 0, h = 0;
        XWindowAttributes attr = new XWindowAttributes();

        XToolkit.awtLock();
        try {
            XlibWrapper.XGetWindowAttributes(XToolkit.getDisplay(),
                getWindow(), attr.pData);
            x = scaleDown(attr.get_x());
            y = scaleDown(attr.get_y());
            w = scaleDown(attr.get_width());
            h = scaleDown(attr.get_height());
        } finally {
            XToolkit.awtUnlock();
        }
        attr.dispose();

        return new Rectangle(x, y, w, h);
    }
    void registerAccelerator(AWTKeyStroke stroke) {
        if (stroke == null) return;
        strokes.add(stroke);
        if (embedder != null && embedder.isActive()) {
            embedder.registerAccelerator(stroke, strokes.size()-1);
        }
    }

    void unregisterAccelerator(AWTKeyStroke stroke) {
        if (stroke == null) return;
        if (embedder != null && embedder.isActive()) {
            int index = strokes.indexOf(stroke);
            embedder.unregisterAccelerator(index);
        }
    }

    void notifyStarted() {
        // Register accelerators
        if (embedder != null && embedder.isActive()) {
            int i = 0;
            for (AWTKeyStroke stroke : strokes) {
                embedder.registerAccelerator(stroke, i++);
            }
        }
        // Now we know that the embedder is an XEmbed server, so we
        // reregister the drop target to enable XDnD protocol support via
        // XEmbed.
        updateDropTarget();
    }
    void notifyStopped() {
        if (embedder != null && embedder.isActive()) {
            for (int i = strokes.size() - 1; i >= 0; i--) {
                embedder.unregisterAccelerator(i);
            }
        }
    }

    long getFocusTargetWindow() {
        return getWindow();
    }

    boolean isXEmbedActive() {
        return embedder != null && embedder.isActive();
    }

    public int getAbsoluteX()
    {
        Point absoluteLoc = XlibUtil.translateCoordinates(getWindow(),
                                                          XToolkit.getDefaultRootWindow(),
                                                          new Point(0, 0), getScale());
        return absoluteLoc != null ? absoluteLoc.x : 0;
    }

    public int getAbsoluteY()
    {
        Point absoluteLoc = XlibUtil.translateCoordinates(getWindow(),
                                                          XToolkit.getDefaultRootWindow(),
                                                          new Point(0, 0), getScale());
        return absoluteLoc != null ? absoluteLoc.y : 0;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Dimension getSize() {
        return new Dimension(width, height);
    }

    // override XWindowPeer's method to let the embedded frame to block
    // the containing window
    public void setModalBlocked(Dialog blocker, boolean blocked) {
        super.setModalBlocked(blocker, blocked);

        EmbeddedFrame frame = (EmbeddedFrame)target;
        frame.notifyModalBlocked(blocker, blocked);
    }

    public void synthesizeFocusInOut(boolean doFocus) {
        XFocusChangeEvent xev = new XFocusChangeEvent();

        XToolkit.awtLock();
        try {
            xev.set_type(doFocus ? FocusIn : FocusOut);
            xev.set_window(getFocusProxy().getWindow());
            xev.set_mode(NotifyNormal);
            XlibWrapper.XSendEvent(XToolkit.getDisplay(), getFocusProxy().getWindow(), false,
                                   NoEventMask, xev.pData);
        } finally {
            XToolkit.awtUnlock();
            xev.dispose();
        }
    }
}
