package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_he extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "\u05e8\u05d1\u05e2\u05d5\u05df 1",
            "\u05e8\u05d1\u05e2\u05d5\u05df 2",
            "\u05e8\u05d1\u05e2\u05d5\u05df 3",
            "\u05e8\u05d1\u05e2\u05d5\u05df 4",
        };

        final String[] sharedAmPmMarkers = {
            "\u05dc\u05e4\u05e0\u05d4\u05f4\u05e6",
            "\u05d0\u05d7\u05d4\u05f4\u05e6",
        };

        final String[] sharedDayAbbreviations = {
            "\u05d9\u05d5\u05dd \u05d0\u05f3",
            "\u05d9\u05d5\u05dd \u05d1\u05f3",
            "\u05d9\u05d5\u05dd \u05d2\u05f3",
            "\u05d9\u05d5\u05dd \u05d3\u05f3",
            "\u05d9\u05d5\u05dd \u05d4\u05f3",
            "\u05d9\u05d5\u05dd \u05d5\u05f3",
            "\u05e9\u05d1\u05ea",
        };

        final String[] sharedDayNames = {
            "\u05d9\u05d5\u05dd \u05e8\u05d0\u05e9\u05d5\u05df",
            "\u05d9\u05d5\u05dd \u05e9\u05e0\u05d9",
            "\u05d9\u05d5\u05dd \u05e9\u05dc\u05d9\u05e9\u05d9",
            "\u05d9\u05d5\u05dd \u05e8\u05d1\u05d9\u05e2\u05d9",
            "\u05d9\u05d5\u05dd \u05d7\u05de\u05d9\u05e9\u05d9",
            "\u05d9\u05d5\u05dd \u05e9\u05d9\u05e9\u05d9",
            "\u05d9\u05d5\u05dd \u05e9\u05d1\u05ea",
        };

        final String[] sharedDayNarrows = {
            "\u05d0\u05f3",
            "\u05d1\u05f3",
            "\u05d2\u05f3",
            "\u05d3\u05f3",
            "\u05d4\u05f3",
            "\u05d5\u05f3",
            "\u05e9\u05f3",
        };

        final String[] sharedEras = {
            "",
            "\u05e9\u05e0\u05ea \u05d4\u05d9\u05d2\u05f3\u05e8\u05d4",
        };

        final String[] sharedTimePatterns = {
            "H:mm:ss zzzz",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d \u05d1MMMM y G",
            "d \u05d1MMMM y G",
            "d \u05d1MMM y G",
            "d.M.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterNames },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u05dc\u05d5\u05d7 \u05e9\u05e0\u05d4 \u05d1\u05d5\u05d3\u05d4\u05d9\u05e1\u05d8\u05d9" },
            { "calendarname.gregorian",
                "\u05dc\u05d5\u05d7 \u05e9\u05e0\u05d4 \u05d2\u05e8\u05d2\u05d5\u05e8\u05d9\u05d0\u05e0\u05d9" },
            { "calendarname.gregory",
                "\u05dc\u05d5\u05d7 \u05e9\u05e0\u05d4 \u05d2\u05e8\u05d2\u05d5\u05e8\u05d9\u05d0\u05e0\u05d9" },
            { "calendarname.islamic",
                "\u05dc\u05d5\u05d7 \u05e9\u05e0\u05d4 \u05de\u05d5\u05e1\u05dc\u05de\u05d9" },
            { "calendarname.islamic-civil",
                "\u05dc\u05d5\u05d7 \u05e9\u05e0\u05d4 \u05de\u05d5\u05e1\u05dc\u05de\u05d9-\u05d0\u05d6\u05e8\u05d7\u05d9" },
            { "calendarname.japanese",
                "\u05dc\u05d5\u05d7 \u05e9\u05e0\u05d4 \u05d9\u05e4\u05e0\u05d9" },
            { "calendarname.roc",
                "\u05dc\u05d5\u05d7 \u05d4\u05e9\u05e0\u05d4 \u05d4\u05e1\u05d9\u05e0\u05d9 Minguo" },
            { "field.dayperiod",
                "\u05dc\u05e4\u05e0\u05d4\u05f4\u05e6/\u05d0\u05d7\u05d4\u05f4\u05e6" },
            { "field.era",
                "\u05ea\u05e7\u05d5\u05e4\u05d4" },
            { "field.hour",
                "\u05e9\u05e2\u05d4" },
            { "field.minute",
                "\u05d3\u05e7\u05d4" },
            { "field.month",
                "\u05d7\u05d5\u05d3\u05e9" },
            { "field.second",
                "\u05e9\u05e0\u05d9\u05d9\u05d4" },
            { "field.week",
                "\u05e9\u05d1\u05d5\u05e2" },
            { "field.weekday",
                "\u05d9\u05d5\u05dd \u05d1\u05e9\u05d1\u05d5\u05e2" },
            { "field.year",
                "\u05e9\u05e0\u05d4" },
            { "field.zone",
                "\u05d0\u05d6\u05d5\u05e8" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE, d \u05d1MMMM y GGGG",
                    "d \u05d1MMMM y GGGG",
                    "d \u05d1MMM y GGGG",
                    "dd/MM/yy G",
                }
            },
            { "islamic.DayAbbreviations",
                sharedDayAbbreviations },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.Eras",
                sharedEras },
            { "islamic.MonthAbbreviations",
                new String[] {
                    "\u05de\u05d5\u05d7\u05e8\u05dd",
                    "\u05e6\u05e4\u05e8",
                    "\u05e8\u05d1\u05d9\u05e2 \u05d0\u05f3",
                    "\u05e8\u05d1\u05d9\u05e2 \u05d1\u05f3",
                    "\u05d2\u05f3\u05d5\u05de\u05d0\u05d3\u05d0 \u05d0\u05f3",
                    "\u05d2\u05f3\u05d5\u05de\u05d0\u05d3\u05d0 \u05d1\u05f3",
                    "\u05e8\u05d2\u05f3\u05d1",
                    "\u05e9\u05e2\u05d1\u05d0\u05df",
                    "\u05e8\u05de\u05d3\u05d0\u05df",
                    "\u05e9\u05d5\u05d5\u05d0\u05dc",
                    "\u05d3\u05f3\u05d5 \u05d0\u05dc\u05be\u05e7\u05e2\u05d3\u05d4",
                    "\u05d3\u05f3\u05d5 \u05d0\u05dc\u05be\u05d7\u05d9\u05d2\u05f3\u05d4",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "\u05de\u05d5\u05d7\u05e8\u05dd",
                    "\u05e6\u05e4\u05e8",
                    "\u05e8\u05d1\u05d9\u05e2 \u05d0\u05dc-\u05d0\u05d5\u05d5\u05dc",
                    "\u05e8\u05d1\u05d9\u05e2 \u05d0-\u05ea\u05f3\u05d0\u05e0\u05d9",
                    "\u05d2\u05f3\u05d5\u05de\u05d0\u05d3\u05d0 \u05d0\u05dc-\u05d0\u05d5\u05dc\u05d0",
                    "\u05d2\u05f3\u05d5\u05de\u05d0\u05d3\u05d0 \u05d0-\u05ea\u05f3\u05d0\u05e0\u05d9\u05d4",
                    "\u05e8\u05d2\u05f3\u05d1",
                    "\u05e9\u05e2\u05d1\u05d0\u05df",
                    "\u05e8\u05de\u05d3\u05d0\u05df",
                    "\u05e9\u05d5\u05d5\u05d0\u05dc",
                    "\u05d3\u05f3\u05d5 \u05d0\u05dc\u05be\u05e7\u05e2\u05d3\u05d4",
                    "\u05d3\u05f3\u05d5 \u05d0\u05dc\u05be\u05d7\u05d9\u05d2\u05f3\u05d4",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterNames },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.long.Eras",
                sharedEras },
            { "islamic.narrow.Eras",
                sharedEras },
            { "islamic.short.Eras",
                sharedEras },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                new String[] {
                    "EEEE, d \u05d1MMMM y G",
                    "d \u05d1MMMM y G",
                    "d \u05d1MMM y G",
                    "dd/MM/yy GGGGG",
                }
            },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "\u05dc\u05e4\u05e0\u05d9 \u05d4\u05e1\u05e4\u05d9\u05e8\u05d4",
                    "\u05dc\u05e1\u05e4\u05d9\u05e8\u05d4",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u05dc\u05e1\u05d4\"\u05e0",
                    "\u05dc\u05e4\u05e1\u05d4\"\u05e0",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE, d \u05d1MMMM y GGGG",
                    "d \u05d1MMMM y GGGG",
                    "d \u05d1MMM y GGGG",
                    "d.M.y G",
                }
            },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.MonthAbbreviations",
                new String[] {
                    "\u05d9\u05e0\u05d5\u05f3",
                    "\u05e4\u05d1\u05e8\u05f3",
                    "\u05de\u05e8\u05e5",
                    "\u05d0\u05e4\u05e8\u05f3",
                    "\u05de\u05d0\u05d9",
                    "\u05d9\u05d5\u05e0\u05d9",
                    "\u05d9\u05d5\u05dc\u05d9",
                    "\u05d0\u05d5\u05d2\u05f3",
                    "\u05e1\u05e4\u05d8\u05f3",
                    "\u05d0\u05d5\u05e7\u05f3",
                    "\u05e0\u05d5\u05d1\u05f3",
                    "\u05d3\u05e6\u05de\u05f3",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u05d9\u05e0\u05d5\u05d0\u05e8",
                    "\u05e4\u05d1\u05e8\u05d5\u05d0\u05e8",
                    "\u05de\u05e8\u05e5",
                    "\u05d0\u05e4\u05e8\u05d9\u05dc",
                    "\u05de\u05d0\u05d9",
                    "\u05d9\u05d5\u05e0\u05d9",
                    "\u05d9\u05d5\u05dc\u05d9",
                    "\u05d0\u05d5\u05d2\u05d5\u05e1\u05d8",
                    "\u05e1\u05e4\u05d8\u05de\u05d1\u05e8",
                    "\u05d0\u05d5\u05e7\u05d8\u05d5\u05d1\u05e8",
                    "\u05e0\u05d5\u05d1\u05de\u05d1\u05e8",
                    "\u05d3\u05e6\u05de\u05d1\u05e8",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterNames },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "timezone.gmtFormat",
                "GMT{0}\u200e" },
            { "timezone.hourFormat",
                "\u200e+HH:mm;-HH:mm\u200e" },
        };
    }
}
