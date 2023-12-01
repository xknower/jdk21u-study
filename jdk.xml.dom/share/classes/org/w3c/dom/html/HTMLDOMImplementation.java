package org.w3c.dom.html;

import org.w3c.dom.DOMImplementation;

/**
 *  The <code>HTMLDOMImplementation</code> interface extends the
 * <code>DOMImplementation</code> interface with a method for creating an
 * HTML document instance.
 *
 * @since 1.4, DOM Level 2
 */
public interface HTMLDOMImplementation extends DOMImplementation {
    /**
     *  Creates an <code>HTMLDocument</code> object with the minimal tree made
     * of the following elements: <code>HTML</code> , <code>HEAD</code> ,
     * <code>TITLE</code> , and <code>BODY</code> .
     * @param title  The title of the document to be set as the content of the
     *   <code>TITLE</code> element, through a child <code>Text</code> node.
     * @return  A new <code>HTMLDocument</code> object.
     */
    public HTMLDocument createHTMLDocument(String title);

}
