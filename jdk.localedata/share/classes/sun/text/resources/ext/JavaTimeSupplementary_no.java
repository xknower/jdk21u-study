package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_no extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "K1",
            "K2",
            "K3",
            "K4",
        };

        final String[] sharedQuarterNames = {
            "1. kvartal",
            "2. kvartal",
            "3. kvartal",
            "4. kvartal",
        };

        final String[] sharedQuarterNarrows = {
            "1.",
            "2.",
            "3.",
            "4.",
        };

        final String[] sharedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedDatePatterns = {
            "EEEE d. MMMM y GGGG",
            "d. MMMM y GGGG",
            "d. MMM y GGGG",
            "d.M. y GGGG",
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
            "d.M. y G",
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
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "calendarname.buddhist",
                "buddhistisk kalender" },
            { "calendarname.gregorian",
                "gregoriansk kalender" },
            { "calendarname.gregory",
                "gregoriansk kalender" },
            { "calendarname.islamic",
                "islamsk kalender" },
            { "calendarname.islamic-civil",
                "islamsk kalender (tabell, sivil)" },
            { "calendarname.islamic-umalqura",
                "islamsk kalender (Umm al-Qura)" },
            { "calendarname.japanese",
                "japansk kalender" },
            { "calendarname.roc",
                "kalender for Republikken Kina" },
            { "field.dayperiod",
                "AM/PM" },
            { "field.era",
                "tidsalder" },
            { "field.hour",
                "time" },
            { "field.minute",
                "minutt" },
            { "field.month",
                "m\u00e5ned" },
            { "field.second",
                "sekund" },
            { "field.week",
                "uke" },
            { "field.weekday",
                "ukedag" },
            { "field.year",
                "\u00e5r" },
            { "field.zone",
                "tidssone" },
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
            { "islamic.MonthAbbreviations",
                new String[] {
                    "muh.",
                    "saf.",
                    "rab. I",
                    "rab. II",
                    "jum. I",
                    "jum. II",
                    "raj.",
                    "sha.",
                    "ram.",
                    "shaw.",
                    "dhu\u02bbl-q.",
                    "dhu\u02bbl-h.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "muharram",
                    "safar",
                    "rabi\u02bb I",
                    "rabi\u02bb II",
                    "jumada I",
                    "jumada II",
                    "rajab",
                    "sha\u02bbban",
                    "ramadan",
                    "shawwal",
                    "dhu\u02bbl-qi\u02bbdah",
                    "dhu\u02bbl-hijjah",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.QuarterNarrows",
                sharedQuarterNarrows },
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
                    "EEEE d. MMMM y G",
                    "d. MMMM y G",
                    "d. MMM y G",
                    "d.M y G",
                }
            },
            { "java.time.japanese.long.Eras",
                new String[] {
                    "etter Kristus",
                    "Meiji",
                    "Taish\u014d",
                    "Sh\u014dwa",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.japanese.short.Eras",
                new String[] {
                    "e.Kr.",
                    "M",
                    "T",
                    "S",
                    "H",
                    "R",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "f\u00f8r Kristus",
                    "etter Kristus",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "f.Kr.",
                    "e.Kr.",
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
                    "jan.",
                    "feb.",
                    "mar.",
                    "apr.",
                    "mai",
                    "jun.",
                    "jul.",
                    "aug.",
                    "sep.",
                    "okt.",
                    "nov.",
                    "des.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "januar",
                    "februar",
                    "mars",
                    "april",
                    "mai",
                    "juni",
                    "juli",
                    "august",
                    "september",
                    "oktober",
                    "november",
                    "desember",
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
            { "roc.QuarterNarrows",
                sharedQuarterNarrows },
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
            { "timezone.hourFormat",
                "+HH.mm;-HH.mm" },
        };
    }
}
