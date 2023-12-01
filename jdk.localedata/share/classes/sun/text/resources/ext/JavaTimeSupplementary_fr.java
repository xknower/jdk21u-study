package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_fr extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "T1",
            "T2",
            "T3",
            "T4",
        };

        final String[] sharedQuarterNames = {
            "1er trimestre",
            "2e trimestre",
            "3e trimestre",
            "4e trimestre",
        };

        final String[] sharedAmPmMarkers = {
            "AM",
            "PM",
        };

        final String[] sharedDatePatterns = {
            "EEEE d MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "dd/MM/y G",
        };

        final String[] sharedDayAbbreviations = {
            "dim.",
            "lun.",
            "mar.",
            "mer.",
            "jeu.",
            "ven.",
            "sam.",
        };

        final String[] sharedDayNames = {
            "dimanche",
            "lundi",
            "mardi",
            "mercredi",
            "jeudi",
            "vendredi",
            "samedi",
        };

        final String[] sharedDayNarrows = {
            "D",
            "L",
            "M",
            "M",
            "J",
            "V",
            "S",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "dd/MM/y GGGGG",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "\u00e8re bouddhiste",
        };

        final String[] sharedEras = {
            "avant RdC",
            "RdC",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "calendrier bouddhiste" },
            { "calendarname.gregorian",
                "calendrier gr\u00e9gorien" },
            { "calendarname.gregory",
                "calendrier gr\u00e9gorien" },
            { "calendarname.islamic",
                "calendrier musulman" },
            { "calendarname.islamic-civil",
                "calendrier musulman (tabulaire, \u00e9poque civile)" },
            { "calendarname.japanese",
                "calendrier japonais" },
            { "calendarname.roc",
                "calendrier r\u00e9publicain chinois" },
            { "field.dayperiod",
                "cadran" },
            { "field.era",
                "\u00e8re" },
            { "field.hour",
                "heure" },
            { "field.minute",
                "minute" },
            { "field.month",
                "mois" },
            { "field.second",
                "seconde" },
            { "field.week",
                "semaine" },
            { "field.weekday",
                "jour de la semaine" },
            { "field.year",
                "ann\u00e9e" },
            { "field.zone",
                "fuseau horaire" },
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
                    "mouh.",
                    "saf.",
                    "rab. aw.",
                    "rab. th.",
                    "joum. oul.",
                    "joum. tha.",
                    "raj.",
                    "chaa.",
                    "ram.",
                    "chaw.",
                    "dhou. q.",
                    "dhou. h.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "mouharram",
                    "safar",
                    "rabia al awal",
                    "rabia ath-thani",
                    "joumada al oula",
                    "joumada ath-thania",
                    "rajab",
                    "chaabane",
                    "ramadan",
                    "chawwal",
                    "dhou al qi`da",
                    "dhou al-hijja",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
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
                    "avant J\u00e9sus-Christ",
                    "apr\u00e8s J\u00e9sus-Christ",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "BC",
                    "ap. J.-C.",
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
                    "janv.",
                    "f\u00e9vr.",
                    "mars",
                    "avr.",
                    "mai",
                    "juin",
                    "juil.",
                    "ao\u00fbt",
                    "sept.",
                    "oct.",
                    "nov.",
                    "d\u00e9c.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "janvier",
                    "f\u00e9vrier",
                    "mars",
                    "avril",
                    "mai",
                    "juin",
                    "juillet",
                    "ao\u00fbt",
                    "septembre",
                    "octobre",
                    "novembre",
                    "d\u00e9cembre",
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
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
            { "timezone.gmtFormat",
                "UTC{0}" },
            { "timezone.hourFormat",
                "+HH:mm;\u2212HH:mm" },
        };
    }
}
