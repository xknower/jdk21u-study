package javax.xml.transform;

/**
 * <p>An object that implements this interface contains the information
 * needed to build a transformation result tree.
 *
 * @author Jeff Suttor
 * @since 1.4
 */
public interface Result {

    /**
     * The name of the processing instruction that is sent if the
     * result tree disables output escaping.
     *
     * <p>Normally, result tree serialization escapes{@literal & and <} (and
     * possibly other characters) when outputting text nodes.
     * This ensures that the output is well-formed XML. However,
     * it is sometimes convenient to be able to produce output that is
     * almost, but not quite well-formed XML; for example,
     * the output may include ill-formed sections that will
     * be transformed into well-formed XML by a subsequent non-XML aware
     * process. If a processing instruction is sent with this name,
     * serialization should be output without any escaping.
     *
     * <p>Result DOM trees may also have PI_DISABLE_OUTPUT_ESCAPING and
     * PI_ENABLE_OUTPUT_ESCAPING inserted into the tree.
     *
     * @see <a href="http://www.w3.org/TR/xslt#disable-output-escaping">disable-output-escaping in XSLT Specification</a>
     */
    public static final String PI_DISABLE_OUTPUT_ESCAPING =
        "javax.xml.transform.disable-output-escaping";

    /**
     * The name of the processing instruction that is sent
     * if the result tree enables output escaping at some point after having
     * received a PI_DISABLE_OUTPUT_ESCAPING processing instruction.
     *
     * @see <a href="http://www.w3.org/TR/xslt#disable-output-escaping">disable-output-escaping in XSLT Specification</a>
     */
    public static final String PI_ENABLE_OUTPUT_ESCAPING =
        "javax.xml.transform.enable-output-escaping";

    /**
     * Set the system identifier for this Result.
     *
     * <p>If the Result is not to be written to a file, the system identifier is optional.
     * The application may still want to provide one, however, for use in error messages
     * and warnings, or to resolve relative output identifiers.
     *
     * @param systemId The system identifier as a URI string.
     */
    public void setSystemId(String systemId);

    /**
     * Get the system identifier that was set with setSystemId.
     *
     * @return The system identifier that was set with setSystemId,
     * or null if setSystemId was not called.
     */
    public String getSystemId();
}
