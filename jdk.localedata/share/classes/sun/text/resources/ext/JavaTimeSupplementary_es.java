package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_es extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "T1",
            "T2",
            "T3",
            "T4",
        };

        final String[] sharedQuarterNames = {
            "1.er trimestre",
            "2.\u00ba trimestre",
            "3.er trimestre",
            "4.\u00ba trimestre",
        };

        final String[] sharedAmPmMarkers = {
            "a. m.",
            "p. m.",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d 'de' MMMM 'de' y GGGG",
            "d 'de' MMMM 'de' y GGGG",
            "d/M/y GGGG",
            "d/M/yy GGGG",
        };

        final String[] sharedDayAbbreviations = {
            "dom.",
            "lun.",
            "mar.",
            "mi\u00e9.",
            "jue.",
            "vie.",
            "s\u00e1b.",
        };

        final String[] sharedDayNames = {
            "domingo",
            "lunes",
            "martes",
            "mi\u00e9rcoles",
            "jueves",
            "viernes",
            "s\u00e1bado",
        };

        final String[] sharedDayNarrows = {
            "D",
            "L",
            "M",
            "X",
            "J",
            "V",
            "S",
        };

        final String[] sharedTimePatterns = {
            "H:mm:ss (zzzz)",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d 'de' MMMM 'de' y G",
            "d 'de' MMMM 'de' y G",
            "d/M/y G",
            "d/M/yy G",
        };

        final String[] sharedEras = {
            "antes de R.O.C.",
            "",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "calendario budista" },
            { "calendarname.gregorian",
                "calendario gregoriano" },
            { "calendarname.gregory",
                "calendario gregoriano" },
            { "calendarname.islamic",
                "calendario isl\u00e1mico" },
            { "calendarname.islamic-civil",
                "calendario civil isl\u00e1mico" },
            { "calendarname.japanese",
                "calendario japon\u00e9s" },
            { "calendarname.roc",
                "calendario de la Rep\u00fablica de China" },
            { "field.dayperiod",
                "a. m./p. m." },
            { "field.era",
                "era" },
            { "field.hour",
                "hora" },
            { "field.minute",
                "minuto" },
            { "field.month",
                "mes" },
            { "field.second",
                "segundo" },
            { "field.week",
                "semana" },
            { "field.weekday",
                "d\u00eda de la semana" },
            { "field.year",
                "a\u00f1o" },
            { "field.zone",
                "zona horaria" },
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
                    "EEEE, d 'de' MMMM 'de' y G",
                    "d 'de' MMMM 'de' y G",
                    "dd/MM/y G",
                    "dd/MM/yy GGGGG",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "antes de Cristo",
                    "despu\u00e9s de Cristo",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "antes de Cristo",
                    "anno D\u00f3mini",
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
                    "ene.",
                    "feb.",
                    "mar.",
                    "abr.",
                    "may.",
                    "jun.",
                    "jul.",
                    "ago.",
                    "sept.",
                    "oct.",
                    "nov.",
                    "dic.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "enero",
                    "febrero",
                    "marzo",
                    "abril",
                    "mayo",
                    "junio",
                    "julio",
                    "agosto",
                    "septiembre",
                    "octubre",
                    "noviembre",
                    "diciembre",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "E",
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
        };
    }
}
