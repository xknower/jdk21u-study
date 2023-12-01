package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_sv extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "K1",
            "K2",
            "K3",
            "K4",
        };

        final String[] sharedQuarterNames = {
            "1:a kvartalet",
            "2:a kvartalet",
            "3:e kvartalet",
            "4:e kvartalet",
        };

        final String[] sharedAmPmMarkers = {
            "fm",
            "em",
        };

        final String[] sharedDatePatterns = {
            "EEEE d MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "GGGG y-MM-dd",
        };

        final String[] sharedDayAbbreviations = {
            "s\u00f6n",
            "m\u00e5n",
            "tis",
            "ons",
            "tors",
            "fre",
            "l\u00f6r",
        };

        final String[] sharedDayNames = {
            "s\u00f6ndag",
            "m\u00e5ndag",
            "tisdag",
            "onsdag",
            "torsdag",
            "fredag",
            "l\u00f6rdag",
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
            "'kl'. HH:mm:ss zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "G y-MM-dd",
        };

        final String[] sharedJavaTimeLongEras = {
            "f\u00f6re Kristus",
            "efter Kristus",
        };

        final String[] sharedEras = {
            "f\u00f6re R.K.",
            "R.K.",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "buddistisk kalender" },
            { "calendarname.gregorian",
                "gregoriansk kalender" },
            { "calendarname.gregory",
                "gregoriansk kalender" },
            { "calendarname.islamic",
                "islamisk kalender" },
            { "calendarname.islamic-civil",
                "islamisk civil kalender" },
            { "calendarname.islamic-umalqura",
                "islamisk kalender, Umm al-Qura" },
            { "calendarname.japanese",
                "japansk kalender" },
            { "calendarname.roc",
                "kinesiska republikens kalender" },
            { "field.dayperiod",
                "fm/em" },
            { "field.era",
                "era" },
            { "field.hour",
                "timme" },
            { "field.minute",
                "minut" },
            { "field.month",
                "m\u00e5nad" },
            { "field.second",
                "sekund" },
            { "field.week",
                "vecka" },
            { "field.weekday",
                "veckodag" },
            { "field.year",
                "\u00e5r" },
            { "field.zone",
                "tidszon" },
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
            { "islamic.MonthNames",
                new String[] {
                    "muharram",
                    "safar",
                    "rabi\u2019 al-awwal",
                    "rabi\u2019 al-akhir",
                    "jumada-l-ula",
                    "jumada-l-akhira",
                    "rajab",
                    "sha\u2019ban",
                    "ramadan",
                    "shawwal",
                    "dhu-l-ga\u2019da",
                    "dhu-l-hijja",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
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
            { "java.time.japanese.long.Eras",
                new String[] {
                    "efter Kristus",
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
                    "Meiji",
                    "Taish\u014d",
                    "Sh\u014dwa",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                sharedJavaTimeLongEras },
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
                    "mars",
                    "apr.",
                    "maj",
                    "juni",
                    "juli",
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
                    "januari",
                    "februari",
                    "mars",
                    "april",
                    "maj",
                    "juni",
                    "juli",
                    "augusti",
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
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
            { "timezone.hourFormat",
                "+HH:mm;\u2212HH:mm" },
        };
    }
}
