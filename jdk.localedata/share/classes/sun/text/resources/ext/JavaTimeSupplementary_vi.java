package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_vi extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "Qu\u00fd 1",
            "Qu\u00fd 2",
            "Qu\u00fd 3",
            "Qu\u00fd 4",
        };

        final String[] sharedAmPmMarkers = {
            "SA",
            "CH",
        };

        final String[] sharedDayAbbreviations = {
            "CN",
            "Th 2",
            "Th 3",
            "Th 4",
            "Th 5",
            "Th 6",
            "Th 7",
        };

        final String[] sharedDayNames = {
            "Ch\u1ee7 Nh\u1eadt",
            "Th\u1ee9 Hai",
            "Th\u1ee9 Ba",
            "Th\u1ee9 T\u01b0",
            "Th\u1ee9 N\u0103m",
            "Th\u1ee9 S\u00e1u",
            "Th\u1ee9 B\u1ea3y",
        };

        final String[] sharedDayNarrows = {
            "CN",
            "T2",
            "T3",
            "T4",
            "T5",
            "T6",
            "T7",
        };

        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "s",
            "c",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, 'ng\u00e0y' dd MMMM 'n\u0103m' y G",
            "'Ng\u00e0y' dd 'th\u00e1ng' M 'n\u0103m' y G",
            "dd-MM-y G",
            "dd/MM/y GGGGG",
        };

        final String[] sharedJavaTimeLongEras = {
            "tr. CN",
            "sau CN",
        };

        final String[] sharedEras = {
            "Tr\u01b0\u1edbc R.O.C",
            "R.O.C.",
        };

        return new Object[][] {
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "L\u1ecbch Ph\u1eadt Gi\u00e1o" },
            { "calendarname.gregorian",
                "L\u1ecbch Gregory" },
            { "calendarname.gregory",
                "L\u1ecbch Gregory" },
            { "calendarname.islamic",
                "L\u1ecbch H\u1ed3i Gi\u00e1o" },
            { "calendarname.islamic-civil",
                "L\u1ecbch Islamic-Civil" },
            { "calendarname.islamic-umalqura",
                "L\u1ecbch H\u1ed3i Gi\u00e1o - Umm al-Qura" },
            { "calendarname.japanese",
                "L\u1ecbch Nh\u1eadt B\u1ea3n" },
            { "calendarname.roc",
                "L\u1ecbch Trung Hoa D\u00e2n Qu\u1ed1c" },
            { "field.dayperiod",
                "SA/CH" },
            { "field.era",
                "Th\u1eddi \u0111\u1ea1i" },
            { "field.hour",
                "Gi\u1edd" },
            { "field.minute",
                "Ph\u00fat" },
            { "field.month",
                "Th\u00e1ng" },
            { "field.second",
                "Gi\u00e2y" },
            { "field.week",
                "Tu\u1ea7n" },
            { "field.weekday",
                "Ng\u00e0y trong tu\u1ea7n" },
            { "field.year",
                "N\u0103m" },
            { "field.zone",
                "M\u00fai gi\u1edd" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE, 'ng\u00e0y' dd 'th\u00e1ng' MM 'n\u0103m' y GGGG",
                    "'Ng\u00e0y' dd 'th\u00e1ng' M 'n\u0103m' y GGGG",
                    "dd-MM-y GGGG",
                    "dd/MM/y G",
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
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
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
                new String[] {
                    "EEEE, 'ng\u00e0y' dd 'th\u00e1ng' MM 'n\u0103m' y G",
                    "'Ng\u00e0y' dd 'th\u00e1ng' M 'n\u0103m' y G",
                    "dd-MM-y G",
                    "dd/MM/y GGGGG",
                }
            },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE, 'ng\u00e0y' dd MMMM 'n\u0103m' y G",
                    "'Ng\u00e0y' dd 'th\u00e1ng' M 'n\u0103m' y G",
                    "dd-MM-y G",
                    "dd/MM/y G",
                }
            },
            { "java.time.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                sharedJavaTimeLongEras },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE, 'ng\u00e0y' dd MMMM 'n\u0103m' y GGGG",
                    "'Ng\u00e0y' dd 'th\u00e1ng' M 'n\u0103m' y GGGG",
                    "dd-MM-y GGGG",
                    "dd/MM/y G",
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
                    "thg 1",
                    "thg 2",
                    "thg 3",
                    "thg 4",
                    "thg 5",
                    "thg 6",
                    "thg 7",
                    "thg 8",
                    "thg 9",
                    "thg 10",
                    "thg 11",
                    "thg 12",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "th\u00e1ng 1",
                    "th\u00e1ng 2",
                    "th\u00e1ng 3",
                    "th\u00e1ng 4",
                    "th\u00e1ng 5",
                    "th\u00e1ng 6",
                    "th\u00e1ng 7",
                    "th\u00e1ng 8",
                    "th\u00e1ng 9",
                    "th\u00e1ng 10",
                    "th\u00e1ng 11",
                    "th\u00e1ng 12",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
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
