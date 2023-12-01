package java.lang;

import jdk.internal.misc.Unsafe;
import jdk.internal.javac.PreviewFeature;
import jdk.internal.util.FormatConcatItem;
import jdk.internal.vm.annotation.ForceInline;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Helper for string concatenation. These methods are mostly looked up with private lookups
 * from {@link java.lang.invoke.StringConcatFactory}, and used in {@link java.lang.invoke.MethodHandle}
 * combinators there.
 */
final class StringConcatHelper {

    private StringConcatHelper() {
        // no instantiation
    }

    /**
     * Return the coder for the character.
     * @param value character
     * @return      coder
     */
    static long coder(char value) {
        return StringLatin1.canEncode(value) ? LATIN1 : UTF16;
    }

    /**
     * Check for overflow, throw exception on overflow.
     *
     * @param lengthCoder String length with coder packed into higher bits
     *                    the upper word.
     * @return            the given parameter value, if valid
     */
    private static long checkOverflow(long lengthCoder) {
        if ((int)lengthCoder >= 0) {
            return lengthCoder;
        }
        throw new OutOfMemoryError("Overflow: String length out of range");
    }

    /**
     * Mix value length and coder into current length and coder.
     * @param lengthCoder String length with coder packed into higher bits
     *                    the upper word.
     * @param value       value to mix in
     * @return            new length and coder
     */
    static long mix(long lengthCoder, boolean value) {
        return checkOverflow(lengthCoder + (value ? 4 : 5));
    }

    /**
     * Mix value length and coder into current length and coder.
     * @param lengthCoder String length with coder packed into higher bits
     *                    the upper word.
     * @param value       value to mix in
     * @return            new length and coder
     */
    static long mix(long lengthCoder, char value) {
        return checkOverflow(lengthCoder + 1) | coder(value);
    }

    /**
     * Mix value length and coder into current length and coder.
     * @param lengthCoder String length with coder packed into higher bits
     *                    the upper word.
     * @param value       value to mix in
     * @return            new length and coder
     */
    static long mix(long lengthCoder, int value) {
        return checkOverflow(lengthCoder + Integer.stringSize(value));
    }

    /**
     * Mix value length and coder into current length and coder.
     * @param lengthCoder String length with coder packed into higher bits
     *                    the upper word.
     * @param value       value to mix in
     * @return            new length and coder
     */
    static long mix(long lengthCoder, long value) {
        return checkOverflow(lengthCoder + Long.stringSize(value));
    }

    /**
     * Mix value length and coder into current length and coder.
     * @param lengthCoder String length with coder packed into higher bits
     *                    the upper word.
     * @param value       value to mix in
     * @return            new length and coder
     */
    static long mix(long lengthCoder, String value) {
        lengthCoder += value.length();
        if (value.coder() == String.UTF16) {
            lengthCoder |= UTF16;
        }
        return checkOverflow(lengthCoder);
    }

    /**
     * Mix value length and coder into current length and coder.
     * @param lengthCoder String length with coder packed into higher bits
     *                    the upper word.
     * @param value       value to mix in
     * @return            new length and coder
     * @since 21
     */
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static long mix(long lengthCoder, FormatConcatItem value) {
        lengthCoder = value.mix(lengthCoder);

        return checkOverflow(lengthCoder);
    }

    /**
     * Prepends the stringly representation of boolean value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      boolean value to encode
     * @return           updated index (coder value retained)
     */
    private static long prepend(long indexCoder, byte[] buf, boolean value) {
        int index = (int)indexCoder;
        if (indexCoder < UTF16) {
            if (value) {
                buf[--index] = 'e';
                buf[--index] = 'u';
                buf[--index] = 'r';
                buf[--index] = 't';
            } else {
                buf[--index] = 'e';
                buf[--index] = 's';
                buf[--index] = 'l';
                buf[--index] = 'a';
                buf[--index] = 'f';
            }
            return index;
        } else {
            if (value) {
                StringUTF16.putChar(buf, --index, 'e');
                StringUTF16.putChar(buf, --index, 'u');
                StringUTF16.putChar(buf, --index, 'r');
                StringUTF16.putChar(buf, --index, 't');
            } else {
                StringUTF16.putChar(buf, --index, 'e');
                StringUTF16.putChar(buf, --index, 's');
                StringUTF16.putChar(buf, --index, 'l');
                StringUTF16.putChar(buf, --index, 'a');
                StringUTF16.putChar(buf, --index, 'f');
            }
            return index | UTF16;
        }
    }

    /**
     * Prepends constant and the stringly representation of value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      boolean value to encode
     * @param prefix     a constant to prepend before value
     * @return           updated index (coder value retained)
     */
    static long prepend(long indexCoder, byte[] buf, boolean value, String prefix) {
        indexCoder = prepend(indexCoder, buf, value);
        if (prefix != null) indexCoder = prepend(indexCoder, buf, prefix);
        return indexCoder;
    }

    /**
     * Prepends the stringly representation of char value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      char value to encode
     * @return           updated index (coder value retained)
     */
    private static long prepend(long indexCoder, byte[] buf, char value) {
        if (indexCoder < UTF16) {
            buf[(int)(--indexCoder)] = (byte) (value & 0xFF);
        } else {
            StringUTF16.putChar(buf, (int)(--indexCoder), value);
        }
        return indexCoder;
    }

    /**
     * Prepends constant and the stringly representation of value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      boolean value to encode
     * @param prefix     a constant to prepend before value
     * @return           updated index (coder value retained)
     */
    static long prepend(long indexCoder, byte[] buf, char value, String prefix) {
        indexCoder = prepend(indexCoder, buf, value);
        if (prefix != null) indexCoder = prepend(indexCoder, buf, prefix);
        return indexCoder;
    }

    /**
     * Prepends the stringly representation of integer value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      integer value to encode
     * @return           updated index (coder value retained)
     */
    private static long prepend(long indexCoder, byte[] buf, int value) {
        if (indexCoder < UTF16) {
            return Integer.getChars(value, (int)indexCoder, buf);
        } else {
            return StringUTF16.getChars(value, (int)indexCoder, buf) | UTF16;
        }
    }

    /**
     * Prepends constant and the stringly representation of value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      boolean value to encode
     * @param prefix     a constant to prepend before value
     * @return           updated index (coder value retained)
     */
    static long prepend(long indexCoder, byte[] buf, int value, String prefix) {
        indexCoder = prepend(indexCoder, buf, value);
        if (prefix != null) indexCoder = prepend(indexCoder, buf, prefix);
        return indexCoder;
    }

    /**
     * Prepends the stringly representation of long value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      long value to encode
     * @return           updated index (coder value retained)
     */
    private static long prepend(long indexCoder, byte[] buf, long value) {
        if (indexCoder < UTF16) {
            return Long.getChars(value, (int)indexCoder, buf);
        } else {
            return StringUTF16.getChars(value, (int)indexCoder, buf) | UTF16;
        }
    }

    /**
     * Prepends constant and the stringly representation of value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      boolean value to encode
     * @param prefix     a constant to prepend before value
     * @return           updated index (coder value retained)
     */
    static long prepend(long indexCoder, byte[] buf, long value, String prefix) {
        indexCoder = prepend(indexCoder, buf, value);
        if (prefix != null) indexCoder = prepend(indexCoder, buf, prefix);
        return indexCoder;
    }

    /**
     * Prepends the stringly representation of String value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      String value to encode
     * @return           updated index (coder value retained)
     */
    private static long prepend(long indexCoder, byte[] buf, String value) {
        indexCoder -= value.length();
        if (indexCoder < UTF16) {
            value.getBytes(buf, (int)indexCoder, String.LATIN1);
        } else {
            value.getBytes(buf, (int)indexCoder, String.UTF16);
        }
        return indexCoder;
    }

    /**
     * Prepends constant and the stringly representation of value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      boolean value to encode
     * @param prefix     a constant to prepend before value
     * @return           updated index (coder value retained)
     */
    static long prepend(long indexCoder, byte[] buf, String value, String prefix) {
        indexCoder = prepend(indexCoder, buf, value);
        if (prefix != null) indexCoder = prepend(indexCoder, buf, prefix);
        return indexCoder;
    }

    /**
     * Prepends the stringly representation of FormatConcatItem value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      String value to encode
     * @return           updated index (coder value retained)
     * @since 21
     */
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    private static long prepend(long indexCoder, byte[] buf,
                                FormatConcatItem value) {
        try {
            return value.prepend(indexCoder, buf);
        } catch (Error ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new AssertionError("FormatConcatItem prepend error", ex);
        }
    }

    /**
     * Prepends constant and the stringly representation of value into buffer,
     * given the coder and final index. Index is measured in chars, not in bytes!
     *
     * @param indexCoder final char index in the buffer, along with coder packed
     *                   into higher bits.
     * @param buf        buffer to append to
     * @param value      boolean value to encode
     * @param prefix     a constant to prepend before value
     * @return           updated index (coder value retained)
     * @since 21
     */
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static long prepend(long indexCoder, byte[] buf,
                        FormatConcatItem value, String prefix) {
        indexCoder = prepend(indexCoder, buf, value);
        if (prefix != null) indexCoder = prepend(indexCoder, buf, prefix);
        return indexCoder;
    }

    /**
     * Instantiates the String with given buffer and coder
     * @param buf           buffer to use
     * @param indexCoder    remaining index (should be zero) and coder
     * @return String       resulting string
     */
    static String newString(byte[] buf, long indexCoder) {
        // Use the private, non-copying constructor (unsafe!)
        if (indexCoder == LATIN1) {
            return new String(buf, String.LATIN1);
        } else if (indexCoder == UTF16) {
            return new String(buf, String.UTF16);
        } else {
            throw new InternalError("Storage is not completely initialized, " +
                    (int)indexCoder + " bytes left");
        }
    }

    /**
     * Perform a simple concatenation between two objects. Added for startup
     * performance, but also demonstrates the code that would be emitted by
     * {@code java.lang.invoke.StringConcatFactory$MethodHandleInlineCopyStrategy}
     * for two Object arguments.
     *
     * @param first         first argument
     * @param second        second argument
     * @return String       resulting string
     */
    @ForceInline
    static String simpleConcat(Object first, Object second) {
        String s1 = stringOf(first);
        String s2 = stringOf(second);
        if (s1.isEmpty()) {
            // newly created string required, see JLS 15.18.1
            return new String(s2);
        }
        if (s2.isEmpty()) {
            // newly created string required, see JLS 15.18.1
            return new String(s1);
        }
        // start "mixing" in length and coder or arguments, order is not
        // important
        long indexCoder = mix(initialCoder(), s1);
        indexCoder = mix(indexCoder, s2);
        byte[] buf = newArray(indexCoder);
        // prepend each argument in reverse order, since we prepending
        // from the end of the byte array
        indexCoder = prepend(indexCoder, buf, s2);
        indexCoder = prepend(indexCoder, buf, s1);
        return newString(buf, indexCoder);
    }

    /**
     * Produce a String from a concatenation of single argument, which we
     * end up using for trivial concatenations like {@code "" + arg}.
     *
     * This will always create a new Object to comply with JLS {@jls 15.18.1}:
     * "The String object is newly created unless the expression is a
     * compile-time constant expression".
     *
     * @param arg           the only argument
     * @return String       resulting string
     */
    @ForceInline
    static String newStringOf(Object arg) {
        return new String(stringOf(arg));
    }

    /**
     * We need some additional conversion for Objects in general, because
     * {@code String.valueOf(Object)} may return null. String conversion rules
     * in Java state we need to produce "null" String in this case, so we
     * provide a customized version that deals with this problematic corner case.
     */
    static String stringOf(Object value) {
        String s;
        return (value == null || (s = value.toString()) == null) ? "null" : s;
    }

    private static final long LATIN1 = (long)String.LATIN1 << 32;

    private static final long UTF16 = (long)String.UTF16 << 32;

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    /**
     * Allocates an uninitialized byte array based on the length and coder
     * information, then prepends the given suffix string at the end of the
     * byte array before returning it. The calling code must adjust the
     * indexCoder so that it's taken the coder of the suffix into account, but
     * subtracted the length of the suffix.
     *
     * @param suffix
     * @param indexCoder
     * @return the newly allocated byte array
     */
    @ForceInline
    static byte[] newArrayWithSuffix(String suffix, long indexCoder) {
        byte[] buf = newArray(indexCoder + suffix.length());
        if (indexCoder < UTF16) {
            suffix.getBytes(buf, (int)indexCoder, String.LATIN1);
        } else {
            suffix.getBytes(buf, (int)indexCoder, String.UTF16);
        }
        return buf;
    }

    /**
     * Allocates an uninitialized byte array based on the length and coder information
     * in indexCoder
     * @param indexCoder
     * @return the newly allocated byte array
     */
    @ForceInline
    static byte[] newArray(long indexCoder) {
        byte coder = (byte)(indexCoder >> 32);
        int index = ((int)indexCoder) << coder;
        if (index < 0) {
            throw new OutOfMemoryError("Overflow: String length out of range");
        }
        return (byte[]) UNSAFE.allocateUninitializedArray(byte.class, index);
    }

    /**
     * Provides the initial coder for the String.
     * @return initial coder, adjusted into the upper half
     */
    static long initialCoder() {
        return String.COMPACT_STRINGS ? LATIN1 : UTF16;
    }

    /*
     * Initialize after phase1.
     */
    private static class LateInit {
        static final MethodHandle GETCHAR_LATIN1_MH;

        static final MethodHandle GETCHAR_UTF16_MH;

        static final MethodHandle PUTCHAR_LATIN1_MH;

        static final MethodHandle PUTCHAR_UTF16_MH;

        static {
            MethodType getCharMT =
                MethodType.methodType(char.class,
                        byte[].class, int.class);
            MethodType putCharMT =
                MethodType.methodType(void.class,
                        byte[].class, int.class, int.class);
            GETCHAR_LATIN1_MH = lookupStatic("getCharLatin1", getCharMT);
            GETCHAR_UTF16_MH = lookupStatic("getCharUTF16", getCharMT);
            PUTCHAR_LATIN1_MH = lookupStatic("putCharLatin1", putCharMT);
            PUTCHAR_UTF16_MH = lookupStatic("putCharUTF16", putCharMT);
        }

    }

    @ForceInline
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static char getCharLatin1(byte[] buffer, int index) {
        return (char)buffer[index];
    }

    @ForceInline
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static char getCharUTF16(byte[] buffer, int index) {
        return StringUTF16.getChar(buffer, index);
    }

    @ForceInline
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static void putCharLatin1(byte[] buffer, int index, int ch) {
        buffer[index] = (byte)ch;
    }

    @ForceInline
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static void putCharUTF16(byte[] buffer, int index, int ch) {
        StringUTF16.putChar(buffer, index, ch);
    }

    @ForceInline
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static MethodHandle selectGetChar(long indexCoder) {
        return indexCoder < UTF16 ? LateInit.GETCHAR_LATIN1_MH :
                                    LateInit.GETCHAR_UTF16_MH;
    }

    @ForceInline
    @PreviewFeature(feature=PreviewFeature.Feature.STRING_TEMPLATES)
    static MethodHandle selectPutChar(long indexCoder) {
        return indexCoder < UTF16 ? LateInit.PUTCHAR_LATIN1_MH :
                                    LateInit.PUTCHAR_UTF16_MH;
    }

    static MethodHandle lookupStatic(String name, MethodType methodType) {
        try {
            return MethodHandles.lookup().findStatic(StringConcatHelper.class, name, methodType);
        } catch (NoSuchMethodException|IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

}
