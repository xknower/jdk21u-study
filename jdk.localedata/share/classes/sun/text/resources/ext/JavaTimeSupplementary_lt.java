package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_lt extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "I k.",
            "II k.",
            "III k.",
            "IV k.",
        };

        final String[] sharedQuarterNames = {
            "I ketvirtis",
            "II ketvirtis",
            "III ketvirtis",
            "IV ketvirtis",
        };

        final String[] sharedAmPmMarkers = {
            "prie\u0161piet",
            "popiet",
        };

        final String[] sharedDatePatterns = {
            "y MMMM d GGGG, EEEE",
            "y MMMM d GGGG",
            "y MMM d GGGG",
            "y-MM-dd GGGG",
        };

        final String[] sharedDayAbbreviations = {
            "sk",
            "pr",
            "an",
            "tr",
            "kt",
            "pn",
            "\u0161t",
        };

        final String[] sharedDayNames = {
            "sekmadienis",
            "pirmadienis",
            "antradienis",
            "tre\u010diadienis",
            "ketvirtadienis",
            "penktadienis",
            "\u0161e\u0161tadienis",
        };

        final String[] sharedDayNarrows = {
            "S",
            "P",
            "A",
            "T",
            "K",
            "P",
            "\u0160",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "pr. p.",
            "pop.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "y MMMM d G, EEEE",
            "y MMMM d G",
            "y MMM d G",
            "y-MM-dd G",
        };

        final String[] sharedEras = {
            "Prie\u0161 R.O.C.",
            "R.O.C.",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "budist\u0173 kalendorius" },
            { "calendarname.gregorian",
                "Grigaliaus kalendorius" },
            { "calendarname.gregory",
                "Grigaliaus kalendorius" },
            { "calendarname.islamic",
                "islamo kalendorius" },
            { "calendarname.islamic-civil",
                "Islamo kalendorius (lentelinis, pilietin\u0117 era)" },
            { "calendarname.islamic-umalqura",
                "Islamo kalendorius (Umm al-Qura)" },
            { "calendarname.japanese",
                "japon\u0173 kalendorius" },
            { "calendarname.roc",
                "Kinijos Respublikos kalendorius" },
            { "field.dayperiod",
                "iki piet\u0173 / po piet\u0173" },
            { "field.era",
                "era" },
            { "field.hour",
                "valanda" },
            { "field.minute",
                "minut\u0117" },
            { "field.month",
                "m\u0117nuo" },
            { "field.second",
                "sekund\u0117" },
            { "field.week",
                "savait\u0117" },
            { "field.weekday",
                "savait\u0117s diena" },
            { "field.year",
                "metai" },
            { "field.zone",
                "laiko juosta" },
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
            { "java.time.japanese.long.Eras",
                new String[] {
                    "po Kristaus",
                    "Meid\u017ei",
                    "Tai\u0161o",
                    "\u0160ova",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.japanese.short.Eras",
                new String[] {
                    "po Kr.",
                    "Meid\u017ei",
                    "Tai\u0161o",
                    "\u0160ova",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "prie\u0161 Krist\u0173",
                    "po Kristaus",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "pr.Kr.",
                    "po.Kr.",
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
                    "saus.",
                    "vas.",
                    "kov.",
                    "bal.",
                    "geg.",
                    "bir\u017e.",
                    "liep.",
                    "rugp.",
                    "rugs.",
                    "spal.",
                    "lapkr.",
                    "gruod.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "sausio",
                    "vasario",
                    "kovo",
                    "baland\u017eio",
                    "gegu\u017e\u0117s",
                    "bir\u017eelio",
                    "liepos",
                    "rugpj\u016b\u010dio",
                    "rugs\u0117jo",
                    "spalio",
                    "lapkri\u010dio",
                    "gruod\u017eio",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "S",
                    "V",
                    "K",
                    "B",
                    "G",
                    "B",
                    "L",
                    "R",
                    "R",
                    "S",
                    "L",
                    "G",
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
            { "timezone.hourFormat",
                "+HH:mm;\u2212HH:mm" },
        };
    }
}
