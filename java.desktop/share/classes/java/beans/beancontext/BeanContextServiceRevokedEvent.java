package java.beans.beancontext;

import java.io.Serial;

/**
 * <p>
 * This event type is used by the
 * {@code BeanContextServiceRevokedListener} in order to
 * identify the service being revoked.
 * </p>
 */
public class BeanContextServiceRevokedEvent extends BeanContextEvent {

    /**
     * Use serialVersionUID from JDK 1.7 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -1295543154724961754L;

    /**
     * Construct a {@code BeanContextServiceEvent}.
     * @param bcs the {@code BeanContextServices}
     * from which this service is being revoked
     * @param sc the service that is being revoked
     * @param invalidate {@code true} for immediate revocation
     */
    public BeanContextServiceRevokedEvent(BeanContextServices bcs, Class<?> sc, boolean invalidate) {
        super((BeanContext)bcs);

        serviceClass    = sc;
        invalidateRefs  = invalidate;
    }

    /**
     * Gets the source as a reference of type {@code BeanContextServices}
     * @return the {@code BeanContextServices} from which
     * this service is being revoked
     */
    public BeanContextServices getSourceAsBeanContextServices() {
        return (BeanContextServices)getBeanContext();
    }

    /**
     * Gets the service class that is the subject of this notification
     * @return A {@code Class} reference to the
     * service that is being revoked
     */
    public Class<?> getServiceClass() { return serviceClass; }

    /**
     * Checks this event to determine whether or not
     * the service being revoked is of a particular class.
     * @param service the service of interest (should be non-null)
     * @return {@code true} if the service being revoked is of the
     * same class as the specified service
     */
    public boolean isServiceClass(Class<?> service) {
        return serviceClass.equals(service);
    }

    /**
     * Reports if the current service is being forcibly revoked,
     * in which case the references are now invalidated and unusable.
     * @return {@code true} if current service is being forcibly revoked
     */
    public boolean isCurrentServiceInvalidNow() { return invalidateRefs; }

    /**
     * fields
     */

    /**
     * A {@code Class} reference to the service that is being revoked.
     */
    protected Class<?> serviceClass;

    /**
     * {@code true} if current service is being forcibly revoked.
     */
    private boolean invalidateRefs;
}
