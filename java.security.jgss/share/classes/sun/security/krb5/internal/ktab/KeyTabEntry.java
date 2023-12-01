package sun.security.krb5.internal.ktab;

import sun.security.krb5.*;
import sun.security.krb5.internal.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * This class represents a Key Table entry. Each entry contains the service principal of
 * the key, time stamp, key version and secret key itself.
 *
 * @author Yanni Zhang
 */
public class KeyTabEntry implements KeyTabConstants {
    PrincipalName service;
    Realm realm;
    KerberosTime timestamp;
    int keyVersion;
    int keyType;
    byte[] keyblock = null;
    boolean DEBUG = Krb5.DEBUG;

    public KeyTabEntry (PrincipalName new_service, Realm new_realm, KerberosTime new_time,
                        int new_keyVersion, int new_keyType, byte[] new_keyblock) {
        service = new_service;
        realm = new_realm;
        timestamp = new_time;
        keyVersion = new_keyVersion;
        keyType = new_keyType;
        if (new_keyblock != null) {
            keyblock = new_keyblock.clone();
        }
    }

    public PrincipalName getService() {
        return service;
    }

    public EncryptionKey getKey() {
        EncryptionKey key = new EncryptionKey(keyblock,
                                              keyType,
                                              keyVersion);
        return key;
    }

    public String getKeyString() {
        StringBuilder sb = new StringBuilder("0x");
        for (int i = 0; i < keyblock.length; i++) {
            sb.append(String.format("%02x", keyblock[i]&0xff));
        }
        return sb.toString();
    }
    public int entryLength() {
        int totalPrincipalLength = 0;
        String[] names = service.getNameStrings();
        for (int i = 0; i < names.length; i++) {
            totalPrincipalLength += principalSize + names[i].getBytes(ISO_8859_1).length;
        }

        int realmLen = realm.toString().getBytes(ISO_8859_1).length;

        int size = principalComponentSize +  realmSize + realmLen
            + totalPrincipalLength + principalTypeSize
            + timestampSize + keyVersionSize
            + keyTypeSize + keySize + keyblock.length;

        if (DEBUG) {
            System.out.println(">>> KeyTabEntry: key tab entry size is " + size);
        }
        return size;
    }

    public KerberosTime getTimeStamp() {
        return timestamp;
    }
}
