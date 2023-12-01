package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_en_MT extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "dd MMMM y GGGG",
            "dd MMM y GGGG",
            "G y-MM-dd",
        };

        final String[] sharedTimePatterns = {
            "HH:mm:ss zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "dd MMMM y G",
            "dd MMM y G",
            "GGGGG y-MM-dd",
        };

        return new Object[][] {
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE, MMMM d, y G",
                    "dd MMMM y G",
                    "dd MMM y G",
                    "M/d/y GGGGG",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.TimePatterns",
                sharedTimePatterns },
        };
    }
}
