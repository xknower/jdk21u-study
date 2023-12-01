package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.DataAmount;
import jdk.jfr.Name;
import jdk.jfr.Timespan;
import jdk.jfr.internal.Type;

@Name(Type.EVENT_NAME_PREFIX + "SocketRead")
@Label("Socket Read")
@Category("Java Application")
@Description("Reading data from a socket")
public final class SocketReadEvent extends AbstractJDKEvent {

    // The order of these fields must be the same as the parameters in
    // commit(..., String, String, int, long, long, boolean)

    @Label("Remote Host")
    public String host;

    @Label("Remote Address")
    public String address;

    @Label("Remote Port")
    public int port;

    @Label("Timeout Value")
    @Timespan(Timespan.MILLISECONDS)
    public long timeout;

    @Label("Bytes Read")
    @Description("Number of bytes read from the socket")
    @DataAmount
    public long bytesRead;

    @Label("End of Stream")
    @Description("If end of stream was reached")
    public boolean endOfStream;

    public static void commit(long start, long duration, String host, String address, int port, long timeout, long byteRead, boolean endOfStream) {
        // Generated
    }
}
