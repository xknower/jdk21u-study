package sun.awt.im;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.im.spi.InputMethod;

/**
 * An input method adapter interfaces with the native input methods
 * on a host platform. In general, it looks to the input method
 * framework like a Java input method (that may support a few more
 * locales than a typical Java input method). However, since it
 * often has to work in a slightly hostile environment that's not
 * designed for easy integration into the Java input method
 * framework, it gets some special treatment that's not available
 * to Java input methods.
 * <p>
 * Licensees are free to modify this class as necessary to implement
 * their host input method adapters.
 *
 * @author JavaSoft International
 */

public abstract class InputMethodAdapter implements InputMethod {

    private Component clientComponent;

    void setClientComponent(Component client) {
        clientComponent = client;
    }

    protected Component getClientComponent() {
        return clientComponent;
    }

    protected boolean haveActiveClient() {
        return clientComponent != null && clientComponent.getInputMethodRequests() != null;
    }

    /**
     * Informs the input method adapter about the component that has the AWT
     * focus if it's using the input context owning this adapter instance.
     */
    protected void setAWTFocussedComponent(Component component) {
        // ignore - adapters can override if needed
    }

    /**
     * Returns whether host input methods can support below-the-spot input.
     * Returns false by default.
     */
    protected boolean supportsBelowTheSpot() {
        return false;
    }

    /**
     * Informs the input method adapter not to listen to the native events.
     */
    protected void stopListening() {
        // ignore - adapters can override if needed
    }

    /**
     * Notifies client Window location or status changes
     */
    public void notifyClientWindowChange(Rectangle location) {
    }

    /**
     * Starts reconvertion. An implementing host adapter has to override
     * this method if it can support reconvert().
     * @throws UnsupportedOperationException when the adapter does not override
     * the method.
     */
    public void reconvert() {
        throw new UnsupportedOperationException();
    }

    /**
     * Disable the native input method. This method is provided for explicitly
     * turning off the native IM. The native IM is not turned off
     * when the native input method is deactivated. This method is
     * always called on AWT EDT. See details in bug 6226489.
     */
    public abstract void disableInputMethod();


    /**
     * Returns a string with information about the native input method, or
     * null.
     */
    public abstract String getNativeInputMethodInfo();
}
