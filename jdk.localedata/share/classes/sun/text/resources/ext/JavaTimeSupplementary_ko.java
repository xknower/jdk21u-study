package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ko extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1\ubd84\uae30",
            "2\ubd84\uae30",
            "3\ubd84\uae30",
            "4\ubd84\uae30",
        };

        final String[] sharedQuarterNames = {
            "\uc81c 1/4\ubd84\uae30",
            "\uc81c 2/4\ubd84\uae30",
            "\uc81c 3/4\ubd84\uae30",
            "\uc81c 4/4\ubd84\uae30",
        };

        final String[] sharedAmPmMarkers = {
            "\uc624\uc804",
            "\uc624\ud6c4",
        };

        final String[] sharedDatePatterns = {
            "GGGG y\ub144 M\uc6d4 d\uc77c EEEE",
            "GGGG y\ub144 M\uc6d4 d\uc77c",
            "GGGG y. M. d.",
            "GGGG y. M. d.",
        };

        final String[] sharedDayNarrows = {
            "\uc77c",
            "\uc6d4",
            "\ud654",
            "\uc218",
            "\ubaa9",
            "\uae08",
            "\ud1a0",
        };

        final String[] sharedDayNames = {
            "\uc77c\uc694\uc77c",
            "\uc6d4\uc694\uc77c",
            "\ud654\uc694\uc77c",
            "\uc218\uc694\uc77c",
            "\ubaa9\uc694\uc77c",
            "\uae08\uc694\uc77c",
            "\ud1a0\uc694\uc77c",
        };

        final String[] sharedTimePatterns = {
            "a h\uc2dc m\ubd84 s\ucd08 zzzz",
            "a h\uc2dc m\ubd84 s\ucd08 z",
            "a h:mm:ss",
            "a h:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y\ub144 M\uc6d4 d\uc77c EEEE",
            "G y\ub144 M\uc6d4 d\uc77c",
            "G y. M. d.",
            "G y. M. d.",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "\ubd88\uae30",
        };

        final String[] sharedJavaTimeShortEras = {
            "\uc11c\uae30",
            "\uba54\uc774\uc9c0",
            "\ub2e4\uc774\uc1fc",
            "\uc1fc\uc640",
            "\ud5e4\uc774\uc138\uc774",
            "\ub808\uc774\uc640",
        };

        final String[] sharedJavaTimeShortEras2 = {
            "\uae30\uc6d0\uc804",
            "\uc11c\uae30",
        };

        final String[] sharedEras = {
            "\uc911\ud654\ubbfc\uad6d\uc804",
            "\uc911\ud654\ubbfc\uad6d",
        };

        final String[] sharedMonthNames = {
            "1\uc6d4",
            "2\uc6d4",
            "3\uc6d4",
            "4\uc6d4",
            "5\uc6d4",
            "6\uc6d4",
            "7\uc6d4",
            "8\uc6d4",
            "9\uc6d4",
            "10\uc6d4",
            "11\uc6d4",
            "12\uc6d4",
            "",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\ubd88\uad50\ub825" },
            { "calendarname.gregorian",
                "\ud0dc\uc591\ub825" },
            { "calendarname.gregory",
                "\ud0dc\uc591\ub825" },
            { "calendarname.islamic",
                "\uc774\uc2ac\ub78c\ub825" },
            { "calendarname.islamic-civil",
                "\uc774\uc2ac\ub78c \uc0c1\uc6a9\ub825" },
            { "calendarname.japanese",
                "\uc77c\ubcf8\ub825" },
            { "calendarname.roc",
                "\ub300\ub9cc\ub825" },
            { "field.dayperiod",
                "\uc624\uc804/\uc624\ud6c4" },
            { "field.era",
                "\uc5f0\ud638" },
            { "field.hour",
                "\uc2dc" },
            { "field.minute",
                "\ubd84" },
            { "field.month",
                "\uc6d4" },
            { "field.second",
                "\ucd08" },
            { "field.week",
                "\uc8fc" },
            { "field.weekday",
                "\uc694\uc77c" },
            { "field.year",
                "\ub144" },
            { "field.zone",
                "\uc2dc\uac04\ub300" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayAbbreviations",
                sharedDayNarrows },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.MonthNames",
                new String[] {
                    "\ubb34\ud558\ub78c",
                    "\uc0ac\ud30c\ub974",
                    "\ub77c\ube44 \uc54c \uc544\uc648",
                    "\ub77c\ube44 \uc54c \uc384\ub2c8",
                    "\uc8fc\ub9c8\ub2e4 \uc54c \uc544\uc648",
                    "\uc8fc\ub9c8\ub2e4 \uc54c \uc384\ub2c8",
                    "\ub77c\uc7a1",
                    "\uc250\uc544\ubc18",
                    "\ub77c\ub9c8\ub2e8",
                    "\uc250\uc648",
                    "\ub4c0 \uc54c \uae4c\ub2e4",
                    "\ub4c0 \uc54c \ud788\uc790",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.narrow.AmPmMarkers",
                new String[] {
                    "AM",
                    "PM",
                }
            },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.buddhist.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.buddhist.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "G y\ub144 M\uc6d4 d\uc77c EEEE",
                    "G y\ub144 M\uc6d4 d\uc77c",
                    "G y. M. d",
                    "G y. M. d",
                }
            },
            { "java.time.japanese.long.Eras",
                sharedJavaTimeShortEras },
            { "java.time.japanese.short.Eras",
                sharedJavaTimeShortEras },
            { "java.time.long.Eras",
                sharedJavaTimeShortEras2 },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                sharedJavaTimeShortEras2 },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                sharedDayNarrows },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedEras },
            { "roc.MonthAbbreviations",
                sharedMonthNames },
            { "roc.MonthNames",
                sharedMonthNames },
            { "roc.MonthNarrows",
                sharedMonthNames },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
        };
    }
}
