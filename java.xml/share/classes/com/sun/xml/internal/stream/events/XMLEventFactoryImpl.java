package com.sun.xml.internal.stream.events;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.Location;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;


/**
 *
 * @author  Neeraj Bajaj, k venugopal
 */
public class XMLEventFactoryImpl extends XMLEventFactory {

    Location location = null;
    /** Creates a new instance of XMLEventFactory */
    public XMLEventFactoryImpl() {
    }

    @Override
    public Attribute createAttribute(String localName, String value) {
        AttributeImpl attr =  new AttributeImpl(localName, value);
        if(location != null)attr.setLocation(location);
        return attr;
    }

    @Override
    public Attribute createAttribute(QName name, String value) {
        return createAttribute(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart(), value);
    }

    @Override
    public Attribute createAttribute(String prefix, String namespaceURI, String localName, String value) {
        AttributeImpl attr =  new AttributeImpl(prefix, namespaceURI, localName, value, null);
        if(location != null)attr.setLocation(location);
        return attr;
    }

    @Override
    public Characters createCData(String content) {
        //stax doesn't have separate CDATA event. This is taken care by
        //CHARACTERS event setting the cdata flag to true.
        CharacterEvent charEvent =  new CharacterEvent(content, true);
        if(location != null)charEvent.setLocation(location);
        return charEvent;
    }

    @Override
    public Characters createCharacters(String content) {
        CharacterEvent charEvent =  new CharacterEvent(content);
        if(location != null)charEvent.setLocation(location);
        return charEvent;
    }

    @Override
    public javax.xml.stream.events.Comment createComment(String text) {
        CommentEvent charEvent =  new CommentEvent(text);
        if(location != null)charEvent.setLocation(location);
        return charEvent;
    }

    @Override
    public javax.xml.stream.events.DTD createDTD(String dtd) {
        DTDEvent dtdEvent = new DTDEvent(dtd);
        if(location != null)dtdEvent.setLocation(location);
        return dtdEvent;
    }

    @Override
    public javax.xml.stream.events.EndDocument createEndDocument() {
        EndDocumentEvent event =new EndDocumentEvent();
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public javax.xml.stream.events.EndElement createEndElement(QName name,
            Iterator<? extends Namespace> namespaces) {
        return createEndElement(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart());
    }

    @Override
    public javax.xml.stream.events.EndElement createEndElement(
            String prefix, String namespaceUri, String localName) {
        EndElementEvent event =  new EndElementEvent(prefix, namespaceUri, localName);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public javax.xml.stream.events.EndElement createEndElement(String prefix, String namespaceUri,
            String localName, Iterator<? extends Namespace> namespaces) {

        EndElementEvent event =  new EndElementEvent(prefix, namespaceUri, localName);
        if(namespaces!=null){
            while(namespaces.hasNext())
                event.addNamespace(namespaces.next());
        }
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public EntityReference createEntityReference(String name, EntityDeclaration entityDeclaration) {
        EntityReferenceEvent event =  new EntityReferenceEvent(name, entityDeclaration);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public Characters createIgnorableSpace(String content) {
        CharacterEvent event =  new CharacterEvent(content, false, true);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public Namespace createNamespace(String namespaceURI) {
        NamespaceImpl event =  new NamespaceImpl(namespaceURI);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public Namespace createNamespace(String prefix, String namespaceURI) {
        NamespaceImpl event =  new NamespaceImpl(prefix, namespaceURI);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public ProcessingInstruction createProcessingInstruction(String target, String data) {
        ProcessingInstructionEvent event =  new ProcessingInstructionEvent(target, data);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public Characters createSpace(String content) {
        CharacterEvent event =  new CharacterEvent(content);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public StartDocument createStartDocument() {
        StartDocumentEvent event = new StartDocumentEvent();
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public StartDocument createStartDocument(String encoding) {
        StartDocumentEvent event =  new StartDocumentEvent(encoding);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public StartDocument createStartDocument(String encoding, String version) {
        StartDocumentEvent event =  new StartDocumentEvent(encoding, version);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public StartDocument createStartDocument(String encoding, String version, boolean standalone) {
        StartDocumentEvent event =  new StartDocumentEvent(encoding, version, standalone);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public StartElement createStartElement(QName name, Iterator<? extends Attribute> attributes,
            Iterator<? extends Namespace> namespaces) {
        return createStartElement(name.getPrefix(), name.getNamespaceURI(),
                name.getLocalPart(), attributes, namespaces);
    }

    @Override
    public StartElement createStartElement(String prefix, String namespaceUri, String localName) {
        StartElementEvent event =  new StartElementEvent(prefix, namespaceUri, localName);
        if(location != null)event.setLocation(location);
        return event;
    }

    @Override
    public StartElement createStartElement(String prefix, String namespaceUri,
            String localName, Iterator<? extends Attribute> attributes,
            Iterator<? extends Namespace> namespaces) {
        return createStartElement(prefix, namespaceUri, localName, attributes, namespaces, null);
    }

    @Override
    public StartElement createStartElement(String prefix, String namespaceUri,
            String localName, Iterator<? extends Attribute> attributes,
            Iterator<? extends Namespace> namespaces, NamespaceContext context) {
        StartElementEvent elem =  new StartElementEvent(prefix, namespaceUri, localName);
        elem.addAttributes(attributes);
        elem.addNamespaceAttributes(namespaces);
        elem.setNamespaceContext(context);
        if(location != null)elem.setLocation(location);
        return elem;
    }

    @Override
    public void setLocation(javax.xml.stream.Location location) {
        this.location = location;
    }

}
