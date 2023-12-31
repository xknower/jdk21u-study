package java.awt.event;

/**
 * An abstract adapter class for receiving keyboard events.
 * The methods in this class are empty. This class exists as
 * convenience for creating listener objects.
 * <P>
 * Extend this class to create a {@code KeyEvent} listener
 * and override the methods for the events of interest. (If you implement the
 * {@code KeyListener} interface, you have to define all of
 * the methods in it. This abstract class defines null methods for them
 * all, so you can only have to define methods for events you care about.)
 * <P>
 * Create a listener object using the extended class and then register it with
 * a component using the component's {@code addKeyListener}
 * method. When a key is pressed, released, or typed,
 * the relevant method in the listener object is invoked,
 * and the {@code KeyEvent} is passed to it.
 *
 * @author Carl Quinn
 *
 * @see KeyEvent
 * @see KeyListener
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html">Tutorial: Writing a Key Listener</a>
 *
 * @since 1.1
 */
public abstract class KeyAdapter implements KeyListener {

    /**
     * Constructs a {@code KeyAdapter}.
     */
    protected KeyAdapter() {}

    /**
     * Invoked when a key has been typed.
     * This event occurs when a key press is followed by a key release.
     */
    public void keyTyped(KeyEvent e) {}

    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e) {}

    /**
     * Invoked when a key has been released.
     */
    public void keyReleased(KeyEvent e) {}
}
