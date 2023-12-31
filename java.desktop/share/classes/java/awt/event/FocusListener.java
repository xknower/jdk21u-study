package java.awt.event;

import java.util.EventListener;

/**
 * The listener interface for receiving keyboard focus events on
 * a component.
 * The class that is interested in processing a focus event
 * either implements this interface (and all the methods it
 * contains) or extends the abstract {@code FocusAdapter} class
 * (overriding only the methods of interest).
 * The listener object created from that class is then registered with a
 * component using the component's {@code addFocusListener}
 * method. When the component gains or loses the keyboard focus,
 * the relevant method in the listener object
 * is invoked, and the {@code FocusEvent} is passed to it.
 *
 * @see FocusAdapter
 * @see FocusEvent
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/focuslistener.html">Tutorial: Writing a Focus Listener</a>
 *
 * @author Carl Quinn
 * @since 1.1
 */
public interface FocusListener extends EventListener {

    /**
     * Invoked when a component gains the keyboard focus.
     * @param e the event to be processed
     */
    public void focusGained(FocusEvent e);

    /**
     * Invoked when a component loses the keyboard focus.
     * @param e the event to be processed
     */
    public void focusLost(FocusEvent e);
}
