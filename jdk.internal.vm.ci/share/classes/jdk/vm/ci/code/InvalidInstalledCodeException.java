package jdk.vm.ci.code;

/**
 * Exception thrown by the runtime in case an invalidated machine code is called.
 */
public final class InvalidInstalledCodeException extends Exception {

    public InvalidInstalledCodeException() {
    }

    public InvalidInstalledCodeException(String message) {
        super(message);
    }

    private static final long serialVersionUID = -3540232440794244844L;
}
