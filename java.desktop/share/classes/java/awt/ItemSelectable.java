package java.awt;

import java.awt.event.*;

/**
 * The interface for objects which contain a set of items for
 * which zero or more can be selected.
 *
 * @author Amy Fowler
 */

public interface ItemSelectable {

    /**
     * Returns the selected items or {@code null} if no
     * items are selected.
     *
     * @return the list of selected objects, or {@code null}
     */
    public Object[] getSelectedObjects();

    /**
     * Adds a listener to receive item events when the state of an item is
     * changed by the user. Item events are not sent when an item's
     * state is set programmatically.  If {@code l} is
     * {@code null}, no exception is thrown and no action is performed.
     *
     * @param    l the listener to receive events
     * @see ItemEvent
     */
    public void addItemListener(ItemListener l);

    /**
     * Removes an item listener.
     * If {@code l} is {@code null},
     * no exception is thrown and no action is performed.
     *
     * @param   l the listener being removed
     * @see ItemEvent
     */
    public void removeItemListener(ItemListener l);
}
