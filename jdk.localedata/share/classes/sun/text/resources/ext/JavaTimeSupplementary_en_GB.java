package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_en_GB extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedAmPmMarkers = {
            "am",
            "pm",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "dd/MM/y G",
        };

        final String[] sharedTimePatterns = {
            "HH:mm:ss zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "dd/MM/y GGGGG",
        };

        return new Object[][] {
            { "field.dayperiod",
                "am/pm" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
        };
    }
}
