package java.awt.peer;

import java.awt.Canvas;
import java.awt.GraphicsConfiguration;

/**
 * The peer interface for {@link Canvas}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface CanvasPeer extends ComponentPeer {
    /**
     * Requests a GC that best suits this Canvas. The returned GC may differ
     * from the requested GC passed as the argument to this method. This method
     * must return a non-null value (given the argument is non-null as well).
     *
     * @param gc the requested graphics configuration
     * @return a graphics configuration that best suits this Canvas
     * @since 1.7
     */
    GraphicsConfiguration getAppropriateGraphicsConfiguration(
            GraphicsConfiguration gc);
}
