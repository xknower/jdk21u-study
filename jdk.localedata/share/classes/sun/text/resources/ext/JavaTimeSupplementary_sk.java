package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_sk extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "1. \u0161tvr\u0165rok",
            "2. \u0161tvr\u0165rok",
            "3. \u0161tvr\u0165rok",
            "4. \u0161tvr\u0165rok",
        };

        final String[] sharedAmPmMarkers = {
            "AM",
            "PM",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d. M. y GGGG",
            "d. M. y GGGG",
            "d. M. y GGGG",
            "d.M.y G",
        };

        final String[] sharedDayAbbreviations = {
            "ne",
            "po",
            "ut",
            "st",
            "\u0161t",
            "pi",
            "so",
        };

        final String[] sharedDayNames = {
            "nede\u013ea",
            "pondelok",
            "utorok",
            "streda",
            "\u0161tvrtok",
            "piatok",
            "sobota",
        };

        final String[] sharedDayNarrows = {
            "n",
            "p",
            "u",
            "s",
            "\u0161",
            "p",
            "s",
        };

        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedTimePatterns = {
            "H:mm:ss zzzz",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d. M. y G",
            "d. M. y G",
            "d. M. y G",
            "d.M.y GGGGG",
        };

        return new Object[][] {
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "buddhistick\u00fd kalend\u00e1r" },
            { "calendarname.gregorian",
                "gregori\u00e1nsky kalend\u00e1r" },
            { "calendarname.gregory",
                "gregori\u00e1nsky kalend\u00e1r" },
            { "calendarname.islamic",
                "islamsk\u00fd kalend\u00e1r" },
            { "calendarname.islamic-civil",
                "Islamsk\u00fd ob\u010diansky kalend\u00e1r" },
            { "calendarname.japanese",
                "japonsk\u00fd kalend\u00e1r" },
            { "calendarname.roc",
                "\u010d\u00ednsky republik\u00e1nsky kalend\u00e1r" },
            { "field.dayperiod",
                "\u010das\u0165 d\u0148a" },
            { "field.era",
                "letopo\u010det" },
            { "field.hour",
                "hodina" },
            { "field.minute",
                "min\u00fata" },
            { "field.month",
                "mesiac" },
            { "field.second",
                "sekunda" },
            { "field.week",
                "t\u00fd\u017ede\u0148" },
            { "field.weekday",
                "de\u0148 v t\u00fd\u017edni" },
            { "field.year",
                "rok" },
            { "field.zone",
                "\u010dasov\u00e9 p\u00e1smo" },
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
                    "pred Kristom",
                    "po Kristovi",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "pred n.l.",
                    "n.l.",
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
            { "roc.MonthAbbreviations",
                new String[] {
                    "jan",
                    "feb",
                    "mar",
                    "apr",
                    "m\u00e1j",
                    "j\u00fan",
                    "j\u00fal",
                    "aug",
                    "sep",
                    "okt",
                    "nov",
                    "dec",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "janu\u00e1ra",
                    "febru\u00e1ra",
                    "marca",
                    "apr\u00edla",
                    "m\u00e1ja",
                    "j\u00fana",
                    "j\u00fala",
                    "augusta",
                    "septembra",
                    "okt\u00f3bra",
                    "novembra",
                    "decembra",
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
            { "roc.TimePatterns",
                sharedTimePatterns },
        };
    }
}
