package javax.xml.transform;

/**
 * The listener interface used by a {@link TransformerFactory} or {@link Transformer}
 * to notify callers of error messages that occur during a transformation process.
 * An ErrorListener receives three levels of messages: warnings, errors and fatal
 * errors as classified by their severity. Each of them is handled as described
 * in their respective method.
 *
 * <p>
 * An ErrorListener instance can be registered to a {@link TransformerFactory}
 * or {@link Transformer} through
 * the {@link TransformerFactory#setErrorListener(ErrorListener)}
 * or {@link Transformer#setErrorListener(ErrorListener)}
 * method to receive errors and warnings reported by the TransformerFactory
 * or Transformer.
 *
 * <p>
 * When a listener is registered, the {@link TransformerFactory} or {@link Transformer}
 * must use this interface to pass on all warnings and errors to the listener
 * and let the application decide how to handle them.
 * Note that the {@code TransformerFactory} or {@code Transformer} is not
 * required to continue with the transformation after a call to
 * {@link #fatalError(TransformerException exception)}.
 *
 * <p>
 * If an application does not provide a listener, the {@link TransformerFactory}
 * or {@link Transformer} shall create one on its own. The default {@code ErrorListener}
 * may take no action for warnings and recoverable errors, and allow the
 * transformation to continue.
 * However, the {@code TransformerFactory} or {@code Transformer} may still throw
 * {@code TransformerException} when it decides it can not continue processing.
 *
 * @apiNote It is recommended that applications register and use their own
 * {@code ErrorListener} to override the default behavior in order to ensure
 * proper handling of warnings and errors.
 *
 * @since 1.4
 */
public interface ErrorListener {

    /**
     * Receive notification of a warning.
     *
     * <p>{@link javax.xml.transform.Transformer} can use this method to report
     * conditions that are not errors or fatal errors.  The default behaviour
     * is to take no action.</p>
     *
     * <p>After invoking this method, the Transformer must continue with
     * the transformation. It should still be possible for the
     * application to process the document through to the end.</p>
     *
     * @param exception The warning information encapsulated in a
     *                  transformer exception.
     *
     * @throws javax.xml.transform.TransformerException if the application
     * chooses to discontinue the transformation.
     *
     * @see javax.xml.transform.TransformerException
     */
    public abstract void warning(TransformerException exception)
        throws TransformerException;

    /**
     * Receive notification of a recoverable error.
     *
     * <p>The transformer must continue to try and provide normal transformation
     * after invoking this method.  It should still be possible for the
     * application to process the document through to the end if no other errors
     * are encountered.</p>
     *
     * @param exception The error information encapsulated in a
     *                  transformer exception.
     *
     * @throws javax.xml.transform.TransformerException if the application
     * chooses to discontinue the transformation.
     *
     * @see javax.xml.transform.TransformerException
     */
    public abstract void error(TransformerException exception)
        throws TransformerException;

    /**
     * <p>Receive notification of a non-recoverable error.</p>
     *
     * <p>The processor may choose to continue, but will not normally
     * proceed to a successful completion.</p>
     *
     * <p>The method should throw an exception if it is unable to
     * process the error, or if it wishes execution to terminate
     * immediately. The processor will not necessarily honor this
     * request.</p>
     *
     * @param exception The error information encapsulated in a
     *    <code>TransformerException</code>.
     *
     * @throws javax.xml.transform.TransformerException if the application
     * chooses to discontinue the transformation.
     *
     * @see javax.xml.transform.TransformerException
     */
    public abstract void fatalError(TransformerException exception)
        throws TransformerException;
}
