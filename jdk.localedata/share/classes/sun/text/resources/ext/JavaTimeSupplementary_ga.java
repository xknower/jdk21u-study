package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ga extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "R1",
            "R2",
            "R3",
            "R4",
        };

        final String[] sharedQuarterNames = {
            "1\u00fa r\u00e1ithe",
            "2\u00fa r\u00e1ithe",
            "3\u00fa r\u00e1ithe",
            "4\u00fa r\u00e1ithe",
        };

        final String[] sharedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedDatePatterns = {
            "EEEE d MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "dd/MM/y G",
        };

        final String[] sharedDayAbbreviations = {
            "Domh",
            "Luan",
            "M\u00e1irt",
            "C\u00e9ad",
            "D\u00e9ar",
            "Aoine",
            "Sath",
        };

        final String[] sharedDayNames = {
            "D\u00e9 Domhnaigh",
            "D\u00e9 Luain",
            "D\u00e9 M\u00e1irt",
            "D\u00e9 C\u00e9adaoin",
            "D\u00e9ardaoin",
            "D\u00e9 hAoine",
            "D\u00e9 Sathairn",
        };

        final String[] sharedDayNarrows = {
            "D",
            "L",
            "M",
            "C",
            "D",
            "A",
            "S",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "dd/MM/y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "F\u00e9ilire B\u00fada\u00edoch" },
            { "calendarname.gregorian",
                "F\u00e9ilire Ghr\u00e9ag\u00f3ra" },
            { "calendarname.gregory",
                "F\u00e9ilire Ghr\u00e9ag\u00f3ra" },
            { "calendarname.islamic",
                "F\u00e9ilire Iosl\u00e1mach" },
            { "calendarname.japanese",
                "F\u00e9ilire Seap\u00e1nach" },
            { "calendarname.roc",
                "F\u00e9ilire T\u00e9av\u00e1nach" },
            { "field.dayperiod",
                "a.m./p.m." },
            { "field.era",
                "R\u00e9" },
            { "field.hour",
                "Uair" },
            { "field.minute",
                "N\u00f3im\u00e9ad" },
            { "field.month",
                "M\u00ed" },
            { "field.second",
                "Soicind" },
            { "field.week",
                "Seachtain" },
            { "field.weekday",
                "L\u00e1 na seachtaine" },
            { "field.year",
                "Bliain" },
            { "field.zone",
                "Crios Ama" },
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
                    "Roimh Chr\u00edost",
                    "Anno Domini",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "RC",
                    "AD",
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
                    "Ean",
                    "Feabh",
                    "M\u00e1rta",
                    "Aib",
                    "Beal",
                    "Meith",
                    "I\u00fail",
                    "L\u00fan",
                    "MF\u00f3mh",
                    "DF\u00f3mh",
                    "Samh",
                    "Noll",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "Ean\u00e1ir",
                    "Feabhra",
                    "M\u00e1rta",
                    "Aibre\u00e1n",
                    "Bealtaine",
                    "Meitheamh",
                    "I\u00fail",
                    "L\u00fanasa",
                    "Me\u00e1n F\u00f3mhair",
                    "Deireadh F\u00f3mhair",
                    "Samhain",
                    "Nollaig",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "E",
                    "F",
                    "M",
                    "A",
                    "B",
                    "M",
                    "I",
                    "L",
                    "M",
                    "D",
                    "S",
                    "N",
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
            { "timezone.gmtFormat",
                "MAG{0}" },
        };
    }
}
