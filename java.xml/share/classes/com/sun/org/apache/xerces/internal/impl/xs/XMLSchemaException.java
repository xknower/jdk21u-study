package com.sun.org.apache.xerces.internal.impl.xs;

/**
 * This exception might be thrown by any constraint checking method.
 *
 * @xerces.internal
 *
 * @author Elena Litani, IBM
 *
 */
public class XMLSchemaException extends Exception {

    /** Serialization version. */
    static final long serialVersionUID = -9096984648537046218L;

    // store a datatype error: error code plus the arguments
    String key;
    @SuppressWarnings("serial") // Array component type is not Serializable
    Object[] args;

    // report an error
    public XMLSchemaException(String key, Object[] args) {
        this.key = key;
        this.args = args;
    }

    public String getKey() {
        return key;
    }

    public Object[] getArgs() {
        return args;
    }

}
