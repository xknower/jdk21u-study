package sun.jvm.hotspot.debugger;

/** <P> This interface abstracts over access to operating system-level
    threads in the underlying process. It is designed to be minimal
    and generic to allow cross-platform compatibility. </P>

    <P> The basic operation this interface supports is creating a
    sun.jvm.hotspot.debugger.ThreadProxy "token" for an existing
    thread. As an example, the HotSpot VM contains a list of Java
    threads, encapsulated in VM-specific JavaThread objects. Each of
    these contains a platform-dependent field with the OS-level thread
    identifier; on Solaris, this field's type is "thread_t", while on
    Windows, it is HANDLE. It is necessary to be able to map from
    these fields to a ThreadProxy object, in particular to be able to
    get the thread's context. However, since the types of these fields
    vary greatly from OS to OS (some use integers as thread IDs, some
    use pointers as thread handles) it is not possible to define one
    particular type (Address, long) in this interface as the lookup
    "key" for a Thread. </P>

    <P> For this reason this mapping mechanism takes the Address of
    the memory location containing the thread identifier. On Solaris,
    this is the address of a location containing a thread_t; on
    Windows, this is the address of a location containing a HANDLE for
    a thread. On Linux, this is the address of a location containing a
    pthread_t.</P>

    <P> The {@link sun.jvm.hotspot.debugger.cdbg.CDebugger} interface
    provides access to the entire thread list of the target process,
    but this is optional functionality not required to get the SA to
    work. </P> */

public interface ThreadAccess {
  /** Gets an abstract ThreadProxy object for the thread identified by
      the contents of the memory location pointed to by addr. The
      contents at location addr are inherently platform-dependent; see
      the documentation for this class for more information. FIXME:
      what exception, if any, should this throw? */
  public ThreadProxy getThreadForIdentifierAddress(Address addr);

  /** Gets an abstract ThreadProxy object for the thread identified by
      id or handle that is platform dependent */
  public ThreadProxy getThreadForThreadId(long id);
}
