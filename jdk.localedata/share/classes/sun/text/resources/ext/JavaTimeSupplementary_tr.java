package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_tr extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "\u00c71",
            "\u00c72",
            "\u00c73",
            "\u00c74",
        };

        final String[] sharedQuarterNames = {
            "1. \u00e7eyrek",
            "2. \u00e7eyrek",
            "3. \u00e7eyrek",
            "4. \u00e7eyrek",
        };

        final String[] sharedQuarterNarrows = {
            "1.",
            "2.",
            "3.",
            "4.",
        };

        final String[] sharedAmPmMarkers = {
            "\u00d6\u00d6",
            "\u00d6S",
        };

        final String[] sharedDatePatterns = {
            "GGGG d MMMM y EEEE",
            "GGGG d MMMM y",
            "GGGG d MMM y",
            "G d.MM.y",
        };

        final String[] sharedDayAbbreviations = {
            "Paz",
            "Pzt",
            "Sal",
            "\u00c7ar",
            "Per",
            "Cum",
            "Cmt",
        };

        final String[] sharedDayNames = {
            "Pazar",
            "Pazartesi",
            "Sal\u0131",
            "\u00c7ar\u015famba",
            "Per\u015fembe",
            "Cuma",
            "Cumartesi",
        };

        final String[] sharedDayNarrows = {
            "P",
            "P",
            "S",
            "\u00c7",
            "P",
            "C",
            "C",
        };

        final String[] sharedEras = {
            "",
            "Hicri",
        };

        final String[] sharedMonthNames = {
            "Muharrem",
            "Safer",
            "Rebi\u00fclevvel",
            "Rebi\u00fclahir",
            "Cemaziyelevvel",
            "Cemaziyelahir",
            "Recep",
            "\u015eaban",
            "Ramazan",
            "\u015eevval",
            "Zilkade",
            "Zilhicce",
            "",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "\u00f6\u00f6",
            "\u00f6s",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G d MMMM y EEEE",
            "G d MMMM y",
            "G d MMM y",
            "GGGGG d.MM.y",
        };

        final String[] sharedShortEras = {
            "Before R.O.C.",
            "Minguo",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "calendarname.buddhist",
                "Budist Takvimi" },
            { "calendarname.gregorian",
                "Miladi Takvim" },
            { "calendarname.gregory",
                "Miladi Takvim" },
            { "calendarname.islamic",
                "Hicri Takvim" },
            { "calendarname.islamic-civil",
                "Arap Takvimi" },
            { "calendarname.islamic-umalqura",
                "Hicri Takvim (\u00dcmm\u00fc-l Kurra Takvimi)" },
            { "calendarname.japanese",
                "Japon Takvimi" },
            { "calendarname.roc",
                "\u00c7in Cumhuriyeti Takvimi" },
            { "field.dayperiod",
                "\u00d6\u00d6/\u00d6S" },
            { "field.era",
                "Miladi D\u00f6nem" },
            { "field.hour",
                "Saat" },
            { "field.minute",
                "Dakika" },
            { "field.month",
                "Ay" },
            { "field.second",
                "Saniye" },
            { "field.week",
                "Hafta" },
            { "field.weekday",
                "Haftan\u0131n G\u00fcn\u00fc" },
            { "field.year",
                "Y\u0131l" },
            { "field.zone",
                "Saat Dilimi" },
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
            { "islamic.Eras",
                sharedEras },
            { "islamic.MonthAbbreviations",
                sharedMonthNames },
            { "islamic.MonthNames",
                sharedMonthNames },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.QuarterNarrows",
                sharedQuarterNarrows },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.long.Eras",
                sharedEras },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "islamic.narrow.Eras",
                sharedEras },
            { "islamic.short.Eras",
                sharedEras },
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
                    "d MMMM y G EEEE",
                    "d MMMM y G",
                    "d MMM y G",
                    "d.MM.y G",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "Milattan \u00d6nce",
                    "Milattan Sonra",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "M\u00d6",
                    "MS",
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
                sharedShortEras },
            { "roc.MonthAbbreviations",
                new String[] {
                    "Oca",
                    "\u015eub",
                    "Mar",
                    "Nis",
                    "May",
                    "Haz",
                    "Tem",
                    "A\u011fu",
                    "Eyl",
                    "Eki",
                    "Kas",
                    "Ara",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "Ocak",
                    "\u015eubat",
                    "Mart",
                    "Nisan",
                    "May\u0131s",
                    "Haziran",
                    "Temmuz",
                    "A\u011fustos",
                    "Eyl\u00fcl",
                    "Ekim",
                    "Kas\u0131m",
                    "Aral\u0131k",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "O",
                    "\u015e",
                    "M",
                    "N",
                    "M",
                    "H",
                    "T",
                    "A",
                    "E",
                    "E",
                    "K",
                    "A",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.QuarterNarrows",
                sharedQuarterNarrows },
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
