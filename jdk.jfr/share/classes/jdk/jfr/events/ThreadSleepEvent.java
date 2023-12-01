package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Timespan;
import jdk.jfr.internal.MirrorEvent;

@Category("Java Application")
@Label("Java Thread Sleep")
@Name("jdk.ThreadSleep")
@MirrorEvent(className = "jdk.internal.event.ThreadSleepEvent")
public final class ThreadSleepEvent extends AbstractJDKEvent {
    @Label("Sleep Time")
    @Timespan(Timespan.NANOSECONDS)
    public long time;
}
