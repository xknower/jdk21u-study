/**
 * Defines the attach API.
 *
 * @uses com.sun.tools.attach.spi.AttachProvider
 *
 * @moduleGraph
 * @since 9
 */
module jdk.attach {
    requires jdk.internal.jvmstat;

    exports com.sun.tools.attach;
    exports com.sun.tools.attach.spi;

    exports sun.tools.attach to
        jdk.jcmd;

    uses com.sun.tools.attach.spi.AttachProvider;

    provides com.sun.tools.attach.spi.AttachProvider with
        sun.tools.attach.AttachProviderImpl;
}
