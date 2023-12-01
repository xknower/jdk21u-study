package jdk.jshell.spi;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import jdk.jshell.JShellConsole;

/**
 * Functionality made available to a pluggable JShell execution engine.  It is
 * provided to the execution engine by the core JShell implementation.
 * <p>
 * This interface is designed to provide the access to core JShell functionality
 * needed to implement ExecutionControl.
 *
 * @since 9
 * @see ExecutionControl
 */
public interface ExecutionEnv {

    /**
     * Returns the user's input stream.
     *
     * @return the user's input stream
     */
    InputStream userIn();

    /**
     * Returns the user's output stream.
     *
     * @return the user's output stream
     */
    PrintStream userOut();

    /**
     * Returns the user's error stream.
     *
     * @return the user's error stream
     */
    PrintStream userErr();

    /**
     * Returns the additional VM options to be used when launching the remote
     * JVM. This is advice to the execution engine.
     * <p>
     * Note: an execution engine need not launch a remote JVM.
     *
     * @return the additional options with which to launch the remote JVM
     */
    List<String> extraRemoteVMOptions();

    /**
     * Reports that the execution engine has shutdown.
     */
    void closeDown();

    /**
     * Returns the {@code JShellConsole} that should be used
     * by the execution engine, or {@code null} if none.
     * <p>
     * Note: an execution engine may not support {@code JShellConsole}.
     *
     * @implSpec The default implementation of this method
     *           returns an empty {@code Optional}.
     *
     * @return returns console, or an empty {@code Optional} if none,
     *         never {@code null}
     * @since 21
     */
    default Optional<JShellConsole> console() {
        return Optional.empty();
    }
}
