package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @param <K>
 * @param <V>
 */
public final class MultiHashtable<K,V> {
    static final long serialVersionUID = -6151608290510033572L;

    private final Map<K, Set<V>> map = new HashMap<>();
    private boolean modifiable = true;

    /**
     * Associates the specified key with a set of values. If the map previously
     * contained a mapping for the key, the value is added to the set.
     * @param key key with which the specified value is to be associated
     * @param value value to be added to a set that is associated with the specified key
     * @return the set that is associated with the specified key.
     * @throw UnsupportedOperationException is the MultiHashtable is not modifiable.
     */
    public Set<V> put(K key, V value) {
        if (modifiable) {
            Set<V> set = map.computeIfAbsent(key, k -> new HashSet<>());
            set.add(value);
            return set;
        }
        throw new UnsupportedOperationException("The MultiHashtable instance is not modifiable.");
    }

    /**
     * Maps a key to a value in a set that is associated with the specified key.
     * The mapping is performed by evaluating whether an item in the set equals
     * the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param value value in a set that is associated with the specified key
     * @return the item in the set if a match is found.
     */
    public V maps(K key, V value) {
        if (key == null) return null;
        final Set<V> set = map.get(key);
        if (set != null) {
            for (V v : set) {
                if (v.equals(value)) {
                    return v;
                }
            }
        }
        return null;
    }

    /**
     * Makes the MultiHashtable unmodifiable.  This method allows modules to set the table
     * as "read-only" so that only query operation, that is maps, is allowed. Any attempts
     * to modify the returned map result in an UnsupportedOperationException.
     */
    public void makeUnmodifiable() {
        modifiable = false;
    }
}
