package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_cs extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "1. \u010dtvrtlet\u00ed",
            "2. \u010dtvrtlet\u00ed",
            "3. \u010dtvrtlet\u00ed",
            "4. \u010dtvrtlet\u00ed",
        };

        final String[] sharedAmPmMarkers = {
            "dop.",
            "odp.",
        };

        final String[] sharedDatePatterns = {
            "EEEE d. MMMM y GGGG",
            "d. MMMM y GGGG",
            "d. M. y GGGG",
            "dd.MM.yy G",
        };

        final String[] sharedDayAbbreviations = {
            "ne",
            "po",
            "\u00fat",
            "st",
            "\u010dt",
            "p\u00e1",
            "so",
        };

        final String[] sharedDayNames = {
            "ned\u011ble",
            "pond\u011bl\u00ed",
            "\u00fater\u00fd",
            "st\u0159eda",
            "\u010dtvrtek",
            "p\u00e1tek",
            "sobota",
        };

        final String[] sharedDayNarrows = {
            "N",
            "P",
            "\u00da",
            "S",
            "\u010c",
            "P",
            "S",
        };

        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedTimePatterns = {
            "H:mm:ss zzzz",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d. MMMM y G",
            "d. MMMM y G",
            "d. M. y G",
            "dd.MM.yy GGGGG",
        };

        final String[] sharedEras = {
            "P\u0159ed R. O. C.",
            "",
        };

        return new Object[][] {
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Buddhistick\u00fd kalend\u00e1\u0159" },
            { "calendarname.gregorian",
                "Gregori\u00e1nsk\u00fd kalend\u00e1\u0159" },
            { "calendarname.gregory",
                "Gregori\u00e1nsk\u00fd kalend\u00e1\u0159" },
            { "calendarname.islamic",
                "Muslimsk\u00fd kalend\u00e1\u0159" },
            { "calendarname.islamic-civil",
                "Muslimsk\u00fd ob\u010dansk\u00fd kalend\u00e1\u0159" },
            { "calendarname.japanese",
                "Japonsk\u00fd kalend\u00e1\u0159" },
            { "calendarname.roc",
                "Kalend\u00e1\u0159 \u010c\u00ednsk\u00e9 republiky" },
            { "field.dayperiod",
                "\u010d\u00e1st dne" },
            { "field.era",
                "letopo\u010det" },
            { "field.hour",
                "hodina" },
            { "field.minute",
                "minuta" },
            { "field.month",
                "m\u011bs\u00edc" },
            { "field.second",
                "sekunda" },
            { "field.week",
                "t\u00fdden" },
            { "field.weekday",
                "den v t\u00fddnu" },
            { "field.year",
                "rok" },
            { "field.zone",
                "\u010dasov\u00e9 p\u00e1smo" },
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
                new String[] {
                    "EEEE, d. MMMM y G",
                    "d. MMMM y G",
                    "d. M. y G",
                    "dd.MM.yy GGGGG",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "p\u0159. n. l.",
                    "n. l.",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "p\u0159.Kr.",
                    "po Kr.",
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
                    "led",
                    "\u00fano",
                    "b\u0159e",
                    "dub",
                    "kv\u011b",
                    "\u010dvn",
                    "\u010dvc",
                    "srp",
                    "z\u00e1\u0159",
                    "\u0159\u00edj",
                    "lis",
                    "pro",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "ledna",
                    "\u00fanora",
                    "b\u0159ezna",
                    "dubna",
                    "kv\u011btna",
                    "\u010dervna",
                    "\u010dervence",
                    "srpna",
                    "z\u00e1\u0159\u00ed",
                    "\u0159\u00edjna",
                    "listopadu",
                    "prosince",
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
                "+H:mm;-H:mm" },
        };
    }
}
