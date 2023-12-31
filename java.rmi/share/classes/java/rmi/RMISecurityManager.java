package java.rmi;

import java.security.*;

/**
 * {@code RMISecurityManager} implements a policy identical to the policy
 * implemented by {@link SecurityManager}. RMI applications
 * should use the {@code SecurityManager} class or another appropriate
 * {@code SecurityManager} implementation instead of this class. RMI's class
 * loader will download classes from remote locations only if a security
 * manager has been set.
 *
 * @implNote
 * <p>Applets typically run in a container that already has a security
 * manager, so there is generally no need for applets to set a security
 * manager. If you have a standalone application, you might need to set a
 * {@code SecurityManager} in order to enable class downloading. This can be
 * done by adding the following to your code. (It needs to be executed before
 * RMI can download code from remote hosts, so it most likely needs to appear
 * in the {@code main} method of your application.)
 *
 * <pre>{@code
 *    if (System.getSecurityManager() == null) {
 *        System.setSecurityManager(new SecurityManager());
 *    }
 * }</pre>
 *
 * @author  Roger Riggs
 * @author  Peter Jones
 * @since 1.1
 * @deprecated This class is only useful in conjunction with
 *       {@linkplain SecurityManager the Security Manager}, which is deprecated
 *       and subject to removal in a future release. Consequently, this class
 *       is also deprecated and subject to removal. There is no replacement for
 *       the Security Manager or this class.
 */
@SuppressWarnings("removal")
@Deprecated(since="1.8", forRemoval = true)
public class RMISecurityManager extends SecurityManager {

    /**
     * Constructs a new {@code RMISecurityManager}.
     * @since 1.1
     */
    public RMISecurityManager() {
    }
}
