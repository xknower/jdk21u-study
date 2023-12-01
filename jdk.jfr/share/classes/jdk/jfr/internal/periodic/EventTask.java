package jdk.jfr.internal.periodic;

import jdk.jfr.internal.PlatformEventType;

/**
 * Base class for periodic events.
 */
abstract class EventTask extends PeriodicTask {
    private final PlatformEventType eventType;

    public EventTask(PlatformEventType eventType, LookupKey lookupKey) {
        super(lookupKey, "event " + eventType.getLogName());
        this.eventType = eventType;
    }

    @Override
    public final boolean isSchedulable() {
        return eventType.isEnabled() && !eventType.isChunkTime();
    }

    @Override
    protected final long fetchPeriod() {
        return eventType.getPeriod();
    }

    public final PlatformEventType getEventType() {
        return eventType;
    }
}
