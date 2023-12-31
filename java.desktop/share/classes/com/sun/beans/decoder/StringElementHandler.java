package com.sun.beans.decoder;

/**
 * This class is intended to handle &lt;string&gt; element.
 * This element specifies {@link String} values.
 * The result value is created from text of the body of this element.
 * For example:<pre>
 * &lt;string&gt;description&lt;/string&gt;</pre>
 * is equivalent to {@code "description"} in Java code.
 * The value of inner element is calculated
 * before adding to the string using {@link String#valueOf(Object)}.
 * Note that all characters are used including whitespaces (' ', '\t', '\n', '\r').
 * So the value of the element<pre>
 * &lt;string&gt&lt;true&gt&lt;/string&gt;</pre>
 * is not equal to the value of the element<pre>
 * &lt;string&gt;
 *     &lt;true&gt;
 * &lt;/string&gt;</pre>
 * <p>The following attribute is supported:
 * <dl>
 * <dt>id
 * <dd>the identifier of the variable that is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @author Sergey A. Malenkov
 */
public class StringElementHandler extends ElementHandler {
    private StringBuilder sb = new StringBuilder();
    private ValueObject value = ValueObjectImpl.NULL;

    /**
     * Adds the character that contained in this element.
     *
     * @param ch  the character
     */
    @Override
    public final void addCharacter(char ch) {
        if (this.sb == null) {
            throw new IllegalStateException("Could not add character to evaluated string element");
        }
        this.sb.append(ch);
    }

    /**
     * Adds the string value of the argument to the string value of this element.
     *
     * @param argument  the value of the element that contained in this one
     */
    @Override
    protected final void addArgument(Object argument) {
        if (this.sb == null) {
            throw new IllegalStateException("Could not add argument to evaluated string element");
        }
        this.sb.append(argument);
    }

    /**
     * Returns the value of this element.
     *
     * @return the value of this element
     */
    @Override
    protected final ValueObject getValueObject() {
        if (this.sb != null) {
            try {
                this.value = ValueObjectImpl.create(getValue(this.sb.toString()));
            }
            catch (RuntimeException exception) {
                getOwner().handleException(exception);
            }
            finally {
                this.sb = null;
            }
        }
        return this.value;
    }

    /**
     * Returns the text of the body of this element.
     * This method evaluates value from text of the body,
     * and should be overridden in those handlers
     * that extend behavior of this element.
     *
     * @param argument  the text of the body
     * @return evaluated value
     */
    protected Object getValue(String argument) {
        return argument;
    }
}
