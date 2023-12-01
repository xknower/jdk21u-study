package sun.security.pkcs11.wrapper;



/**
 * class CK_SESSION_INFO provides information about a session.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SESSION_INFO {&nbsp;&nbsp;
 *   CK_SLOT_ID slotID;&nbsp;&nbsp;
 *   CK_STATE state;&nbsp;&nbsp;
 *   CK_FLAGS flags;&nbsp;&nbsp;
 *   CK_ULONG ulDeviceError;&nbsp;&nbsp;
 * } CK_SESSION_INFO;
 * </PRE>
 *
 * @author Karl Scheibelhofer <Karl.Scheibelhofer@iaik.at>
 * @author Martin Schlaeffer <schlaeff@sbox.tugraz.at>
 */
public class CK_SESSION_INFO {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SLOT_ID slotID;
     * </PRE>
     */
    public long slotID;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_STATE state;
     * </PRE>
     */
    public long state;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;          /* see below */

    /* ulDeviceError was changed from CK_USHORT to CK_ULONG for
     * v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulDeviceError;
     * </PRE>
     */
    public long ulDeviceError;  /* device-dependent error code */

    public CK_SESSION_INFO(long slotID, long state,
                           long flags, long ulDeviceError) {
        this.slotID = slotID;
        this.state = state;
        this.flags = flags;
        this.ulDeviceError = ulDeviceError;
    }

    /**
     * Returns the string representation of CK_SESSION_INFO.
     *
     * @return the string representation of CK_SESSION_INFO
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Constants.INDENT);
        sb.append("slotID: ");
        sb.append(String.valueOf(slotID));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("state: ");
        sb.append(Functions.sessionStateToString(state));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("flags: ");
        sb.append(Functions.sessionInfoFlagsToString(flags));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("ulDeviceError: ");
        sb.append(Functions.toHexString(ulDeviceError));
        //buffer.append(Constants.NEWLINE);

        return sb.toString() ;
    }

}
