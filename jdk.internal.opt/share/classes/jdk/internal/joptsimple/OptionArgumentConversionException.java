package jdk.internal.joptsimple;

import static java.util.Collections.*;

/**
 * Thrown when a problem occurs converting an argument of an option from {@link String} to another type.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class OptionArgumentConversionException extends OptionException {
    private static final long serialVersionUID = -1L;

    private final String argument;

    OptionArgumentConversionException( OptionSpec<?> options, String argument, Throwable cause ) {
        super( singleton( options ), cause );

        this.argument = argument;
    }

    @Override
    Object[] messageArguments() {
        return new Object[] { argument, singleOptionString() };
    }
}
