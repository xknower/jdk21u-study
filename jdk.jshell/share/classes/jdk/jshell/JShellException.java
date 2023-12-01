package jdk.jshell;

/**
 * The superclass of JShell generated exceptions
 *
 * @since 9
 */
@SuppressWarnings("serial")             // serialVersionUID intentionally omitted
public class JShellException extends Exception {

    JShellException(String message) {
        super(message);
    }

    JShellException(String message, Throwable cause) {
        super(message, cause);
    }
}
