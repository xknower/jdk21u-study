package javax.swing.plaf;

import java.awt.Dimension;
import javax.swing.plaf.UIResource;


/**
 * A subclass of <code>Dimension</code> that implements
 * <code>UIResource</code>.  UI classes that use
 * <code>Dimension</code> values for default properties
 * should use this class.
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
 * @see javax.swing.plaf.UIResource
 * @author Amy Fowler
 *
 */
@SuppressWarnings("serial") // Same-version serialization only
public class DimensionUIResource extends Dimension implements UIResource
{
    /**
     * Constructs a {@code DimensionUIResource}.
     * @param width the width
     * @param height the height
     */
    public DimensionUIResource(int width, int height) {
        super(width, height);
    }
}
