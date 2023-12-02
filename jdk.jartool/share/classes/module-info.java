/**
 * Defines tools for manipulating Java Archive (JAR) files,
 * including the <em>{@index jar jar tool}</em> and
 * <em>{@index jarsigner jarsigner tool}</em> tools.
 * This module also defines APIs for signing JAR files.
 *
 * <p> This module provides the equivalent of command-line access to
 * <em>jar</em> via the {@link java.util.spi.ToolProvider ToolProvider} SPI.
 * Instances of the tool can be obtained by calling
 * {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst}
 * or the {@linkplain java.util.ServiceLoader service loader} with the name
 * {@code "jar"}.
 *
 * @toolGuide jar
 * @toolGuide jarsigner
 *
 * @provides java.util.spi.ToolProvider
 *     Use {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst("jar")}
 *     to obtain an instance of a {@code ToolProvider} that provides the equivalent
 *     of command-line access to the {@code jar} tool.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.jartool {
    requires jdk.internal.opt;

    exports jdk.security.jarsigner;

    provides java.util.spi.ToolProvider with
        sun.tools.jar.JarToolProvider;
}
