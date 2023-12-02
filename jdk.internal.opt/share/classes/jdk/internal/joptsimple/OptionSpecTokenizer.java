package jdk.internal.joptsimple;

import java.util.NoSuchElementException;

import static jdk.internal.joptsimple.ParserRules.*;

/**
 * Tokenizes a short option specification string.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class OptionSpecTokenizer {
    private static final char POSIXLY_CORRECT_MARKER = '+';
    private static final char HELP_MARKER = '*';

    private String specification;
    private int index;

    OptionSpecTokenizer( String specification ) {
        if ( specification == null )
            throw new NullPointerException( "null option specification" );

        this.specification = specification;
    }

    boolean hasMore() {
        return index < specification.length();
    }

    AbstractOptionSpec<?> next() {
        if ( !hasMore() )
            throw new NoSuchElementException();


        String optionCandidate = String.valueOf( specification.charAt( index ) );
        index++;

        AbstractOptionSpec<?> spec;
        if ( RESERVED_FOR_EXTENSIONS.equals( optionCandidate ) ) {
            spec = handleReservedForExtensionsToken();

            if ( spec != null )
                return spec;
        }

        ensureLegalOption( optionCandidate );

        if ( hasMore() ) {
            boolean forHelp = false;
            if ( specification.charAt( index ) == HELP_MARKER ) {
                forHelp = true;
                ++index;
            }
            spec = hasMore() && specification.charAt( index ) == ':'
                ? handleArgumentAcceptingOption( optionCandidate )
                : new NoArgumentOptionSpec( optionCandidate );
            if ( forHelp )
                spec.forHelp();
        } else
            spec = new NoArgumentOptionSpec( optionCandidate );

        return spec;
    }

    void configure( OptionParser parser ) {
        adjustForPosixlyCorrect( parser );

        while ( hasMore() )
            parser.recognize( next() );
    }

    private void adjustForPosixlyCorrect( OptionParser parser ) {
        if ( POSIXLY_CORRECT_MARKER == specification.charAt( 0 ) ) {
            parser.posixlyCorrect( true );
            specification = specification.substring( 1 );
        }
    }

    private AbstractOptionSpec<?> handleReservedForExtensionsToken() {
        if ( !hasMore() )
            return new NoArgumentOptionSpec( RESERVED_FOR_EXTENSIONS );

        if ( specification.charAt( index ) == ';' ) {
            ++index;
            return new AlternativeLongOptionSpec();
        }

        return null;
    }

    private AbstractOptionSpec<?> handleArgumentAcceptingOption( String candidate ) {
        index++;

        if ( hasMore() && specification.charAt( index ) == ':' ) {
            index++;
            return new OptionalArgumentOptionSpec<String>( candidate );
        }

        return new RequiredArgumentOptionSpec<String>( candidate );
    }
}
