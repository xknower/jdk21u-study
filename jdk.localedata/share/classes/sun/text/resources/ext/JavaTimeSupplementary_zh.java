package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_zh extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1\u5b63\u5ea6",
            "2\u5b63\u5ea6",
            "3\u5b63\u5ea6",
            "4\u5b63\u5ea6",
        };

        final String[] sharedQuarterNames = {
            "\u7b2c\u4e00\u5b63\u5ea6",
            "\u7b2c\u4e8c\u5b63\u5ea6",
            "\u7b2c\u4e09\u5b63\u5ea6",
            "\u7b2c\u56db\u5b63\u5ea6",
        };

        final String[] sharedAmPmMarkers = {
            "\u4e0a\u5348",
            "\u4e0b\u5348",
        };

        final String[] sharedDayAbbreviations = {
            "\u5468\u65e5",
            "\u5468\u4e00",
            "\u5468\u4e8c",
            "\u5468\u4e09",
            "\u5468\u56db",
            "\u5468\u4e94",
            "\u5468\u516d",
        };

        final String[] sharedDayNames = {
            "\u661f\u671f\u65e5",
            "\u661f\u671f\u4e00",
            "\u661f\u671f\u4e8c",
            "\u661f\u671f\u4e09",
            "\u661f\u671f\u56db",
            "\u661f\u671f\u4e94",
            "\u661f\u671f\u516d",
        };

        final String[] sharedDayNarrows = {
            "\u65e5",
            "\u4e00",
            "\u4e8c",
            "\u4e09",
            "\u56db",
            "\u4e94",
            "\u516d",
        };

        final String[] sharedEras = {
            "",
            "\u4f0a\u65af\u5170\u5386",
        };

        final String[] sharedMonthAbbreviations = {
            "1\u6708",
            "2\u6708",
            "3\u6708",
            "4\u6708",
            "5\u6708",
            "6\u6708",
            "7\u6708",
            "8\u6708",
            "9\u6708",
            "10\u6708",
            "11\u6708",
            "12\u6708",
            "",
        };

        final String[] sharedMonthNames = {
            "\u4e00\u6708",
            "\u4e8c\u6708",
            "\u4e09\u6708",
            "\u56db\u6708",
            "\u4e94\u6708",
            "\u516d\u6708",
            "\u4e03\u6708",
            "\u516b\u6708",
            "\u4e5d\u6708",
            "\u5341\u6708",
            "\u5341\u4e00\u6708",
            "\u5341\u4e8c\u6708",
            "",
        };

        final String[] sharedTimePatterns = {
            "zzzz ah:mm:ss",
            "z ah:mm:ss",
            "ah:mm:ss",
            "ah:mm",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "\u4f5b\u5386",
        };

        final String[] sharedJavaTimeShortEras = {
            "\u516c\u5143",
            "\u660e\u6cbb",
            "\u5927\u6b63",
            "\u662d\u548c",
            "\u5e73\u6210",
            "\u4ee4\u548c",
        };

        final String[] sharedJavaTimeShortEras2 = {
            "\u516c\u5143\u524d",
            "\u516c\u5143",
        };

        final String[] sharedShortEras = {
            "\u6c11\u56fd\u524d",
            "\u6c11\u56fd",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u4f5b\u6559\u65e5\u5386" },
            { "calendarname.gregorian",
                "\u516c\u5386" },
            { "calendarname.gregory",
                "\u516c\u5386" },
            { "calendarname.islamic",
                "\u4f0a\u65af\u5170\u65e5\u5386" },
            { "calendarname.islamic-civil",
                "\u4f0a\u65af\u5170\u5e0c\u5409\u6765\u65e5\u5386" },
            { "calendarname.japanese",
                "\u65e5\u672c\u65e5\u5386" },
            { "calendarname.roc",
                "\u6c11\u56fd\u65e5\u5386" },
            { "field.dayperiod",
                "\u4e0a\u5348/\u4e0b\u5348" },
            { "field.era",
                "\u7eaa\u5143" },
            { "field.hour",
                "\u5c0f\u65f6" },
            { "field.minute",
                "\u5206\u949f" },
            { "field.month",
                "\u6708" },
            { "field.second",
                "\u79d2\u949f" },
            { "field.week",
                "\u5468" },
            { "field.weekday",
                "\u5de5\u4f5c\u65e5" },
            { "field.year",
                "\u5e74" },
            { "field.zone",
                "\u65f6\u533a" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                new String[] {
                    "GGGGy\u5e74M\u6708d\u65e5EEEE",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy/M/d",
                }
            },
            { "islamic.DayAbbreviations",
                sharedDayAbbreviations },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.Eras",
                sharedEras },
            { "islamic.MonthAbbreviations",
                sharedMonthAbbreviations },
            { "islamic.MonthNames",
                sharedMonthNames },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
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
                new String[] {
                    "Gy\u5e74M\u6708d\u65e5EEEE",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy-M-d",
                }
            },
            { "java.time.buddhist.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.buddhist.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.islamic.DatePatterns",
                new String[] {
                    "Gy\u5e74M\u6708d\u65e5EEEE",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy/M/d",
                }
            },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "Gy\u5e74M\u6708d\u65e5EEEE",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gyy-MM-dd",
                }
            },
            { "java.time.japanese.long.Eras",
                sharedJavaTimeShortEras },
            { "java.time.japanese.short.Eras",
                sharedJavaTimeShortEras },
            { "java.time.long.Eras",
                sharedJavaTimeShortEras2 },
            { "java.time.roc.DatePatterns",
                new String[] {
                    "Gy\u5e74M\u6708d\u65e5EEEE",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gyy/M/d",
                }
            },
            { "java.time.short.Eras",
                sharedJavaTimeShortEras2 },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                new String[] {
                    "GGGGy\u5e74M\u6708d\u65e5EEEE",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGyy/M/d",
                }
            },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedShortEras },
            { "roc.MonthAbbreviations",
                sharedMonthAbbreviations },
            { "roc.MonthNames",
                sharedMonthNames },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
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
        };
    }
}
