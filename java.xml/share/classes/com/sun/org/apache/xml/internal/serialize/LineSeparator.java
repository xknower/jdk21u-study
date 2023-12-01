package com.sun.org.apache.xml.internal.serialize;


/**
 * @author <a href="mailto:arkin@intalio..com">Assaf Arkin</a>
 * @see OutputFormat
 *
 * @deprecated As of JDK 9, Xerces 2.9.0, Xerces DOM L3 Serializer implementation
 * is replaced by that of Xalan. Main class
 * {@link com.sun.org.apache.xml.internal.serialize.DOMSerializerImpl} is replaced
 * by {@link com.sun.org.apache.xml.internal.serializer.dom3.LSSerializerImpl}.
 */
@Deprecated
public final class LineSeparator
{


    /**
     * Line separator for Unix systems (<tt>\n</tt>).
     */
    public static final String Unix = "\n";


    /**
     * Line separator for Windows systems (<tt>\r\n</tt>).
     */
    public static final String Windows = "\r\n";


    /**
     * Line separator for Macintosh systems (<tt>\r</tt>).
     */
    public static final String Macintosh = "\r";


    /**
     * Line separator for the Web (<tt>\n</tt>).
     */
    public static final String Web = "\n";


}
