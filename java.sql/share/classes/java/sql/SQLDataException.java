package java.sql;

/**
 * The subclass of {@link SQLException} thrown when the SQLState class value
 * is '<i>22</i>', or under vendor-specified conditions.  This indicates
 * various data errors, including but not limited to data conversion errors,
 * division by 0, and invalid arguments to functions.
 * <p>
 * Please consult your driver vendor documentation for the vendor-specified
 * conditions for which this {@code Exception} may be thrown.
 * @since 1.6
 */
public class SQLDataException extends SQLNonTransientException {

        /**
         * Constructs a {@code SQLDataException} object.
         * The {@code reason}, {@code SQLState} are initialized
         * to {@code null} and the vendor code is initialized to 0.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         * @since 1.6
         */
        public SQLDataException() {
                 super();
        }

        /**
         * Constructs a {@code SQLDataException} object with a given
         * {@code reason}.
         * The {@code SQLState} is initialized
         * to {@code null} and the vendor code is initialized to 0.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         * @param reason a description of the exception
         * @since 1.6
         */
        public SQLDataException(String reason) {
                super(reason);
        }

        /**
         * Constructs a {@code SQLDataException} object with a given
         * {@code reason} and {@code SQLState}. The
         * vendor code is initialized to 0.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         * @param reason a description of the exception
         * @param SQLState an XOPEN or SQL:2003 code identifying the exception
         * @since 1.6
         */
        public SQLDataException(String reason, String SQLState) {
                super(reason, SQLState);
        }

        /**
         * Constructs a {@code SQLDataException} object with a given
         * {@code reason}, {@code SQLState}  and
         * {@code vendorCode}.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         * @param reason a description of the exception
         * @param SQLState an XOPEN or SQL:2003 code identifying the exception
         * @param vendorCode a database vendor specific exception code
         * @since 1.6
         */
        public SQLDataException(String reason, String SQLState, int vendorCode) {
                 super(reason, SQLState, vendorCode);
        }

    /**
     * Constructs a {@code SQLDataException} object with a given
     * {@code cause}.
     * The {@code SQLState} is initialized
     * to {@code null} and the vendor code is initialized to 0.
     * The {@code reason}  is initialized to {@code null} if
     * {@code cause==null} or to {@code cause.toString()} if
     * {@code cause!=null}.
     *
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
     * @since 1.6
         */
    public SQLDataException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code SQLDataException} object with a given
     * {@code reason} and  {@code cause}.
     * The {@code SQLState} is  initialized to {@code null}
     * and the vendor code is initialized to 0.
     *
     * @param reason a description of the exception.
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
     * @since 1.6
     */
    public SQLDataException(String reason, Throwable cause) {
         super(reason, cause);
    }

    /**
     *  Constructs a {@code SQLDataException} object with a given
     * {@code reason}, {@code SQLState} and  {@code cause}.
     * The vendor code is initialized to 0.
     *
     * @param reason a description of the exception.
     * @param SQLState an XOPEN or SQL:2003 code identifying the exception
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
     * @since 1.6
     */
    public SQLDataException(String reason, String SQLState, Throwable cause) {
        super(reason, SQLState, cause);
    }

    /**
     * Constructs a {@code SQLDataException} object with a given
     * {@code reason}, {@code SQLState}, {@code vendorCode}
     * and  {@code cause}.
     *
     * @param reason a description of the exception
     * @param SQLState an XOPEN or SQL:2003 code identifying the exception
     * @param vendorCode a database vendor-specific exception code
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
     * @since 1.6
     */
    public SQLDataException(String reason, String SQLState, int vendorCode, Throwable cause) {
          super(reason, SQLState, vendorCode, cause);
    }

   private static final long serialVersionUID = -6889123282670549800L;
}
