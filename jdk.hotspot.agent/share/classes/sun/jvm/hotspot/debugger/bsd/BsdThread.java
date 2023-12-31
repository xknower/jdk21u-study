package sun.jvm.hotspot.debugger.bsd;

import sun.jvm.hotspot.debugger.*;

class BsdThread implements ThreadProxy {
    private BsdDebugger debugger;
    private int         thread_id;
    private long        unique_thread_id;

    /** The address argument must be the address of the _thread_id in the
        OSThread. It's value is result ::gettid() call. */
    BsdThread(BsdDebugger debugger, Address threadIdAddr, Address uniqueThreadIdAddr) {
        this.debugger = debugger;
        // FIXME: size of data fetched here should be configurable.
        // However, making it so would produce a dependency on the "types"
        // package from the debugger package, which is not desired.
        this.thread_id = (int) threadIdAddr.getCIntegerAt(0, 4, true);
        this.unique_thread_id = uniqueThreadIdAddr.getCIntegerAt(0, 8, true);
    }

    BsdThread(BsdDebugger debugger, long id) {
        this.debugger = debugger;
        // use unique_thread_id to identify thread
        this.unique_thread_id = id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BsdThread other)) {
            return false;
        }

        return (other.unique_thread_id == unique_thread_id);
    }

    public int hashCode() {
        return thread_id;
    }

    public String toString() {
        return Integer.toString(thread_id);
    }

    public ThreadContext getContext() throws IllegalThreadStateException {
        long[] data = debugger.getThreadIntegerRegisterSet(unique_thread_id);
        ThreadContext context = BsdThreadContextFactory.createThreadContext(debugger);
        // null means we failed to get the register set for some reason. The caller
        // is responsible for dealing with the set of null registers in that case.
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                context.setRegister(i, data[i]);
            }
        }
        return context;
    }

    public boolean canSetContext() throws DebuggerException {
        return false;
    }

    public void setContext(ThreadContext context)
      throws IllegalThreadStateException, DebuggerException {
        throw new DebuggerException("Unimplemented");
    }

    /** this is not interface function, used in core file to get unique thread id on Macosx*/
    public long getUniqueThreadId() {
        return unique_thread_id;
    }
}
