package javax.swing.tree;

import javax.swing.event.TreeExpansionEvent;

/**
 * Exception used to stop an expand/collapse from happening.
 * See <a
 href="https://docs.oracle.com/javase/tutorial/uiswing/events/treewillexpandlistener.html">How to Write a Tree-Will-Expand Listener</a>
 * in <em>The Java Tutorial</em>
 * for further information and examples.
 *
 * @author Scott Violet
 */
@SuppressWarnings("serial") // Same-version serialization only
public class ExpandVetoException extends Exception {
    /** The event that the exception was created for. */
    protected TreeExpansionEvent      event;

    /**
     * Constructs an ExpandVetoException object with no message.
     *
     * @param event  a TreeExpansionEvent object
     */

    public ExpandVetoException(TreeExpansionEvent event) {
        this(event, null);
    }

    /**
     * Constructs an ExpandVetoException object with the specified message.
     *
     * @param event    a TreeExpansionEvent object
     * @param message  a String containing the message
     */
    public ExpandVetoException(TreeExpansionEvent event, String message) {
        super(message);
        this.event = event;
    }
}
