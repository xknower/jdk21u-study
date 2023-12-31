package javax.imageio.spi;

/**
 * An optional interface that may be provided by service provider
 * objects that will be registered with a
 * {@code ServiceRegistry}.  If this interface is present,
 * notification of registration and deregistration will be performed.
 *
 * @see ServiceRegistry
 *
 */
public interface RegisterableService {

    /**
     * Called when an object implementing this interface is added to
     * the given {@code category} of the given
     * {@code registry}.  The object may already be registered
     * under another category or categories.
     *
     * @param registry a {@code ServiceRegistry} where this
     * object has been registered.
     * @param category a {@code Class} object indicating the
     * registry category under which this object has been registered.
     */
    void onRegistration(ServiceRegistry registry, Class<?> category);

    /**
     * Called when an object implementing this interface is removed
     * from the given {@code category} of the given
     * {@code registry}.  The object may still be registered
     * under another category or categories.
     *
     * @param registry a {@code ServiceRegistry} from which this
     * object is being (wholly or partially) deregistered.
     * @param category a {@code Class} object indicating the
     * registry category from which this object is being deregistered.
     */
    void onDeregistration(ServiceRegistry registry, Class<?> category);
}
