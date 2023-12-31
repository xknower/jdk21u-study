package sun.lwawt.macosx;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Safely holds and disposes of native AppKit resources, using the
 * correct AppKit threading and Objective-C GC semantics.
 */
public class CFRetainedResource {
    private static native void nativeCFRelease(final long ptr, final boolean disposeOnAppKitThread);

    private final boolean disposeOnAppKitThread;
    // TODO this pointer should be private and accessed via CFNativeAction class
    protected volatile long ptr;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    /**
     * @param ptr CFRetained native object pointer
     * @param disposeOnAppKitThread is the object needs to be CFReleased on the main thread
     */
    protected CFRetainedResource(final long ptr, final boolean disposeOnAppKitThread) {
        this.disposeOnAppKitThread = disposeOnAppKitThread;
        this.ptr = ptr;
    }

    /**
     * CFReleases previous resource and assigns new pre-retained resource.
     * @param ptr CFRetained native object pointer
     */
    protected void setPtr(final long ptr) {
        writeLock.lock();
        try {
            if (this.ptr != 0) {
                dispose();
            }
            this.ptr = ptr;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Manually CFReleases the native resource.
     */
    protected void dispose() {
        long oldPtr = 0L;
        writeLock.lock();
        try {
            if (ptr == 0) {
                return;
            }
            oldPtr = ptr;
            ptr = 0;
        } finally {
            writeLock.unlock();
        }

        nativeCFRelease(oldPtr, disposeOnAppKitThread); // perform outside of the synchronized block
    }

    /**
     * The interface which allows to execute some native operations with
     * assumption that the native pointer will be valid till the end.
     */
    public interface CFNativeAction {

        /**
         * The native operation should be called from this method.
         *
         * @param  ptr the pointer to the native data
         */
        void run(long ptr);
    }

    /**
     * The interface which allows to execute some native operations and get a
     * result with assumption that the native pointer will be valid till the
     * end.
     */
    interface CFNativeActionGet {

        /**
         * The native operation should be called from this method.
         *
         * @param  ptr the pointer to the native data
         * @return result of the native operation
         */
        long run(long ptr);
    }

    /**
     * This is utility method which should be used instead of the direct access
     * to the {@link #ptr}, because this method guaranteed that the pointer will
     * not be zero and will be valid till the end of the operation.It is highly
     * recommended to not use any external lock in action. If the current
     * {@link #ptr} is {@code 0} then action will be ignored.
     *
     * @param  action The native operation
     */
    public final void execute(final CFNativeAction action) {
        readLock.lock();
        try {
            if (ptr != 0) {
                action.run(ptr);
            }
        } finally {
            readLock.unlock();
        }
    }

    /**
     * This is utility method which should be used instead of the direct access
     * to the {@link #ptr}, because this method guaranteed that the pointer will
     * not be zero and will be valid till the end of the operation. It is highly
     * recommended to not use any external lock in action. If the current
     * {@link #ptr} is {@code 0} then action will be ignored and {@code} is
     * returned.
     *
     * @param  action the native operation
     * @return result of the native operation, usually the native pointer to
     *         some other data
     */
    final long executeGet(final CFNativeActionGet action) {
        readLock.lock();
        try {
            if (ptr != 0) {
                return action.run(ptr);
            }
        } finally {
            readLock.unlock();
        }
        return 0;
    }

    @Override
    @SuppressWarnings("removal")
    protected final void finalize() throws Throwable {
        dispose();
    }
}
