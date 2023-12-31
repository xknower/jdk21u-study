package org.w3c.dom.html;

/**
 *  Local change to font. See the  FONT element definition in HTML 4.0. This
 * element is deprecated in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 * @since 1.4, DOM Level 2
 */
public interface HTMLFontElement extends HTMLElement {
    /**
     *  Font color. See the  color attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    public String getColor();
    public void setColor(String color);

    /**
     *  Font face identifier. See the  face attribute definition in HTML 4.0.
     * This attribute is deprecated in HTML 4.0.
     */
    public String getFace();
    public void setFace(String face);

    /**
     *  Font size. See the  size attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    public String getSize();
    public void setSize(String size);

}
