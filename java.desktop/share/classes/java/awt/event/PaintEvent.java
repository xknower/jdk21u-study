package java.awt.event;

import java.awt.Component;
import java.awt.Rectangle;
import java.io.Serial;

/**
 * The component-level paint event.
 * This event is a special type which is used to ensure that
 * paint/update method calls are serialized along with the other
 * events delivered from the event queue.  This event is not
 * designed to be used with the Event Listener model; programs
 * should continue to override paint/update methods in order
 * render themselves properly.
 * <p>
 * An unspecified behavior will be caused if the {@code id} parameter
 * of any particular {@code PaintEvent} instance is not
 * in the range from {@code PAINT_FIRST} to {@code PAINT_LAST}.
 *
 * @author Amy Fowler
 * @since 1.1
 */
public class PaintEvent extends ComponentEvent {

    /**
     * Marks the first integer id for the range of paint event ids.
     */
    public static final int PAINT_FIRST         = 800;

    /**
     * Marks the last integer id for the range of paint event ids.
     */
    public static final int PAINT_LAST          = 801;

    /**
     * The paint event type.
     */
    public static final int PAINT = PAINT_FIRST;

    /**
     * The update event type.
     */
    public static final int UPDATE = PAINT_FIRST + 1; //801

    /**
     * This is the rectangle that represents the area on the source
     * component that requires a repaint.
     * This rectangle should be non null.
     *
     * @serial
     * @see java.awt.Rectangle
     * @see #setUpdateRect(Rectangle)
     * @see #getUpdateRect()
     */
    Rectangle updateRect;

    /**
     * Use serialVersionUID from JDK 1.1 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 1267492026433337593L;

    /**
     * Constructs a {@code PaintEvent} object with the specified
     * source component and type.
     * <p> This method throws an
     * {@code IllegalArgumentException} if {@code source}
     * is {@code null}.
     *
     * @param source     The object where the event originated
     * @param id           The integer that identifies the event type.
     *                     For information on allowable values, see
     *                     the class description for {@link PaintEvent}
     * @param updateRect The rectangle area which needs to be repainted
     * @throws IllegalArgumentException if {@code source} is null
     * @see #getSource()
     * @see #getID()
     * @see #getUpdateRect()
     */
    public PaintEvent(Component source, int id, Rectangle updateRect) {
        super(source, id);
        this.updateRect = updateRect;
    }

    /**
     * Returns the rectangle representing the area which needs to be
     * repainted in response to this event.
     * @return the rectangle representing the area which needs to be
     * repainted in response to this event
     */
    public Rectangle getUpdateRect() {
        return updateRect;
    }

    /**
     * Sets the rectangle representing the area which needs to be
     * repainted in response to this event.
     * @param updateRect the rectangle area which needs to be repainted
     */
    public void setUpdateRect(Rectangle updateRect) {
        this.updateRect = updateRect;
    }

    public String paramString() {
        String typeStr;
        switch(id) {
          case PAINT:
              typeStr = "PAINT";
              break;
          case UPDATE:
              typeStr = "UPDATE";
              break;
          default:
              typeStr = "unknown type";
        }
        return typeStr + ",updateRect="+(updateRect != null ? updateRect.toString() : "null");
    }
}
