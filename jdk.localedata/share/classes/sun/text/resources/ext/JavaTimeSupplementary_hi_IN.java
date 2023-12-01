package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_hi_IN extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "\u0924\u093f1",
            "\u0924\u093f2",
            "\u0924\u093f3",
            "\u0924\u093f4",
        };

        final String[] sharedQuarterNames = {
            "\u092a\u0939\u0932\u0940 \u0924\u093f\u092e\u093e\u0939\u0940",
            "\u0926\u0942\u0938\u0930\u0940 \u0924\u093f\u092e\u093e\u0939\u0940",
            "\u0924\u0940\u0938\u0930\u0940 \u0924\u093f\u092e\u093e\u0939\u0940",
            "\u091a\u094c\u0925\u0940 \u0924\u093f\u092e\u093e\u0939\u0940",
        };

        final String[] sharedAmPmMarkers = {
            "\u092a\u0942\u0930\u094d\u0935\u093e\u0939\u094d\u0928",
            "\u0905\u092a\u0930\u093e\u0939\u094d\u0928",
        };

        final String[] sharedDatePatterns = {
            "GGGG EEEE, d MMMM y",
            "GGGG d MMMM y",
            "GGGG d MMM y",
            "GGGG d/M/y",
        };

        final String[] sharedDayAbbreviations = {
            "\u0930\u0935\u093f",
            "\u0938\u094b\u092e",
            "\u092e\u0902\u0917\u0932",
            "\u092c\u0941\u0927",
            "\u0917\u0941\u0930\u0941",
            "\u0936\u0941\u0915\u094d\u0930",
            "\u0936\u0928\u093f",
        };

        final String[] sharedDayNames = {
            "\u0930\u0935\u093f\u0935\u093e\u0930",
            "\u0938\u094b\u092e\u0935\u093e\u0930",
            "\u092e\u0902\u0917\u0932\u0935\u093e\u0930",
            "\u092c\u0941\u0927\u0935\u093e\u0930",
            "\u0917\u0941\u0930\u0941\u0935\u093e\u0930",
            "\u0936\u0941\u0915\u094d\u0930\u0935\u093e\u0930",
            "\u0936\u0928\u093f\u0935\u093e\u0930",
        };

        final String[] sharedDayNarrows = {
            "\u0930",
            "\u0938\u094b",
            "\u092e\u0902",
            "\u092c\u0941",
            "\u0917\u0941",
            "\u0936\u0941",
            "\u0936",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedAbbreviatedAmPmMarkers = {
            "\u092a\u0942\u0930\u094d\u0935",
            "\u0905\u092a\u0930",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "\u092a\u0942",
            "\u0905",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G EEEE, d MMMM y",
            "G d MMMM y",
            "G d MMM y",
            "G d/M/y",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u092c\u094c\u0926\u094d\u0927 \u092a\u0902\u091a\u093e\u0902\u0917" },
            { "calendarname.gregorian",
                "\u0917\u094d\u0930\u0947\u0917\u094b\u0930\u093f\u092f\u0928 \u0915\u0948\u0932\u0947\u0902\u0921\u0930" },
            { "calendarname.gregory",
                "\u0917\u094d\u0930\u0947\u0917\u094b\u0930\u093f\u092f\u0928 \u0915\u0948\u0932\u0947\u0902\u0921\u0930" },
            { "calendarname.islamic",
                "\u0907\u0938\u094d\u0932\u093e\u092e\u0940 \u092a\u0902\u091a\u093e\u0902\u0917" },
            { "calendarname.islamic-civil",
                "\u0907\u0938\u094d\u0932\u093e\u092e\u0940 \u0928\u093e\u0917\u0930\u093f\u0915 \u092a\u0902\u091a\u093e\u0902\u0917" },
            { "calendarname.japanese",
                "\u091c\u093e\u092a\u093e\u0928\u0940 \u092a\u0902\u091a\u093e\u0902\u0917" },
            { "calendarname.roc",
                "\u091a\u0940\u0928\u0940 \u0917\u0923\u0924\u0902\u0924\u094d\u0930 \u092a\u0902\u091a\u093e\u0902\u0917" },
            { "field.dayperiod",
                "\u092a\u0942\u0930\u094d\u0935\u093e\u0939\u094d\u0928/\u0905\u092a\u0930\u093e\u0939\u094d\u0928" },
            { "field.era",
                "\u092f\u0941\u0917" },
            { "field.hour",
                "\u0918\u0902\u091f\u093e" },
            { "field.minute",
                "\u092e\u093f\u0928\u091f" },
            { "field.month",
                "\u092e\u093e\u0939" },
            { "field.second",
                "\u0938\u0947\u0915\u0902\u0921" },
            { "field.week",
                "\u0938\u092a\u094d\u0924\u093e\u0939" },
            { "field.weekday",
                "\u0938\u092a\u094d\u0924\u093e\u0939 \u0915\u093e \u0926\u093f\u0928" },
            { "field.year",
                "\u0935\u0930\u094d\u0937" },
            { "field.zone",
                "\u0938\u092e\u092f \u0915\u094d\u0937\u0947\u0924\u094d\u0930" },
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
                    "\u092e\u0941\u0939\u0930\u094d\u0930\u092e",
                    "\u0938\u092b\u0930",
                    "\u0930\u093e\u092c\u0940 \u092a\u094d\u0930\u0925\u092e",
                    "\u0930\u093e\u092c\u0940 \u0926\u094d\u0935\u093f\u0924\u0940\u092f",
                    "\u091c\u0941\u092e\u094d\u0921\u093e \u092a\u094d\u0930\u0925\u092e",
                    "\u091c\u0941\u092e\u094d\u0921\u093e \u0926\u094d\u0935\u093f\u0924\u0940\u092f",
                    "\u0930\u091c\u092c",
                    "\u0936\u093e\u0935\u0928",
                    "\u0930\u092e\u091c\u093e\u0928",
                    "\u0936\u0935\u094d\u0935\u094d\u0932",
                    "\u091c\u093f\u0932-\u0915\u094d\u0926\u093e\u0939",
                    "\u091c\u093f\u0932\u094d-\u0939\u093f\u091c\u094d\u091c\u093e\u0939",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAbbreviatedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.long.Eras",
                new String[] {
                    "\u0908\u0938\u0935\u0940 \u0938\u0928",
                    "\u092e\u0947\u091c\u0940",
                    "\u0924\u093e\u0908\u0936\u094b",
                    "\u0936\u094b\u0935\u093e",
                    "\u0939\u0947\u0908\u0938\u0947\u0908",
                    "\u0930\u0947\u0907\u0935\u093e",
                }
            },
            { "java.time.japanese.short.Eras",
                new String[] {
                    "\u0908\u0938\u094d\u0935\u0940",
                    "\u092e\u0947\u091c\u0940",
                    "\u0924\u093e\u0908\u0936\u094b",
                    "\u0936\u094b\u0935\u093e",
                    "\u0939\u0947\u0908\u0938\u0947\u0908",
                    "\u0930\u0947\u0907\u0935\u093e",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "\u0908\u0938\u093e-\u092a\u0942\u0930\u094d\u0935",
                    "\u0908\u0938\u0935\u0940 \u0938\u0928",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u0908\u0938\u093e\u092a\u0942\u0930\u094d\u0935",
                    "\u0938\u0928",
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
                    "\u091c\u0928\u0970",
                    "\u092b\u093c\u0930\u0970",
                    "\u092e\u093e\u0930\u094d\u091a",
                    "\u0905\u092a\u094d\u0930\u0948\u0932",
                    "\u092e\u0908",
                    "\u091c\u0942\u0928",
                    "\u091c\u0941\u0932\u0970",
                    "\u0905\u0917\u0970",
                    "\u0938\u093f\u0924\u0970",
                    "\u0905\u0915\u094d\u0924\u0942\u0970",
                    "\u0928\u0935\u0970",
                    "\u0926\u093f\u0938\u0970",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u091c\u0928\u0935\u0930\u0940",
                    "\u092b\u093c\u0930\u0935\u0930\u0940",
                    "\u092e\u093e\u0930\u094d\u091a",
                    "\u0905\u092a\u094d\u0930\u0948\u0932",
                    "\u092e\u0908",
                    "\u091c\u0942\u0928",
                    "\u091c\u0941\u0932\u093e\u0908",
                    "\u0905\u0917\u0938\u094d\u0924",
                    "\u0938\u093f\u0924\u0902\u092c\u0930",
                    "\u0905\u0915\u094d\u0924\u0942\u092c\u0930",
                    "\u0928\u0935\u0902\u092c\u0930",
                    "\u0926\u093f\u0938\u0902\u092c\u0930",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "\u091c",
                    "\u092b\u093c",
                    "\u092e\u093e",
                    "\u0905",
                    "\u092e",
                    "\u091c\u0942",
                    "\u091c\u0941",
                    "\u0905",
                    "\u0938\u093f",
                    "\u0905",
                    "\u0928",
                    "\u0926\u093f",
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
                sharedAbbreviatedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
