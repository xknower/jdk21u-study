package com.sun.jdi.event;

import com.sun.jdi.ObjectReference;
import com.sun.jdi.ThreadReference;

/**
 * Notification that a thread in the target VM has finished
 * waiting on an monitor object.
 *
 * @see EventQueue
 * @see MonitorWaitEvent
 *
 * @author Swamy Venkataramanappa
 * @since  1.6
 */
public interface MonitorWaitedEvent extends LocatableEvent {

    /**
     * Returns the thread in which this event has occurred.
     *
     * @return a {@link ThreadReference} which mirrors the event's thread in
     * the target VM.
     */
    public ThreadReference thread();

    /**
     * Returns the monitor object this thread waited on.
     *
     * @return an {@link ObjectReference} for the monitor.
     */
    public ObjectReference monitor();

    /**
     * Returns whether the wait has timed out or been interrupted.
     *
     * @return {@code true} if the wait is timed out.
     */
    public boolean timedout();
}
