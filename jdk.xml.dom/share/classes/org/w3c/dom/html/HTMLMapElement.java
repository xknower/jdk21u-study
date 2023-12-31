package org.w3c.dom.html;

/**
 *  Client-side image map. See the  MAP element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 * @since 1.4, DOM Level 2
 */
public interface HTMLMapElement extends HTMLElement {
    /**
     *  The list of areas defined for the image map.
     */
    public HTMLCollection getAreas();

    /**
     *  Names the map (for use with <code>usemap</code> ). See the  name
     * attribute definition in HTML 4.0.
     */
    public String getName();
    public void setName(String name);

}
