package com.sun.org.apache.xml.internal.security.keys.storage;

import java.security.cert.Certificate;
import java.util.Iterator;

public abstract class StorageResolverSpi {

    /**
     * Method getIterator
     *
     * @return the iterator for the storage
     */
    public abstract Iterator<Certificate> getIterator();
}
