package com.sun.jndi.rmi.registry;

/**
 * ReferenceWrapper_Stub.
 */
@SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
public final class ReferenceWrapper_Stub
        extends java.rmi.server.RemoteStub
        implements com.sun.jndi.rmi.registry.RemoteReference, java.rmi.Remote {
    private static final long serialVersionUID = 2;

    private static java.lang.reflect.Method $method_getReference_0;

    static {
        try {
            $method_getReference_0 = com.sun.jndi.rmi.registry.RemoteReference.class.getMethod("getReference", new java.lang.Class[]{});
        } catch (java.lang.NoSuchMethodException e) {
            throw new java.lang.NoSuchMethodError(
                    "stub class initialization failed");
        }
    }

    // constructors
    public ReferenceWrapper_Stub(java.rmi.server.RemoteRef ref) {
        super(ref);
    }

    // methods from remote interfaces

    // implementation of getReference()
    public javax.naming.Reference getReference()
            throws java.rmi.RemoteException, javax.naming.NamingException {
        try {
            Object $result = ref.invoke(this, $method_getReference_0, null, 3529874867989176284L);
            return ((javax.naming.Reference) $result);
        } catch (java.lang.RuntimeException e) {
            throw e;
        } catch (java.rmi.RemoteException e) {
            throw e;
        } catch (javax.naming.NamingException e) {
            throw e;
        } catch (java.lang.Exception e) {
            throw new java.rmi.UnexpectedException("undeclared checked exception", e);
        }
    }
}
