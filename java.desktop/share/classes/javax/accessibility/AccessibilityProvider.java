package javax.accessibility;

/**
 * Service Provider Interface (SPI) for Assistive Technology.
 * <p>
 * This service provider class provides mappings from the platform specific
 * accessibility APIs to the Java Accessibility API.
 * <p>
 * Each service provider implementation is named and can be activated via the
 * {@link #activate} method. Service providers can be loaded when the default
 * {@link java.awt.Toolkit toolkit} is initialized.
 *
 * @apiNote There will typically be one provider per platform, such as Windows
 *          or Linux, to support accessibility for screen readers and
 *          magnifiers. However, more than one service provider can be
 *          activated. For example, a test tool which provides visual results
 *          obtained by interrogating the Java Accessibility API can be
 *          activated along with the activation of the support for screen
 *          readers and screen magnifiers.
 * @see java.awt.Toolkit#getDefaultToolkit
 * @see java.util.ServiceLoader
 * @since 9
 */
public abstract class AccessibilityProvider {

    /**
     * Initializes a new accessibility provider.
     *
     * @throws SecurityException If a security manager has been installed and it
     *         denies {@link RuntimePermission} {@code "accessibilityProvider"}
     */
    protected AccessibilityProvider() {
        // Use a permission check when calling a private constructor to check
        // that the proper security permission has been granted before the
        // {@code Object} superclass is called. If an exception is thrown before
        // the {@code Object} superclass is constructed a finalizer in a
        // subclass of this class will not be run. This protects against a
        // finalizer vulnerability.
        this(checkPermission());
    }

    /**
     * Allows to check a permission before the {@code Object} is called.
     *
     * @param  ignore unused stub to call a {@link #checkPermission()}}
     */
    private AccessibilityProvider(Void ignore) { }

    /**
     * If this code is running with a security manager and if the permission
     * {@code "accessibilityProvider"} has not been granted
     * {@code SecurityException} will be thrown.
     *
     * @return {@code null} if {@code SecurityException} was not thrown
     * @throws SecurityException If a security manager has been installed and it
     *         denies {@link RuntimePermission} {@code "accessibilityProvider"}
     */
    private static Void checkPermission() {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("accessibilityProvider"));
        return null;
    }

    /**
     * Returns the name of this service provider. This name is used to locate a
     * requested service provider.
     *
     * @return the name of this service provider
     */
    public abstract String getName();

    /**
     * Activates the support provided by this service provider.
     */
    public abstract void activate();
}
