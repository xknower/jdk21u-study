package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_lv extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1.\u00a0cet.",
            "2.\u00a0cet.",
            "3.\u00a0cet.",
            "4.\u00a0cet.",
        };

        final String[] sharedQuarterNames = {
            "1. ceturksnis",
            "2. ceturksnis",
            "3. ceturksnis",
            "4. ceturksnis",
        };

        final String[] sharedQuarterNarrows = {
            "1.",
            "2.",
            "3.",
            "4.",
        };

        final String[] sharedAmPmMarkers = {
            "priek\u0161pusdien\u0101",
            "p\u0113cpusdien\u0101",
        };

        final String[] sharedDatePatterns = {
            "EEEE, y. 'gada' d. MMMM GGGG",
            "y. 'gada' d. MMMM GGGG",
            "y. 'gada' d. MMM GGGG",
            "dd.MM.y G",
        };

        final String[] sharedDayAbbreviations = {
            "Sv",
            "Pr",
            "Ot",
            "Tr",
            "Ce",
            "Pk",
            "Se",
        };

        final String[] sharedDayNames = {
            "sv\u0113tdiena",
            "pirmdiena",
            "otrdiena",
            "tre\u0161diena",
            "ceturtdiena",
            "piektdiena",
            "sestdiena",
        };

        final String[] sharedDayNarrows = {
            "S",
            "P",
            "O",
            "T",
            "C",
            "P",
            "S",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "priek\u0161p.",
            "p\u0113cp.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, y. 'gada' d. MMMM G",
            "y. 'gada' d. MMMM G",
            "y. 'gada' d. MMM G",
            "dd.MM.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "calendarname.buddhist",
                "budistu kalend\u0101rs" },
            { "calendarname.gregorian",
                "Gregora kalend\u0101rs" },
            { "calendarname.gregory",
                "Gregora kalend\u0101rs" },
            { "calendarname.islamic",
                "isl\u0101ma kalend\u0101rs" },
            { "calendarname.islamic-civil",
                "isl\u0101ma pilso\u0146u kalend\u0101rs" },
            { "calendarname.japanese",
                "jap\u0101\u0146u kalend\u0101rs" },
            { "calendarname.roc",
                "\u0136\u012bnas Republikas kalend\u0101rs" },
            { "field.dayperiod",
                "priek\u0161pusdien\u0101/p\u0113cpusdien\u0101" },
            { "field.era",
                "\u0113ra" },
            { "field.hour",
                "stundas" },
            { "field.minute",
                "min\u016btes" },
            { "field.month",
                "m\u0113nesis" },
            { "field.second",
                "sekundes" },
            { "field.week",
                "ned\u0113\u013ca" },
            { "field.weekday",
                "ned\u0113\u013cas diena" },
            { "field.year",
                "gads" },
            { "field.zone",
                "laika josla" },
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
            { "islamic.MonthNames",
                new String[] {
                    "muharams",
                    "safars",
                    "1. rab\u012b",
                    "2. rab\u012b",
                    "1. d\u017eum\u0101d\u0101",
                    "2. d\u017eum\u0101d\u0101",
                    "rad\u017eabs",
                    "\u0161abans",
                    "ramad\u0101ns",
                    "\u0161auvals",
                    "du al-kid\u0101",
                    "du al-hid\u017e\u0101",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.QuarterNarrows",
                sharedQuarterNarrows },
            { "islamic.abbreviated.AmPmMarkers",
                sharedNarrowAmPmMarkers },
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
                    "pirms m\u016bsu \u0113ras",
                    "m\u016bsu \u0113r\u0101",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "pm\u0113",
                    "m\u0113",
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
                    "janv.",
                    "febr.",
                    "marts",
                    "apr.",
                    "maijs",
                    "j\u016bn.",
                    "j\u016bl.",
                    "aug.",
                    "sept.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "janv\u0101ris",
                    "febru\u0101ris",
                    "marts",
                    "apr\u012blis",
                    "maijs",
                    "j\u016bnijs",
                    "j\u016blijs",
                    "augusts",
                    "septembris",
                    "oktobris",
                    "novembris",
                    "decembris",
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
            { "roc.QuarterNarrows",
                sharedQuarterNarrows },
            { "roc.abbreviated.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
