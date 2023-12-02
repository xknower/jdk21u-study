package jdk.internal.org.jline.reader;

/**
 * An extension of {@link ParsedLine} that, being aware of the quoting and escaping rules
 * of the {@link jdk.internal.org.jline.reader.Parser} that produced it, knows if and how a completion candidate
 * should be escaped/quoted.
 *
 * @author Eric Bottard
 */
public interface CompletingParsedLine extends ParsedLine {

    CharSequence escape(CharSequence candidate, boolean complete);

    int rawWordCursor();

    int rawWordLength();

}
