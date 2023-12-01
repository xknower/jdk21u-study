package jdk.internal.event;

/**
 * Event recording when an attempt to submit the task for a virtual thread failed.
 */
public class VirtualThreadSubmitFailedEvent extends Event {
    public long javaThreadId;
    public String exceptionMessage;
}
