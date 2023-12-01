package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_hr extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1kv",
            "2kv",
            "3kv",
            "4kv",
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
            "AM",
            "PM",
        };

        final String[] sharedDayAbbreviations = {
            "ned",
            "pon",
            "uto",
            "sri",
            "\u010det",
            "pet",
            "sub",
        };

        final String[] sharedDayNames = {
            "nedjelja",
            "ponedjeljak",
            "utorak",
            "srijeda",
            "\u010detvrtak",
            "petak",
            "subota",
        };

        final String[] sharedDayNarrows = {
            "N",
            "P",
            "U",
            "S",
            "\u010c",
            "P",
            "S",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d. MMMM y. G",
            "d. MMMM y. G",
            "d. MMM y. G",
            "dd.MM.y. GGGGG",
        };

        final String[] sharedJavaTimeDatePatterns2 = {
            "EEEE, d. MMMM y. G",
            "d. MMMM y. G",
            "d. M. y. G",
            "d.M.y. GGGGG",
        };

        final String[] sharedEras = {
            "prije R.O.C.",
            "R.O.C.",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "calendarname.buddhist",
                "budisti\u010dki kalendar" },
            { "calendarname.gregorian",
                "gregorijanski kalendar" },
            { "calendarname.gregory",
                "gregorijanski kalendar" },
            { "calendarname.islamic",
                "islamski kalendar" },
            { "calendarname.islamic-civil",
                "islamski civilni kalendar" },
            { "calendarname.japanese",
                "japanski kalendar" },
            { "calendarname.roc",
                "kalendar Republike Kine" },
            { "field.dayperiod",
                "AM/PM" },
            { "field.era",
                "era" },
            { "field.hour",
                "sat" },
            { "field.minute",
                "minuta" },
            { "field.month",
                "mjesec" },
            { "field.second",
                "sekunda" },
            { "field.week",
                "tjedan" },
            { "field.weekday",
                "dan u tjednu" },
            { "field.year",
                "godina" },
            { "field.zone",
                "vremenska zona" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE, d. MMMM y. GGGG",
                    "d. MMMM y. GGGG",
                    "d. M. y. GGGG",
                    "d.M.y. G",
                }
            },
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
            { "islamic.QuarterNarrows",
                sharedQuarterNarrows },
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
                sharedJavaTimeDatePatterns2 },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns2 },
            { "java.time.japanese.long.Eras",
                new String[] {
                    "poslije Krista",
                    "Meiji",
                    "Taish\u014d",
                    "Sh\u014dwa",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.japanese.short.Eras",
                new String[] {
                    "p. Kr.",
                    "Meiji",
                    "Taish\u014d",
                    "Sh\u014dwa",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "prije Krista",
                    "poslije Krista",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "Prije Krista",
                    "Poslije Krista",
                }
            },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE, d. MMMM y. GGGG",
                    "d. MMMM y. GGGG",
                    "d. MMM y. GGGG",
                    "dd.MM.y. G",
                }
            },
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
                    "sij",
                    "velj",
                    "o\u017eu",
                    "tra",
                    "svi",
                    "lip",
                    "srp",
                    "kol",
                    "ruj",
                    "lis",
                    "stu",
                    "pro",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "sije\u010dnja",
                    "velja\u010de",
                    "o\u017eujka",
                    "travnja",
                    "svibnja",
                    "lipnja",
                    "srpnja",
                    "kolovoza",
                    "rujna",
                    "listopada",
                    "studenoga",
                    "prosinca",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "1.",
                    "2.",
                    "3.",
                    "4.",
                    "5.",
                    "6.",
                    "7.",
                    "8.",
                    "9.",
                    "10.",
                    "11.",
                    "12.",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.QuarterNarrows",
                sharedQuarterNarrows },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
            { "timezone.hourFormat",
                "+HH:mm; -HH:mm" },
        };
    }
}
