package java.awt.event;

/**
 * An abstract adapter class for receiving container events.
 * The methods in this class are empty. This class exists as
 * convenience for creating listener objects.
 * <P>
 * Extend this class to create a {@code ContainerEvent} listener
 * and override the methods for the events of interest. (If you implement the
 * {@code ContainerListener} interface, you have to define all of
 * the methods in it. This abstract class defines null methods for them
 * all, so you can only have to define methods for events you care about.)
 * <P>
 * Create a listener object using the extended class and then register it with
 * a component using the component's {@code addContainerListener}
 * method. When the container's contents change because a component has
 * been added or removed, the relevant method in the listener object is invoked,
 * and the {@code ContainerEvent} is passed to it.
 *
 * @see ContainerEvent
 * @see ContainerListener
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/containerlistener.html">Tutorial: Writing a Container Listener</a>
 *
 * @author Amy Fowler
 * @since 1.1
 */
public abstract class ContainerAdapter implements ContainerListener {

    /**
     * Constructs a {@code ContainerAdapter}.
     */
    protected ContainerAdapter() {}

    /**
     * Invoked when a component has been added to the container.
     */
    public void componentAdded(ContainerEvent e) {}

    /**
     * Invoked when a component has been removed from the container.
     */
    public void componentRemoved(ContainerEvent e) {}
}
