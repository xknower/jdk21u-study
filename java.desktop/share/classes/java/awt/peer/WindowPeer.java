package java.awt.peer;

import java.awt.*;

/**
 * The peer interface for {@link Window}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface WindowPeer extends ContainerPeer {

    /**
     * Makes this window the topmost window on the desktop.
     *
     * @see Window#toFront()
     */
    void toFront();

    /**
     * Makes this window the bottommost window on the desktop.
     *
     * @see Window#toBack()
     */
    void toBack();

    /**
     * Updates the window's always-on-top state.
     * Sets if the window should always stay
     * on top of all other windows or not.
     *
     * @see Window#isAlwaysOnTop()
     * @see Window#setAlwaysOnTop(boolean)
     */
    void updateAlwaysOnTopState();

    /**
     * Updates the window's focusable state.
     *
     * @see Window#setFocusableWindowState(boolean)
     */
    void updateFocusableWindowState();

    /**
     * Sets if this window is blocked by a modal dialog or not.
     *
     * @param blocker the blocking modal dialog
     * @param blocked {@code true} to block the window, {@code false}
     *        to unblock it
     */
    void setModalBlocked(Dialog blocker, boolean blocked);

    /**
     * Updates the minimum size on the peer.
     *
     * @see Window#setMinimumSize(Dimension)
     */
    void updateMinimumSize();

    /**
     * Updates the icons for the window.
     *
     * @see Window#setIconImages(java.util.List)
     */
    void updateIconImages();

    /**
     * Sets the level of opacity for the window.
     * @param opacity the level of opacity
     * @see Window#setOpacity(float)
     */
    void setOpacity(float opacity);

    /**
     * Enables the per-pixel alpha support for the window.
     * @param isOpaque whether or not per-pixel alpha support is
     * enabled
     * @see Window#setBackground(Color)
     */
    void setOpaque(boolean isOpaque);

    /**
     * Updates the native part of non-opaque window.
     *
     * @see Window#setBackground(Color)
     */
    void updateWindow();

    /**
     * Instructs the peer to update the position of the security warning.
     */
    void repositionSecurityWarning();
}
