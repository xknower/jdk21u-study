package com.sun.security.jgss;

/**
 * Kerberos 5 AuthorizationData entry.
 */
public final class AuthorizationDataEntry {

    private final int type;
    private final byte[] data;

    /**
     * Create an AuthorizationDataEntry object.
     * @param type the ad-type
     * @param data the ad-data, a copy of the data will be saved
     * inside the object.
     */
    public AuthorizationDataEntry(int type, byte[] data) {
        this.type = type;
        this.data = data.clone();
    }

    /**
     * Get the ad-type field.
     * @return ad-type
     */
    public int getType() {
        return type;
    }

    /**
     * Get a copy of the ad-data field.
     * @return ad-data
     */
    public byte[] getData() {
        return data.clone();
    }

    public String toString() {
        return "AuthorizationDataEntry: type="+type+", data=" +
                data.length + " bytes:\n" +
                new sun.security.util.HexDumpEncoder().encodeBuffer(data);
    }
}
