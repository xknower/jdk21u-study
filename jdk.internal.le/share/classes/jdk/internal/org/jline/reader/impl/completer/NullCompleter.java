package jdk.internal.org.jline.reader.impl.completer;

import java.util.List;

import jdk.internal.org.jline.reader.Candidate;
import jdk.internal.org.jline.reader.Completer;
import jdk.internal.org.jline.reader.LineReader;
import jdk.internal.org.jline.reader.ParsedLine;

/**
 * Null completer.
 *
 * @author <a href="mailto:mwp1@cornell.edu">Marc Prud'hommeaux</a>
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 2.3
 */
public final class NullCompleter
    implements Completer
{
    public static final NullCompleter INSTANCE = new NullCompleter();

    public void complete(LineReader reader, final ParsedLine line, final List<Candidate> candidates) {
    }
}
