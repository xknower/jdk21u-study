package java.awt.event;

import java.util.EventListener;

/**
 * The listener interface for receiving adjustment events.
 *
 * @author Amy Fowler
 * @since 1.1
 */
public interface AdjustmentListener extends EventListener {

    /**
     * Invoked when the value of the adjustable has changed.
     * @param e the event to be processed
     */
    public void adjustmentValueChanged(AdjustmentEvent e);

}
