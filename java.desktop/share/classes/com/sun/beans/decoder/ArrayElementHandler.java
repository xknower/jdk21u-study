package com.sun.beans.decoder;

import java.lang.reflect.Array;

/**
 * This class is intended to handle &lt;array&gt; element,
 * that is used to array creation.
 * The {@code length} attribute specifies the length of the array.
 * The {@code class} attribute specifies the elements type.
 * The {@link Object} type is used by default.
 * For example:<pre>
 * &lt;array length="10"/&gt;</pre>
 * is equivalent to {@code new Component[10]} in Java code.
 * The {@code set} and {@code get} methods,
 * as defined in the {@link java.util.List} interface,
 * can be used as if they could be applied to array instances.
 * The {@code index} attribute can thus be used with arrays.
 * For example:<pre>
 * &lt;array length="3" class="java.lang.String"&gt;
 *     &lt;void index="1"&gt;
 *         &lt;string&gt;Hello, world&lt;/string&gt;
 *     &lt;/void&gt;
 * &lt;/array&gt;</pre>
 * is equivalent to the following Java code:<pre>
 * String[] s = new String[3];
 * s[1] = "Hello, world";</pre>
 * It is possible to omit the {@code length} attribute and
 * specify the values directly, without using {@code void} tags.
 * The length of the array is equal to the number of values specified.
 * For example:<pre>
 * &lt;array id="array" class="int"&gt;
 *     &lt;int&gt;123&lt;/int&gt;
 *     &lt;int&gt;456&lt;/int&gt;
 * &lt;/array&gt;</pre>
 * is equivalent to {@code int[] array = {123, 456}} in Java code.
 * <p>The following attributes are supported:
 * <dl>
 * <dt>length
 * <dd>the array length
 * <dt>class
 * <dd>the type of object for instantiation
 * <dt>id
 * <dd>the identifier of the variable that is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @author Sergey A. Malenkov
 */
final class ArrayElementHandler extends NewElementHandler {
    private Integer length;

    /**
     * Parses attributes of the element.
     * The following attributes are supported:
     * <dl>
     * <dt>length
     * <dd>the array length
     * <dt>class
     * <dd>the type of object for instantiation
     * <dt>id
     * <dd>the identifier of the variable that is intended to store the result
     * </dl>
     *
     * @param name   the attribute name
     * @param value  the attribute value
     */
    @Override
    public void addAttribute(String name, String value) {
        if (name.equals("length")) { // NON-NLS: the attribute name
            this.length = Integer.valueOf(value);
        } else {
            super.addAttribute(name, value);
        }
    }

    /**
     * Calculates the value of this element
     * if the length attribute is set.
     */
    @Override
    public void startElement() {
        if (this.length != null) {
            getValueObject();
        }
    }

    /**
     * Tests whether the value of this element can be used
     * as an argument of the element that contained in this one.
     *
     * @return {@code true} if the value of this element can be used
     *         as an argument of the element that contained in this one,
     *         {@code false} otherwise
     */
    @Override
    protected boolean isArgument() {
        return true; // hack for compatibility
    }


    /**
     * Creates an instance of the array.
     *
     * @param type  the base class
     * @param args  the array of arguments
     * @return the value of this element
     */
    @Override
    protected ValueObject getValueObject(Class<?> type, Object[] args) {
        if (type == null) {
            type = Object.class;
        }
        if (this.length != null) {
            return ValueObjectImpl.create(Array.newInstance(type, this.length));
        }
        Object array = Array.newInstance(type, args.length);
        for (int i = 0; i < args.length; i++) {
            Array.set(array, i, args[i]);
        }
        return ValueObjectImpl.create(array);
    }
}
