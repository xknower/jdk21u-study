package java.awt.dnd;

import java.util.EventListener;

/**
 * A listener interface for receiving mouse motion events during a drag
 * operation.
 * <p>
 * The class that is interested in processing mouse motion events during
 * a drag operation either implements this interface or extends the abstract
 * {@code DragSourceAdapter} class (overriding only the methods of
 * interest).
 * <p>
 * Create a listener object using that class and then register it with
 * a {@code DragSource}. Whenever the mouse moves during a drag
 * operation initiated with this {@code DragSource}, that object's
 * {@code dragMouseMoved} method is invoked, and the
 * {@code DragSourceDragEvent} is passed to it.
 *
 * @see DragSourceDragEvent
 * @see DragSource
 * @see DragSourceListener
 * @see DragSourceAdapter
 *
 * @since 1.4
 */

public interface DragSourceMotionListener extends EventListener {

    /**
     * Called whenever the mouse is moved during a drag operation.
     *
     * @param dsde the {@code DragSourceDragEvent}
     */
    void dragMouseMoved(DragSourceDragEvent dsde);
}
