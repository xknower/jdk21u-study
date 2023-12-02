package sun.tools.jstat;

/**
 * A class for describing exceptions generated by the Parser class.
 *
 * @author Brian Doherty
 * @since 1.5
 */
@SuppressWarnings("serial") // JDK implementation class
public class ParserException extends Exception {

    public ParserException() {
        super();
    }

    public ParserException(String msg) {
        super(msg);
    }
}
