package jdk.jfr.internal.jfc.model;
/**
 * Signals that a JFCModel is invalid.
 */
public final class JFCModelException extends Exception {
    private static final long serialVersionUID = -613252344752758699L;

    public JFCModelException(String errorMessage) {
        super(errorMessage);
    }
}
