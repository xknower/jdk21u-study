package com.sun.jdi;

/**
 * The value assigned to a field or variable of primitive type in a
 * target VM. Each primitive values is accessed through a subinterface
 * of this interface.
 *
 * @author Robert Field
 * @author Gordon Hirsch
 * @author James McIlree
 * @since  1.3
 */
public interface PrimitiveValue extends Value {

    /**
     * Converts this value to a BooleanValue and returns the result
     * as a boolean.
     *
     * @return <code>true</code> if this value is non-zero (or
     * <code>true</code> if already a BooleanValue); false otherwise.
     */
    boolean booleanValue();

    /**
     * Converts this value to a ByteValue and returns the result
     * as a byte. The value will be narrowed as
     * necessary, and magnitude or precision information
     * may be lost (as if the primitive had been cast to a byte).
     *
     * @return the value, converted to byte
     */
    byte byteValue();

    /**
     * Converts this value to a CharValue and returns the result
     * as a char. The value will be narrowed or widened as
     * necessary, and magnitude or precision information
     * may be lost (as if the primitive had been cast to a char,
     * in the narrowing case).
     *
     * @return the value, converted to char
     */
    char charValue();

    /**
     * Converts this value to a ShortValue and returns the result
     * as a short. The value will be narrowed or widened as
     * necessary, and magnitude or precision information
     * may be lost (as if the primitive had been cast to a short,
     * in the narrowing case).
     *
     * @return the value, converted to short
     */
    short shortValue();

    /**
     * Converts this value to an IntegerValue and returns the result
     * as an int. The value will be narrowed or widened as
     * necessary, and magnitude or precision information
     * may be lost (as if the primitive had been cast to an int,
     * in the narrowing case).
     *
     * @return the value, converted to int
     */
    int intValue();

    /**
     * Converts this value to a LongValue and returns the result
     * as a long. The value will be narrowed or widened as
     * necessary, and magnitude or precision information
     * may be lost (as if the primitive had been cast to a long,
     * in the narrowing case).
     *
     * @return the value, converted to long
     */
    long longValue();

    /**
     * Converts this value to a FloatValue and returns the result
     * as a float. The value will be narrowed or widened as
     * necessary, and magnitude or precision information
     * may be lost (as if the primitive had been cast to a float,
     * in the narrowing case).
     *
     * @return the value, converted to float
     */
    float floatValue();

    /**
     * Converts this value to a DoubleValue and returns the result
     * as a double. The value will be widened as
     * necessary, and precision information
     * may be lost.
     *
     * @return the value, converted to double
     */
    double doubleValue();
}
