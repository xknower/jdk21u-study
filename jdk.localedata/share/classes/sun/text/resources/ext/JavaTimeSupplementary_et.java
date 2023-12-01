package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_et extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "K1",
            "K2",
            "K3",
            "K4",
        };

        final String[] sharedQuarterNames = {
            "1. kvartal",
            "2. kvartal",
            "3. kvartal",
            "4. kvartal",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d. MMMM y GGGG",
            "d. MMMM y GGGG",
            "dd.MM.y GGGG",
            "dd.MM.y G",
        };

        final String[] sharedDayNarrows = {
            "P",
            "E",
            "T",
            "K",
            "N",
            "R",
            "L",
        };

        final String[] sharedDayNames = {
            "p\u00fchap\u00e4ev",
            "esmasp\u00e4ev",
            "teisip\u00e4ev",
            "kolmap\u00e4ev",
            "neljap\u00e4ev",
            "reede",
            "laup\u00e4ev",
        };

        final String[] sharedTimePatterns = {
            "H:mm.ss zzzz",
            "H:mm.ss z",
            "H:mm.ss",
            "H:mm",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d. MMMM y G",
            "d. MMMM y G",
            "dd.MM.y G",
            "dd.MM.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "budistlik kalender" },
            { "calendarname.gregorian",
                "Gregoriuse kalender" },
            { "calendarname.gregory",
                "Gregoriuse kalender" },
            { "calendarname.islamic",
                "islamikalender" },
            { "calendarname.islamic-civil",
                "islami ilmalik kalender" },
            { "calendarname.japanese",
                "Jaapani kalender" },
            { "calendarname.roc",
                "Hiina Vabariigi kalender" },
            { "field.dayperiod",
                "enne/p\u00e4rast l\u00f5unat" },
            { "field.era",
                "ajastu" },
            { "field.hour",
                "tund" },
            { "field.minute",
                "minut" },
            { "field.month",
                "kuu" },
            { "field.second",
                "sekund" },
            { "field.week",
                "n\u00e4dal" },
            { "field.weekday",
                "n\u00e4dalap\u00e4ev" },
            { "field.year",
                "aasta" },
            { "field.zone",
                "ajav\u00f6\u00f6nd" },
            { "islamic.AmPmMarkers",
                new String[] {
                    "AM",
                    "PM",
                }
            },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayAbbreviations",
                sharedDayNarrows },
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
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "enne Kristust",
                    "p\u00e4rast Kristust",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "e.m.a.",
                    "m.a.j.",
                }
            },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                sharedDayNarrows },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.MonthAbbreviations",
                new String[] {
                    "jaan",
                    "veebr",
                    "m\u00e4rts",
                    "apr",
                    "mai",
                    "juuni",
                    "juuli",
                    "aug",
                    "sept",
                    "okt",
                    "nov",
                    "dets",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "jaanuar",
                    "veebruar",
                    "m\u00e4rts",
                    "aprill",
                    "mai",
                    "juuni",
                    "juuli",
                    "august",
                    "september",
                    "oktoober",
                    "november",
                    "detsember",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "J",
                    "V",
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
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "timezone.hourFormat",
                "+HH:mm;\u2212HH:mm" },
        };
    }
}
