/**
 * Defines the Java Packaging tool, jpackage.
 *
 * <p>jpackage is a tool for generating self-contained application bundles.
 *
 * <p> This module provides the equivalent of command-line access to <em>jpackage</em>
 * via the {@link java.util.spi.ToolProvider ToolProvider} SPI.
 * Instances of the tool can be obtained by calling
 * {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst}
 * or the {@link java.util.ServiceLoader service loader} with the name
 * {@code "jpackage"}.
 *
 * @implNote The {@code jpackage} tool is not thread-safe. An application
 * should not call either of the
 * {@link java.util.spi.ToolProvider ToolProvider} {@code run} methods
 * concurrently, even with separate {@code "jpackage"} {@code ToolProvider}
 * instances, or undefined behavior may result.
 *
 * @provides java.util.spi.ToolProvider
 *     Use {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst("jpackage")}
 *     to obtain an instance of a {@code ToolProvider} that provides the equivalent
 *     of command-line access to the {@code jpackage} tool.
 *
 * @moduleGraph
 * @since 16
 */

module jdk.jpackage {
    requires jdk.internal.opt;
    requires jdk.jlink;

    requires java.desktop;

    uses jdk.jpackage.internal.Bundler;
    uses jdk.jpackage.internal.Bundlers;

    provides jdk.jpackage.internal.Bundlers with
        jdk.jpackage.internal.BasicBundlers;

    provides java.util.spi.ToolProvider
        with jdk.jpackage.internal.JPackageToolProvider;
}
