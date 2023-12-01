package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_en_IE extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedDatePatterns = {
            "EEEE d MMMM y GGGG",
            "GGGG y MMMM d",
            "GGGG y MMM d",
            "G y-MM-dd",
        };

        final String[] sharedTimePatterns = {
            "HH:mm:ss zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "G y MMMM d",
            "G y MMM d",
            "GGGGG y-MM-dd",
        };

        return new Object[][] {
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
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
                    "EEEE d MMMM y G",
                    "MMMM d, y G",
                    "MMM d, y G",
                    "M/d/y GGGGG",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.TimePatterns",
                sharedTimePatterns },
        };
    }
}
