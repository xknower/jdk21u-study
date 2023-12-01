package jdk.dynalink.beans;

import java.lang.reflect.Modifier;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import jdk.dynalink.internal.AccessControlContextFactory;

/**
 * A utility class to check whether a given class is in a package with restricted access e.g. "sun.*" etc.
 */
class CheckRestrictedPackage {
    @SuppressWarnings("removal")
    private static final AccessControlContext NO_PERMISSIONS_CONTEXT =
            AccessControlContextFactory.createAccessControlContext();

    /**
     * Returns true if the class is either not public, or it resides in a package with restricted access.
     * @param clazz the class to test
     * @return true if the class is either not public, or it resides in a package with restricted access.
     */
    @SuppressWarnings("removal")
    static boolean isRestrictedClass(final Class<?> clazz) {
        if(!Modifier.isPublic(clazz.getModifiers())) {
            // Non-public classes are always restricted
            return true;
        }
        final String name = clazz.getName();
        final int i = name.lastIndexOf('.');
        if (i == -1) {
            // Classes in default package are never restricted
            return false;
        }
        final String pkgName = name.substring(0, i);
        final Module module = clazz.getModule();
        if (module != null && !module.isExported(pkgName)) {
            // Classes in unexported packages of modules are always restricted
            return true;
        }

        final SecurityManager sm = System.getSecurityManager();
        if(sm == null) {
            // No further restrictions if we don't have a security manager
            return false;
        }
        // Do a package access check from within an access control context with no permissions
        try {
            AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                sm.checkPackageAccess(pkgName);
                return null;
            }, NO_PERMISSIONS_CONTEXT);
        } catch(final SecurityException e) {
            return true;
        }
        return false;
    }
}
