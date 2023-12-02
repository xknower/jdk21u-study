package jdk.internal.joptsimple;

import java.util.List;

/**
 * Specification of an option that accepts a required argument.
 *
 * @param <V> represents the type of the arguments this option accepts
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class RequiredArgumentOptionSpec<V> extends ArgumentAcceptingOptionSpec<V> {
    RequiredArgumentOptionSpec( String option ) {
        super( option, true );
    }

    RequiredArgumentOptionSpec( List<String> options, String description ) {
        super( options, true, description );
    }

    @Override
    protected void detectOptionArgument( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions ) {
        if ( !arguments.hasMore() )
            throw new OptionMissingRequiredArgumentException( this );

        addArguments( detectedOptions, arguments.next() );
    }
}
