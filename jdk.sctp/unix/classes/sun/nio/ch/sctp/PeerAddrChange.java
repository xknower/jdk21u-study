package sun.nio.ch.sctp;

import java.net.SocketAddress;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import java.lang.annotation.Native;

/**
 * An implementation of PeerAddressChangeNotification
 */
public class PeerAddrChange extends PeerAddressChangeNotification
    implements SctpNotification
{
    /* static final ints so that they can be referenced from native */
    @Native private static final int SCTP_ADDR_AVAILABLE = 1;
    @Native private static final int SCTP_ADDR_UNREACHABLE = 2;
    @Native private static final int SCTP_ADDR_REMOVED = 3;
    @Native private static final int SCTP_ADDR_ADDED = 4;
    @Native private static final int SCTP_ADDR_MADE_PRIM = 5;
    @Native private static final int SCTP_ADDR_CONFIRMED =6;

    private Association association;

    /* assocId is used to look up the association before the notification is
     * returned to user code */
    private final int assocId;
    private final SocketAddress address;
    private final AddressChangeEvent event;

    /* Invoked from native */
    private PeerAddrChange(int assocId, SocketAddress address, int intEvent) {
        this.event = switch (intEvent) {
            case SCTP_ADDR_AVAILABLE   -> AddressChangeEvent.ADDR_AVAILABLE;
            case SCTP_ADDR_UNREACHABLE -> AddressChangeEvent.ADDR_UNREACHABLE;
            case SCTP_ADDR_REMOVED     -> AddressChangeEvent.ADDR_REMOVED;
            case SCTP_ADDR_ADDED       -> AddressChangeEvent.ADDR_ADDED;
            case SCTP_ADDR_MADE_PRIM   -> AddressChangeEvent.ADDR_MADE_PRIMARY;
            case SCTP_ADDR_CONFIRMED   -> AddressChangeEvent.ADDR_CONFIRMED;
            default -> throw new AssertionError("Unknown event type");
        };
        this.assocId = assocId;
        this.address = address;
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
    public SocketAddress address() {
        assert address != null;
        return address;
    }

    @Override
    public Association association() {
        assert association != null;
        return association;
    }

    @Override
    public AddressChangeEvent event() {
        return event;
    }

    @Override
    public String toString() {
        return super.toString() + " [" +
                "Address: " + address +
                ", Association:" + association +
                ", Event: " + event + "]";
    }
}

