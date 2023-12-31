package java.awt.event;

import java.util.EventListener;

/**
 * The listener interface for receiving component events.
 * The class that is interested in processing a component event
 * either implements this interface (and all the methods it
 * contains) or extends the abstract {@code ComponentAdapter} class
 * (overriding only the methods of interest).
 * The listener object created from that class is then registered with a
 * component using the component's {@code addComponentListener}
 * method. When the component's size, location, or visibility
 * changes, the relevant method in the listener object is invoked,
 * and the {@code ComponentEvent} is passed to it.
 * <P>
 * Component events are provided for notification purposes ONLY;
 * The AWT will automatically handle component moves and resizes
 * internally so that GUI layout works properly regardless of
 * whether a program registers a {@code ComponentListener} or not.
 *
 * @see ComponentAdapter
 * @see ComponentEvent
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/componentlistener.html">Tutorial: Writing a Component Listener</a>
 *
 * @author Carl Quinn
 * @since 1.1
 */
public interface ComponentListener extends EventListener {
    /**
     * Invoked when the component's size changes.
     * @param e the event to be processed
     */
    public void componentResized(ComponentEvent e);

    /**
     * Invoked when the component's position changes.
     * @param e the event to be processed
     */
    public void componentMoved(ComponentEvent e);

    /**
     * Invoked when the component has been made visible.
     * @param e the event to be processed
     */
    public void componentShown(ComponentEvent e);

    /**
     * Invoked when the component has been made invisible.
     * @param e the event to be processed
     */
    public void componentHidden(ComponentEvent e);
}
