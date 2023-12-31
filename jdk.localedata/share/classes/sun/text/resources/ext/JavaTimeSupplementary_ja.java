package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ja extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "\u7b2c1\u56db\u534a\u671f",
            "\u7b2c2\u56db\u534a\u671f",
            "\u7b2c3\u56db\u534a\u671f",
            "\u7b2c4\u56db\u534a\u671f",
        };

        final String[] sharedAmPmMarkers = {
            "\u5348\u524d",
            "\u5348\u5f8c",
        };

        final String[] sharedDatePatterns = {
            "GGGGy\u5e74M\u6708d\u65e5EEEE",
            "GGGGy\u5e74M\u6708d\u65e5",
            "GGGGy/MM/dd",
            "GGGGy/MM/dd",
        };

        final String[] sharedDayNarrows = {
            "\u65e5",
            "\u6708",
            "\u706b",
            "\u6c34",
            "\u6728",
            "\u91d1",
            "\u571f",
        };

        final String[] sharedDayNames = {
            "\u65e5\u66dc\u65e5",
            "\u6708\u66dc\u65e5",
            "\u706b\u66dc\u65e5",
            "\u6c34\u66dc\u65e5",
            "\u6728\u66dc\u65e5",
            "\u91d1\u66dc\u65e5",
            "\u571f\u66dc\u65e5",
        };

        final String[] sharedMonthNames = {
            "\u30e0\u30cf\u30c3\u30e9\u30e0",
            "\u30b5\u30d5\u30a2\u30eb",
            "\u30e9\u30d3\u30fc\u30fb\u30a6\u30eb\u30fb\u30a2\u30a6\u30ef\u30eb",
            "\u30e9\u30d3\u30fc\u30fb\u30a6\u30c3\u30fb\u30b5\u30fc\u30cb\u30fc",
            "\u30b8\u30e5\u30de\u30fc\u30c0\u30eb\u30fb\u30a2\u30a6\u30ef\u30eb",
            "\u30b8\u30e5\u30de\u30fc\u30c0\u30c3\u30b5\u30fc\u30cb\u30fc",
            "\u30e9\u30b8\u30e3\u30d6",
            "\u30b7\u30e3\u30a2\u30d0\u30fc\u30f3",
            "\u30e9\u30de\u30c0\u30fc\u30f3",
            "\u30b7\u30e3\u30a6\u30ef\u30fc\u30eb",
            "\u30ba\u30eb\u30fb\u30ab\u30a4\u30c0",
            "\u30ba\u30eb\u30fb\u30d2\u30c3\u30b8\u30e3",
            "",
        };

        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedTimePatterns = {
            "H\u6642mm\u5206ss\u79d2 zzzz",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "Gy\u5e74M\u6708d\u65e5EEEE",
            "Gy\u5e74M\u6708d\u65e5",
            "Gy/MM/dd",
            "Gy/MM/dd",
        };

        final String[] sharedJavaTimeLongEras = {
            "\u897f\u66a6",
            "\u660e\u6cbb",
            "\u5927\u6b63",
            "\u662d\u548c",
            "\u5e73\u6210",
            "\u4ee4\u548c",
        };

        final String[] sharedJavaTimeShortEras = {
            "\u7d00\u5143\u524d",
            "\u897f\u66a6",
        };

        final String[] sharedEras = {
            "\u6c11\u56fd\u524d",
            "\u6c11\u56fd",
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

        return new Object[][] {
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u30bf\u30a4\u4ecf\u6559\u66a6" },
            { "calendarname.gregorian",
                "\u897f\u66a6(\u30b0\u30ec\u30b4\u30ea\u30aa\u66a6)" },
            { "calendarname.gregory",
                "\u897f\u66a6(\u30b0\u30ec\u30b4\u30ea\u30aa\u66a6)" },
            { "calendarname.islamic",
                "\u30a4\u30b9\u30e9\u30e0\u66a6" },
            { "calendarname.islamic-civil",
                "\u30a4\u30b9\u30e9\u30e0\u6b74(\u5b9a\u5468\u671f\u3001\u516c\u6c11\u7d00\u5143)" },
            { "calendarname.islamic-umalqura",
                "\u30a4\u30b9\u30e9\u30e0\u66a6(\u30a6\u30f3\u30e0\u30fb\u30a2\u30eb\u30af\u30e9\u30fc)" },
            { "calendarname.japanese",
                "\u548c\u66a6" },
            { "calendarname.roc",
                "\u4e2d\u83ef\u6c11\u56fd\u66a6" },
            { "field.dayperiod",
                "\u5348\u524d/\u5348\u5f8c" },
            { "field.era",
                "\u6642\u4ee3" },
            { "field.hour",
                "\u6642" },
            { "field.minute",
                "\u5206" },
            { "field.month",
                "\u6708" },
            { "field.second",
                "\u79d2" },
            { "field.week",
                "\u9031" },
            { "field.weekday",
                "\u66dc\u65e5" },
            { "field.year",
                "\u5e74" },
            { "field.zone",
                "\u30bf\u30a4\u30e0\u30be\u30fc\u30f3" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayAbbreviations",
                sharedDayNarrows },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.DayNarrows",
                sharedDayNarrows },
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
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                new String[] {
                    "GGGGy\u5e74M\u6708d\u65e5EEEE",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "Gy/MM/dd",
                    "Gy/MM/dd",
                }
            },
            { "java.time.buddhist.long.Eras",
                new String[] {
                    "BC",
                    "\u4ecf\u66a6",
                }
            },
            { "java.time.buddhist.short.Eras",
                new String[] {
                    "\u7d00\u5143\u524d",
                    "\u4ecf\u66a6",
                }
            },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "Gy'\u5e74'M'\u6708'd'\u65e5'",
                    "GGGGGy.MM.dd",
                    "GGGGGy.MM.dd",
                    "GGGGGy.MM.dd",
                }
            },
            { "java.time.japanese.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.japanese.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.long.Eras",
                sharedJavaTimeShortEras },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                sharedJavaTimeShortEras },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                sharedDayNarrows },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedEras },
            { "roc.MonthAbbreviations",
                sharedMonthAbbreviations },
            { "roc.MonthNames",
                sharedMonthAbbreviations },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
        };
    }
}
