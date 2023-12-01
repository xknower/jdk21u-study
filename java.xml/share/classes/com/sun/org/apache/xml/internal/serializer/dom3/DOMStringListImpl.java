package com.sun.org.apache.xml.internal.serializer.dom3;

//import org.apache.xerces.dom3.DOMStringList;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMStringList;

/**
 * This class implemets the DOM Level 3 Core interface DOMStringList.
 *
 * @xsl.usage internal
 * @LastModified: Oct 2017
 */
final class DOMStringListImpl implements DOMStringList {

    //A collection of DOMString values
    private List<String> fStrings;

    /**
     * Construct an empty list of DOMStringListImpl
     */
    DOMStringListImpl() {
        fStrings = new ArrayList<>();
    }

    /**
     * Construct an empty list of DOMStringListImpl
     */
    DOMStringListImpl(List<String> params) {
        fStrings = params;
    }

    /**
     * Construct an empty list of DOMStringListImpl
     */
    DOMStringListImpl(String[] params ) {
        fStrings = new ArrayList<>();
        if (params != null) {
            for (int i=0; i < params.length; i++) {
                fStrings.add(params[i]);
            }
        }
    }

    /**
     * @see org.apache.xerces.dom3.DOMStringList#item(int)
     */
    public String item(int index) {
        try {
            return fStrings.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * @see org.apache.xerces.dom3.DOMStringList#getLength()
     */
    public int getLength() {
        return fStrings.size();
    }

    /**
     * @see org.apache.xerces.dom3.DOMStringList#contains(String)
     */
    public boolean contains(String param) {
        return fStrings.contains(param) ;
    }

    /**
     * DOM Internal:
     * Add a <code>DOMString</code> to the list.
     *
     * @param domString A string to add to the list
     */
    public void add(String param) {
        fStrings.add(param);
    }

}
