package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_uk extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1-\u0439 \u043a\u0432.",
            "2-\u0439 \u043a\u0432.",
            "3-\u0439 \u043a\u0432.",
            "4-\u0439 \u043a\u0432.",
        };

        final String[] sharedQuarterNames = {
            "1-\u0439 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "2-\u0439 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "3-\u0439 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "4-\u0439 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
        };

        final String[] sharedAmPmMarkers = {
            "\u0434\u043f",
            "\u043f\u043f",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y '\u0440'. GGGG",
            "d MMMM y '\u0440'. GGGG",
            "d MMM y GGGG",
            "dd.MM.yy G",
        };

        final String[] sharedDayAbbreviations = {
            "\u041d\u0434",
            "\u041f\u043d",
            "\u0412\u0442",
            "\u0421\u0440",
            "\u0427\u0442",
            "\u041f\u0442",
            "\u0421\u0431",
        };

        final String[] sharedDayNames = {
            "\u043d\u0435\u0434\u0456\u043b\u044f",
            "\u043f\u043e\u043d\u0435\u0434\u0456\u043b\u043e\u043a",
            "\u0432\u0456\u0432\u0442\u043e\u0440\u043e\u043a",
            "\u0441\u0435\u0440\u0435\u0434\u0430",
            "\u0447\u0435\u0442\u0432\u0435\u0440",
            "\u043f\u02bc\u044f\u0442\u043d\u0438\u0446\u044f",
            "\u0441\u0443\u0431\u043e\u0442\u0430",
        };

        final String[] sharedDayNarrows = {
            "\u041d",
            "\u041f",
            "\u0412",
            "\u0421",
            "\u0427",
            "\u041f",
            "\u0421",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y '\u0440'. G",
            "d MMMM y '\u0440'. G",
            "d MMM y G",
            "dd.MM.yy GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u0431\u0443\u0434\u0434\u0456\u0439\u0441\u044c\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregorian",
                "\u0433\u0440\u0438\u0433\u043e\u0440\u0456\u0430\u043d\u0441\u044c\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregory",
                "\u0433\u0440\u0438\u0433\u043e\u0440\u0456\u0430\u043d\u0441\u044c\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic",
                "\u043c\u0443\u0441\u0443\u043b\u044c\u043c\u0430\u043d\u0441\u044c\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic-civil",
                "\u043c\u0443\u0441\u0443\u043b\u044c\u043c\u0430\u043d\u0441\u044c\u043a\u0438\u0439 \u0441\u0432\u0456\u0442\u0441\u044c\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.japanese",
                "\u044f\u043f\u043e\u043d\u0441\u044c\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.roc",
                "\u043a\u0438\u0442\u0430\u0439\u0441\u044c\u043a\u0438\u0439 \u0433\u0440\u0438\u0433\u043e\u0440\u0456\u0430\u043d\u0441\u044c\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "field.dayperiod",
                "\u0447\u0430\u0441\u0442\u0438\u043d\u0430 \u0434\u043e\u0431\u0438" },
            { "field.era",
                "\u0435\u0440\u0430" },
            { "field.hour",
                "\u0433\u043e\u0434\u0438\u043d\u0430" },
            { "field.minute",
                "\u0445\u0432\u0438\u043b\u0438\u043d\u0430" },
            { "field.month",
                "\u043c\u0456\u0441\u044f\u0446\u044c" },
            { "field.second",
                "\u0441\u0435\u043a\u0443\u043d\u0434\u0430" },
            { "field.week",
                "\u0442\u0438\u0436\u0434\u0435\u043d\u044c" },
            { "field.weekday",
                "\u0434\u0435\u043d\u044c \u0442\u0438\u0436\u043d\u044f" },
            { "field.year",
                "\u0440\u0456\u043a" },
            { "field.zone",
                "\u0447\u0430\u0441\u043e\u0432\u0438\u0439 \u043f\u043e\u044f\u0441" },
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
                    "\u041c\u0443\u0445\u0430\u0440\u0440\u0430\u043c",
                    "\u0421\u0430\u0444\u0430\u0440",
                    "\u0420\u0430\u0431\u0456 I",
                    "\u0420\u0430\u0431\u0456 II",
                    "\u0414\u0436\u0443\u043c\u0430\u0434\u0430 I",
                    "\u0414\u0436\u0443\u043c\u0430\u0434\u0430 II",
                    "\u0420\u0430\u0434\u0436\u0430\u0431",
                    "\u0428\u0430\u0430\u0431\u0430\u043d",
                    "\u0420\u0430\u043c\u0430\u0434\u0430\u043d",
                    "\u0414\u0430\u0432\u0432\u0430\u043b",
                    "\u0417\u0443-\u043b\u044c-\u043a\u0430\u0430\u0434\u0430",
                    "\u0417\u0443-\u043b\u044c-\u0445\u0456\u0434\u0436\u0430",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
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
                    "\u0434\u043e \u043d\u0430\u0448\u043e\u0457 \u0435\u0440\u0438",
                    "\u043d\u0430\u0448\u043e\u0457 \u0435\u0440\u0438",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u0434\u043e \u043d.\u0435.",
                    "\u043f\u0456\u0441\u043b\u044f \u043d.\u0435.",
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
                    "\u0441\u0456\u0447.",
                    "\u043b\u044e\u0442.",
                    "\u0431\u0435\u0440.",
                    "\u043a\u0432\u0456\u0442.",
                    "\u0442\u0440\u0430\u0432.",
                    "\u0447\u0435\u0440\u0432.",
                    "\u043b\u0438\u043f.",
                    "\u0441\u0435\u0440\u043f.",
                    "\u0432\u0435\u0440.",
                    "\u0436\u043e\u0432\u0442.",
                    "\u043b\u0438\u0441\u0442.",
                    "\u0433\u0440\u0443\u0434.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u0441\u0456\u0447\u043d\u044f",
                    "\u043b\u044e\u0442\u043e\u0433\u043e",
                    "\u0431\u0435\u0440\u0435\u0437\u043d\u044f",
                    "\u043a\u0432\u0456\u0442\u043d\u044f",
                    "\u0442\u0440\u0430\u0432\u043d\u044f",
                    "\u0447\u0435\u0440\u0432\u043d\u044f",
                    "\u043b\u0438\u043f\u043d\u044f",
                    "\u0441\u0435\u0440\u043f\u043d\u044f",
                    "\u0432\u0435\u0440\u0435\u0441\u043d\u044f",
                    "\u0436\u043e\u0432\u0442\u043d\u044f",
                    "\u043b\u0438\u0441\u0442\u043e\u043f\u0430\u0434\u0430",
                    "\u0433\u0440\u0443\u0434\u043d\u044f",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "\u0421",
                    "\u041b",
                    "\u0411",
                    "\u041a",
                    "\u0422",
                    "\u0427",
                    "\u041b",
                    "\u0421",
                    "\u0412",
                    "\u0416",
                    "\u041b",
                    "\u0413",
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
                sharedAmPmMarkers },
        };
    }
}
