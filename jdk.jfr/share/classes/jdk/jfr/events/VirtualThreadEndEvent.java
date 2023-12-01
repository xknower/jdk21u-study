package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.internal.MirrorEvent;

@Category("Java Application")
@Label("Virtual Thread End")
@Name("jdk.VirtualThreadEnd")
@MirrorEvent(className = "jdk.internal.event.VirtualThreadEndEvent")
public final class VirtualThreadEndEvent extends AbstractJDKEvent {

    @Label("Thread Id")
    public long javaThreadId;

}
