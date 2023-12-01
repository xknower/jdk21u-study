/**
 * Defines the API for JDK Flight Recorder.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.jfr {
    exports jdk.jfr;
    exports jdk.jfr.consumer;

    exports jdk.jfr.internal.management to jdk.management.jfr;
}
