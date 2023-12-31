package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_el extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "\u03a41",
            "\u03a42",
            "\u03a43",
            "\u03a44",
        };

        final String[] sharedQuarterNames = {
            "1\u03bf \u03c4\u03c1\u03af\u03bc\u03b7\u03bd\u03bf",
            "2\u03bf \u03c4\u03c1\u03af\u03bc\u03b7\u03bd\u03bf",
            "3\u03bf \u03c4\u03c1\u03af\u03bc\u03b7\u03bd\u03bf",
            "4\u03bf \u03c4\u03c1\u03af\u03bc\u03b7\u03bd\u03bf",
        };

        final String[] sharedAmPmMarkers = {
            "\u03c0.\u03bc.",
            "\u03bc.\u03bc.",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "d/M/y G",
        };

        final String[] sharedDayAbbreviations = {
            "\u039a\u03c5\u03c1",
            "\u0394\u03b5\u03c5",
            "\u03a4\u03c1\u03af",
            "\u03a4\u03b5\u03c4",
            "\u03a0\u03ad\u03bc",
            "\u03a0\u03b1\u03c1",
            "\u03a3\u03ac\u03b2",
        };

        final String[] sharedDayNames = {
            "\u039a\u03c5\u03c1\u03b9\u03b1\u03ba\u03ae",
            "\u0394\u03b5\u03c5\u03c4\u03ad\u03c1\u03b1",
            "\u03a4\u03c1\u03af\u03c4\u03b7",
            "\u03a4\u03b5\u03c4\u03ac\u03c1\u03c4\u03b7",
            "\u03a0\u03ad\u03bc\u03c0\u03c4\u03b7",
            "\u03a0\u03b1\u03c1\u03b1\u03c3\u03ba\u03b5\u03c5\u03ae",
            "\u03a3\u03ac\u03b2\u03b2\u03b1\u03c4\u03bf",
        };

        final String[] sharedDayNarrows = {
            "\u039a",
            "\u0394",
            "\u03a4",
            "\u03a4",
            "\u03a0",
            "\u03a0",
            "\u03a3",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "\u03c0\u03bc",
            "\u03bc\u03bc",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "d/M/y GGGGG",
        };

        final String[] sharedEras = {
            "\u03a0\u03c1\u03b9\u03bd R.O.C.",
            "R.O.C.",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u0392\u03bf\u03c5\u03b4\u03b9\u03c3\u03c4\u03b9\u03ba\u03cc \u03b7\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.gregorian",
                "\u0393\u03c1\u03b7\u03b3\u03bf\u03c1\u03b9\u03b1\u03bd\u03cc \u03b7\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.gregory",
                "\u0393\u03c1\u03b7\u03b3\u03bf\u03c1\u03b9\u03b1\u03bd\u03cc \u03b7\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.islamic",
                "\u0399\u03c3\u03bb\u03b1\u03bc\u03b9\u03ba\u03cc \u03b7\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.islamic-civil",
                "\u0399\u03c3\u03bb\u03b1\u03bc\u03b9\u03ba\u03cc \u03b1\u03c3\u03c4\u03b9\u03ba\u03cc \u03b7\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.japanese",
                "\u0399\u03b1\u03c0\u03c9\u03bd\u03b9\u03ba\u03cc \u03b7\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.roc",
                "\u0397\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf \u03c4\u03b7\u03c2 \u0394\u03b7\u03bc\u03bf\u03ba\u03c1\u03b1\u03c4\u03af\u03b1\u03c2 \u03c4\u03b7\u03c2 \u039a\u03af\u03bd\u03b1\u03c2" },
            { "field.dayperiod",
                "\u03c0.\u03bc./\u03bc.\u03bc." },
            { "field.era",
                "\u03c0\u03b5\u03c1\u03af\u03bf\u03b4\u03bf\u03c2" },
            { "field.hour",
                "\u03ce\u03c1\u03b1" },
            { "field.minute",
                "\u03bb\u03b5\u03c0\u03c4\u03cc" },
            { "field.month",
                "\u03bc\u03ae\u03bd\u03b1\u03c2" },
            { "field.second",
                "\u03b4\u03b5\u03c5\u03c4\u03b5\u03c1\u03cc\u03bb\u03b5\u03c0\u03c4\u03bf" },
            { "field.week",
                "\u03b5\u03b2\u03b4\u03bf\u03bc\u03ac\u03b4\u03b1" },
            { "field.weekday",
                "\u03ba\u03b1\u03b8\u03b7\u03bc\u03b5\u03c1\u03b9\u03bd\u03ae" },
            { "field.year",
                "\u03ad\u03c4\u03bf\u03c2" },
            { "field.zone",
                "\u03b6\u03ce\u03bd\u03b7 \u03ce\u03c1\u03b1\u03c2" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayAbbreviations",
                sharedDayAbbreviations },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.buddhist.short.Eras",
                new String[] {
                    "BC",
                    "BE",
                }
            },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE, d MMMM, y G",
                    "d MMMM, y G",
                    "d MMM, y G",
                    "d/M/yy",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "\u03c0\u03c1\u03bf \u03a7\u03c1\u03b9\u03c3\u03c4\u03bf\u03cd",
                    "\u03bc\u03b5\u03c4\u03ac \u03a7\u03c1\u03b9\u03c3\u03c4\u03cc\u03bd",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u03c0.\u03a7.",
                    "\u03bc.\u03a7.",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedEras },
            { "roc.MonthAbbreviations",
                new String[] {
                    "\u0399\u03b1\u03bd",
                    "\u03a6\u03b5\u03b2",
                    "\u039c\u03b1\u03c1",
                    "\u0391\u03c0\u03c1",
                    "\u039c\u03b1\u0390",
                    "\u0399\u03bf\u03c5\u03bd",
                    "\u0399\u03bf\u03c5\u03bb",
                    "\u0391\u03c5\u03b3",
                    "\u03a3\u03b5\u03c0",
                    "\u039f\u03ba\u03c4",
                    "\u039d\u03bf\u03b5",
                    "\u0394\u03b5\u03ba",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u0399\u03b1\u03bd\u03bf\u03c5\u03b1\u03c1\u03af\u03bf\u03c5",
                    "\u03a6\u03b5\u03b2\u03c1\u03bf\u03c5\u03b1\u03c1\u03af\u03bf\u03c5",
                    "\u039c\u03b1\u03c1\u03c4\u03af\u03bf\u03c5",
                    "\u0391\u03c0\u03c1\u03b9\u03bb\u03af\u03bf\u03c5",
                    "\u039c\u03b1\u0390\u03bf\u03c5",
                    "\u0399\u03bf\u03c5\u03bd\u03af\u03bf\u03c5",
                    "\u0399\u03bf\u03c5\u03bb\u03af\u03bf\u03c5",
                    "\u0391\u03c5\u03b3\u03bf\u03cd\u03c3\u03c4\u03bf\u03c5",
                    "\u03a3\u03b5\u03c0\u03c4\u03b5\u03bc\u03b2\u03c1\u03af\u03bf\u03c5",
                    "\u039f\u03ba\u03c4\u03c9\u03b2\u03c1\u03af\u03bf\u03c5",
                    "\u039d\u03bf\u03b5\u03bc\u03b2\u03c1\u03af\u03bf\u03c5",
                    "\u0394\u03b5\u03ba\u03b5\u03bc\u03b2\u03c1\u03af\u03bf\u03c5",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "\u0399",
                    "\u03a6",
                    "\u039c",
                    "\u0391",
                    "\u039c",
                    "\u0399",
                    "\u0399",
                    "\u0391",
                    "\u03a3",
                    "\u039f",
                    "\u039d",
                    "\u0394",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
        };
    }
}
