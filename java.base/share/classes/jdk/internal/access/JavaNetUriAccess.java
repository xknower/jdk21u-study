package jdk.internal.access;

import java.net.URI;

public interface JavaNetUriAccess {
    /**
     * Create a URI of pre-validated scheme and path.
     */
    URI create(String scheme, String path);
}
