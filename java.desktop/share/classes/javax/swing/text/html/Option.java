package javax.swing.text.html;

import java.io.Serializable;
import javax.swing.text.*;

/**
 * Value for the ListModel used to represent
 * &lt;option&gt; elements.  This is the object
 * installed as items of the DefaultComboBoxModel
 * used to represent the &lt;select&gt; element.
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
 * @author  Timothy Prinzing
 */
@SuppressWarnings("serial") // Same-version serialization only
public class Option implements Serializable {

    /**
     * Creates a new Option object.
     *
     * @param attr the attributes associated with the
     *  option element.  The attributes are copied to
     *  ensure they won't change.
     */
    public Option(AttributeSet attr) {
        this.attr = attr.copyAttributes();
        selected = (attr.getAttribute(HTML.Attribute.SELECTED) != null);
    }

    /**
     * Sets the label to be used for the option.
     *
     * @param label a label.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Fetch the label associated with the option.
     *
     * @return the label associated with the option.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Fetch the attributes associated with this option.
     *
     * @return the attributes associated with this option.
     */
    public AttributeSet getAttributes() {
        return attr;
    }

    /**
     * String representation is the label.
     */
    public String toString() {
        return label;
    }

    /**
     * Sets the selected state.
     *
     * @param state a selection state
     */
    protected void setSelection(boolean state) {
        selected = state;
    }

    /**
     * Fetches the selection state associated with this option.
     *
     * @return the selection state.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Convenient method to return the string associated
     * with the {@code value} attribute. If the
     * {@code value} has not been specified, the {@code label} will be
     * returned.
     *
     * @return the string associated with the {@code value} attribute,
     * or {@code label} if the value has been not specified.
     */
    public String getValue() {
        String value = (String) attr.getAttribute(HTML.Attribute.VALUE);
        if (value == null) {
            value = label;
        }
        return value;
    }

    private boolean selected;
    private String label;
    private AttributeSet attr;
}
