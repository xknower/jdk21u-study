package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_es_US extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedAmPmMarkers = {
            "AM",
            "PM",
        };

        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "GGGG y MMM d",
            "dd/MM/yy G",
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

        final String[] sharedAbbreviatedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "G y MMM d",
            "dd/MM/yy GGGGG",
        };

        return new Object[][] {
            { "field.dayperiod",
                "a.m./p.m." },
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
                sharedAbbreviatedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "a.C.",
                    "d.C.",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.MonthAbbreviations",
                new String[] {
                    "ene.",
                    "feb.",
                    "mar.",
                    "abr.",
                    "may.",
                    "jun.",
                    "jul.",
                    "ago.",
                    "sep.",
                    "oct.",
                    "nov.",
                    "dic.",
                    "",
                }
            },
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
                sharedAbbreviatedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
