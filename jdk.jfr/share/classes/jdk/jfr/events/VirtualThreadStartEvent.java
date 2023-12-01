package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.internal.MirrorEvent;

@Category("Java Application")
@Label("Virtual Thread Start")
@Name("jdk.VirtualThreadStart")
@MirrorEvent(className = "jdk.internal.event.VirtualThreadStartEvent")
public final class VirtualThreadStartEvent extends AbstractJDKEvent {

    @Label("Thread Id")
    public long javaThreadId;

}
