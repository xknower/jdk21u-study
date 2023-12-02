package jdk.javadoc.internal.doclets.formats.html.markup;

import java.io.IOException;
import java.io.Writer;

import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 * Class for generating string content for HTML tags of javadoc output.
 * The content is mutable to the extent that additional content may be added.
 * Newlines are always represented by {@code \n}.
 * Any special HTML characters will be escaped if and when the content is written out.
 */
public class TextBuilder extends Content {

    private final StringBuilder stringBuilder;

    /**
     * Constructor to construct StringContent object.
     */
    public TextBuilder() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Constructor to construct StringContent object with some initial content.
     *
     * @param initialContent initial content for the object
     */
    public TextBuilder(CharSequence initialContent) {
        assert Text.checkNewlines(initialContent);
        stringBuilder = new StringBuilder(initialContent);
    }

    /**
     * Adds content for the StringContent object.
     *
     * @param strContent string content to be added
     */
    @Override
    public TextBuilder add(CharSequence strContent) {
        assert Text.checkNewlines(strContent);
        stringBuilder.append(strContent);
        return this;
    }

    @Override
    public boolean isEmpty() {
        return (stringBuilder.length() == 0);
    }

    @Override
    public int charCount() {
        return stringBuilder.length();
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    @Override
    public boolean write(Writer out, String newline, boolean atNewline) throws IOException {
        String s = Entity.escapeHtmlChars(stringBuilder);
        out.write(s.replace("\n", newline));
        return s.endsWith("\n");
    }
}
