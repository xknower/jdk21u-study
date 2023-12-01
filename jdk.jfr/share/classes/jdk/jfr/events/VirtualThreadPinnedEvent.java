package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.internal.MirrorEvent;

@Category("Java Application")
@Label("Virtual Thread Pinned")
@Name("jdk.VirtualThreadPinned")
@MirrorEvent(className = "jdk.internal.event.VirtualThreadPinnedEvent")
public final class VirtualThreadPinnedEvent extends AbstractJDKEvent {
}
