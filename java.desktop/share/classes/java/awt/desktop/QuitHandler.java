package java.awt.desktop;

/**
 * An implementor determines if requests to quit this application should proceed
 * or cancel.
 *
 * @see java.awt.Desktop#setQuitHandler(QuitHandler)
 * @see java.awt.Desktop#setQuitStrategy(QuitStrategy)
 * @since 9
 */
public interface QuitHandler {

    /**
     * Invoked when the application is asked to quit.
     * <p>
     * Implementors must call either {@link QuitResponse#cancelQuit()},
     * {@link QuitResponse#performQuit()}, or ensure the application terminates.
     * The process (or log-out) requesting this app to quit will be blocked
     * until the {@link QuitResponse} is handled. Apps that require complex UI
     * to shutdown may call the {@link QuitResponse} from any thread. Your app
     * may be asked to quit multiple times before you have responded to the
     * initial request. This handler is called each time a quit is requested,
     * and the same {@link QuitResponse} object is passed until it is handled.
     * Once used, the {@link QuitResponse} cannot be used again to change the
     * decision.
     *
     * @param  e the request to quit this application
     * @param  response the one-shot response object used to cancel or proceed
     *         with the quit action
     */
    public void handleQuitRequestWith(QuitEvent e, QuitResponse response);
}
