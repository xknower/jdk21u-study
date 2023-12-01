package sun.security.ssl;

import java.security.PublicKey;

interface NamedGroupCredentials extends SSLCredentials {

    PublicKey getPublicKey();

    NamedGroup getNamedGroup();

}
