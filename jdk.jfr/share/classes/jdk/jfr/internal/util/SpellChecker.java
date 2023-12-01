package jdk.jfr.internal.util;

import java.util.List;

public final class SpellChecker {
    public static String check(String name, List<String> alternatives) {
        for (String expected : alternatives) {
            String s = name.toLowerCase();
            int lengthDifference = expected.length() - s.length();
            boolean spellingError = false;
            if (lengthDifference == 0) {
                if (expected.equals(s)) {
                    spellingError = true; // incorrect case, or we wouldn't be here
                } else {
                    if (s.length() < 6) {
                        spellingError = diff(expected, s) < 2; // one incorrect letter
                    } else {
                        spellingError = diff(expected, s) < 3; // two incorrect letter
                    }
                }
            }
            if (lengthDifference == 1) {
                spellingError = inSequence(expected, s); // missing letter
            }
            if (lengthDifference == -1) {
                spellingError = inSequence(s, expected); // additional letter
            }
            if (spellingError) {
                return expected;
            }
        }
        return null;
    }

    private static int diff(String a, String b) {
        int count = a.length();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                count--;
            }
        }
        return count;
    }

    private static boolean inSequence(String longer, String shorter) {
        int l = 0;
        int s = 0;
        while (l < longer.length() && s < shorter.length()) {
            if (longer.charAt(l) == shorter.charAt(s)) {
                s++;
            }
            l++;
        }
        return shorter.length() == s; // if 0, all letters in longer found in shorter
    }
}
