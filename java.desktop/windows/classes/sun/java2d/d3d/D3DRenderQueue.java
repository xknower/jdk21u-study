package sun.java2d.d3d;

import sun.java2d.ScreenUpdateManager;
import sun.java2d.pipe.RenderBuffer;
import sun.java2d.pipe.RenderQueue;
import static sun.java2d.pipe.BufferedOpCodes.*;

/**
 * D3D-specific implementation of RenderQueue.
 */
public class D3DRenderQueue extends RenderQueue {

    private static D3DRenderQueue theInstance;
    private static Thread rqThread;

    private D3DRenderQueue() {
    }

    /**
     * Returns the single D3DRenderQueue instance.  If it has not yet been
     * initialized, this method will first construct the single instance
     * before returning it.
     */
    public static synchronized D3DRenderQueue getInstance() {
        if (theInstance == null) {
            theInstance = new D3DRenderQueue();
            // no need to lock, no one has reference to this instance yet
            theInstance.flushAndInvokeNow(new Runnable() {
                public void run() {
                    rqThread = Thread.currentThread();
                }
            });
        }
        return theInstance;
    }

    /**
     * Flushes the single D3DRenderQueue instance synchronously.  If an
     * D3DRenderQueue has not yet been instantiated, this method is a no-op.
     * This method is useful in the case of Toolkit.sync(), in which we want
     * to flush the D3D pipeline, but only if the D3D pipeline is currently
     * enabled.  Since this class has few external dependencies, callers need
     * not be concerned that calling this method will trigger initialization
     * of the D3D pipeline and related classes.
     */
    public static void sync() {
        if (theInstance != null) {
            // need to make sure any/all screen surfaces are presented prior
            // to completing the sync operation
            D3DScreenUpdateManager mgr =
                (D3DScreenUpdateManager)ScreenUpdateManager.getInstance();
            mgr.runUpdateNow();

            theInstance.lock();
            try {
                theInstance.ensureCapacity(4);
                theInstance.getBuffer().putInt(SYNC);
                theInstance.flushNow();
            } finally {
                theInstance.unlock();
            }
        }
    }

    /**
     * Attempt to restore the devices if they're in the lost state.
     * (used when a full-screen window is activated/deactivated)
     */
    public static void restoreDevices() {
        D3DRenderQueue rq = getInstance();
        rq.lock();
        try {
            rq.ensureCapacity(4);
            rq.getBuffer().putInt(RESTORE_DEVICES);
            rq.flushNow();
        } finally {
            rq.unlock();
        }
    }

    /**
     * @return true if current thread is the render queue thread,
     * false otherwise
     */
    public static boolean isRenderQueueThread() {
        return (Thread.currentThread() == rqThread);
    }

    /**
     * Disposes the native memory associated with the given native
     * graphics config info pointer on the single queue flushing thread.
     */
    public static void disposeGraphicsConfig(long pConfigInfo) {
        D3DRenderQueue rq = getInstance();
        rq.lock();
        try {

            RenderBuffer buf = rq.getBuffer();
            rq.ensureCapacityAndAlignment(12, 4);
            buf.putInt(DISPOSE_CONFIG);
            buf.putLong(pConfigInfo);

            // this call is expected to complete synchronously, so flush now
            rq.flushNow();
        } finally {
            rq.unlock();
        }
    }

    public void flushNow() {
        // assert lock.isHeldByCurrentThread();
        flushBuffer(null);
    }

    public void flushAndInvokeNow(Runnable r) {
        // assert lock.isHeldByCurrentThread();
        flushBuffer(r);
    }

    private native void flushBuffer(long buf, int limit, Runnable task);

    private void flushBuffer(Runnable task) {
        // assert lock.isHeldByCurrentThread();
        int limit = buf.position();
        if (limit > 0 || task != null) {
            // process the queue
            flushBuffer(buf.getAddress(), limit, task);
        }
        // reset the buffer position
        buf.clear();
        // clear the set of references, since we no longer need them
        refSet.clear();
    }
}
