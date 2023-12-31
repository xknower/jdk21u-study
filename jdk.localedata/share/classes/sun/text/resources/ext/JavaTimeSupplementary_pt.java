package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_pt extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "T1",
            "T2",
            "T3",
            "T4",
        };

        final String[] sharedQuarterNames = {
            "1\u00ba trimestre",
            "2\u00ba trimestre",
            "3\u00ba trimestre",
            "4\u00ba trimestre",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d 'de' MMMM 'de' y GGGG",
            "d 'de' MMMM 'de' y GGGG",
            "dd/MM/y GGGG",
            "dd/MM/yy G",
        };

        final String[] sharedDayAbbreviations = {
            "dom",
            "seg",
            "ter",
            "qua",
            "qui",
            "sex",
            "s\u00e1b",
        };

        final String[] sharedDayNames = {
            "domingo",
            "segunda-feira",
            "ter\u00e7a-feira",
            "quarta-feira",
            "quinta-feira",
            "sexta-feira",
            "s\u00e1bado",
        };

        final String[] sharedDayNarrows = {
            "D",
            "S",
            "T",
            "Q",
            "Q",
            "S",
            "S",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d 'de' MMMM 'de' y G",
            "d 'de' MMMM 'de' y G",
            "dd/MM/y G",
            "dd/MM/yy GGGGG",
        };

        final String[] sharedEras = {
            "Antes de R.O.C.",
            "R.O.C.",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Calend\u00e1rio Budista" },
            { "calendarname.gregorian",
                "Calend\u00e1rio Gregoriano" },
            { "calendarname.gregory",
                "Calend\u00e1rio Gregoriano" },
            { "calendarname.islamic",
                "Calend\u00e1rio Isl\u00e2mico" },
            { "calendarname.islamic-civil",
                "Calend\u00e1rio Civil Isl\u00e2mico" },
            { "calendarname.japanese",
                "Calend\u00e1rio Japon\u00eas" },
            { "calendarname.roc",
                "Calend\u00e1rio da Rep\u00fablica da China" },
            { "field.dayperiod",
                "AM/PM" },
            { "field.era",
                "era" },
            { "field.hour",
                "hora" },
            { "field.minute",
                "minuto" },
            { "field.month",
                "m\u00eas" },
            { "field.second",
                "segundo" },
            { "field.week",
                "semana" },
            { "field.weekday",
                "dia da semana" },
            { "field.year",
                "ano" },
            { "field.zone",
                "fuso hor\u00e1rio" },
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
                new String[] {
                    "antes de Cristo",
                    "depois de Cristo",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "a.C.",
                    "d.C.",
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
            { "roc.Eras",
                sharedEras },
            { "roc.MonthAbbreviations",
                new String[] {
                    "jan",
                    "fev",
                    "mar",
                    "abr",
                    "mai",
                    "jun",
                    "jul",
                    "ago",
                    "set",
                    "out",
                    "nov",
                    "dez",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "janeiro",
                    "fevereiro",
                    "mar\u00e7o",
                    "abril",
                    "maio",
                    "junho",
                    "julho",
                    "agosto",
                    "setembro",
                    "outubro",
                    "novembro",
                    "dezembro",
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
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
        };
    }
}
