package jdk.jfr;

/**
 * Base class for events, to be subclassed in order to define events and their
 * fields.
 * <p>
 * The following example shows how to implement an {@code Event} class.
 *
 * {@snippet class="Snippets" region="EventOverview"}
 * <p>
 * After an event is allocated and its field members are populated, it can be
 * written to the Flight Recorder system by using the {@link #commit()} method.
 * <p>
 * By default, an event is enabled. To disable an event annotate the
 * {@link Event} class with {@code @Enabled(false)}.
 * <p>
 * Supported field types are the Java primitives: {@code boolean}, {@code char},
 * {@code byte}, {@code short}, {@code int}, {@code long}, {@code float}, and
 * {@code double}. Supported reference types are: {@code String}, {@code Thread}
 * and {@code Class}. Arrays, enums, and other reference types are silently
 * ignored and not included. Fields that are of the supported types can be
 * excluded by using the transient modifier. Static fields, even of the
 * supported types, are not included.
 * <p>
 * Tools can visualize data in a meaningful way when annotations are used (for
 * example, {@code Label}, {@code Description}, and {@code Timespan}).
 * Annotations applied to an {@link Event} class or its fields are included if
 * they are present (indirectly, directly, or associated), have the
 * {@code MetadataDefinition} annotation, and they do not contain enums, arrays,
 * or classes.
 * <p>
 * Gathering data to store in an event can be expensive. The
 * {@link Event#shouldCommit()} method can be used to verify whether an event
 * instance would actually be written to the system when the
 * {@code commit()} method is invoked. If
 * {@code shouldCommit()} returns false, then those operations can be
 * avoided.
 *
 * @since 9
 */
@Enabled(true)
@StackTrace(true)
@Registered(true)
public abstract class Event extends jdk.internal.event.Event {
    /**
     * Sole constructor, for invocation by subclass constructors, typically
     * implicit.
     */
    protected Event() {
    }

    /**
     * Starts the timing of this event.
     */
    @Override
    public final void begin() {
    }

    /**
     * Ends the timing of this event.
     *
     * The {@code end} method must be invoked after the {@code begin} method.
     */
    @Override
    public final void end() {
    }

    /**
     * Writes the field values, time stamp, and event duration to the Flight
     * Recorder system.
     * <p>
     * If the event starts with an invocation of the {@code begin} method, but does
     * not end with an explicit invocation of the {@code end} method, then the event
     * ends when the {@code commit} method is invoked.
     */
    @Override
    public final void commit() {
    }

    /**
     * Returns {@code true} if at least one recording is running, and the
     * enabled setting for this event is set to {@code true}, otherwise
     * {@code false} is returned.
     *
     * @return {@code true} if event is enabled, {@code false} otherwise
     */
    @Override
    public final boolean isEnabled() {
        return false;
    }

    /**
     * Returns {@code true} if the enabled setting for this event is set to
     * {@code true} and if the duration is within the threshold for the event,
     * {@code false} otherwise. The threshold is the minimum threshold for all
     * running recordings.
     *
     * @return {@code true} if the event can be written to the Flight Recorder
     *         system, {@code false} otherwise
     */
    @Override
    public final boolean shouldCommit() {
        return false;
    }

    /**
     * Sets a field value.
     * <p>
     * Applicable only if the event is dynamically defined using the
     * {@code EventFactory} class.
     * <p>
     * The supplied {@code index} corresponds to the index of the
     * {@link ValueDescriptor} object passed to the factory method of the
     * {@code EventFactory} class.
     *
     * @param index the index of the field that is passed to
     *        {@link EventFactory#create(java.util.List, java.util.List)}
     * @param value value to set, can be {@code null}
     * @throws UnsupportedOperationException if it's not a dynamically generated
     *         event
     * @throws IndexOutOfBoundsException if {@code index} is less than {@code 0} or
     *         greater than or equal to the number of fields specified for the event
     *
     * @see EventType#getFields()
     * @see EventFactory
     */
    @Override
    public final void set(int index, Object value) {
    }
}
