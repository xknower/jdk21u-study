package java.awt.event;

import java.util.EventListener;

/**
 * The listener interface for receiving ancestor moved and resized events.
 * The class that is interested in processing these events either implements
 * this interface (and all the methods it contains) or extends the abstract
 * {@code HierarchyBoundsAdapter} class (overriding only the method of
 * interest).
 * The listener object created from that class is then registered with a
 * Component using the Component's {@code addHierarchyBoundsListener}
 * method. When the hierarchy to which the Component belongs changes by
 * the resizing or movement of an ancestor, the relevant method in the listener
 * object is invoked, and the {@code HierarchyEvent} is passed to it.
 * <p>
 * Hierarchy events are provided for notification purposes ONLY;
 * The AWT will automatically handle changes to the hierarchy internally so
 * that GUI layout works properly regardless of whether a
 * program registers an {@code HierarchyBoundsListener} or not.
 *
 * @author      David Mendenhall
 * @see         HierarchyBoundsAdapter
 * @see         HierarchyEvent
 * @since       1.3
 */
public interface HierarchyBoundsListener extends EventListener {
    /**
     * Called when an ancestor of the source is moved.
     * @param e the event to be processed
     */
    public void ancestorMoved(HierarchyEvent e);

    /**
     * Called when an ancestor of the source is resized.
     * @param e the event to be processed
     */
    public void ancestorResized(HierarchyEvent e);
}
