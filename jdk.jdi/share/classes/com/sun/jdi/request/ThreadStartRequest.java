package com.sun.jdi.request;

import com.sun.jdi.ThreadReference;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.ThreadStartEvent;

/**
 * Request for notification when a thread starts execution in the target VM.
 * When an enabled ThreadStartRequest is hit, an
 * {@link EventSet event set} containing a
 * {@link ThreadStartEvent ThreadStartEvent}
 * will be placed on the
 * {@link EventQueue EventQueue}.
 * The collection of existing ThreadStartRequests is
 * managed by the {@link EventRequestManager}
 *
 * @see ThreadStartEvent
 * @see EventQueue
 * @see EventRequestManager
 *
 * @author Robert Field
 * @since  1.3
 */
public interface ThreadStartRequest extends EventRequest {

    /**
     * Restricts the events generated by this request to those in
     * the given thread.
     * @param thread the thread to filter on.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addThreadFilter(ThreadReference thread);

    /**
     * Restricts the events generated by this request to only
     * <a href="{@docRoot}/java.base/java/lang/Thread.html#platform-threads">platform threads</a>.
     *
     * @implSpec
     * The default implementation throws {@code UnsupportedOperationException}.
     *
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted
     *
     * @since 21
     */
    default void addPlatformThreadsOnlyFilter() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
