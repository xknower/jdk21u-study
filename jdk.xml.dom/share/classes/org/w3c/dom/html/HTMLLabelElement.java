package org.w3c.dom.html;

/**
 *  Form field label text. See the  LABEL element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 * @since 1.4, DOM Level 2
 */
public interface HTMLLabelElement extends HTMLElement {
    /**
     *  Returns the <code>FORM</code> element containing this control. Returns
     * <code>null</code> if this control is not within the context of a form.
     */
    public HTMLFormElement getForm();

    /**
     *  A single character access key to give access to the form control. See
     * the  accesskey attribute definition in HTML 4.0.
     */
    public String getAccessKey();
    public void setAccessKey(String accessKey);

    /**
     *  This attribute links this label with another form control by
     * <code>id</code> attribute. See the  for attribute definition in HTML
     * 4.0.
     */
    public String getHtmlFor();
    public void setHtmlFor(String htmlFor);

}
