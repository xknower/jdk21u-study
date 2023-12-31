package java.beans.beancontext;

import java.beans.beancontext.BeanContextServiceRevokedEvent;

import java.util.EventListener;

/**
 *  The listener interface for receiving
 * {@code BeanContextServiceRevokedEvent} objects. A class that is
 * interested in processing a {@code BeanContextServiceRevokedEvent}
 * implements this interface.
 */
public interface BeanContextServiceRevokedListener extends EventListener {

    /**
     * The service named has been revoked. getService requests for
     * this service will no longer be satisfied.
     * @param bcsre the {@code BeanContextServiceRevokedEvent} received
     * by this listener.
     */
    void serviceRevoked(BeanContextServiceRevokedEvent bcsre);
}
