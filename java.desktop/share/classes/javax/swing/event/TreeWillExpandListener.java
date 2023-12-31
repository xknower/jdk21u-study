package javax.swing.event;

import java.util.EventListener;
import javax.swing.tree.ExpandVetoException;

/**
  * The listener that's notified when a tree expands or collapses
  * a node.
  * For further information and examples see
  * <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/treewillexpandlistener.html">How to Write a Tree-Will-Expand Listener</a>,
  * a section in <em>The Java Tutorial.</em>
  *
  * @author Scott Violet
  */

public interface TreeWillExpandListener extends EventListener {
    /**
     * Invoked whenever a node in the tree is about to be expanded.
     *
     * @param event a {@code TreeExpansionEvent} containing a {@code TreePath}
     *              object for the node
     * @throws ExpandVetoException to signify expansion has been canceled
     */
    public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException;

    /**
     * Invoked whenever a node in the tree is about to be collapsed.
     *
     * @param event a {@code TreeExpansionEvent} containing a {@code TreePath}
     *              object for the node
     * @throws ExpandVetoException to signify collapse has been canceled
     */
    public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException;
}
