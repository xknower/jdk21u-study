package com.sun.jmx.mbeanserver;

import java.security.PrivilegedAction;

/**
 * Utility class to be used by the method {@code AccessControler.doPrivileged}
 * to get a system property.
 *
 * @since 1.5
 */
public class GetPropertyAction implements PrivilegedAction<String> {
    private final String key;

    public GetPropertyAction(String key) {
        this.key = key;
    }

    public String run() {
        return System.getProperty(key);
    }
}
