package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_da extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1. kvt.",
            "2. kvt.",
            "3. kvt.",
            "4. kvt.",
        };

        final String[] sharedQuarterNames = {
            "1. kvartal",
            "2. kvartal",
            "3. kvartal",
            "4. kvartal",
        };

        final String[] sharedDatePatterns = {
            "EEEE d. MMMM y GGGG",
            "d. MMMM y GGGG",
            "d. MMM y GGGG",
            "d/M/y",
        };

        final String[] sharedDayAbbreviations = {
            "s\u00f8n.",
            "man.",
            "tir.",
            "ons.",
            "tor.",
            "fre.",
            "l\u00f8r.",
        };

        final String[] sharedDayNames = {
            "s\u00f8ndag",
            "mandag",
            "tirsdag",
            "onsdag",
            "torsdag",
            "fredag",
            "l\u00f8rdag",
        };

        final String[] sharedDayNarrows = {
            "S",
            "M",
            "T",
            "O",
            "T",
            "F",
            "L",
        };

        final String[] sharedTimePatterns = {
            "HH.mm.ss zzzz",
            "HH.mm.ss z",
            "HH.mm.ss",
            "HH.mm",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d. MMMM y G",
            "d. MMMM y G",
            "d. MMM y G",
            "d/M/y",
        };

        final String[] sharedJavaTimeLongEras = {
            "f.Kr.",
            "e.Kr.",
        };

        final String[] sharedEras = {
            "Before R.O.C.",
            "Minguo",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "buddhistisk kalender" },
            { "calendarname.gregorian",
                "gregoriansk kalender" },
            { "calendarname.gregory",
                "gregoriansk kalender" },
            { "calendarname.islamic",
                "islamisk kalender" },
            { "calendarname.islamic-civil",
                "verdslig islamisk kalender" },
            { "calendarname.islamic-umalqura",
                "islamisk kalender (Umm al-Qura)" },
            { "calendarname.japanese",
                "japansk kalender" },
            { "calendarname.roc",
                "kalender for Republikken Kina" },
            { "field.dayperiod",
                "AM/PM" },
            { "field.era",
                "\u00e6ra" },
            { "field.hour",
                "time" },
            { "field.minute",
                "minut" },
            { "field.month",
                "m\u00e5ned" },
            { "field.second",
                "sekund" },
            { "field.week",
                "uge" },
            { "field.weekday",
                "ugedag" },
            { "field.year",
                "\u00e5r" },
            { "field.zone",
                "tidszone" },
            { "islamic.AmPmMarkers",
                new String[] {
                    "AM",
                    "PM",
                }
            },
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
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                sharedJavaTimeLongEras },
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
                    "jan.",
                    "feb.",
                    "mar.",
                    "apr.",
                    "maj",
                    "jun.",
                    "jul.",
                    "aug.",
                    "sep.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "januar",
                    "februar",
                    "marts",
                    "april",
                    "maj",
                    "juni",
                    "juli",
                    "august",
                    "september",
                    "oktober",
                    "november",
                    "december",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "A",
                    "M",
                    "J",
                    "J",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
            { "timezone.hourFormat",
                "+HH.mm;-HH.mm" },
        };
    }
}
