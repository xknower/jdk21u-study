package org.w3c.dom.html;

/**
 *  Notice of modification to part of a document. See the   INS  and  DEL
 * element definitions in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 * @since 1.4, DOM Level 2
 */
public interface HTMLModElement extends HTMLElement {
    /**
     *  A URI designating a document that describes the reason for the change.
     * See the  cite attribute definition in HTML 4.0.
     */
    public String getCite();
    public void setCite(String cite);

    /**
     *  The date and time of the change. See the  datetime attribute definition
     *  in HTML 4.0.
     */
    public String getDateTime();
    public void setDateTime(String dateTime);

}
