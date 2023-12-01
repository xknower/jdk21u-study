package com.sun.org.apache.xerces.internal.dom;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMStringList;

/**
 * DOM Level 3
 *
 * This class implements the DOM Level 3 Core interface DOMStringList.
 *
 * @xerces.internal
 *
 * @author Neil Delima, IBM
 * @LastModified: Nov 2017
 */
public class DOMStringListImpl implements DOMStringList {

    // A collection of DOMString values
    private final List<String> fStrings;

    /**
     * Construct an empty list of DOMStringListImpl
     */
    public DOMStringListImpl() {
        fStrings = new ArrayList<>();
    }

    /**
     * Construct a DOMStringListImpl from an ArrayList
     */
    public DOMStringListImpl(List<String> params) {
        fStrings = params;
    }

    /**
     * @see org.w3c.dom.DOMStringList#item(int)
     */
    public String item(int index) {
        final int length = getLength();
        if (index >= 0 && index < length) {
            return fStrings.get(index);
        }
        return null;
    }

    /**
     * @see org.w3c.dom.DOMStringList#getLength()
     */
    public int getLength() {
            return fStrings.size();
    }

    /**
     * @see org.w3c.dom.DOMStringList#contains(String)
     */
    public boolean contains(String param) {
        return fStrings.contains(param);
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
