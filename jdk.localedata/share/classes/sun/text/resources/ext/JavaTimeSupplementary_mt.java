package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_mt extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "K1",
            "K2",
            "K3",
            "K4",
        };

        final String[] sharedQuarterNames = {
            "1el kwart",
            "2ni kwart",
            "3et kwart",
            "4ba\u2019 kwart",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d 'ta'\u2019 MMMM y GGGG",
            "d 'ta'\u2019 MMMM y GGGG",
            "dd MMM y GGGG",
            "dd/MM/y G",
        };

        final String[] sharedDayAbbreviations = {
            "\u0126ad",
            "Tne",
            "Tli",
            "Erb",
            "\u0126am",
            "\u0120im",
            "Sib",
        };

        final String[] sharedDayNames = {
            "Il-\u0126add",
            "It-Tnejn",
            "It-Tlieta",
            "L-Erbg\u0127a",
            "Il-\u0126amis",
            "Il-\u0120img\u0127a",
            "Is-Sibt",
        };

        final String[] sharedDayNarrows = {
            "\u0126",
            "T",
            "T",
            "E",
            "\u0126",
            "\u0120",
            "S",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d 'ta'\u2019 MMMM y G",
            "d 'ta'\u2019 MMMM y G",
            "dd MMM y G",
            "dd/MM/y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Kalendarju Buddist" },
            { "calendarname.gregorian",
                "Kalendarju Gregorjan" },
            { "calendarname.gregory",
                "Kalendarju Gregorjan" },
            { "calendarname.islamic",
                "Kalendarju I\u017clamiku" },
            { "calendarname.islamic-civil",
                "Kalendarju Islamiku-\u010aivili" },
            { "calendarname.japanese",
                "Kalendarju \u0120appuni\u017c" },
            { "field.era",
                "Epoka" },
            { "field.hour",
                "Sieg\u0127a" },
            { "field.minute",
                "Minuta" },
            { "field.month",
                "Xahar" },
            { "field.second",
                "Sekonda" },
            { "field.week",
                "\u0120img\u0127a" },
            { "field.weekday",
                "Jum tal-\u0120img\u0127a" },
            { "field.year",
                "Sena" },
            { "field.zone",
                "\u017bona" },
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
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "Qabel Kristu",
                    "Wara Kristu",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "QK",
                    "WK",
                }
            },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.MonthAbbreviations",
                new String[] {
                    "Jan",
                    "Fra",
                    "Mar",
                    "Apr",
                    "Mej",
                    "\u0120un",
                    "Lul",
                    "Aww",
                    "Set",
                    "Ott",
                    "Nov",
                    "Di\u010b",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "Jannar",
                    "Frar",
                    "Marzu",
                    "April",
                    "Mejju",
                    "\u0120unju",
                    "Lulju",
                    "Awwissu",
                    "Settembru",
                    "Ottubru",
                    "Novembru",
                    "Di\u010bembru",
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
                    "\u0120",
                    "L",
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
        };
    }
}
