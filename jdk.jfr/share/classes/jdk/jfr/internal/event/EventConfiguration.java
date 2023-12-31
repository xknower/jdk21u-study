package jdk.jfr.internal.event;

import jdk.jfr.EventType;
import jdk.jfr.internal.EventControl;
import jdk.jfr.internal.JVM;
import jdk.jfr.internal.PlatformEventType;
import jdk.jfr.internal.PrivateAccess;
import jdk.jfr.SettingControl;

// Users should not be able to subclass or instantiate for security reasons.
public final class EventConfiguration {
    private final PlatformEventType platformEventType;
    private final EventType eventType;
    private final EventControl eventControl;
    private final SettingControl[] settings;

    // Private constructor so user code can't instantiate
    private EventConfiguration(EventType eventType, EventControl eventControl) {
        this.eventType = eventType;
        this.platformEventType = PrivateAccess.getInstance().getPlatformEventType(eventType);
        this.eventControl = eventControl;
        this.settings = eventControl.getSettingControls().toArray(new SettingControl[0]);
    }

    // Class jdk.jfr.internal.PlatformEventType is not
    // accessible from event class by design
    public PlatformEventType getPlatformEventType() {
        return platformEventType;
    }

    // Class jdk.jfr.internal.EventControl is not
    // accessible from event class by design
    public EventControl getEventControl() {
        return eventControl;
    }

    // Accessed by generated code in event class
    public boolean shouldCommit(long duration) {
        return isEnabled() && duration >= platformEventType.getThresholdTicks();
    }

    // Accessed by generated code in event class
    public SettingControl getSetting(int index) {
        return settings[index];
    }

    // Accessed by generated code in event class
    public boolean isEnabled() {
        return platformEventType.isCommittable();
    }

    // Accessed by generated code in event class
    public EventType getEventType() {
        return eventType;
    }

    // Not really part of the configuration, but the method
    // needs to be somewhere the event class can access,
    // but not part of the public API.
    public static long timestamp() {
        return JVM.counterTime();
    }

    // Accessed by generated code in event class
    public static long duration(long startTime) {
        if (startTime == 0) {
            // User forgot to invoke begin, or instrumentation was
            // added after the user invoked begin.
            // Returning 0 will make it an instant event
            return 0;
        }
        return timestamp() - startTime;
    }

    public boolean isRegistered() {
        return platformEventType.isRegistered();
    }

    public long getId() {
        return eventType.getId();
    }
}
