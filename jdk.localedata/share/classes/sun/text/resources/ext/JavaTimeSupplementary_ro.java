package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ro extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "trim. I",
            "trim. II",
            "trim. III",
            "trim. IV",
        };

        final String[] sharedQuarterNames = {
            "trimestrul I",
            "trimestrul al II-lea",
            "trimestrul al III-lea",
            "trimestrul al IV-lea",
        };

        final String[] sharedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y GGGG",
            "d MMMM y GGGG",
            "dd.MM.y GGGG",
            "dd.MM.y G",
        };

        final String[] sharedDayAbbreviations = {
            "dum.",
            "lun.",
            "mar.",
            "mie.",
            "joi",
            "vin.",
            "s\u00e2m.",
        };

        final String[] sharedDayNames = {
            "duminic\u0103",
            "luni",
            "mar\u021bi",
            "miercuri",
            "joi",
            "vineri",
            "s\u00e2mb\u0103t\u0103",
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
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "dd.MM.y G",
            "dd.MM.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "calendar budist" },
            { "calendarname.gregorian",
                "calendar gregorian" },
            { "calendarname.gregory",
                "calendar gregorian" },
            { "calendarname.islamic",
                "calendar islamic" },
            { "calendarname.islamic-civil",
                "calendar islamic civil" },
            { "calendarname.japanese",
                "calendar japonez" },
            { "calendarname.roc",
                "calendarul Republicii Chineze" },
            { "field.dayperiod",
                "a.m/p.m." },
            { "field.era",
                "Er\u0103" },
            { "field.hour",
                "Or\u0103" },
            { "field.minute",
                "Minut" },
            { "field.month",
                "Lun\u0103" },
            { "field.second",
                "Secund\u0103" },
            { "field.week",
                "S\u0103pt\u0103m\u00e2n\u0103" },
            { "field.weekday",
                "Zi a s\u0103pt\u0103m\u00e2nii" },
            { "field.year",
                "An" },
            { "field.zone",
                "Fus orar" },
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
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.buddhist.long.Eras",
                new String[] {
                    "BC",
                    "era budist\u0103",
                }
            },
            { "java.time.buddhist.short.Eras",
                new String[] {
                    "BC",
                    "e.b.",
                }
            },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "\u00eenainte de Hristos",
                    "dup\u0103 Hristos",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "d.C.",
                    "\u00ee.d.C.",
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
            { "roc.MonthAbbreviations",
                new String[] {
                    "ian.",
                    "feb.",
                    "mar.",
                    "apr.",
                    "mai",
                    "iun.",
                    "iul.",
                    "aug.",
                    "sept.",
                    "oct.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "ianuarie",
                    "februarie",
                    "martie",
                    "aprilie",
                    "mai",
                    "iunie",
                    "iulie",
                    "august",
                    "septembrie",
                    "octombrie",
                    "noiembrie",
                    "decembrie",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "I",
                    "F",
                    "M",
                    "A",
                    "M",
                    "I",
                    "I",
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
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
        };
    }
}
