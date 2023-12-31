package jdk.internal.net.http.common;

import java.util.function.Supplier;

/**
 * An internal {@code System.Logger} that is used for internal
 * debugging purposes in the {@link java.net.http} module.
 * <p>
 * Though not enforced, this interface is designed for emitting
 * debug messages with Level.DEBUG.
 * <p>
 * It defines {@code log} methods that default to {@code Level.DEBUG},
 * so that they can be called in statements like:
 * <pre>{@code debug.log("some %s with %d %s", message(), one(), params());}</pre>
 *
 * @implSpec
 * This interface is implemented by loggers returned by
 * {@link Utils#getDebugLogger(Supplier)},
 * {@link Utils#getWebSocketLogger(Supplier)} and
 * {@link Utils#getHpackLogger(Supplier)}.
 * It is not designed to be implemented by any other
 * loggers. Do not use outside of this module.
 */
public interface Logger extends System.Logger {
    /**
     * Tells whether this logger is on.
     * @implSpec The default implementation for this method calls
     * {@code this.isLoggable(Level.DEBUG);}
     */
    public default boolean on() {
        return isLoggable(Level.DEBUG);
    }

    /**
     * Logs a message.
     *
     * @implSpec The default implementation for this method calls
     * {@code this.log(Level.DEBUG, msg);}
     *
     * @param msg the string message (or a key in the message catalog, if
     * this logger is a {@link
     * System.LoggerFinder#getLocalizedLogger(java.lang.String,
     * java.util.ResourceBundle, java.lang.Module) localized logger});
     * can be {@code null}.
     */
    public default void log(String msg) {
        log(Level.DEBUG, msg);
    }

    /**
     * Logs a lazily supplied message.
     *
     * @implSpec The default implementation for this method calls
     * {@code this.log(Level.DEBUG, msgSupplier);}
     *
     * @param msgSupplier a supplier function that produces a message.
     *
     * @throws NullPointerException if {@code msgSupplier} is {@code null}.
     */
    public default void log(Supplier<String> msgSupplier) {
        log(Level.DEBUG, msgSupplier);
    }

    /**
     * Logs a message produced from the given object.
     *
     * @implSpec The default implementation for this method calls
     * {@code this.log(Level.DEBUG, obj);}
     *
     * @param obj the object to log.
     *
     * @throws NullPointerException if {@code obj} is {@code null}.
     */
    public default void log(Object obj) {
        log(Level.DEBUG,  obj);
    }

    /**
     * Logs a message associated with a given throwable.
     *
     * @implSpec The default implementation for this method calls
     * {@code this.log(Level.DEBUG, msg, thrown);}
     *
     * @param msg the string message (or a key in the message catalog, if
     * this logger is a {@link
     * System.LoggerFinder#getLocalizedLogger(java.lang.String,
     * java.util.ResourceBundle, java.lang.Module) localized logger});
     * can be {@code null}.
     * @param thrown a {@code Throwable} associated with the log message;
     *        can be {@code null}.
     */
    public default void log(String msg, Throwable thrown) {
        this.log(Level.DEBUG, msg, thrown);
    }

    /**
     * Logs a lazily supplied message associated with a given throwable.
     *
     * @implSpec The default implementation for this method calls
     * {@code this.log(Level.DEBUG, msgSupplier, thrown);}
     *
     * @param msgSupplier a supplier function that produces a message.
     * @param thrown a {@code Throwable} associated with log message;
     *               can be {@code null}.
     *
     * @throws NullPointerException if {@code msgSupplier} is {@code null}.
     */
    public default void log(Supplier<String> msgSupplier, Throwable thrown) {
        log(Level.DEBUG, msgSupplier, thrown);
    }

    /**
     * Logs a message with an optional list of parameters.
     *
     * @implSpec The default implementation for this method calls
     * {@code this.log(Level.DEBUG, format, params);}
     *
     * @param format the string message format in
     * {@link String#format(String, Object...)} or {@link
     * java.text.MessageFormat} format, (or a key in the message
     * catalog, if this logger is a {@link
     * System.LoggerFinder#getLocalizedLogger(java.lang.String,
     * java.util.ResourceBundle, java.lang.Module) localized logger});
     * can be {@code null}.
     * @param params an optional list of parameters to the message (may be
     * none).
     */
    public default void log(String format, Object... params) {
        log(Level.DEBUG, format, params);
    }
}
