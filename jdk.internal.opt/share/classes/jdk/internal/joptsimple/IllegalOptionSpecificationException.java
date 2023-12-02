package jdk.internal.joptsimple;

import static java.util.Collections.*;

/**
 * Thrown when the option parser is asked to recognize an option with illegal characters in it.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class IllegalOptionSpecificationException extends OptionException {
    private static final long serialVersionUID = -1L;

    IllegalOptionSpecificationException( String option ) {
        super( singletonList( option ) );
    }

    @Override
    Object[] messageArguments() {
        return new Object[] { singleOptionString() };
    }
}
