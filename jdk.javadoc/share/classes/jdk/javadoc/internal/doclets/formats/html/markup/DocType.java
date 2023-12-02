package jdk.javadoc.internal.doclets.formats.html.markup;

/**
 * Supported DOCTYPE declarations.
 */
public enum DocType {
    HTML5("<!DOCTYPE HTML>");

    public final String text;

    DocType(String text) {
        this.text = text;
    }
}
