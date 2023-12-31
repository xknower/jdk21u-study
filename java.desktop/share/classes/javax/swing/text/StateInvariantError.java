package javax.swing.text;

/**
 * This exception is to report the failure of state invariant
 * assertion that was made.  This indicates an internal error
 * has occurred.
 *
 * @author  Timothy Prinzing
 */
@SuppressWarnings("serial") // Same-version serialization only
class StateInvariantError extends Error
{
    /**
     * Creates a new StateInvariantFailure object.
     *
     * @param s         a string indicating the assertion that failed
     */
    public StateInvariantError(String s) {
        super(s);
    }

}
