package com.sun.xml.internal.stream.events;

import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.XMLEvent;
import javax.xml.namespace.QName;

import javax.xml.XMLConstants;
/**
 *
 * @author  Neeraj Bajaj,K Venugopal
 */
public class NamespaceImpl extends AttributeImpl implements Namespace{

    public NamespaceImpl( ) {
        init();
    }

    /** Creates a new instance of NamespaceImpl */
    public NamespaceImpl(String namespaceURI) {
        super(XMLConstants.XMLNS_ATTRIBUTE,XMLConstants.XMLNS_ATTRIBUTE_NS_URI,XMLConstants.DEFAULT_NS_PREFIX,namespaceURI,null);
        init();
    }

    public NamespaceImpl(String prefix, String namespaceURI){
        super(XMLConstants.XMLNS_ATTRIBUTE,XMLConstants.XMLNS_ATTRIBUTE_NS_URI,prefix,namespaceURI,null);
        init();
    }

    public boolean isDefaultNamespaceDeclaration() {
        QName name = this.getName();

        if(name != null && (name.getLocalPart().equals(XMLConstants.DEFAULT_NS_PREFIX)))
            return true;
        return false;
    }

    void setPrefix(String prefix){
        if(prefix == null)
            setName(new QName(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,XMLConstants.DEFAULT_NS_PREFIX,XMLConstants.XMLNS_ATTRIBUTE));
        else// new QName(uri, localpart, prefix)
            setName(new QName(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,prefix,XMLConstants.XMLNS_ATTRIBUTE));
    }

    public String getPrefix() {
        //for a namespace declaration xmlns:prefix="uri" to get the prefix we have to get the
        //local name if this declaration is stored as QName.
        QName name = this.getName();
        if(name != null)
            return name.getLocalPart();
        return null;
    }

    public String getNamespaceURI() {
        //we are treating namespace declaration as attribute -- so URI is stored as value
        //xmlns:prefix="Value"
        return this.getValue();
    }

    void setNamespaceURI(String uri) {
        //we are treating namespace declaration as attribute -- so URI is stored as value
        //xmlns:prefix="Value"
        this.setValue(uri);
    }

    protected void init(){
        setEventType(XMLEvent.NAMESPACE);
    }

    public int getEventType(){
        return XMLEvent.NAMESPACE;
    }

    public boolean isNamespace(){
        return true;
    }
}
