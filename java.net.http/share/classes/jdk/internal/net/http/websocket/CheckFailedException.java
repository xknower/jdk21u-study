package jdk.internal.net.http.websocket;

/*
 * Used as a context-neutral exception which can be wrapped into (for example)
 * a `ProtocolException` or an `IllegalArgumentException` depending on who's
 * doing the check.
 */
final class CheckFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    CheckFailedException(String message) {
        super(message);
    }
}
