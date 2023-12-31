package javax.imageio.spi;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A node in a directed graph.  In addition to an arbitrary
 * {@code Object} containing user data associated with the node,
 * each node maintains a {@code Set}s of nodes which are pointed
 * to by the current node (available from {@code getOutNodes}).
 * The in-degree of the node (that is, number of nodes that point to
 * the current node) may be queried.
 *
 */
class DigraphNode<E> implements Cloneable, Serializable {

    /**
     * Use serialVersionUID from JDK 9 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 5308261378582246841L;

    /** The data associated with this node. */
    @SuppressWarnings("serial") // Not statically typed as Serializable
    protected E data;

    /**
     * A {@code Set} of neighboring nodes pointed to by this
     * node.
     */
    @SuppressWarnings("serial") // Not statically typed as Serializable
    protected Set<DigraphNode<E>> outNodes = new HashSet<>();

    /** The in-degree of the node. */
    protected int inDegree = 0;

    /**
     * A {@code Set} of neighboring nodes that point to this
     * node.
     */
    @SuppressWarnings("serial") // Not statically typed as Serializable
    private Set<DigraphNode<E>> inNodes = new HashSet<>();

    public DigraphNode(E data) {
        this.data = data;
    }

    /** Returns the {@code Object} referenced by this node. */
    public E getData() {
        return data;
    }

    /**
     * Returns an {@code Iterator} containing the nodes pointed
     * to by this node.
     */
    public Iterator<DigraphNode<E>> getOutNodes() {
        return outNodes.iterator();
    }

    /**
     * Adds a directed edge to the graph.  The outNodes list of this
     * node is updated and the in-degree of the other node is incremented.
     *
     * @param node a {@code DigraphNode}.
     *
     * @return {@code true} if the node was not previously the
     * target of an edge.
     */
    public boolean addEdge(DigraphNode<E> node) {
        if (outNodes.contains(node)) {
            return false;
        }

        outNodes.add(node);
        node.inNodes.add(this);
        node.incrementInDegree();
        return true;
    }

    /**
     * Returns {@code true} if an edge exists between this node
     * and the given node.
     *
     * @param node a {@code DigraphNode}.
     *
     * @return {@code true} if the node is the target of an edge.
     */
    public boolean hasEdge(DigraphNode<E> node) {
        return outNodes.contains(node);
    }

    /**
     * Removes a directed edge from the graph.  The outNodes list of this
     * node is updated and the in-degree of the other node is decremented.
     *
     * @return {@code true} if the node was previously the target
     * of an edge.
     */
    public boolean removeEdge(DigraphNode<E> node) {
        if (!outNodes.contains(node)) {
            return false;
        }

        outNodes.remove(node);
        node.inNodes.remove(this);
        node.decrementInDegree();
        return true;
    }

    /**
     * Removes this node from the graph, updating neighboring nodes
     * appropriately.
     */
    public void dispose() {
        Object[] inNodesArray = inNodes.toArray();
        for(int i=0; i<inNodesArray.length; i++) {
            @SuppressWarnings("unchecked")
            DigraphNode<E> node = (DigraphNode<E>)inNodesArray[i];
            node.removeEdge(this);
        }

        Object[] outNodesArray = outNodes.toArray();
        for(int i=0; i<outNodesArray.length; i++) {
            @SuppressWarnings("unchecked")
            DigraphNode<E> node = (DigraphNode<E>)outNodesArray[i];
            removeEdge(node);
        }
    }

    /** Returns the in-degree of this node. */
    public int getInDegree() {
        return inDegree;
    }

    /** Increments the in-degree of this node. */
    private void incrementInDegree() {
        ++inDegree;
    }

    /** Decrements the in-degree of this node. */
    private void decrementInDegree() {
        --inDegree;
    }
}
