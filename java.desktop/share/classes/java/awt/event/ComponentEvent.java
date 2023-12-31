package java.awt.event;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Rectangle;
import java.io.Serial;
import java.lang.annotation.Native;

/**
 * A low-level event which indicates that a component moved, changed
 * size, or changed visibility (also, the root class for the other
 * component-level events).
 * <P>
 * Component events are provided for notification purposes ONLY;
 * The AWT will automatically handle component moves and resizes
 * internally so that GUI layout works properly regardless of
 * whether a program is receiving these events or not.
 * <P>
 * In addition to serving as the base class for other component-related
 * events (InputEvent, FocusEvent, WindowEvent, ContainerEvent),
 * this class defines the events that indicate changes in
 * a component's size, position, or visibility.
 * <P>
 * This low-level event is generated by a component object (such as a
 * List) when the component is moved, resized, rendered invisible, or made
 * visible again. The event is passed to every {@code ComponentListener}
 * or {@code ComponentAdapter} object which registered to receive such
 * events using the component's {@code addComponentListener} method.
 * ({@code ComponentAdapter} objects implement the
 * {@code ComponentListener} interface.) Each such listener object
 * gets this {@code ComponentEvent} when the event occurs.
 * <p>
 * An unspecified behavior will be caused if the {@code id} parameter
 * of any particular {@code ComponentEvent} instance is not
 * in the range from {@code COMPONENT_FIRST} to {@code COMPONENT_LAST}.
 *
 * @see ComponentAdapter
 * @see ComponentListener
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/componentlistener.html">Tutorial: Writing a Component Listener</a>
 *
 * @author Carl Quinn
 * @since 1.1
 */
public class ComponentEvent extends AWTEvent {

    /**
     * The first number in the range of ids used for component events.
     */
    public static final int COMPONENT_FIRST             = 100;

    /**
     * The last number in the range of ids used for component events.
     */
    public static final int COMPONENT_LAST              = 103;

   /**
     * This event indicates that the component's position changed.
     */
    @Native public static final int COMPONENT_MOVED     = COMPONENT_FIRST;

    /**
     * This event indicates that the component's size changed.
     */
    @Native public static final int COMPONENT_RESIZED   = 1 + COMPONENT_FIRST;

    /**
     * This event indicates that the component was made visible.
     */
    @Native public static final int COMPONENT_SHOWN     = 2 + COMPONENT_FIRST;

    /**
     * This event indicates that the component was rendered invisible.
     */
    @Native public static final int COMPONENT_HIDDEN    = 3 + COMPONENT_FIRST;

    /**
     * Use serialVersionUID from JDK 1.1 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 8101406823902992965L;

    /**
     * Constructs a {@code ComponentEvent} object.
     * <p> This method throws an
     * {@code IllegalArgumentException} if {@code source}
     * is {@code null}.
     *
     * @param source The {@code Component} that originated the event
     * @param id     An integer indicating the type of event.
     *                     For information on allowable values, see
     *                     the class description for {@link ComponentEvent}
     * @throws IllegalArgumentException if {@code source} is null
     * @see #getComponent()
     * @see #getID()
     */
    public ComponentEvent(Component source, int id) {
        super(source, id);
    }

    /**
     * Returns the originator of the event.
     *
     * @return the {@code Component} object that originated
     * the event, or {@code null} if the object is not a
     * {@code Component}.
     */
    public Component getComponent() {
        return (source instanceof Component) ? (Component)source : null;
    }

    /**
     * Returns a parameter string identifying this event.
     * This method is useful for event-logging and for debugging.
     *
     * @return a string identifying the event and its attributes
     */
    public String paramString() {
        String typeStr;
        Rectangle b = (source !=null
                       ? ((Component)source).getBounds()
                       : null);

        switch(id) {
          case COMPONENT_SHOWN:
              typeStr = "COMPONENT_SHOWN";
              break;
          case COMPONENT_HIDDEN:
              typeStr = "COMPONENT_HIDDEN";
              break;
          case COMPONENT_MOVED:
              typeStr = "COMPONENT_MOVED ("+
                         b.x+","+b.y+" "+b.width+"x"+b.height+")";
              break;
          case COMPONENT_RESIZED:
              typeStr = "COMPONENT_RESIZED ("+
                         b.x+","+b.y+" "+b.width+"x"+b.height+")";
              break;
          default:
              typeStr = "unknown type";
        }
        return typeStr;
    }
}
