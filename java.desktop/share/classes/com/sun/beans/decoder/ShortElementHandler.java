package com.sun.beans.decoder;

/**
 * This class is intended to handle &lt;short&gt; element.
 * This element specifies {@code short} values.
 * The class {@link Short} is used as wrapper for these values.
 * The result value is created from text of the body of this element.
 * The body parsing is described in the class {@link StringElementHandler}.
 * For example:<pre>
 * &lt;short&gt;200&lt;/short&gt;</pre>
 * is shortcut to<pre>
 * &lt;method name="decode" class="java.lang.Short"&gt;
 *     &lt;string&gt;200&lt;/string&gt;
 * &lt;/method&gt;</pre>
 * which is equivalent to {@code Short.decode("200")} in Java code.
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
final class ShortElementHandler extends StringElementHandler {

    /**
     * Creates {@code short} value from
     * the text of the body of this element.
     *
     * @param argument  the text of the body
     * @return evaluated {@code short} value
     */
    @Override
    public Object getValue(String argument) {
        return Short.decode(argument);
    }
}
