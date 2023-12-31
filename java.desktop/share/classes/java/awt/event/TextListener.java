package java.awt.event;

import java.util.EventListener;

/**
 * The listener interface for receiving text events.
 *
 * The class that is interested in processing a text event
 * implements this interface. The object created with that
 * class is then registered with a component using the
 * component's {@code addTextListener} method. When the
 * component's text changes, the listener object's
 * {@code textValueChanged} method is invoked.
 *
 * @author Georges Saab
 *
 * @see TextEvent
 *
 * @since 1.1
 */
public interface TextListener extends EventListener {

    /**
     * Invoked when the value of the text has changed.
     * The code written for this method performs the operations
     * that need to occur when text changes.
     *
     * @param e the event to be processed
     */
    public void textValueChanged(TextEvent e);

}
