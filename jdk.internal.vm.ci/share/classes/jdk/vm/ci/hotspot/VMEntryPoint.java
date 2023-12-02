package jdk.vm.ci.hotspot;

/**
 * Marker interface for methods which are called from the JVM.
 */
@interface VMEntryPoint {
    /**
     * An optional comment describing the caller.
     */
    String value() default "";
}
