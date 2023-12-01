package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_it extends OpenListResourceBundle {
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
            "EEEE d MMMM y GGGG",
            "dd MMMM y GGGG",
            "dd MMM y GGGG",
            "dd/MM/yy G",
        };

        final String[] sharedDayAbbreviations = {
            "dom",
            "lun",
            "mar",
            "mer",
            "gio",
            "ven",
            "sab",
        };

        final String[] sharedDayNames = {
            "domenica",
            "luned\u00ec",
            "marted\u00ec",
            "mercoled\u00ec",
            "gioved\u00ec",
            "venerd\u00ec",
            "sabato",
        };

        final String[] sharedDayNarrows = {
            "D",
            "L",
            "M",
            "M",
            "G",
            "V",
            "S",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "m.",
            "p.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "dd MMMM y G",
            "dd MMM y G",
            "dd/MM/yy GGGGG",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "EB",
        };

        final String[] sharedEras = {
            "Prima di R.O.C.",
            "Minguo",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Calendario buddista" },
            { "calendarname.gregorian",
                "Calendario gregoriano" },
            { "calendarname.gregory",
                "Calendario gregoriano" },
            { "calendarname.islamic",
                "Calendario islamico" },
            { "calendarname.islamic-civil",
                "calendario civile islamico" },
            { "calendarname.islamic-umalqura",
                "Calendario islamico (Umm al-Qura)" },
            { "calendarname.japanese",
                "Calendario giapponese" },
            { "calendarname.roc",
                "Calendario Minguo" },
            { "field.dayperiod",
                "AM/PM" },
            { "field.era",
                "era" },
            { "field.hour",
                "ora" },
            { "field.minute",
                "minuto" },
            { "field.month",
                "mese" },
            { "field.second",
                "Secondo" },
            { "field.week",
                "settimana" },
            { "field.weekday",
                "giorno della settimana" },
            { "field.year",
                "anno" },
            { "field.zone",
                "fuso orario" },
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
                    "a.C.",
                    "d.C.",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "BC",
                    "dopo Cristo",
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
                    "gen",
                    "feb",
                    "mar",
                    "apr",
                    "mag",
                    "giu",
                    "lug",
                    "ago",
                    "set",
                    "ott",
                    "nov",
                    "dic",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "gennaio",
                    "febbraio",
                    "marzo",
                    "aprile",
                    "maggio",
                    "giugno",
                    "luglio",
                    "agosto",
                    "settembre",
                    "ottobre",
                    "novembre",
                    "dicembre",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "G",
                    "F",
                    "M",
                    "A",
                    "M",
                    "G",
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
