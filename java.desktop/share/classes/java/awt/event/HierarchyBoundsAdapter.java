package java.awt.event;

/**
 * An abstract adapter class for receiving ancestor moved and resized events.
 * The methods in this class are empty. This class exists as a
 * convenience for creating listener objects.
 * <p>
 * Extend this class and override the method for the event of interest. (If
 * you implement the {@code HierarchyBoundsListener} interface, you have
 * to define both methods in it. This abstract class defines null methods for
 * them both, so you only have to define the method for the event you care
 * about.)
 * <p>
 * Create a listener object using your class and then register it with a
 * Component using the Component's {@code addHierarchyBoundsListener}
 * method. When the hierarchy to which the Component belongs changes by
 * resize or movement of an ancestor, the relevant method in the listener
 * object is invoked, and the {@code HierarchyEvent} is passed to it.
 *
 * @author      David Mendenhall
 * @see         HierarchyBoundsListener
 * @see         HierarchyEvent
 * @since       1.3
 */
public abstract class HierarchyBoundsAdapter implements HierarchyBoundsListener
{
    /**
     * Constructs a {@code HierarchyBoundsAdapter}.
     */
    protected HierarchyBoundsAdapter() {}

    /**
     * Called when an ancestor of the source is moved.
     */
    public void ancestorMoved(HierarchyEvent e) {}

    /**
     * Called when an ancestor of the source is resized.
     */
    public void ancestorResized(HierarchyEvent e) {}
}
