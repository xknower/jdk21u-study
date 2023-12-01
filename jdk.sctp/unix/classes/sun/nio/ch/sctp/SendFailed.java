package sun.nio.ch.sctp;

import java.nio.ByteBuffer;
import java.net.SocketAddress;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.SendFailedNotification;

/**
 * An implementation of SendFailedNotification
 */
public class SendFailed extends SendFailedNotification
    implements SctpNotification
{
    private Association association;
    /* assocId is used to lookup the association before the notification is
     * returned to user code */
    private final int assocId;
    private final SocketAddress address;
    private final ByteBuffer buffer;
    private final int errorCode;
    private final int streamNumber;

    /* Invoked from native */
    private SendFailed(int assocId,
                       SocketAddress address,
                       ByteBuffer buffer,
                       int errorCode,
                       int streamNumber) {
        this.assocId = assocId;
        this.errorCode = errorCode;
        this.streamNumber = streamNumber;
        this.address = address;
        this.buffer = buffer;
    }

    @Override
    public int assocId() {
        return assocId;
    }

    @Override
    public void setAssociation(Association association) {
        this.association = association;
    }

    @Override
    public Association association() {
        /* may be null */
        return association;
    }

    @Override
    public SocketAddress address() {
        assert address != null;
        return address;
    }

    @Override
    public ByteBuffer buffer() {
        assert buffer != null;
        return buffer;
    }

    @Override
    public int errorCode() {
        return errorCode;
    }

    @Override
    public int streamNumber() {
        return streamNumber;
    }

    @Override
    public String toString() {
        return super.toString() + " [" +
                "Association:" + association +
                ", Address: " + address +
                ", buffer: " + buffer +
                ", errorCode: " + errorCode +
                ", streamNumber: " + streamNumber +
                "]";
    }
}
