/**
 * Provides the <em>{@index jshell jshell tool}</em> tool for evaluating
 * snippets of Java code, and defines a JDK-specific API for modeling and
 * executing snippets.
 * The JShell API supports Java Programming Language 'snippet' evaluating
 * tools, such as Read-Eval-Print Loops (REPLs).
 * Separate packages support building tools, configuring the execution of tools,
 * and programmatically launching the existing Java shell tool.
 * <p>
 *     The {@link jdk.jshell} is the package for creating 'snippet' evaluating tools.
 *     Generally, this is only package that would be needed for creating tools.
 * </p>
 * <p>
 *     The {@link jdk.jshell.spi} package specifies a Service Provider Interface (SPI)
 *     for defining execution engine implementations for tools based on the
 *     {@link jdk.jshell} API. The {@link jdk.jshell.execution} package provides
 *     standard implementations of {@link jdk.jshell.spi} interfaces and supporting code.  It
 *     also serves as a library of functionality for defining new execution engine
 *     implementations.
 * </p>
 * <p>
 *     The {@link jdk.jshell.tool} package supports programmatically launching the
 *     <em>jshell</em> tool.
 * </p>
 * <p>
 *     The {@link jdk.jshell.execution} package contains implementations of the
 *     interfaces in {@link jdk.jshell.spi}.  Otherwise, the four packages are
 *     independent, operate at different levels, and do not share functionality or
 *     definitions.
 * </p>
 *
 * @toolGuide jshell
 *
 * @provides javax.tools.Tool
 * @provides jdk.jshell.spi.ExecutionControlProvider
 * @uses jdk.jshell.spi.ExecutionControlProvider
 *
 * @moduleGraph
 * @since 9
 */
module jdk.jshell {
    requires java.logging;
    requires jdk.compiler;
    requires jdk.internal.ed;
    requires jdk.internal.le;
    requires jdk.internal.opt;

    requires transitive java.compiler;
    requires transitive java.prefs;
    requires transitive jdk.jdi;

    exports jdk.jshell;
    exports jdk.jshell.execution;
    exports jdk.jshell.spi;
    exports jdk.jshell.tool;

    uses jdk.jshell.spi.ExecutionControlProvider;
    uses jdk.internal.editor.spi.BuildInEditorProvider;

    provides javax.tools.Tool with
        jdk.internal.jshell.tool.JShellToolProvider;
    provides jdk.jshell.spi.ExecutionControlProvider with
        jdk.jshell.execution.JdiExecutionControlProvider,
        jdk.jshell.execution.LocalExecutionControlProvider,
        jdk.jshell.execution.FailOverExecutionControlProvider;
    provides jdk.internal.io.JdkConsoleProvider with jdk.jshell.execution.impl.ConsoleImpl.ConsoleProviderImpl;
}
