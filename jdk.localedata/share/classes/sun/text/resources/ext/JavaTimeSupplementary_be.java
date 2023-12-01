package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_be extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1-\u0448\u044b \u043a\u0432.",
            "2-\u0433\u0456 \u043a\u0432.",
            "3-\u0446\u0456 \u043a\u0432.",
            "4-\u0442\u044b \u043a\u0432.",
        };

        final String[] sharedQuarterNames = {
            "1-\u0448\u044b \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "2-\u0433\u0456 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "3-\u0446\u0456 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "4-\u0442\u044b \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
        };

        final String[] sharedAmPmMarkers = {
            "\u0434\u0430 \u043f\u0430\u045e\u0434\u043d\u044f",
            "\u043f\u0430\u0441\u043b\u044f \u043f\u0430\u045e\u0434\u043d\u044f",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y GGGG",
            "d MMMM y GGGG",
            "d.M.y GGGG",
            "d.M.y G",
        };

        final String[] sharedDayAbbreviations = {
            "\u043d\u0434",
            "\u043f\u043d",
            "\u0430\u045e",
            "\u0441\u0440",
            "\u0447\u0446",
            "\u043f\u0442",
            "\u0441\u0431",
        };

        final String[] sharedDayNames = {
            "\u043d\u044f\u0434\u0437\u0435\u043b\u044f",
            "\u043f\u0430\u043d\u044f\u0434\u0437\u0435\u043b\u0430\u043a",
            "\u0430\u045e\u0442\u043e\u0440\u0430\u043a",
            "\u0441\u0435\u0440\u0430\u0434\u0430",
            "\u0447\u0430\u0446\u0432\u0435\u0440",
            "\u043f\u044f\u0442\u043d\u0456\u0446\u0430",
            "\u0441\u0443\u0431\u043e\u0442\u0430",
        };

        final String[] sharedDayNarrows = {
            "\u043d",
            "\u043f",
            "\u0430",
            "\u0441",
            "\u0447",
            "\u043f",
            "\u0441",
        };

        final String[] sharedTimePatterns = {
            "HH.mm.ss zzzz",
            "HH.mm.ss z",
            "HH.mm.ss",
            "HH.mm",
        };

        final String[] sharedAbbreviatedAmPmMarkers = {
            "\u0440\u0430\u043d\u0456\u0446\u044b",
            "\u0432\u0435\u0447\u0430\u0440\u0430",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "\u0440\u0430\u043d.",
            "\u0432\u0435\u0447.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "d.M.y G",
            "d.M.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u0431\u0443\u0434\u044b\u0439\u0441\u043a\u0456 \u043a\u0430\u043b\u044f\u043d\u0434\u0430\u0440" },
            { "calendarname.gregorian",
                "\u0433\u0440\u044b\u0433\u0430\u0440\u044b\u044f\u043d\u0441\u043a\u0456 \u043a\u0430\u043b\u044f\u043d\u0434\u0430\u0440" },
            { "calendarname.gregory",
                "\u0433\u0440\u044b\u0433\u0430\u0440\u044b\u044f\u043d\u0441\u043a\u0456 \u043a\u0430\u043b\u044f\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic",
                "\u043c\u0443\u0441\u0443\u043b\u044c\u043c\u0430\u043d\u0441\u043a\u0456 \u043a\u0430\u043b\u044f\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic-civil",
                "\u043c\u0443\u0441\u0443\u043b\u044c\u043c\u0430\u043d\u0441\u043a\u0456 \u0441\u0432\u0435\u0446\u043a\u0456 \u043a\u0430\u043b\u044f\u043d\u0434\u0430\u0440" },
            { "calendarname.japanese",
                "\u044f\u043f\u043e\u043d\u0441\u043a\u0456 \u043a\u0430\u043b\u044f\u043d\u0434\u0430\u0440" },
            { "calendarname.roc",
                "\u043a\u0430\u043b\u044f\u043d\u0434\u0430\u0440 \u041c\u0456\u043d\u044c\u0433\u043e" },
            { "field.dayperiod",
                "\u0414\u041f/\u041f\u041f" },
            { "field.era",
                "\u044d\u0440\u0430" },
            { "field.hour",
                "\u0433\u0430\u0434\u0437\u0456\u043d\u0430" },
            { "field.minute",
                "\u0445\u0432\u0456\u043b\u0456\u043d\u0430" },
            { "field.month",
                "\u043c\u0435\u0441\u044f\u0446" },
            { "field.second",
                "\u0441\u0435\u043a\u0443\u043d\u0434\u0430" },
            { "field.week",
                "\u0442\u044b\u0434\u0437\u0435\u043d\u044c" },
            { "field.weekday",
                "\u0434\u0437\u0435\u043d\u044c \u0442\u044b\u0434\u043d\u044f" },
            { "field.year",
                "\u0433\u043e\u0434" },
            { "field.zone",
                "\u0447\u0430\u0441\u0430\u0432\u044b \u043f\u043e\u044f\u0441" },
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
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAbbreviatedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                new String[] {
                    "EEEE, d MMMM y G",
                    "d MMMM y G",
                    "d MMM y G",
                    "d.M.yy",
                }
            },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "\u0434\u0430 \u043d\u0430\u0448\u0430\u0439 \u044d\u0440\u044b",
                    "\u043d\u0430\u0448\u0430\u0439 \u044d\u0440\u044b",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u0434\u0430 \u043d.\u0435.",
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
                    "\u0441\u0442\u0443",
                    "\u043b\u044e\u0442",
                    "\u0441\u0430\u043a",
                    "\u043a\u0440\u0430",
                    "\u043c\u0430\u044f",
                    "\u0447\u044d\u0440",
                    "\u043b\u0456\u043f",
                    "\u0436\u043d\u0456",
                    "\u0432\u0435\u0440",
                    "\u043a\u0430\u0441",
                    "\u043b\u0456\u0441",
                    "\u0441\u043d\u0435",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u0441\u0442\u0443\u0434\u0437\u0435\u043d\u044f",
                    "\u043b\u044e\u0442\u0430\u0433\u0430",
                    "\u0441\u0430\u043a\u0430\u0432\u0456\u043a\u0430",
                    "\u043a\u0440\u0430\u0441\u0430\u0432\u0456\u043a\u0430",
                    "\u043c\u0430\u044f",
                    "\u0447\u044d\u0440\u0432\u0435\u043d\u044f",
                    "\u043b\u0456\u043f\u0435\u043d\u044f",
                    "\u0436\u043d\u0456\u045e\u043d\u044f",
                    "\u0432\u0435\u0440\u0430\u0441\u043d\u044f",
                    "\u043a\u0430\u0441\u0442\u0440\u044b\u0447\u043d\u0456\u043a\u0430",
                    "\u043b\u0456\u0441\u0442\u0430\u043f\u0430\u0434\u0430",
                    "\u0441\u043d\u0435\u0436\u043d\u044f",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "\u0441",
                    "\u043b",
                    "\u0441",
                    "\u043a",
                    "\u043c",
                    "\u0447",
                    "\u043b",
                    "\u0436",
                    "\u0432",
                    "\u043a",
                    "\u043b",
                    "\u0441",
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
            { "timezone.hourFormat",
                "+HH.mm;-HH.mm" },
        };
    }
}
