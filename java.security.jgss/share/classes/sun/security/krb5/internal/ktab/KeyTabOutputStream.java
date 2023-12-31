package sun.security.krb5.internal.ktab;

import sun.security.krb5.internal.*;
import sun.security.krb5.internal.util.KrbDataOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * This class implements a buffered input stream. It is used for parsing key table
 * data to memory.
 *
 * @author Yanni Zhang
 *
 */
public class KeyTabOutputStream extends KrbDataOutputStream implements KeyTabConstants {
    private KeyTabEntry entry;
    private int keyType;
    private byte[] keyValue;
    public int version;

    public KeyTabOutputStream(OutputStream os) {
        super(os);
    }

    public void writeVersion(int num) throws IOException {
        version = num;
        write16(num);           //we use the standard version.
    }

    public void writeEntry(KeyTabEntry entry) throws IOException {
        write32(entry.entryLength());
        String[] serviceNames =  entry.service.getNameStrings();
        int comp_num = serviceNames.length;
        if (version == KRB5_KT_VNO_1) {
            write16(comp_num + 1);
        }
        else write16(comp_num);

        byte[] realm = entry.service.getRealmString().getBytes(ISO_8859_1);
        write16(realm.length);
        write(realm);

        for (int i = 0; i < comp_num; i++) {
            byte[] serviceName = serviceNames[i].getBytes(ISO_8859_1);
            write16(serviceName.length);
            write(serviceName);
        }

        write32(entry.service.getNameType());
        //time is long, but we only use 4 bytes to store the data.
        write32((int)(entry.timestamp.getTime()/1000));

        // the key version might be a 32 bit extended number.
        write8(entry.keyVersion % 256 );
        write16(entry.keyType);
        write16(entry.keyblock.length);
        write(entry.keyblock);

        // if the key version isn't smaller than 256, it could be saved as
        // extension key version number in 4 bytes. The nonzero extension
        // key version number will be trusted. However, it isn't standardized
        // yet, we won't support it.
        // if (entry.keyVersion >= 256) {
        //    write32(entry.keyVersion);
        //}
    }
}
