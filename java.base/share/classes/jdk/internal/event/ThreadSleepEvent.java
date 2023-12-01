package jdk.internal.event;

/**
 * Event recording thread sleeping.
 */

public final class ThreadSleepEvent extends Event {
    private static final ThreadSleepEvent EVENT = new ThreadSleepEvent();

    /**
     * Returns {@code true} if event is enabled, {@code false} otherwise.
     */
    public static boolean isTurnedOn() {
        return EVENT.isEnabled();
    }

    public long time;
}
