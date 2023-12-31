package javax.naming.event;

/**
  * Specifies the methods that a listener interested in namespace changes
  * must implement.
  * Specifically, the listener is interested in {@code NamingEvent}s
  * with event types of {@code OBJECT_ADDED, OBJECT_RENAMED}, or
  * {@code OBJECT_REMOVED}.
  *<p>
  * Such a listener must:
  *<ol>
  *<li>Implement this interface and its methods.
  *<li>Implement {@code NamingListener.namingExceptionThrown()} so that
  * it will be notified of exceptions thrown while attempting to
  * collect information about the events.
  *<li>Register with the source using the source's {@code addNamingListener()}
  *    method.
  *</ol>
  * A listener that wants to be notified of {@code OBJECT_CHANGED} event types
  * should also implement the {@code ObjectChangeListener}
  * interface.
  *
  * @author Rosanna Lee
  * @author Scott Seligman
  *
  * @see NamingEvent
  * @see ObjectChangeListener
  * @see EventContext
  * @see EventDirContext
  * @since 1.3
  */
public interface NamespaceChangeListener extends NamingListener {

    /**
     * Called when an object has been added.
     *<p>
     * The binding of the newly added object can be obtained using
     * {@code evt.getNewBinding()}.
     * @param evt The nonnull event.
     * @see NamingEvent#OBJECT_ADDED
     */
    void objectAdded(NamingEvent evt);

    /**
     * Called when an object has been removed.
     *<p>
     * The binding of the newly removed object can be obtained using
     * {@code evt.getOldBinding()}.
     * @param evt The nonnull event.
     * @see NamingEvent#OBJECT_REMOVED
     */
    void objectRemoved(NamingEvent evt);

    /**
     * Called when an object has been renamed.
     *<p>
     * The binding of the renamed object can be obtained using
     * {@code evt.getNewBinding()}. Its old binding (before the rename)
     * can be obtained using {@code evt.getOldBinding()}.
     * One of these may be null if the old/new binding was outside the
     * scope in which the listener has registered interest.
     * @param evt The nonnull event.
     * @see NamingEvent#OBJECT_RENAMED
     */
    void objectRenamed(NamingEvent evt);
}
