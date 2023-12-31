package java.awt.peer;

import java.awt.Menu;
import java.awt.MenuItem;

/**
 * The peer interface for menus. This is used by {@link Menu}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface MenuPeer extends MenuItemPeer {

    /**
     * Adds the specified menu item to the menu.
     *
     * @param item the menu item to add
     *
     * @see Menu#add(MenuItem)
     */
    void addItem(MenuItem item);

    /**
     * Removes the menu item at the specified index.
     *
     * @param index the index of the item to remove
     *
     * @see Menu#remove(int)
     */
    void delItem(int index);
}
