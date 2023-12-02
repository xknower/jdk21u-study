package jdk.internal.joptsimple;

import static java.util.Collections.*;

/**
 * Thrown when the option parser encounters an unrecognized option.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class UnrecognizedOptionException extends OptionException {
    private static final long serialVersionUID = -1L;

    UnrecognizedOptionException( String option ) {
        super( singletonList( option ) );
    }

    @Override
    Object[] messageArguments() {
        return new Object[] { singleOptionString() };
    }
}
