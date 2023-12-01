package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_sl extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1. \u010det.",
            "2. \u010det.",
            "3. \u010det.",
            "4. \u010det.",
        };

        final String[] sharedQuarterNames = {
            "1. \u010detrtletje",
            "2. \u010detrtletje",
            "3. \u010detrtletje",
            "4. \u010detrtletje",
        };

        final String[] sharedAmPmMarkers = {
            "dop.",
            "pop.",
        };

        final String[] sharedDatePatterns = {
            "EEEE, dd. MMMM y GGGG",
            "dd. MMMM y GGGG",
            "d. MMM y GGGG",
            "d. MM. yy G",
        };

        final String[] sharedDayAbbreviations = {
            "ned.",
            "pon.",
            "tor.",
            "sre.",
            "\u010det.",
            "pet.",
            "sob.",
        };

        final String[] sharedDayNames = {
            "nedelja",
            "ponedeljek",
            "torek",
            "sreda",
            "\u010detrtek",
            "petek",
            "sobota",
        };

        final String[] sharedDayNarrows = {
            "n",
            "p",
            "t",
            "s",
            "\u010d",
            "p",
            "s",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "d",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, dd. MMMM y G",
            "dd. MMMM y G",
            "d. MMM y G",
            "d. MM. yy GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "budisti\u010dni koledar" },
            { "calendarname.gregorian",
                "gregorijanski koledar" },
            { "calendarname.gregory",
                "gregorijanski koledar" },
            { "calendarname.islamic",
                "islamski koledar" },
            { "calendarname.islamic-civil",
                "islamski civilni koledar" },
            { "calendarname.japanese",
                "japonski koledar" },
            { "calendarname.roc",
                "kitajski dr\u017eavni koledar" },
            { "field.dayperiod",
                "\u010cas dneva" },
            { "field.era",
                "doba" },
            { "field.hour",
                "ura" },
            { "field.minute",
                "minuta" },
            { "field.month",
                "mesec" },
            { "field.second",
                "sekunda" },
            { "field.week",
                "teden" },
            { "field.weekday",
                "dan v tednu" },
            { "field.year",
                "leto" },
            { "field.zone",
                "\u010dasovni pas" },
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
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "pred Kristusom",
                    "na\u0161e \u0161tetje",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "pr.n.\u0161.",
                    "po Kr.",
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
                    "jan.",
                    "feb.",
                    "mar.",
                    "apr.",
                    "maj",
                    "jun.",
                    "jul.",
                    "avg.",
                    "sep.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "januar",
                    "februar",
                    "marec",
                    "april",
                    "maj",
                    "junij",
                    "julij",
                    "avgust",
                    "september",
                    "oktober",
                    "november",
                    "december",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "j",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "a",
                    "s",
                    "o",
                    "n",
                    "d",
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
                sharedNarrowAmPmMarkers },
            { "timezone.hourFormat",
                "+HH.mm;-HH.mm" },
        };
    }
}
