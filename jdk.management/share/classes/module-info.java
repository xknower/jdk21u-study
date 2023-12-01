/**
 * Defines JDK-specific management interfaces for the JVM.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.management {
    requires transitive java.management;

    exports com.sun.management;

    provides sun.management.spi.PlatformMBeanProvider with
        com.sun.management.internal.PlatformMBeanProviderImpl;
}

