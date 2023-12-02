/**
 * Internal option processing API
 *
 * @since 9
 */
module jdk.internal.opt {
    exports jdk.internal.joptsimple to
        jdk.jlink,
        jdk.jshell;
    exports jdk.internal.opt to
        jdk.compiler,
        jdk.jartool,
        jdk.javadoc,
        jdk.jlink,
        jdk.jpackage;
}
