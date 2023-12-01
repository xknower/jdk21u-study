package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_sq extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "tremujori I",
            "tremujori II",
            "tremujori III",
            "tremujori IV",
        };

        final String[] sharedQuarterNames = {
            "tremujori i par\u00eb",
            "tremujori i dyt\u00eb",
            "tremujori i tret\u00eb",
            "tremujori i kat\u00ebrt",
        };

        final String[] sharedAmPmMarkers = {
            "e paradites",
            "e pasdites",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMM y GGGG",
            "d MMM y GGGG",
            "d MMM y GGGG",
            "d.M.y G",
        };

        final String[] sharedDayAbbreviations = {
            "Die",
            "H\u00ebn",
            "Mar",
            "M\u00ebr",
            "Enj",
            "Pre",
            "Sht",
        };

        final String[] sharedDayNames = {
            "e diel",
            "e h\u00ebn\u00eb",
            "e mart\u00eb",
            "e m\u00ebrkur\u00eb",
            "e enjte",
            "e premte",
            "e shtun\u00eb",
        };

        final String[] sharedDayNarrows = {
            "D",
            "H",
            "M",
            "M",
            "E",
            "P",
            "S",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a, zzzz",
            "h:mm:ss a, z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMM y G",
            "d MMM y G",
            "d MMM y G",
            "d.M.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "kalendar budist" },
            { "calendarname.gregorian",
                "kalendar gregorian" },
            { "calendarname.gregory",
                "kalendar gregorian" },
            { "calendarname.islamic",
                "kalendar islamik" },
            { "calendarname.islamic-civil",
                "Kalendari Islamik (tabelor, periudha civile)" },
            { "calendarname.islamic-umalqura",
                "Kalendari Islamik (Um al-Qura)" },
            { "calendarname.japanese",
                "kalendar japonez" },
            { "calendarname.roc",
                "kalendar minguo (kinez)" },
            { "field.dayperiod",
                "paradite/pasdite" },
            { "field.era",
                "er\u00eb" },
            { "field.hour",
                "or\u00eb" },
            { "field.minute",
                "minut\u00eb" },
            { "field.month",
                "muaj" },
            { "field.second",
                "sekond\u00eb" },
            { "field.week",
                "jav\u00eb" },
            { "field.weekday",
                "dit\u00eb e jav\u00ebs" },
            { "field.year",
                "vit" },
            { "field.zone",
                "brezi orar" },
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
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "para er\u00ebs s\u00eb re",
                    "er\u00ebs s\u00eb re",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "p.e.r.",
                    "n.e.r.",
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
                    "Jan",
                    "Shk",
                    "Mar",
                    "Pri",
                    "Maj",
                    "Qer",
                    "Kor",
                    "Gsh",
                    "Sht",
                    "Tet",
                    "N\u00ebn",
                    "Dhj",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "janar",
                    "shkurt",
                    "mars",
                    "prill",
                    "maj",
                    "qershor",
                    "korrik",
                    "gusht",
                    "shtator",
                    "tetor",
                    "n\u00ebntor",
                    "dhjetor",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "J",
                    "S",
                    "M",
                    "P",
                    "M",
                    "Q",
                    "K",
                    "G",
                    "S",
                    "T",
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
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "timezone.gmtFormat",
                "Ora e Grenui\u00e7it: {0}" },
        };
    }
}
