package java.rmi.server;

import java.rmi.Remote;

/**
 * The <code>Skeleton</code> interface is used solely by the RMI
 * implementation.
 *
 * <p> Every version 1.1 compatible skeleton implements this interface.
 * A skeleton for a remote object is a server-side entity that dispatches calls
 * to the actual remote object implementation.
 *
 * @author  Ann Wollrath
 * @since   1.1
 * @deprecated no replacement.  Skeletons are no longer required for remote
 * method calls in the Java 2 platform v1.2 and greater.
 */
@Deprecated
public interface Skeleton {
    /**
     * Unmarshals arguments, calls the actual remote object implementation,
     * and marshals the return value or any exception.
     *
     * @param obj remote implementation to dispatch call to
     * @param theCall object representing remote call
     * @param opnum operation number
     * @param hash stub/skeleton interface hash
     * @throws java.lang.Exception if a general exception occurs.
     * @since 1.1
     * @deprecated no replacement
     */
    @Deprecated
    void dispatch(Remote obj, RemoteCall theCall, int opnum, long hash)
        throws Exception;

    /**
     * Returns the operations supported by the skeleton.
     * @return operations supported by skeleton
     * @since 1.1
     * @deprecated no replacement
     */
    @Deprecated
    Operation[] getOperations();
}
