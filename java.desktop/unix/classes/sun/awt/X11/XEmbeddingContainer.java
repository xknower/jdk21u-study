package sun.awt.X11;

import java.awt.*;
import java.util.HashMap;
import java.awt.event.KeyEvent;
import sun.awt.AWTAccessor;

public class XEmbeddingContainer extends XEmbedHelper implements XEventDispatcher {
    HashMap<Long, java.awt.peer.ComponentPeer> children = new HashMap<>();

    XEmbeddingContainer() {
    }

    XWindow embedder;
    void install(XWindow embedder) {
        this.embedder = embedder;
        XToolkit.addEventDispatcher(embedder.getWindow(), this);
    }
    void deinstall() {
        XToolkit.removeEventDispatcher(embedder.getWindow(), this);
    }

    void add(long child) {
        if (checkXEmbed(child)) {
            Component proxy = createChildProxy(child);
            ((Container)embedder.getTarget()).add("Center", proxy);
            XEmbeddedFramePeer peer = AWTAccessor.getComponentAccessor()
                                                 .getPeer(proxy);
            if (peer != null) {
                children.put(Long.valueOf(child), peer);
            }
        }
    }

    Component createChildProxy(long child) {
        return new XEmbedChildProxy(this, child);
    }
    void notifyChildEmbedded(long child) {
        sendMessage(child, XEMBED_EMBEDDED_NOTIFY, embedder.getWindow(), XEMBED_VERSION, 0);
    }

    void childResized(Component child) {
    }

    boolean checkXEmbed(long child) {
        long data = unsafe.allocateMemory(8);
        try {
            if (XEmbedInfo.getAtomData(child, data, 2)) {
                int protocol = unsafe.getInt(data);
                int flags = unsafe.getInt(data);
                return true;
            }
        } finally {
            unsafe.freeMemory(data);
        }
        return false;
    }

    void detachChild(long child) {
        // The embedder can unmap the client and reparent the client window
        // to the root window. If the client receives an ReparentNotify
        // event, it should check the parent field of the XReparentEvent
        // structure. If this is the root window of the window's screen, then
        // the protocol is finished and there is no further interaction. If
        // it is a window other than the root window, then the protocol
        // continues with the new parent acting as the embedder window.
        XToolkit.awtLock();
        try {
            XlibWrapper.XUnmapWindow(XToolkit.getDisplay(), child);
            XlibWrapper.XReparentWindow(XToolkit.getDisplay(), child, XToolkit.getDefaultRootWindow(), 0, 0);
        }
        finally {
            XToolkit.awtUnlock();
        }
    }

    void focusGained(long child) {
        sendMessage(child, XEMBED_FOCUS_IN, XEMBED_FOCUS_CURRENT, 0, 0);
    }
    void focusLost(long child) {
        sendMessage(child, XEMBED_FOCUS_OUT);
    }

    XEmbedChildProxyPeer getChild(long child) {
        return (XEmbedChildProxyPeer)children.get(Long.valueOf(child));
    }
    public void handleClientMessage(XEvent xev) {
        XClientMessageEvent msg = xev.get_xclient();
        if (msg.get_message_type() == XEmbed.getAtom()) {
            switch ((int)msg.get_data(1)) {
              case XEMBED_REQUEST_FOCUS:
                  long child = msg.get_data(2); // Unspecified
                  getChild(child).requestXEmbedFocus();
                  break;
            }
        }
    }
    public void dispatchEvent(XEvent xev) {
        switch(xev.get_type()) {
          case XConstants.ClientMessage:
              handleClientMessage(xev);
              break;
        }
    }

    void forwardKeyEvent(long child, KeyEvent e) {
        byte[] bdata = AWTAccessor.getAWTEventAccessor().getBData(e);
        long data = Native.toData(bdata);
        if (data == 0) {
            return;
        }
        XKeyEvent ke = new XKeyEvent(data);
        ke.set_window(child);
        XToolkit.awtLock();
        try {
            XlibWrapper.XSendEvent(XToolkit.getDisplay(), child, false, XConstants.NoEventMask, data);
        }
        finally {
            XToolkit.awtUnlock();
        }
        XlibWrapper.unsafe.freeMemory(data);
    }
}
