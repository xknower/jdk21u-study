package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ar extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "\u0627\u0644\u0631\u0628\u0639 \u0627\u0644\u0623\u0648\u0644",
            "\u0627\u0644\u0631\u0628\u0639 \u0627\u0644\u062b\u0627\u0646\u064a",
            "\u0627\u0644\u0631\u0628\u0639 \u0627\u0644\u062b\u0627\u0644\u062b",
            "\u0627\u0644\u0631\u0628\u0639 \u0627\u0644\u0631\u0627\u0628\u0639",
        };

        final String[] sharedQuarterNarrows = {
            "\u0661",
            "\u0662",
            "\u0663",
            "\u0664",
        };

        final String[] sharedAmPmMarkers = {
            "\u0635",
            "\u0645",
        };

        final String[] sharedDayNames = {
            "\u0627\u0644\u0623\u062d\u062f",
            "\u0627\u0644\u0627\u062b\u0646\u064a\u0646",
            "\u0627\u0644\u062b\u0644\u0627\u062b\u0627\u0621",
            "\u0627\u0644\u0623\u0631\u0628\u0639\u0627\u0621",
            "\u0627\u0644\u062e\u0645\u064a\u0633",
            "\u0627\u0644\u062c\u0645\u0639\u0629",
            "\u0627\u0644\u0633\u0628\u062a",
        };

        final String[] sharedDayNarrows = {
            "\u062d",
            "\u0646",
            "\u062b",
            "\u0631",
            "\u062e",
            "\u062c",
            "\u0633",
        };

        final String[] sharedEras = {
            "",
            "\u0647\u0640",
        };

        final String[] sharedMonthNames = {
            "\u0645\u062d\u0631\u0645",
            "\u0635\u0641\u0631",
            "\u0631\u0628\u064a\u0639 \u0627\u0644\u0623\u0648\u0644",
            "\u0631\u0628\u064a\u0639 \u0627\u0644\u0622\u062e\u0631",
            "\u062c\u0645\u0627\u062f\u0649 \u0627\u0644\u0623\u0648\u0644\u0649",
            "\u062c\u0645\u0627\u062f\u0649 \u0627\u0644\u0622\u062e\u0631\u0629",
            "\u0631\u062c\u0628",
            "\u0634\u0639\u0628\u0627\u0646",
            "\u0631\u0645\u0636\u0627\u0646",
            "\u0634\u0648\u0627\u0644",
            "\u0630\u0648 \u0627\u0644\u0642\u0639\u062f\u0629",
            "\u0630\u0648 \u0627\u0644\u062d\u062c\u0629",
            "",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE\u060c d MMMM\u060c y G",
            "d MMMM\u060c y G",
            "dd\u200f/MM\u200f/y G",
            "d\u200f/M\u200f/y GGGGG",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0628\u0648\u0630\u064a",
        };

        final String[] sharedJavaTimeShortEras = {
            "\u0645",
            "\u0645\u064a\u062c\u064a",
            "\u062a\u064a\u0634\u0648",
            "\u0634\u0648\u0648\u0627",
            "\u0647\u064a\u0633\u064a",
            "\u0631\u064a\u0648\u0627",
        };

        final String[] sharedShortEras = {
            "Before R.O.C.",
            "\u062c\u0645\u0647\u0648\u0631\u064a\u0629 \u0627\u0644\u0635\u064a",
        };

        final String[] sharedMonthAbbreviations = {
            "\u064a\u0646\u0627\u064a\u0631",
            "\u0641\u0628\u0631\u0627\u064a\u0631",
            "\u0645\u0627\u0631\u0633",
            "\u0623\u0628\u0631\u064a\u0644",
            "\u0645\u0627\u064a\u0648",
            "\u064a\u0648\u0646\u064a\u0648",
            "\u064a\u0648\u0644\u064a\u0648",
            "\u0623\u063a\u0633\u0637\u0633",
            "\u0633\u0628\u062a\u0645\u0628\u0631",
            "\u0623\u0643\u062a\u0648\u0628\u0631",
            "\u0646\u0648\u0641\u0645\u0628\u0631",
            "\u062f\u064a\u0633\u0645\u0628\u0631",
            "",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterNames },
            { "QuarterNames",
                sharedQuarterNames },
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "calendarname.buddhist",
                "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0628\u0648\u0630\u064a" },
            { "calendarname.gregorian",
                "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0645\u064a\u0644\u0627\u062f\u064a" },
            { "calendarname.gregory",
                "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0645\u064a\u0644\u0627\u062f\u064a" },
            { "calendarname.islamic",
                "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0647\u062c\u0631\u064a" },
            { "calendarname.islamic-civil",
                "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0625\u0633\u0644\u0627\u0645\u064a \u0627\u0644\u0645\u062f\u0646\u064a" },
            { "calendarname.islamic-umalqura",
                "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0625\u0633\u0644\u0627\u0645\u064a (\u0623\u0645 \u0627\u0644\u0642\u0631\u0649)" },
            { "calendarname.japanese",
                "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u064a\u0627\u0628\u0627\u0646\u064a" },
            { "calendarname.roc",
                "\u062a\u0642\u0648\u064a\u0645 \u0645\u064a\u0646\u062c\u0648" },
            { "field.dayperiod",
                "\u0635/\u0645" },
            { "field.era",
                "\u0627\u0644\u0639\u0635\u0631" },
            { "field.hour",
                "\u0627\u0644\u0633\u0627\u0639\u0627\u062a" },
            { "field.minute",
                "\u0627\u0644\u062f\u0642\u0627\u0626\u0642" },
            { "field.month",
                "\u0627\u0644\u0634\u0647\u0631" },
            { "field.second",
                "\u0627\u0644\u062b\u0648\u0627\u0646\u064a" },
            { "field.week",
                "\u0627\u0644\u0623\u0633\u0628\u0648\u0639" },
            { "field.weekday",
                "\u0627\u0644\u064a\u0648\u0645" },
            { "field.year",
                "\u0627\u0644\u0633\u0646\u0629" },
            { "field.zone",
                "\u0627\u0644\u062a\u0648\u0642\u064a\u062a" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE\u060c d MMMM\u060c y GGGG",
                    "d MMMM\u060c y GGGG",
                    "d MMM\u060c y GGGG",
                    "d\u200f/M\u200f/y G",
                }
            },
            { "islamic.DayAbbreviations",
                sharedDayNames },
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
            { "islamic.MonthNarrows",
                new String[] {
                    "\u0661",
                    "\u0662",
                    "\u0663",
                    "\u0664",
                    "\u0665",
                    "\u0666",
                    "\u0667",
                    "\u0668",
                    "\u0669",
                    "\u0661\u0660",
                    "\u0661\u0661",
                    "\u0661\u0662",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterNames },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.QuarterNarrows",
                sharedQuarterNarrows },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.long.Eras",
                sharedEras },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.narrow.Eras",
                sharedEras },
            { "islamic.short.Eras",
                sharedEras },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.buddhist.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.buddhist.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.islamic.DatePatterns",
                new String[] {
                    "EEEE\u060c d MMMM\u060c y G",
                    "d MMMM\u060c y G",
                    "d MMM\u060c y G",
                    "d\u200f/M\u200f/y GGGGG",
                }
            },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.long.Eras",
                sharedJavaTimeShortEras },
            { "java.time.japanese.short.Eras",
                sharedJavaTimeShortEras },
            { "java.time.long.Eras",
                new String[] {
                    "\u0642\u0628\u0644 \u0627\u0644\u0645\u064a\u0644\u0627\u062f",
                    "\u0645\u064a\u0644\u0627\u062f\u064a",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u0642.\u0645",
                    "\u0645",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE\u060c d MMMM\u060c y GGGG",
                    "d MMMM\u060c y GGGG",
                    "dd\u200f/MM\u200f/y GGGG",
                    "d\u200f/M\u200f/y G",
                }
            },
            { "roc.DayAbbreviations",
                sharedDayNames },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedShortEras },
            { "roc.MonthAbbreviations",
                sharedMonthAbbreviations },
            { "roc.MonthNames",
                sharedMonthAbbreviations },
            { "roc.MonthNarrows",
                new String[] {
                    "\u064a",
                    "\u0641",
                    "\u0645",
                    "\u0623",
                    "\u0648",
                    "\u0646",
                    "\u0644",
                    "\u063a",
                    "\u0633",
                    "\u0643",
                    "\u0628",
                    "\u062f",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterNames },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.QuarterNarrows",
                sharedQuarterNarrows },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.long.Eras",
                sharedShortEras },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.Eras",
                sharedShortEras },
            { "roc.short.Eras",
                sharedShortEras },
            { "timezone.gmtFormat",
                "\u062c\u0631\u064a\u0646\u062a\u0634{0}" },
        };
    }
}
