package jdk.internal.event;

/**
 * Event details relating to the modification of a Security property.
 */

public final class SecurityPropertyModificationEvent extends Event {
    public String key;
    public String value;
}
