package sun.security.pkcs11.wrapper;



/**
 * class CK_X9_42_DH1_DERIVE_PARAMS provides the parameters to the
 * CKM_X9_42_DH_DERIVE mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_X9_42_DH1_DERIVE_PARAMS {
 *   CK_X9_42_DH_KDF_TYPE kdf;
 *   CK_ULONG ulOtherInfoLen;
 *   CK_BYTE_PTR pOtherInfo;
 *   CK_ULONG ulPublicDataLen;
 *   CK_BYTE_PTR pPublicData;
 * } CK_X9_42_DH1_DERIVE_PARAMS;
 * </PRE>
 *
 * @author Karl Scheibelhofer <Karl.Scheibelhofer@iaik.at>
 */
public class CK_X9_42_DH1_DERIVE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_X9_42_DH_KDF_TYPE kdf;
     * </PRE>
     */
    public long kdf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulOtherInfoLen;
     *   CK_BYTE_PTR pOtherInfo;
     * </PRE>
     */
    public byte[] pOtherInfo;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDataLen;
     *   CK_BYTE_PTR pPublicData;
     * </PRE>
     */
    public byte[] pPublicData;

    /**
     * Returns the string representation of CK_X9_42_DH1_DERIVE_PARAMS.
     *
     * @return the string representation of CK_X9_42_DH1_DERIVE_PARAMS
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Constants.INDENT);
        sb.append("kdf: 0x");
        sb.append(Functions.toFullHexString(kdf));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("pOtherInfoLen: ");
        sb.append(pOtherInfo.length);
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("pOtherInfo: ");
        sb.append(Functions.toHexString(pOtherInfo));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("pPublicDataLen: ");
        sb.append(pPublicData.length);
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("pPublicData: ");
        sb.append(Functions.toHexString(pPublicData));
        //buffer.append(Constants.NEWLINE);

        return sb.toString();
    }

}
