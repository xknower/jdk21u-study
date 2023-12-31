package jdk.jshell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.lang.model.element.Name;

/**
 * Assorted shared utilities and constants.
 */
class Util {

    /**
     * The package name of all wrapper classes.
     */
    static final String REPL_PACKAGE = "REPL";

    /**
     * The prefix for all wrapper class names.
     */
    static final String REPL_CLASS_PREFIX = "$JShell$";

    /**
     * The name of the invoke method.
     */
    static final String DOIT_METHOD_NAME = "do_it$";

    /**
     * The prefix for all anonymous classes upgraded to member classes.
     */
    static final String JSHELL_ANONYMOUS = "$JShell$anonymous$";

    /**
     * A pattern matching the full or simple class name of a wrapper class.
     */
    static final Pattern PREFIX_PATTERN = Pattern.compile(
            "(" + REPL_PACKAGE + "\\.)?"
            + "(?<class>" + Pattern.quote(REPL_CLASS_PREFIX)
            + "\\w+" + ")" + "[\\$\\.]?");

    static final String REPL_DOESNOTMATTER_CLASS_NAME = REPL_CLASS_PREFIX + "DOESNOTMATTER";

    static final Locale PARSED_LOCALE = Locale.ROOT;

    static boolean isDoIt(Name name) {
        return isDoIt(name.toString());
    }

    static boolean isDoIt(String sname) {
        return sname.equals(DOIT_METHOD_NAME);
    }

    static String expunge(String s) {
        StringBuilder sb = new StringBuilder();
        for (String comp : PREFIX_PATTERN.split(s)) {
            sb.append(comp);
        }
        return sb.toString();
    }

    /**
     * Check if this is the name of something in JShell.
     *
     * @param s the name of the class, method, variable, ...
     * @return true if it is, or is within a JShell defined wrapper class
     */
    static boolean isInJShellClass(String s) {
        Matcher m = PREFIX_PATTERN.matcher(s);
        return m.find() && m.start() == 0;
    }

    static String asLetters(int i) {
        if (i == 0) {
            return "";
        }

        char buf[] = new char[33];
        int charPos = 32;

        i = -i;
        while (i <= -26) {
            buf[charPos--] = (char) ('A'-(i % 26));
            i = i / 26;
        }
        buf[charPos] = (char) ('A'-i);

        return new String(buf, charPos, (33 - charPos));
    }


    static String trimEnd(String s) {
        int last = s.length() - 1;
        int i = last;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            --i;
        }
        if (i != last) {
            return s.substring(0, i + 1);
        } else {
            return s;
        }
    }

    static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    static String[] join(String[] a1, String[] a2) {
        List<String> result = new ArrayList<>();

        result.addAll(Arrays.asList(a1));
        result.addAll(Arrays.asList(a2));

        return result.toArray(new String[0]);
    }

    static class Pair<T, U> {
        final T first;
        final U second;

        Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }
}
