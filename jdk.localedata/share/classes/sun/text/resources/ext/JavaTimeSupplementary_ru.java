package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ru extends OpenListResourceBundle {
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
            "\u0414\u041f",
            "\u041f\u041f",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y '\u0433'. GGGG",
            "d MMMM y '\u0433'. GGGG",
            "d MMM y '\u0433'. GGGG",
            "dd.MM.y GGGG",
        };

        final String[] sharedDayNarrows = {
            "\u0432\u0441",
            "\u043f\u043d",
            "\u0432\u0442",
            "\u0441\u0440",
            "\u0447\u0442",
            "\u043f\u0442",
            "\u0441\u0431",
        };

        final String[] sharedDayNames = {
            "\u0432\u043e\u0441\u043a\u0440\u0435\u0441\u0435\u043d\u044c\u0435",
            "\u043f\u043e\u043d\u0435\u0434\u0435\u043b\u044c\u043d\u0438\u043a",
            "\u0432\u0442\u043e\u0440\u043d\u0438\u043a",
            "\u0441\u0440\u0435\u0434\u0430",
            "\u0447\u0435\u0442\u0432\u0435\u0440\u0433",
            "\u043f\u044f\u0442\u043d\u0438\u0446\u0430",
            "\u0441\u0443\u0431\u0431\u043e\u0442\u0430",
        };

        final String[] sharedMonthNames = {
            "\u041c\u0443\u0445\u0430\u0440\u0440\u0430\u043c",
            "\u0421\u0430\u0444\u0430\u0440",
            "\u0420\u0430\u0431\u0438-\u0443\u043b\u044c-\u0430\u0432\u0432\u0430\u043b\u044c",
            "\u0420\u0430\u0431\u0438-\u0443\u043b\u044c-\u0430\u0445\u0438\u0440",
            "\u0414\u0436\u0443\u043c\u0430\u0434-\u0443\u043b\u044c-\u0430\u0432\u0432\u0430\u043b\u044c",
            "\u0414\u0436\u0443\u043c\u0430\u0434-\u0443\u043b\u044c-\u0430\u0445\u0438\u0440",
            "\u0420\u0430\u0434\u0436\u0430\u0431",
            "\u0428\u0430\u0430\u0431\u0430\u043d",
            "\u0420\u0430\u043c\u0430\u0434\u0430\u043d",
            "\u0428\u0430\u0432\u0432\u0430\u043b\u044c",
            "\u0417\u0443\u043b\u044c-\u041a\u0430\u0430\u0434\u0430",
            "\u0417\u0443\u043b\u044c-\u0425\u0438\u0434\u0436\u0436\u0430",
            "",
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
            "d MMM y '\u0433'. G",
            "dd.MM.y G",
        };

        final String[] sharedEras = {
            "Before R.O.C.",
            "Minguo",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u0431\u0443\u0434\u0434\u0438\u0439\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c" },
            { "calendarname.gregorian",
                "\u0433\u0440\u0438\u0433\u043e\u0440\u0438\u0430\u043d\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c" },
            { "calendarname.gregory",
                "\u0433\u0440\u0438\u0433\u043e\u0440\u0438\u0430\u043d\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c" },
            { "calendarname.islamic",
                "\u0438\u0441\u043b\u0430\u043c\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c" },
            { "calendarname.islamic-civil",
                "\u0418\u0441\u043b\u0430\u043c\u0441\u043a\u0438\u0439 \u0433\u0440\u0430\u0436\u0434\u0430\u043d\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c" },
            { "calendarname.islamic-umalqura",
                "\u0438\u0441\u043b\u0430\u043c\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c (\u0423\u043c\u043c \u0430\u043b\u044c-\u041a\u0443\u0440\u0430)" },
            { "calendarname.japanese",
                "\u044f\u043f\u043e\u043d\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c" },
            { "calendarname.roc",
                "\u043a\u0438\u0442\u0430\u0439\u0441\u043a\u0438\u0439 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440\u044c" },
            { "field.dayperiod",
                "\u0414\u041f/\u041f\u041f" },
            { "field.era",
                "\u044d\u0440\u0430" },
            { "field.hour",
                "\u0447\u0430\u0441" },
            { "field.minute",
                "\u043c\u0438\u043d\u0443\u0442\u0430" },
            { "field.month",
                "\u043c\u0435\u0441\u044f\u0446" },
            { "field.second",
                "\u0441\u0435\u043a\u0443\u043d\u0434\u0430" },
            { "field.week",
                "\u043d\u0435\u0434\u0435\u043b\u044f" },
            { "field.weekday",
                "\u0434\u0435\u043d\u044c \u043d\u0435\u0434\u0435\u043b\u0438" },
            { "field.year",
                "\u0433\u043e\u0434" },
            { "field.zone",
                "\u0447\u0430\u0441\u043e\u0432\u043e\u0439 \u043f\u043e\u044f\u0441" },
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
                sharedJavaTimeDatePatterns },
            { "java.time.buddhist.short.Eras",
                new String[] {
                    "BC",
                    "BE",
                }
            },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.long.Eras",
                new String[] {
                    "\u043e\u0442 \u0420\u043e\u0436\u0434\u0435\u0441\u0442\u0432\u0430 \u0425\u0440\u0438\u0441\u0442\u043e\u0432\u0430",
                    "\u042d\u043f\u043e\u0445\u0430 \u041c\u044d\u0439\u0434\u0437\u0438",
                    "\u042d\u043f\u043e\u0445\u0430 \u0422\u0430\u0439\u0441\u044c\u043e",
                    "\u0421\u044c\u043e\u0432\u0430",
                    "\u042d\u043f\u043e\u0445\u0430 \u0425\u044d\u0439\u0441\u044d\u0439",
                    "\u0420\u044d\u0439\u0432\u0430",
                }
            },
            { "java.time.japanese.short.Eras",
                new String[] {
                    "\u043d. \u044d.",
                    "\u042d\u043f\u043e\u0445\u0430 \u041c\u044d\u0439\u0434\u0437\u0438",
                    "\u042d\u043f\u043e\u0445\u0430 \u0422\u0430\u0439\u0441\u044c\u043e",
                    "\u0421\u044c\u043e\u0432\u0430",
                    "\u042d\u043f\u043e\u0445\u0430 \u0425\u044d\u0439\u0441\u044d\u0439",
                    "\u0420\u044d\u0439\u0432\u0430",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "\u0434\u043e \u0420\u043e\u0436\u0434\u0435\u0441\u0442\u0432\u0430 \u0425\u0440\u0438\u0441\u0442\u043e\u0432\u0430",
                    "\u043e\u0442 \u0420\u043e\u0436\u0434\u0435\u0441\u0442\u0432\u0430 \u0425\u0440\u0438\u0441\u0442\u043e\u0432\u0430",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u0434\u043e \u043d.\u044d.",
                    "\u043d.\u044d.",
                }
            },
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
                new String[] {
                    "\u044f\u043d\u0432.",
                    "\u0444\u0435\u0432\u0440.",
                    "\u043c\u0430\u0440.",
                    "\u0430\u043f\u0440.",
                    "\u043c\u0430\u044f",
                    "\u0438\u044e\u043d.",
                    "\u0438\u044e\u043b.",
                    "\u0430\u0432\u0433.",
                    "\u0441\u0435\u043d\u0442.",
                    "\u043e\u043a\u0442.",
                    "\u043d\u043e\u044f\u0431.",
                    "\u0434\u0435\u043a.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u044f\u043d\u0432\u0430\u0440\u044f",
                    "\u0444\u0435\u0432\u0440\u0430\u043b\u044f",
                    "\u043c\u0430\u0440\u0442\u0430",
                    "\u0430\u043f\u0440\u0435\u043b\u044f",
                    "\u043c\u0430\u044f",
                    "\u0438\u044e\u043d\u044f",
                    "\u0438\u044e\u043b\u044f",
                    "\u0430\u0432\u0433\u0443\u0441\u0442\u0430",
                    "\u0441\u0435\u043d\u0442\u044f\u0431\u0440\u044f",
                    "\u043e\u043a\u0442\u044f\u0431\u0440\u044f",
                    "\u043d\u043e\u044f\u0431\u0440\u044f",
                    "\u0434\u0435\u043a\u0430\u0431\u0440\u044f",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "\u042f",
                    "\u0424",
                    "\u041c",
                    "\u0410",
                    "\u041c",
                    "\u0418",
                    "\u0418",
                    "\u0410",
                    "\u0421",
                    "\u041e",
                    "\u041d",
                    "\u0414",
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
