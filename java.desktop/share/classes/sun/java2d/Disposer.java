package sun.java2d;

import sun.awt.util.ThreadGroupUtils;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * This class is used for registering and disposing the native
 * data associated with java objects.
 *
 * The object can register itself by calling one of the addRecord
 * methods and providing either the pointer to the native disposal
 * method or a descendant of the DisposerRecord class with overridden
 * dispose() method.
 *
 * When the object becomes unreachable, the dispose() method
 * of the associated DisposerRecord object will be called.
 *
 * @see DisposerRecord
 */
@SuppressWarnings("removal")
public class Disposer implements Runnable {
    private static final ReferenceQueue<Object> queue = new ReferenceQueue<>();
    private static final Hashtable<java.lang.ref.Reference<Object>, DisposerRecord> records =
        new Hashtable<>();

    private static Disposer disposerInstance;
    public static final int WEAK = 0;
    public static final int PHANTOM = 1;
    public static int refType = PHANTOM;

    static {
        java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.loadLibrary("awt");
                    return null;
                }
            });
        initIDs();
        String type = java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction("sun.java2d.reftype"));
        if (type != null) {
            if (type.equals("weak")) {
                refType = WEAK;
                System.err.println("Using WEAK refs");
            } else {
                refType = PHANTOM;
                System.err.println("Using PHANTOM refs");
            }
        }
        disposerInstance = new Disposer();
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            String name = "Java2D Disposer";
            ThreadGroup rootTG = ThreadGroupUtils.getRootThreadGroup();
            Thread t = new Thread(rootTG, disposerInstance, name, 0, false);
            t.setContextClassLoader(null);
            t.setDaemon(true);
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
            return null;
        });
    }

    /**
     * Registers the object and the native data for later disposal.
     * @param target Object to be registered
     * @param disposeMethod pointer to the native disposal method
     * @param pData pointer to the data to be passed to the
     *              native disposal method
     */
    public static void addRecord(Object target,
                                 long disposeMethod, long pData)
    {
        disposerInstance.add(target,
                             new DefaultDisposerRecord(disposeMethod, pData));
    }

    /**
     * Registers the object and the native data for later disposal.
     * @param target Object to be registered
     * @param rec the associated DisposerRecord object
     * @see DisposerRecord
     */
    public static void addRecord(Object target, DisposerRecord rec) {
        disposerInstance.add(target, rec);
    }

    /**
     * Performs the actual registration of the target object to be disposed.
     * @param target Object to be registered, or if target is an instance
     *               of DisposerTarget, its associated disposer referent
     *               will be the Object that is registered
     * @param rec the associated DisposerRecord object
     * @see DisposerRecord
     */
    synchronized void add(Object target, DisposerRecord rec) {
        if (target instanceof DisposerTarget) {
            target = ((DisposerTarget)target).getDisposerReferent();
        }
        java.lang.ref.Reference<Object> ref;
        if (refType == PHANTOM) {
            ref = new PhantomReference<>(target, queue);
        } else {
            ref = new WeakReference<>(target, queue);
        }
        records.put(ref, rec);
    }

    public void run() {
        while (true) {
            try {
                Reference<?> obj = queue.remove();
                obj.clear();
                DisposerRecord rec = records.remove(obj);
                safeDispose(rec);
                obj = null;
                rec = null;
                clearDeferredRecords();
            } catch (Exception e) {
                System.out.println("Exception while removing reference.");
            }
        }
    }

    /*
     * This is a marker interface that, if implemented, means it
     * doesn't acquire any special locks, and is safe to
     * be disposed in the poll loop on whatever thread
     * which happens to be the Toolkit thread, is in use.
     */
    public static interface PollDisposable {
    }

    private static ConcurrentLinkedDeque<DisposerRecord> deferredRecords = new ConcurrentLinkedDeque<>();

    private static void safeDispose(DisposerRecord rec) {
        try {
            rec.dispose();
        } catch (final Exception e) {
            System.out.println("Exception while disposing deferred rec.");
        }
    }

    private static void clearDeferredRecords() {
        while (!deferredRecords.isEmpty()) {
            final DisposerRecord rec = deferredRecords.pollFirst();
            if (rec != null) {
                safeDispose(rec);
            }
        }
    }

    /*
     * Set to indicate the queue is presently being polled.
     */
    public static volatile boolean pollingQueue;

    /*
     * The pollRemove() method is called back from a dispose method
     * that is running on the toolkit thread and wants to
     * dispose any pending refs that are safe to be disposed
     * on that thread.
     */
    public static void pollRemove() {

        /* This should never be called recursively, so this check
         * is just a safeguard against the unexpected.
         */
        if (pollingQueue) {
            return;
        }
        Reference<?> obj;
        pollingQueue = true;
        int freed = 0;
        int deferred = 0;
        try {
            while ( freed < 10000 && deferred < 100 &&
                    (obj = queue.poll()) != null ) {
                freed++;
                obj.clear();
                DisposerRecord rec = records.remove(obj);
                if (rec instanceof PollDisposable) {
                    safeDispose(rec);
                    obj = null;
                    rec = null;
                } else {
                    if (rec == null) { // shouldn't happen, but just in case.
                        continue;
                    }
                    deferred++;
                    deferredRecords.offerLast(rec);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while removing reference.");
        } finally {
            pollingQueue = false;
        }
    }

    private static native void initIDs();

    /*
     * This was added for use by the 2D font implementation to avoid creation
     * of an additional disposer thread.
     * WARNING: this thread class monitors a specific queue, so a reference
     * added here must have been created with this queue. Failure to do
     * so will clutter the records hashmap and no one will be cleaning up
     * the reference queue.
     */
    @SuppressWarnings("unchecked")
    public static void addReference(Reference<Object> ref, DisposerRecord rec) {
        records.put(ref, rec);
    }

    public static void addObjectRecord(Object obj, DisposerRecord rec) {
        records.put(new WeakReference<>(obj, queue) , rec);
    }

    /* This is intended for use in conjunction with addReference(..)
     */
    public static ReferenceQueue<Object> getQueue() {
        return queue;
    }

}
