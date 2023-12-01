package sun.java2d.cmm;

import java.awt.color.ICC_Profile;

/* Pluggable CMM interface */

public interface PCMM {

    /* methods invoked from ICC_Profile */
    Profile loadProfile(byte[] data);
    byte[] getProfileData(Profile p);
    byte[] getTagData(Profile p, int tagSignature);
    void setTagData(Profile p, int tagSignature, byte[] data);

    /* Creates ColorTransform */
    ColorTransform createTransform(int renderingIntent,
                                   ICC_Profile... profiles);
}
