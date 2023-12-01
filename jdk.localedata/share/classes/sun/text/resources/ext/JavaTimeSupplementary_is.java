package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_is extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "F1",
            "F2",
            "F3",
            "F4",
        };

        final String[] sharedQuarterNames = {
            "1. fj\u00f3r\u00f0ungur",
            "2. fj\u00f3r\u00f0ungur",
            "3. fj\u00f3r\u00f0ungur",
            "4. fj\u00f3r\u00f0ungur",
        };

        final String[] sharedAmPmMarkers = {
            "f.h.",
            "e.h.",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d. MMMM y GGGG",
            "d. MMMM y GGGG",
            "d.M.y GGGG",
            "d.M.y G",
        };

        final String[] sharedDayAbbreviations = {
            "sun.",
            "m\u00e1n.",
            "\u00feri.",
            "mi\u00f0.",
            "fim.",
            "f\u00f6s.",
            "lau.",
        };

        final String[] sharedDayNames = {
            "sunnudagur",
            "m\u00e1nudagur",
            "\u00feri\u00f0judagur",
            "mi\u00f0vikudagur",
            "fimmtudagur",
            "f\u00f6studagur",
            "laugardagur",
        };

        final String[] sharedDayNarrows = {
            "S",
            "M",
            "\u00de",
            "M",
            "F",
            "F",
            "L",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "f.",
            "e.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d. MMMM y G",
            "d. MMMM y G",
            "d.M.y G",
            "d.M.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "B\u00fadd\u00edskt dagatal" },
            { "calendarname.gregorian",
                "Gregor\u00edskt dagatal" },
            { "calendarname.gregory",
                "Gregor\u00edskt dagatal" },
            { "calendarname.islamic",
                "\u00cdslamskt dagatal" },
            { "calendarname.islamic-civil",
                "\u00cdslamskt borgaradagatal" },
            { "calendarname.japanese",
                "Japanskt dagatal" },
            { "calendarname.roc",
                "Minguo dagatal" },
            { "field.dayperiod",
                "f.h./e.h." },
            { "field.era",
                "t\u00edmabil" },
            { "field.hour",
                "klukkustund" },
            { "field.minute",
                "m\u00edn\u00fata" },
            { "field.month",
                "m\u00e1nu\u00f0ur" },
            { "field.second",
                "sek\u00fanda" },
            { "field.week",
                "vika" },
            { "field.weekday",
                "vikudagur" },
            { "field.year",
                "\u00e1r" },
            { "field.zone",
                "t\u00edmabelti" },
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
                    "fyrir Krist",
                    "eftir Krist",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "f.Kr.",
                    "e.Kr.",
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
                    "ma\u00ed",
                    "j\u00fan.",
                    "j\u00fal.",
                    "\u00e1g\u00fa.",
                    "sep.",
                    "okt.",
                    "n\u00f3v.",
                    "des.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "jan\u00faar",
                    "febr\u00faar",
                    "mars",
                    "apr\u00edl",
                    "ma\u00ed",
                    "j\u00fan\u00ed",
                    "j\u00fal\u00ed",
                    "\u00e1g\u00fast",
                    "september",
                    "okt\u00f3ber",
                    "n\u00f3vember",
                    "desember",
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
                    "\u00c1",
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
                sharedNarrowAmPmMarkers },
        };
    }
}
