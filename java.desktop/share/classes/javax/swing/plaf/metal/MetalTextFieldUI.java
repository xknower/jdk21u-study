package javax.swing.plaf.metal;

import java.awt.*;
import java.beans.*;

import javax.swing.*;

import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

/**
 * Basis of a look and feel for a JTextField.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author  Steve Wilson
 */
@SuppressWarnings("serial") // Same-version serialization only
public class MetalTextFieldUI extends BasicTextFieldUI {

    /**
     * Constructs a {@code MetalTextFieldUI}.
     */
    public MetalTextFieldUI() {}

    /**
     * Constructs {@code MetalTextFieldUI}.
     *
     * @param c a component
     * @return the instance of {@code MetalTextFieldUI}
     */
    public static ComponentUI createUI(JComponent c) {
        return new MetalTextFieldUI();
    }

    /**
     * This method gets called when a bound property is changed
     * on the associated JTextComponent.  This is a hook
     * which UI implementations may change to reflect how the
     * UI displays bound properties of JTextComponent subclasses.
     *
     * @param evt the property change event
     */
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

 }
