package java.awt.peer;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Window;

/**
 * The native peer interface for {@link KeyboardFocusManager}.
 */
public interface KeyboardFocusManagerPeer {

    /**
     * Sets the window that should become the focused window.
     *
     * @param win the window that should become the focused window
     *
     */
    void setCurrentFocusedWindow(Window win);

    /**
     * Returns the currently focused window.
     *
     * @return the currently focused window
     *
     * @see KeyboardFocusManager#getNativeFocusedWindow()
     */
    Window getCurrentFocusedWindow();

    /**
     * Sets the component that should become the focus owner.
     *
     * @param comp the component to become the focus owner
     *
     * @see KeyboardFocusManager#setNativeFocusOwner(Component)
     */
    void setCurrentFocusOwner(Component comp);

    /**
     * Returns the component that currently owns the input focus.
     *
     * @return the component that currently owns the input focus
     *
     * @see KeyboardFocusManager#getNativeFocusOwner()
     */
    Component getCurrentFocusOwner();

    /**
     * Clears the current global focus owner.
     *
     * @param activeWindow the active window
     *
     * @see KeyboardFocusManager#clearGlobalFocusOwner()
     */
    void clearGlobalFocusOwner(Window activeWindow);

}
