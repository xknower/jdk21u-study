package sun.lwawt.macosx;

import java.awt.*;
import java.awt.dnd.DropTarget;

import sun.awt.dnd.SunDropTargetContextPeer;
import sun.awt.dnd.SunDropTargetEvent;

import javax.swing.*;


final class CDropTargetContextPeer extends SunDropTargetContextPeer {

    private long    fNativeDropTransfer = 0;
    private long    fNativeDataAvailable = 0;
    private Object  fNativeData    = null;
    private DropTarget insideTarget = null;

    Object awtLockAccess = new Object();

    static CDropTargetContextPeer getDropTargetContextPeer() {
        return new CDropTargetContextPeer();
    }

    private CDropTargetContextPeer() {
        super();
    }

    protected Object getNativeData(long format) {
        long nativeDropTarget = this.getNativeDragContext();

        synchronized (awtLockAccess) {
            fNativeDataAvailable = 0;

            if (fNativeDropTransfer == 0) {
                fNativeDropTransfer = startTransfer(nativeDropTarget, format);
            } else {
                addTransfer(nativeDropTarget, fNativeDropTransfer, format);
            }

            while (format != fNativeDataAvailable) {
                try {
                    awtLockAccess.wait();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        return fNativeData;
    }

    // We need to take care of dragEnter and dragExit messages because
    // native system generates them only for heavyweights
    @Override
    protected void processMotionMessage(SunDropTargetEvent event, boolean operationChanged) {
        boolean eventInsideTarget = isEventInsideTarget(event);
        if (event.getComponent().getDropTarget() == insideTarget) {
            if (!eventInsideTarget) {
                processExitMessage(event);
                return;
            }
        } else {
            if (eventInsideTarget) {
                processEnterMessage(event);
            } else {
                return;
            }
        }
        super.processMotionMessage(event, operationChanged);
    }

    /**
     * Could be called when DnD enters a heavyweight or synthesized in processMotionMessage
     */
    @Override
    protected void processEnterMessage(SunDropTargetEvent event) {
        Component c = event.getComponent();
        DropTarget dt = event.getComponent().getDropTarget();
        if (isEventInsideTarget(event)
                && dt != insideTarget
                && c.isShowing()
                && dt != null
                && dt.isActive()) {
            insideTarget = dt;
            super.processEnterMessage(event);
        }
    }

    /**
     * Could be called when DnD exits a heavyweight or synthesized in processMotionMessage
     */
    @Override
    protected void processExitMessage(SunDropTargetEvent event) {
        if (event.getComponent().getDropTarget() == insideTarget) {
            insideTarget = null;
            super.processExitMessage(event);
        }
    }

    @Override
    protected void processDropMessage(SunDropTargetEvent event) {
        if (isEventInsideTarget(event)) {
            super.processDropMessage(event);
            insideTarget = null;
        }
    }

    private boolean isEventInsideTarget(SunDropTargetEvent event) {
        Component eventSource = event.getComponent();
        Point screenPoint = event.getPoint();
        SwingUtilities.convertPointToScreen(screenPoint, eventSource);
        Point locationOnScreen = eventSource.getLocationOnScreen();
        Rectangle screenBounds = new Rectangle(locationOnScreen.x,
                                               locationOnScreen.y,
                                               eventSource.getWidth(),
                                               eventSource.getHeight());
        return screenBounds.contains(screenPoint);
    }

    @Override
    protected int postDropTargetEvent(Component component, int x, int y, int dropAction,
                                      int actions, long[] formats, long nativeCtxt, int eventID,
                                      boolean dispatchType) {
        // On MacOS X all the DnD events should be synchronous
        return super.postDropTargetEvent(component, x, y, dropAction, actions, formats, nativeCtxt,
                eventID, SunDropTargetContextPeer.DISPATCH_SYNC);
    }

    // Signal drop complete:
    protected void doDropDone(boolean success, int dropAction, boolean isLocal) {
        long nativeDropTarget = this.getNativeDragContext();

        dropDone(nativeDropTarget, fNativeDropTransfer, isLocal, success, dropAction);
    }

    // Notify transfer complete - this is an upcall from getNativeData's native calls:
    private void newData(long format, byte[] data) {
        fNativeDataAvailable = format;
        fNativeData          = data;

        awtLockAccess.notifyAll();
    }

    // Notify transfer failed - this is an upcall from getNativeData's native calls:
    private void transferFailed(long format) {
        fNativeDataAvailable = format;
        fNativeData          = null;

        awtLockAccess.notifyAll();
    }

    // Schedule a native dnd transfer:
    private native long startTransfer(long nativeDropTarget, long format);

    // Schedule a native dnd data transfer:
    private native void addTransfer(long nativeDropTarget, long nativeDropTransfer, long format);

    // Notify drop completed:
    private native void dropDone(long nativeDropTarget, long nativeDropTransfer, boolean isLocal, boolean success, int dropAction);
}
