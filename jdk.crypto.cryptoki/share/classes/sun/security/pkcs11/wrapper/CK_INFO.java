package sun.security.pkcs11.wrapper;



/**
 * class  CK_INFO provides general information about Cryptoki.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_INFO {&nbsp;&nbsp;
 *    CK_VERSION cryptokiVersion;&nbsp;&nbsp;
 *    CK_UTF8CHAR manufacturerID[32];&nbsp;&nbsp;
 *    CK_FLAGS flags;&nbsp;&nbsp;
 *    CK_UTF8CHAR libraryDescription[32];&nbsp;&nbsp;
 *    CK_VERSION libraryVersion;&nbsp;&nbsp;
 *  } CK_INFO;
 * </PRE>
 *
 * @author Karl Scheibelhofer <Karl.Scheibelhofer@iaik.at>
 * @author Martin Schlaeffer <schlaeff@sbox.tugraz.at>
 */
public class CK_INFO {

    /**
     * Cryptoki interface version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION cryptokiVersion;
     * </PRE>
     */
    public CK_VERSION cryptokiVersion;

    /**
     * ID of the Cryptoki library manufacturer. must be blank
     * padded - only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR manufacturerID[32];
     * </PRE>
     */
    public char[] manufacturerID;

    /**
     * bit flags reserved for future versions. must be zero<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;


/* libraryDescription and libraryVersion are new for v2.0 */

    /**
     * must be blank padded - only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR libraryDescription[32];
     * </PRE>
     */
    public char[] libraryDescription;

    /**
     * Cryptoki library version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION libraryVersion;
     * </PRE>
     */
    public CK_VERSION libraryVersion;

    public CK_INFO(CK_VERSION cryptoVer, char[] vendor, long flags,
                   char[] libDesc, CK_VERSION libVer) {
        this.cryptokiVersion = cryptoVer;
        this.manufacturerID = vendor;
        this.flags = flags;
        this.libraryDescription = libDesc;
        this.libraryVersion = libVer;
    }

    /**
     * Returns the string representation of CK_INFO.
     *
     * @return the string representation of CK_INFO
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Constants.INDENT);
        sb.append("cryptokiVersion: ");
        sb.append(cryptokiVersion.toString());
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("manufacturerID: ");
        sb.append(new String(manufacturerID));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("flags: ");
        sb.append(Functions.toBinaryString(flags));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("libraryDescription: ");
        sb.append(new String(libraryDescription));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("libraryVersion: ");
        sb.append(libraryVersion.toString());
        //buffer.append(Constants.NEWLINE);

        return sb.toString() ;
    }

}
