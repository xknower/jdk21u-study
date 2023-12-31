package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_th extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "\u0e44\u0e15\u0e23\u0e21\u0e32\u0e2a 1",
            "\u0e44\u0e15\u0e23\u0e21\u0e32\u0e2a 2",
            "\u0e44\u0e15\u0e23\u0e21\u0e32\u0e2a 3",
            "\u0e44\u0e15\u0e23\u0e21\u0e32\u0e2a 4",
        };

        final String[] sharedAmPmMarkers = {
            "\u0e01\u0e48\u0e2d\u0e19\u0e40\u0e17\u0e35\u0e48\u0e22\u0e07",
            "\u0e2b\u0e25\u0e31\u0e07\u0e40\u0e17\u0e35\u0e48\u0e22\u0e07",
        };

        final String[] sharedDayAbbreviations = {
            "\u0e2d\u0e32.",
            "\u0e08.",
            "\u0e2d.",
            "\u0e1e.",
            "\u0e1e\u0e24.",
            "\u0e28.",
            "\u0e2a.",
        };

        final String[] sharedDayNames = {
            "\u0e27\u0e31\u0e19\u0e2d\u0e32\u0e17\u0e34\u0e15\u0e22\u0e4c",
            "\u0e27\u0e31\u0e19\u0e08\u0e31\u0e19\u0e17\u0e23\u0e4c",
            "\u0e27\u0e31\u0e19\u0e2d\u0e31\u0e07\u0e04\u0e32\u0e23",
            "\u0e27\u0e31\u0e19\u0e1e\u0e38\u0e18",
            "\u0e27\u0e31\u0e19\u0e1e\u0e24\u0e2b\u0e31\u0e2a\u0e1a\u0e14\u0e35",
            "\u0e27\u0e31\u0e19\u0e28\u0e38\u0e01\u0e23\u0e4c",
            "\u0e27\u0e31\u0e19\u0e40\u0e2a\u0e32\u0e23\u0e4c",
        };

        final String[] sharedDayNarrows = {
            "\u0e2d\u0e32",
            "\u0e08",
            "\u0e2d",
            "\u0e1e",
            "\u0e1e\u0e24",
            "\u0e28",
            "\u0e2a",
        };

        final String[] sharedEras = {
            "",
            "\u0e2e.\u0e28.",
        };

        final String[] sharedTimePatterns = {
            "H \u0e19\u0e32\u0e2c\u0e34\u0e01\u0e32 mm \u0e19\u0e32\u0e17\u0e35 ss \u0e27\u0e34\u0e19\u0e32\u0e17\u0e35 zzzz",
            "H \u0e19\u0e32\u0e2c\u0e34\u0e01\u0e32 mm \u0e19\u0e32\u0e17\u0e35 ss \u0e27\u0e34\u0e19\u0e32\u0e17\u0e35 z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeLongEras = {
            "\u0e04.\u0e28.",
            "\u0e40\u0e21\u0e08\u0e34",
            "\u0e17\u0e30\u0e2d\u0e34\u0e42\u0e0a",
            "\u0e42\u0e0a\u0e27\u0e30",
            "\u0e40\u0e2e\u0e40\u0e0b",
            "\u0e40\u0e23\u0e27\u0e30",
        };

        final String[] sharedShortEras = {
            "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e44\u0e15\u0e49\u0e2b\u0e27\u0e31\u0e19",
            "\u0e44\u0e15\u0e49\u0e2b\u0e27\u0e31\u0e19",
        };

        final String[] sharedMonthNarrows = {
            "\u0e21.\u0e04.",
            "\u0e01.\u0e1e.",
            "\u0e21\u0e35.\u0e04.",
            "\u0e40\u0e21.\u0e22.",
            "\u0e1e.\u0e04.",
            "\u0e21\u0e34.\u0e22.",
            "\u0e01.\u0e04.",
            "\u0e2a.\u0e04.",
            "\u0e01.\u0e22.",
            "\u0e15.\u0e04.",
            "\u0e1e.\u0e22.",
            "\u0e18.\u0e04.",
            "",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterNames },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e1e\u0e38\u0e17\u0e18" },
            { "calendarname.gregorian",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e40\u0e01\u0e23\u0e01\u0e2d\u0e40\u0e23\u0e35\u0e22\u0e19" },
            { "calendarname.gregory",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e40\u0e01\u0e23\u0e01\u0e2d\u0e40\u0e23\u0e35\u0e22\u0e19" },
            { "calendarname.islamic",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e2d\u0e34\u0e2a\u0e25\u0e32\u0e21" },
            { "calendarname.islamic-civil",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e2d\u0e34\u0e2a\u0e25\u0e32\u0e21\u0e0b\u0e35\u0e27\u0e34\u0e25" },
            { "calendarname.islamic-umalqura",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e2d\u0e34\u0e2a\u0e25\u0e32\u0e21 (\u0e2d\u0e38\u0e21\u0e21\u0e4c\u0e2d\u0e31\u0e25\u0e01\u0e38\u0e23\u0e32)" },
            { "calendarname.japanese",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e0d\u0e35\u0e48\u0e1b\u0e38\u0e48\u0e19" },
            { "calendarname.roc",
                "\u0e1b\u0e0f\u0e34\u0e17\u0e34\u0e19\u0e44\u0e15\u0e49\u0e2b\u0e27\u0e31\u0e19" },
            { "field.dayperiod",
                "\u0e0a\u0e48\u0e27\u0e07\u0e27\u0e31\u0e19" },
            { "field.era",
                "\u0e2a\u0e21\u0e31\u0e22" },
            { "field.hour",
                "\u0e0a\u0e31\u0e48\u0e27\u0e42\u0e21\u0e07" },
            { "field.minute",
                "\u0e19\u0e32\u0e17\u0e35" },
            { "field.month",
                "\u0e40\u0e14\u0e37\u0e2d\u0e19" },
            { "field.second",
                "\u0e27\u0e34\u0e19\u0e32\u0e17\u0e35" },
            { "field.week",
                "\u0e2a\u0e31\u0e1b\u0e14\u0e32\u0e2b\u0e4c" },
            { "field.weekday",
                "\u0e27\u0e31\u0e19\u0e43\u0e19\u0e2a\u0e31\u0e1b\u0e14\u0e32\u0e2b\u0e4c" },
            { "field.year",
                "\u0e1b\u0e35" },
            { "field.zone",
                "\u0e40\u0e02\u0e15\u0e40\u0e27\u0e25\u0e32" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE\u0e17\u0e35\u0e48 d MMMM GGGG y",
                    "d MMMM GGGG y",
                    "d MMM GGGG y",
                    "d/M/y GGGG",
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
                    "\u0e21\u0e38\u0e2e\u0e31\u0e23.",
                    "\u0e40\u0e28\u0e32\u0e30.",
                    "\u0e23\u0e2d\u0e1a\u0e35 I",
                    "\u0e23\u0e2d\u0e1a\u0e35 II",
                    "\u0e08\u0e38\u0e21\u0e32\u0e14\u0e32 I",
                    "\u0e08\u0e38\u0e21\u0e32\u0e14\u0e32 II",
                    "\u0e40\u0e23\u0e32\u0e30.",
                    "\u0e0a\u0e30\u0e2d\u0e4c.",
                    "\u0e40\u0e23\u0e32\u0e30\u0e21\u0e30.",
                    "\u0e40\u0e0a\u0e32\u0e27.",
                    "\u0e0b\u0e38\u0e25\u0e01\u0e34\u0e2d\u0e3a.",
                    "\u0e0b\u0e38\u0e25\u0e2b\u0e34\u0e08.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "\u0e21\u0e38\u0e2e\u0e30\u0e23\u0e4c\u0e23\u0e2d\u0e21",
                    "\u0e0b\u0e2d\u0e1f\u0e32\u0e23\u0e4c",
                    "\u0e23\u0e2d\u0e1a\u0e35 I",
                    "\u0e23\u0e2d\u0e1a\u0e35 II",
                    "\u0e08\u0e38\u0e21\u0e32\u0e14\u0e32 I",
                    "\u0e08\u0e38\u0e21\u0e32\u0e14\u0e32 II",
                    "\u0e23\u0e2d\u0e08\u0e31\u0e1a",
                    "\u0e0a\u0e30\u0e2d\u0e30\u0e1a\u0e32\u0e19",
                    "\u0e23\u0e2d\u0e21\u0e30\u0e14\u0e2d\u0e19",
                    "\u0e40\u0e0a\u0e32\u0e27\u0e31\u0e25",
                    "\u0e0b\u0e38\u0e25\u0e01\u0e34\u0e2d\u0e3a\u0e14\u0e30\u0e2e\u0e3a",
                    "\u0e0b\u0e38\u0e25\u0e2b\u0e34\u0e08\u0e0d\u0e30\u0e2e\u0e3a",
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
                new String[] {
                    "",
                    "\u0e2e\u0e34\u0e08\u0e40\u0e23\u0e32\u0e30\u0e2b\u0e4c\u0e28\u0e31\u0e01\u0e23\u0e32\u0e0a",
                }
            },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "islamic.narrow.Eras",
                sharedEras },
            { "islamic.short.Eras",
                sharedEras },
            { "java.time.buddhist.DatePatterns",
                new String[] {
                    "EEEE\u0e17\u0e35\u0e48 d MMMM G y",
                    "d MMMM y",
                    "d MMM y",
                    "d/M/yy",
                }
            },
            { "java.time.buddhist.long.Eras",
                new String[] {
                    "BC",
                    "\u0e1e\u0e38\u0e17\u0e18\u0e28\u0e31\u0e01\u0e23\u0e32\u0e0a",
                }
            },
            { "java.time.buddhist.short.Eras",
                new String[] {
                    "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2a\u0e15\u0e4c\u0e01\u0e32\u0e25\u0e17\u0e35\u0e48",
                    "\u0e1e.\u0e28.",
                }
            },
            { "java.time.islamic.DatePatterns",
                new String[] {
                    "EEEE\u0e17\u0e35\u0e48 d MMMM G y",
                    "d MMMM G y",
                    "d MMM G y",
                    "d/M/y G",
                }
            },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE\u0e17\u0e35\u0e48 d MMMM \u0e1b\u0e35G\u0e17\u0e35\u0e48 y",
                    "d MMMM \u0e1b\u0e35G y",
                    "d MMM G y",
                    "d/M/yy G",
                }
            },
            { "java.time.japanese.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.japanese.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.long.Eras",
                new String[] {
                    "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2a\u0e15\u0e4c\u0e28\u0e31\u0e01\u0e23\u0e32\u0e0a",
                    "\u0e04\u0e23\u0e34\u0e2a\u0e15\u0e4c\u0e28\u0e31\u0e01\u0e23\u0e32\u0e0a",
                }
            },
            { "java.time.roc.DatePatterns",
                new String[] {
                    "EEEE\u0e17\u0e35\u0e48 d MMMM \u0e1b\u0e35G\u0e17\u0e35\u0e48 y",
                    "d MMMM \u0e1b\u0e35G y",
                    "d MMM G y",
                    "d/M/y G",
                }
            },
            { "java.time.short.Eras",
                new String[] {
                    "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2a\u0e15\u0e4c\u0e01\u0e32\u0e25\u0e17\u0e35\u0e48",
                    "\u0e04.\u0e28.",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE\u0e17\u0e35\u0e48 d MMMM \u0e1b\u0e35GGGG\u0e17\u0e35\u0e48 y",
                    "d MMMM \u0e1b\u0e35GGGG y",
                    "d MMM GGGG y",
                    "d/M/y GGGG",
                }
            },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedShortEras },
            { "roc.MonthAbbreviations",
                sharedMonthNarrows },
            { "roc.MonthNames",
                new String[] {
                    "\u0e21\u0e01\u0e23\u0e32\u0e04\u0e21",
                    "\u0e01\u0e38\u0e21\u0e20\u0e32\u0e1e\u0e31\u0e19\u0e18\u0e4c",
                    "\u0e21\u0e35\u0e19\u0e32\u0e04\u0e21",
                    "\u0e40\u0e21\u0e29\u0e32\u0e22\u0e19",
                    "\u0e1e\u0e24\u0e29\u0e20\u0e32\u0e04\u0e21",
                    "\u0e21\u0e34\u0e16\u0e38\u0e19\u0e32\u0e22\u0e19",
                    "\u0e01\u0e23\u0e01\u0e0e\u0e32\u0e04\u0e21",
                    "\u0e2a\u0e34\u0e07\u0e2b\u0e32\u0e04\u0e21",
                    "\u0e01\u0e31\u0e19\u0e22\u0e32\u0e22\u0e19",
                    "\u0e15\u0e38\u0e25\u0e32\u0e04\u0e21",
                    "\u0e1e\u0e24\u0e28\u0e08\u0e34\u0e01\u0e32\u0e22\u0e19",
                    "\u0e18\u0e31\u0e19\u0e27\u0e32\u0e04\u0e21",
                    "",
                }
            },
            { "roc.MonthNarrows",
                sharedMonthNarrows },
            { "roc.QuarterAbbreviations",
                sharedQuarterNames },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.long.Eras",
                sharedShortEras },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.Eras",
                sharedShortEras },
            { "roc.short.Eras",
                sharedShortEras },
        };
    }
}
