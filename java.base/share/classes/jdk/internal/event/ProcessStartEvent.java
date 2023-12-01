package jdk.internal.event;

/**
 * Event for the start of an OS procsss
 */

public final class ProcessStartEvent extends Event {
    public long pid;
    public String directory;
    public String command;
}
