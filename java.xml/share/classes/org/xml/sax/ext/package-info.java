/**
 * Provides interfaces to SAX2 facilities that
 * conformant SAX drivers won't necessarily support.
 *
 *
 * <p>
 * This package is independent of the SAX2 core, though the functionality
 * exposed generally needs to be implemented within a parser core.
 * That independence has several consequences:
 *
 * <ul>
 *
 * <li>SAX2 drivers are <em>not</em> required to recognize these handlers.
 * </li>
 *
 * <li>You cannot assume that the class files will be present in every SAX2
 * installation.</li>
 *
 * <li>This package may be updated independently of SAX2 (i.e. new
 * handlers and classes may be added without updating SAX2 itself).</li>
 *
 * <li>The new handlers are not implemented by the SAX2
 * <code>org.xml.sax.helpers.DefaultHandler</code> or
 * <code>org.xml.sax.helpers.XMLFilterImpl</code> classes.
 * You can subclass these if you need such behavior, or
 * use the helper classes found here.</li>
 *
 * <li>The handlers need to be registered differently than core SAX2
 * handlers.</li>
 *
 * </ul>
 *
 * <p>This package, SAX2-ext, is a standardized extension to SAX2.  It is
 * designed both to allow SAX parsers to pass certain types of information
 * to applications, and to serve as a simple model for other SAX2 parser
 * extension packages.  Not all such extension packages should need to
 * be recognized directly by parsers, however.
 * As an example, most validation systems can be cleanly layered on top
 * of parsers supporting the standardized SAX2 interfaces.
 *
 * @apiNote The SAX API, originally developed at
 * <a href="http://www.saxproject.org">the SAX Project</a>,
 * has been defined by Java SE since 1.4.
 *
 * @since 1.4
 */
package org.xml.sax.ext;
