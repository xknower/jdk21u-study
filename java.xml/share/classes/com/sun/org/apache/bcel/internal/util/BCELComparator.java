package com.sun.org.apache.bcel.internal.util;

/**
 * Used for BCEL comparison strategy
 *
 * @since 5.2
 */
public interface BCELComparator {

    /**
     * Compare two objects and return what THIS.equals(THAT) should return
     *
     * @param THIS
     * @param THAT
     * @return true if and only if THIS equals THAT
     */
    boolean equals(Object THIS, Object THAT);

    /**
     * Return hashcode for THIS.hashCode()
     *
     * @param THIS
     * @return hashcode for THIS.hashCode()
     */
    int hashCode(Object THIS);
}
