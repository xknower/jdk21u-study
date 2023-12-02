package com.sun.jdi.event;

import com.sun.jdi.Value;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.request.ModificationWatchpointRequest;

/**
 * Notification of a field modification in the
 * target VM.
 *
 * @see EventQueue
 * @see VirtualMachine
 * @see ModificationWatchpointRequest
 *
 * @author Robert Field
 * @since  1.3
 */
public interface ModificationWatchpointEvent extends WatchpointEvent {

    /**
     * Value that will be assigned to the field when the instruction
     * completes.
     */
    Value valueToBe();
}
