package com.sun.org.apache.xml.internal.security.parser;

import java.io.InputStream;

import org.w3c.dom.Document;

/**
 * A interface to allow pluggable ways of parsing an InputStream into a DOM Document.
 */
public interface XMLParser {

    Document parse(InputStream inputStream, boolean disallowDocTypeDeclarations) throws XMLParserException;

}