package sun.nio.ch;


// Signalling operations on native threads
//
// On some operating systems (e.g., Linux), closing a channel while another
// thread is blocked in an I/O operation upon that channel does not cause that
// thread to be released.  This class provides access to the native threads
// upon which Java threads are built, and defines a simple signal mechanism
// that can be used to release a native thread from a blocking I/O operation.
// On systems that do not require this type of signalling, the current() method
// always returns -1 and the signal(long) method has no effect.

public class NativeThread {
    private static final long VIRTUAL_THREAD_ID = -1L;

    /**
     * Returns the id of the current native thread if the platform can signal
     * native threads, 0 if the platform can not signal native threads, or
     * -1L if the current thread is a virtual thread.
     */
    public static long current() {
        if (Thread.currentThread().isVirtual()) {
            return VIRTUAL_THREAD_ID;
        } else {
            return current0();
        }
    }

    /**
     * Returns the id of the current native thread if the platform can signal
     * native threads, 0 if the platform can not signal native threads.
     */
    static long currentNativeThread() {
        return current0();
    }

    /**
     * Signals the given native thread.
     *
     * @throws IllegalArgumentException if tid is not a token to a native thread
     */
    public static void signal(long tid) {
        if (tid == 0 || tid == VIRTUAL_THREAD_ID)
            throw new IllegalArgumentException();
        signal0(tid);
    }

    /**
     * Returns true the tid is the id of a native thread.
     */
    static boolean isNativeThread(long tid) {
        return (tid != 0 && tid != VIRTUAL_THREAD_ID);
    }

    /**
     * Returns true if tid is -1L.
     * @see #current()
     */
    static boolean isVirtualThread(long tid) {
        return (tid == VIRTUAL_THREAD_ID);
    }

    // Returns an opaque token representing the native thread underlying the
    // invoking Java thread.  On systems that do not require signalling, this
    // method always returns 0.
    //
    private static native long current0();

    // Signals the given native thread so as to release it from a blocking I/O
    // operation.  On systems that do not require signalling, this method has
    // no effect.
    //
    private static native void signal0(long tid);

    private static native void init();

    static {
        IOUtil.load();
        init();
    }

}
