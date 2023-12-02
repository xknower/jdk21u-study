/**
 * Internal API for line editing
 *
 * @since 9
 */
module jdk.internal.le {
    exports jdk.internal.org.jline.keymap to
        jdk.jshell;
    exports jdk.internal.org.jline.reader to
        jdk.jshell;
    exports jdk.internal.org.jline.reader.impl to
        jdk.jshell;
    exports jdk.internal.org.jline.reader.impl.completer to
        jdk.jshell;
    exports jdk.internal.org.jline.reader.impl.history to
        jdk.jshell;
    exports jdk.internal.org.jline.terminal.impl to
        jdk.jshell;
    exports jdk.internal.org.jline.terminal to
        jdk.jshell;
    exports jdk.internal.org.jline.utils to
        jdk.jshell;
    exports jdk.internal.org.jline.terminal.spi to
        jdk.jshell;

    // Console
    provides jdk.internal.io.JdkConsoleProvider with
            jdk.internal.org.jline.JdkConsoleProviderImpl;
}

