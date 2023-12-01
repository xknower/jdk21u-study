/**
 * Defines the Management Interface for JDK Flight Recorder.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.management.jfr {
    requires jdk.management;

    requires transitive java.management;
    requires transitive jdk.jfr;

    exports jdk.management.jfr;

    provides sun.management.spi.PlatformMBeanProvider with
        jdk.management.jfr.internal.FlightRecorderMXBeanProvider;
}
