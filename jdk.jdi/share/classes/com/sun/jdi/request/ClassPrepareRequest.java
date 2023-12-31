package com.sun.jdi.request;

import com.sun.jdi.ReferenceType;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;

/**
 * Request for notification when a class is prepared in the target VM.
 * When an enabled ClassPrepareRequest is satisfied, an
 * {@link EventSet event set} containing a
 * {@link ClassPrepareEvent ClassPrepareEvent}
 * will be placed on the
 * {@link EventQueue EventQueue}.
 * The collection of existing ClassPrepareRequests is
 * managed by the {@link EventRequestManager}
 * <p>
 * Class preparation is defined in the Java Virtual Machine
 * Specification.
 *
 * @see ClassPrepareEvent
 * @see EventQueue
 * @see EventRequestManager
 *
 * @author Robert Field
 * @since  1.3
 */
public interface ClassPrepareRequest extends EventRequest {

    /**
     * Restricts the events generated by this request to be the
     * preparation of the given reference type and any subtypes.
     * An event will be generated for any prepared reference type that can
     * be safely cast to the given reference type.
     *
     * @param refType the reference type to filter on.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addClassFilter(ReferenceType refType);

    /**
     * Restricts the events generated by this request to the
     * preparation of reference types whose name matches this restricted
     * regular expression. Regular expressions are limited
     * to exact matches and patterns that begin with '*' or end with '*';
     * for example, "*.Foo" or "java.*".
     *
     * @param classPattern the pattern String to filter for.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addClassFilter(String classPattern);

    /**
     * Restricts the events generated by this request to the
     * preparation of reference types whose name does <b>not</b> match
     * this restricted regular expression. Regular expressions are limited
     * to exact matches and patterns that begin with '*' or end with '*';
     * for example, "*.Foo" or "java.*".
     *
     * @param classPattern the pattern String to filter against.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addClassExclusionFilter(String classPattern);

    /**
     * Restricts the events generated by this request to the
     * preparation of reference types for which the restricted regular
     * expression 'sourceNamePattern' matches one of the 'sourceNames' for
     * the reference type being prepared.
     * That is, if refType is the ReferenceType being prepared,
     * then there exists at least one stratum, call it 'someStratum'
     * on the list returned by
     *     refType.availableStrata();
     *
     * such that a name on the list returned by
     *     refType.sourceNames(someStratam)
     *
     * matches 'sourceNamePattern'.
     * Regular expressions are limited
     * to exact matches and patterns that begin with '*' or end with '*';
     * for example, "*.Foo" or "java.*".
     * <P>
     * Not all targets support this operation.
     * Use {@link VirtualMachine#canUseSourceNameFilters()}
     * to determine if the operation is supported.
     * @since 1.6
     * @param sourceNamePattern the pattern string to filter for.
     * @throws java.lang.UnsupportedOperationException if
     * the target virtual machine does not support this
     * operation.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addSourceNameFilter(String sourceNamePattern);
}
