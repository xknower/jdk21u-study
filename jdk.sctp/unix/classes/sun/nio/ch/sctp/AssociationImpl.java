package sun.nio.ch.sctp;

import com.sun.nio.sctp.Association;

/**
 * An implementation of Association
 */
public class AssociationImpl extends Association {
    public AssociationImpl(int associationID,
                           int maxInStreams,
                           int maxOutStreams) {
        super(associationID, maxInStreams, maxOutStreams);
    }

    @Override
    public String toString() {
        return super.toString() + "[associationID:" +
                associationID() +
                ", maxIn:" +
                maxInboundStreams() +
                ", maxOut:" +
                maxOutboundStreams() +
                "]";
    }
}

