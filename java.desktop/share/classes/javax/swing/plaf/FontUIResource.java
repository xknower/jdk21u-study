package javax.swing.plaf;

import java.awt.Font;
import javax.swing.plaf.UIResource;


/**
 * A subclass of java.awt.Font that implements UIResource.
 * UI classes which set default font properties should use
 * this class.
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
 * @author Hans Muller
 *
 */
@SuppressWarnings("serial") // Same-version serialization only
public class FontUIResource extends Font implements UIResource
{
    /**
     * Constructs a {@code FontUIResource}.
     * @param name the font name
     * @param style the style constant for the font
     * @param size the point size of the font
     */
    public FontUIResource(String name, int style, int size) {
        super(name, style, size);
    }

    /**
     * Constructs a {@code FontUIResource}.
     * @param font the font
     */
    public FontUIResource(Font font) {
        super(font);
    }
}
