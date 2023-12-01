package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ca extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1T",
            "2T",
            "3T",
            "4T",
        };

        final String[] sharedQuarterNames = {
            "1r trimestre",
            "2n trimestre",
            "3r trimestre",
            "4t trimestre",
        };

        final String[] sharedAmPmMarkers = {
            "a. m.",
            "p. m.",
        };

        final String[] sharedDayAbbreviations = {
            "dg.",
            "dl.",
            "dt.",
            "dc.",
            "dj.",
            "dv.",
            "ds.",
        };

        final String[] sharedDayNames = {
            "diumenge",
            "dilluns",
            "dimarts",
            "dimecres",
            "dijous",
            "divendres",
            "dissabte",
        };

        final String[] sharedDayNarrows = {
            "dg",
            "dl",
            "dt",
            "dc",
            "dj",
            "dv",
            "ds",
        };

        final String[] sharedTimePatterns = {
            "H:mm:ss zzzz",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "eB",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM 'de' y G",
            "d MMMM 'de' y G",
            "d/M/y G",
            "d/M/yy GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "calendari budista" },
            { "calendarname.gregorian",
                "calendari gregori\u00e0" },
            { "calendarname.gregory",
                "calendari gregori\u00e0" },
            { "calendarname.islamic",
                "calendari musulm\u00e0" },
            { "calendarname.islamic-civil",
                "calendari civil isl\u00e0mic" },
            { "calendarname.japanese",
                "calendari japon\u00e8s" },
            { "calendarname.roc",
                "calendari de la Rep\u00fablica de Xina" },
            { "field.dayperiod",
                "a. m./p. m." },
            { "field.era",
                "era" },
            { "field.hour",
                "hora" },
            { "field.minute",
                "minut" },
            { "field.month",
                "mes" },
            { "field.second",
                "segon" },
            { "field.week",
                "setmana" },
            { "field.weekday",
                "dia de la setmana" },
            { "field.year",
                "any" },
            { "field.zone",
                "fus horari" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE d MMMM 'de' y GGGG",
                    "d MMMM 'de' y GGGG",
                    "d/M/y GGGG",
                    "d/M/yy G",
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
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                new String[] {
                    "EEEE, dd MMMM y G",
                    "d MMMM y G",
                    "d MMM y G",
                    "dd/MM/y GGGGG",
                }
            },
            { "java.time.buddhist.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.buddhist.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "abans de Crist",
                    "despr\u00e9s de Crist",
                }
            },
            { "java.time.roc.DatePatterns",
                new String[] {
                    "EEEE d MMMM 'de' y G",
                    "d MMMM 'de' y G",
                    "dd/MM/y G",
                    "dd/MM/y GGGGG",
                }
            },
            { "java.time.short.Eras",
                new String[] {
                    "aC",
                    "dC",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE d MMMM 'de' y GGGG",
                    "d MMMM 'de' y GGGG",
                    "dd/MM/y GGGG",
                    "dd/MM/y G",
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
                    "gen.",
                    "febr.",
                    "mar\u00e7",
                    "abr.",
                    "maig",
                    "juny",
                    "jul.",
                    "ag.",
                    "set.",
                    "oct.",
                    "nov.",
                    "des.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "de gener",
                    "de febrer",
                    "de mar\u00e7",
                    "d\u2019abril",
                    "de maig",
                    "de juny",
                    "de juliol",
                    "d\u2019agost",
                    "de setembre",
                    "d\u2019octubre",
                    "de novembre",
                    "de desembre",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "GN",
                    "FB",
                    "M\u00c7",
                    "AB",
                    "MG",
                    "JN",
                    "JL",
                    "AG",
                    "ST",
                    "OC",
                    "NV",
                    "DS",
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
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
        };
    }
}
