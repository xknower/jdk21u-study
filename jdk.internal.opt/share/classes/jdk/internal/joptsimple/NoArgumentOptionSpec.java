package jdk.internal.joptsimple;

import java.util.List;

import static java.util.Collections.*;

/**
 * A specification for an option that does not accept arguments.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
class NoArgumentOptionSpec extends AbstractOptionSpec<Void> {
    NoArgumentOptionSpec( String option ) {
        this( singletonList( option ), "" );
    }

    NoArgumentOptionSpec( List<String> options, String description ) {
        super( options, description );
    }

    @Override
    void handleOption( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions,
        String detectedArgument ) {

        detectedOptions.add( this );
    }

    public boolean acceptsArguments() {
        return false;
    }

    public boolean requiresArgument() {
        return false;
    }

    public boolean isRequired() {
        return false;
    }

    public String argumentDescription() {
        return "";
    }

    public String argumentTypeIndicator() {
        return "";
    }

    @Override
    protected Void convert( String argument ) {
        return null;
    }

    public List<Void> defaultValues() {
        return emptyList();
    }
}
