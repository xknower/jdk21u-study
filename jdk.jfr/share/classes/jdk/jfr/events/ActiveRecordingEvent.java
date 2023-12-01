package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Label;
import jdk.jfr.DataAmount;
import jdk.jfr.Name;
import jdk.jfr.StackTrace;
import jdk.jfr.Timespan;
import jdk.jfr.Timestamp;
import jdk.jfr.internal.Type;

@Name(Type.EVENT_NAME_PREFIX + "ActiveRecording")
@Label("Flight Recording")
@Category("Flight Recorder")
@StackTrace(false)
public final class ActiveRecordingEvent extends AbstractJDKEvent {

    // The order of these fields must be the same as the parameters in
    // commit(... , long, String, String, long, long, long, long, long)

    @Label("Id")
    public long id;

    @Label("Name")
    public String name;

    @Label("Destination")
    public String destination;

    @Label("To Disk")
    public boolean disk;

    @Label("Max Age")
    @Timespan(Timespan.MILLISECONDS)
    public long maxAge;

    @Label("Flush Interval")
    @Timespan(Timespan.MILLISECONDS)
    public long flushInterval;

    @Label("Max Size")
    @DataAmount
    public long maxSize;

    @Label("Start Time")
    @Timestamp(Timestamp.MILLISECONDS_SINCE_EPOCH)
    public long recordingStart;

    @Label("Recording Duration")
    @Timespan(Timespan.MILLISECONDS)
    public long recordingDuration;

    public static boolean enabled() {
        return false; // Generated
    }

    public static void commit(long timestamp, long duration, long id, String name,
                              String destination, boolean disk, long maxAge, long flushInterval,
                              long maxSize, long recordingStart, long recordingDuration) {
        // Generated
    }
}
