package com.sun.org.apache.xalan.internal.xsltc.dom;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/**
 * @author Morten Jorgensen
 */
public interface ExtendedSAX extends ContentHandler, LexicalHandler, DTDHandler,
                                     DeclHandler
{
}
