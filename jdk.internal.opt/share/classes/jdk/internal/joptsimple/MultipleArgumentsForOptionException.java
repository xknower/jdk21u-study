package jdk.internal.joptsimple;

import static java.util.Collections.*;

/**
 * Thrown when asking an {@link OptionSet} for a single argument of an option when many have been specified.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class MultipleArgumentsForOptionException extends OptionException {
    private static final long serialVersionUID = -1L;

    MultipleArgumentsForOptionException( OptionSpec<?> options ) {
        super( singleton( options ) );
    }

    @Override
    Object[] messageArguments() {
        return new Object[] { singleOptionString() };
    }
}
