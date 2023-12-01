package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_bg extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1. \u0442\u0440\u0438\u043c.",
            "2. \u0442\u0440\u0438\u043c.",
            "3. \u0442\u0440\u0438\u043c.",
            "4. \u0442\u0440\u0438\u043c.",
        };

        final String[] sharedQuarterNames = {
            "1. \u0442\u0440\u0438\u043c\u0435\u0441\u0435\u0447\u0438\u0435",
            "2. \u0442\u0440\u0438\u043c\u0435\u0441\u0435\u0447\u0438\u0435",
            "3. \u0442\u0440\u0438\u043c\u0435\u0441\u0435\u0447\u0438\u0435",
            "4. \u0442\u0440\u0438\u043c\u0435\u0441\u0435\u0447\u0438\u0435",
        };

        final String[] sharedAmPmMarkers = {
            "\u043f\u0440.\u043e\u0431.",
            "\u0441\u043b.\u043e\u0431.",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y '\u0433'. GGGG",
            "d MMMM y '\u0433'. GGGG",
            "d.MM.y '\u0433'. GGGG",
            "d.MM.yy GGGG",
        };

        final String[] sharedDayAbbreviations = {
            "\u043d\u0434",
            "\u043f\u043d",
            "\u0432\u0442",
            "\u0441\u0440",
            "\u0447\u0442",
            "\u043f\u0442",
            "\u0441\u0431",
        };

        final String[] sharedDayNames = {
            "\u043d\u0435\u0434\u0435\u043b\u044f",
            "\u043f\u043e\u043d\u0435\u0434\u0435\u043b\u043d\u0438\u043a",
            "\u0432\u0442\u043e\u0440\u043d\u0438\u043a",
            "\u0441\u0440\u044f\u0434\u0430",
            "\u0447\u0435\u0442\u0432\u044a\u0440\u0442\u044a\u043a",
            "\u043f\u0435\u0442\u044a\u043a",
            "\u0441\u044a\u0431\u043e\u0442\u0430",
        };

        final String[] sharedDayNarrows = {
            "\u043d",
            "\u043f",
            "\u0432",
            "\u0441",
            "\u0447",
            "\u043f",
            "\u0441",
        };

        final String[] sharedTimePatterns = {
            "H:mm:ss zzzz",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y '\u0433'. G",
            "d MMMM y '\u0433'. G",
            "d.MM.y '\u0433'. G",
            "d.MM.yy G",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u0431\u0443\u0434\u0438\u0441\u0442\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregorian",
                "\u0433\u0440\u0438\u0433\u043e\u0440\u0438\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregory",
                "\u0433\u0440\u0438\u0433\u043e\u0440\u0438\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic",
                "\u0438\u0441\u043b\u044f\u043c\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic-civil",
                "\u0418\u0441\u043b\u044f\u043c\u0441\u043a\u0438 \u0446\u0438\u0432\u0438\u043b\u0435\u043d \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.japanese",
                "\u044f\u043f\u043e\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.roc",
                "\u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440 \u043d\u0430 \u0420\u0435\u043f\u0443\u0431\u043b\u0438\u043a\u0430 \u041a\u0438\u0442\u0430\u0439" },
            { "field.dayperiod",
                "\u043f\u0440.\u043e\u0431./\u0441\u043b.\u043e\u0431." },
            { "field.era",
                "\u0435\u0440\u0430" },
            { "field.hour",
                "\u0447\u0430\u0441" },
            { "field.minute",
                "\u043c\u0438\u043d\u0443\u0442\u0430" },
            { "field.month",
                "\u043c\u0435\u0441\u0435\u0446" },
            { "field.second",
                "\u0441\u0435\u043a\u0443\u043d\u0434\u0430" },
            { "field.week",
                "\u0441\u0435\u0434\u043c\u0438\u0446\u0430" },
            { "field.weekday",
                "\u0434\u0435\u043d \u043e\u0442 \u0441\u0435\u0434\u043c\u0438\u0446\u0430\u0442\u0430" },
            { "field.year",
                "\u0433\u043e\u0434\u0438\u043d\u0430" },
            { "field.zone",
                "\u0447\u0430\u0441\u043e\u0432\u0430 \u0437\u043e\u043d\u0430" },
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
                    "\u043c\u0443\u0445\u0430\u0440\u0430\u043c",
                    "\u0441\u0430\u0444\u0430\u0440",
                    "\u0440\u0430\u0431\u0438-1",
                    "\u0440\u0430\u0431\u0438-2",
                    "\u0434\u0436\u0443\u043c\u0430\u0434\u0430-1",
                    "\u0434\u0436\u0443\u043c\u0430\u0434\u0430-2",
                    "\u0440\u0430\u0434\u0436\u0430\u0431",
                    "\u0448\u0430\u0431\u0430\u043d",
                    "\u0440\u0430\u043c\u0430\u0437\u0430\u043d",
                    "\u0428\u0430\u0432\u0430\u043b",
                    "\u0414\u0445\u0443\u043b-\u041a\u0430\u0430\u0434\u0430",
                    "\u0414\u0445\u0443\u043b-\u0445\u0438\u0434\u0436\u0430",
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
                sharedAmPmMarkers },
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
                    "\u043f\u0440\u0435\u0434\u0438 \u0425\u0440\u0438\u0441\u0442\u0430",
                    "\u0441\u043b\u0435\u0434 \u0425\u0440\u0438\u0441\u0442\u0430",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u043f\u0440.\u043d.\u0435.",
                    "\u043d.\u0435.",
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
                    "\u044f\u043d\u0443",
                    "\u0444\u0435\u0432",
                    "\u043c\u0430\u0440\u0442",
                    "\u0430\u043f\u0440",
                    "\u043c\u0430\u0439",
                    "\u044e\u043d\u0438",
                    "\u044e\u043b\u0438",
                    "\u0430\u0432\u0433",
                    "\u0441\u0435\u043f",
                    "\u043e\u043a\u0442",
                    "\u043d\u043e\u0435",
                    "\u0434\u0435\u043a",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u044f\u043d\u0443\u0430\u0440\u0438",
                    "\u0444\u0435\u0432\u0440\u0443\u0430\u0440\u0438",
                    "\u043c\u0430\u0440\u0442",
                    "\u0430\u043f\u0440\u0438\u043b",
                    "\u043c\u0430\u0439",
                    "\u044e\u043d\u0438",
                    "\u044e\u043b\u0438",
                    "\u0430\u0432\u0433\u0443\u0441\u0442",
                    "\u0441\u0435\u043f\u0442\u0435\u043c\u0432\u0440\u0438",
                    "\u043e\u043a\u0442\u043e\u043c\u0432\u0440\u0438",
                    "\u043d\u043e\u0435\u043c\u0432\u0440\u0438",
                    "\u0434\u0435\u043a\u0435\u043c\u0432\u0440\u0438",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "\u044f",
                    "\u0444",
                    "\u043c",
                    "\u0430",
                    "\u043c",
                    "\u044e",
                    "\u044e",
                    "\u0430",
                    "\u0441",
                    "\u043e",
                    "\u043d",
                    "\u0434",
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
                sharedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "timezone.gmtFormat",
                "\u0413\u0440\u0438\u043d\u0443\u0438\u0447{0}" },
        };
    }
}
