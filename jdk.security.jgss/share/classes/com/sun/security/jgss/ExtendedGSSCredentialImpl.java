package com.sun.security.jgss;

import sun.security.jgss.GSSCredentialImpl;

class ExtendedGSSCredentialImpl extends GSSCredentialImpl
        implements ExtendedGSSCredential {

    public ExtendedGSSCredentialImpl(GSSCredentialImpl old) {
        super(old);
    }
}
