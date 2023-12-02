package jdk.internal.joptsimple;

import jdk.internal.joptsimple.internal.Messages;

import java.util.Locale;

import static java.util.Collections.*;

import static jdk.internal.joptsimple.ParserRules.*;

/**
 * Represents the {@code "-W"} form of long option specification.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class AlternativeLongOptionSpec extends ArgumentAcceptingOptionSpec<String> {
    AlternativeLongOptionSpec() {
        super( singletonList( RESERVED_FOR_EXTENSIONS ),
            true,
            Messages.message(
                Locale.getDefault(),
                "jdk.internal.joptsimple.HelpFormatterMessages",
                AlternativeLongOptionSpec.class,
                "description" ) );

        describedAs( Messages.message(
            Locale.getDefault(),
            "jdk.internal.joptsimple.HelpFormatterMessages",
            AlternativeLongOptionSpec.class,
            "arg.description" ) );
    }

    @Override
    protected void detectOptionArgument( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions ) {
        if ( !arguments.hasMore() )
            throw new OptionMissingRequiredArgumentException( this );

        arguments.treatNextAsLongOption();
    }
}
