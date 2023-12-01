package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_es_CO extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "d/MM/y GGGG",
            "d/MM/yy G",
        };

        final String[] sharedDayNarrows = {
            "d",
            "l",
            "m",
            "m",
            "j",
            "v",
            "s",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "d/MM/y G",
            "d/MM/yy GGGGG",
        };

        return new Object[][] {
            { "field.zone",
                "Zona horaria" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE, d 'de' MMMM 'de' y G",
                    "d 'de' MMMM 'de' y G",
                    "d/MM/y G",
                    "d/MM/yy GGGGG",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.MonthNarrows",
                new String[] {
                    "e",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "a",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
        };
    }
}
