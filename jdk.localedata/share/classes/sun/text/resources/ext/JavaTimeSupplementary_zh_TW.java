package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_zh_TW extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1\u5b63",
            "2\u5b63",
            "3\u5b63",
            "4\u5b63",
        };

        final String[] sharedQuarterNames = {
            "\u7b2c1\u5b63",
            "\u7b2c2\u5b63",
            "\u7b2c3\u5b63",
            "\u7b2c4\u5b63",
        };

        final String[] sharedDayAbbreviations = {
            "\u9031\u65e5",
            "\u9031\u4e00",
            "\u9031\u4e8c",
            "\u9031\u4e09",
            "\u9031\u56db",
            "\u9031\u4e94",
            "\u9031\u516d",
        };

        final String[] sharedEras = {
            "",
            "\u4f0a\u65af\u862d\u66c6",
        };

        final String[] sharedMonthNames = {
            "\u7a46\u54c8\u862d\u59c6\u6708",
            "\u8272\u6cd5\u723e\u6708",
            "\u8cf4\u6bd4\u6708 I",
            "\u8cf4\u6bd4\u6708 II",
            "\u4e3b\u99ac\u9054\u6708 I",
            "\u4e3b\u99ac\u9054\u6708 II",
            "\u8cf4\u54f2\u535c\u6708",
            "\u820d\u723e\u90a6\u6708",
            "\u8cf4\u8cb7\u4e39\u6708",
            "\u9583\u74e6\u9b6f\u6708",
            "\u90fd\u723e\u5580\u723e\u5fb7\u6708",
            "\u90fd\u723e\u9ed1\u54f2\u6708",
            "",
        };

        final String[] sharedTimePatterns = {
            "ah:mm:ss [zzzz]",
            "ah:mm:ss [z]",
            "ah:mm:ss",
            "ah:mm",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "\u4f5b\u66c6",
        };

        final String[] sharedJavaTimeShortEras = {
            "\u897f\u5143",
            "\u660e\u6cbb",
            "\u5927\u6b63",
            "\u662d\u548c",
            "\u5e73\u6210",
            "\u4ee4\u548c",
        };

        final String[] sharedJavaTimeShortEras2 = {
            "\u897f\u5143\u524d",
            "\u897f\u5143",
        };

        final String[] sharedShortEras = {
            "\u6c11\u570b\u524d",
            "\u6c11\u570b",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u4f5b\u66c6" },
            { "calendarname.gregorian",
                "\u516c\u66c6" },
            { "calendarname.gregory",
                "\u516c\u66c6" },
            { "calendarname.islamic",
                "\u4f0a\u65af\u862d\u66c6" },
            { "calendarname.islamic-civil",
                "\u4f0a\u65af\u862d\u6c11\u7528\u66c6" },
            { "calendarname.islamic-umalqura",
                "\u70cf\u59c6\u5eab\u62c9\u66c6" },
            { "calendarname.japanese",
                "\u65e5\u672c\u66c6" },
            { "calendarname.roc",
                "\u6c11\u570b\u66c6" },
            { "field.era",
                "\u5e74\u4ee3" },
            { "field.hour",
                "\u5c0f\u6642" },
            { "field.minute",
                "\u5206\u9418" },
            { "field.second",
                "\u79d2" },
            { "field.week",
                "\u9031" },
            { "field.weekday",
                "\u9031\u5929" },
            { "field.zone",
                "\u6642\u5340" },
            { "islamic.DayAbbreviations",
                sharedDayAbbreviations },
            { "islamic.Eras",
                sharedEras },
            { "islamic.MonthAbbreviations",
                sharedMonthNames },
            { "islamic.MonthNames",
                sharedMonthNames },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.long.Eras",
                sharedEras },
            { "islamic.narrow.Eras",
                sharedEras },
            { "islamic.short.Eras",
                sharedEras },
            { "java.time.buddhist.DatePatterns",
                new String[] {
                    "Gy\u5e74M\u6708d\u65e5EEEE",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy/M/d",
                }
            },
            { "java.time.buddhist.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.buddhist.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "Gy\u5e74M\u6708d\u65e5EEEE",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy/M/d",
                    "Gy/M/d",
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
                    "Gy\u5e74M\u6708d\u65e5 EEEE",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy\u5e74M\u6708d\u65e5",
                    "Gy/M/d",
                }
            },
            { "java.time.short.Eras",
                sharedJavaTimeShortEras2 },
            { "roc.DatePatterns",
                new String[] {
                    "GGGGy\u5e74M\u6708d\u65e5 EEEE",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy/M/d",
                }
            },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.Eras",
                sharedShortEras },
            { "roc.MonthNames",
                new String[] {
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
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.long.Eras",
                sharedShortEras },
            { "roc.narrow.Eras",
                sharedShortEras },
            { "roc.short.Eras",
                sharedShortEras },
        };
    }
}
