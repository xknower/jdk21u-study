package sun.security.pkcs11.wrapper;



/**
 * class .<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_DATE {&nbsp;&nbsp;
 *   CK_CHAR year[4];&nbsp;&nbsp;
 *   CK_CHAR month[2];&nbsp;&nbsp;
 *   CK_CHAR day[2];&nbsp;&nbsp;
 * } CK_DATE;
 * </PRE>
 *
 * @author Karl Scheibelhofer <Karl.Scheibelhofer@iaik.at>
 * @author Martin Schlaeffer <schlaeff@sbox.tugraz.at>
 */
public class CK_DATE implements Cloneable {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR year[4];   - the year ("1900" - "9999")
     * </PRE>
     */
    public char[] year;    /* the year ("1900" - "9999") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR month[2];  - the month ("01" - "12")
     * </PRE>
     */
    public char[] month;   /* the month ("01" - "12") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR day[2];    - the day ("01" - "31")
     * </PRE>
     */
    public char[] day;     /* the day ("01" - "31") */

    public CK_DATE(char[] year, char[] month, char[] day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Create a (deep) clone of this object.
     *
     * @return A clone of this object.
     */
    public Object clone() {
        CK_DATE copy = null;
        try {
            copy = (CK_DATE) super.clone();
        } catch (CloneNotSupportedException cnse) {
            // re-throw as RuntimeException
            throw (RuntimeException)
                (new RuntimeException("Clone error").initCause(cnse));
        }
        copy.year = this.year.clone();
        copy.month = this.month.clone();
        copy.day =  this.day.clone();

        return copy;
    }

    /**
     * Returns the string representation of CK_DATE.
     *
     * @return the string representation of CK_DATE
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(new String(day));
        sb.append('.');
        sb.append(new String(month));
        sb.append('.');
        sb.append(new String(year));
        sb.append(" (DD.MM.YYYY)");

        return sb.toString();
    }

}
