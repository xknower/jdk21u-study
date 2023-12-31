package org.w3c.dom.html;

/**
 *  Group options together in logical subdivisions. See the  OPTGROUP element
 * definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 * @since 1.4, DOM Level 2
 */
public interface HTMLOptGroupElement extends HTMLElement {
    /**
     *  The control is unavailable in this context. See the  disabled
     * attribute definition in HTML 4.0.
     */
    public boolean getDisabled();
    public void setDisabled(boolean disabled);

    /**
     *  Assigns a label to this option group. See the  label attribute
     * definition in HTML 4.0.
     */
    public String getLabel();
    public void setLabel(String label);

}
