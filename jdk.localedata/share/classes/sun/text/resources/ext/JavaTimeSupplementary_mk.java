package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_mk extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "\u0458\u0430\u043d-\u043c\u0430\u0440",
            "\u0430\u043f\u0440-\u0458\u0443\u043d",
            "\u0458\u0443\u043b-\u0441\u0435\u043f",
            "\u043e\u043a\u0442-\u0434\u0435\u043a",
        };

        final String[] sharedQuarterNames = {
            "\u043f\u0440\u0432\u043e \u0442\u0440\u043e\u043c\u0435\u0441\u0435\u0447\u0458\u0435",
            "\u0432\u0442\u043e\u0440\u043e \u0442\u0440\u043e\u043c\u0435\u0441\u0435\u0447\u0458\u0435",
            "\u0442\u0440\u0435\u0442\u043e \u0442\u0440\u043e\u043c\u0435\u0441\u0435\u0447\u0458\u0435",
            "\u0447\u0435\u0442\u0432\u0440\u0442\u043e \u0442\u0440\u043e\u043c\u0435\u0441\u0435\u0447\u0458\u0435",
        };

        final String[] sharedAmPmMarkers = {
            "\u043f\u0440\u0435\u0442\u043f\u043b\u0430\u0434\u043d\u0435",
            "\u043f\u043e\u043f\u043b\u0430\u0434\u043d\u0435",
        };

        final String[] sharedDatePatterns = {
            "EEEE, dd MMMM y '\u0433'. GGGG",
            "dd MMMM y '\u0433'. GGGG",
            "dd.M.y GGGG",
            "dd.M.y G",
        };

        final String[] sharedDayAbbreviations = {
            "\u043d\u0435\u0434.",
            "\u043f\u043e\u043d.",
            "\u0432\u0442.",
            "\u0441\u0440\u0435.",
            "\u0447\u0435\u0442.",
            "\u043f\u0435\u0442.",
            "\u0441\u0430\u0431.",
        };

        final String[] sharedDayNames = {
            "\u043d\u0435\u0434\u0435\u043b\u0430",
            "\u043f\u043e\u043d\u0435\u0434\u0435\u043b\u043d\u0438\u043a",
            "\u0432\u0442\u043e\u0440\u043d\u0438\u043a",
            "\u0441\u0440\u0435\u0434\u0430",
            "\u0447\u0435\u0442\u0432\u0440\u0442\u043e\u043a",
            "\u043f\u0435\u0442\u043e\u043a",
            "\u0441\u0430\u0431\u043e\u0442\u0430",
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

        final String[] sharedNarrowAmPmMarkers = {
            "\u043f\u0440\u0435\u0442.",
            "\u043f\u043e\u043f\u043b.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, dd MMMM y '\u0433'. G",
            "dd MMMM y '\u0433'. G",
            "dd.M.y G",
            "dd.M.y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "\u0411\u0443\u0434\u0438\u0441\u0442\u0438\u0447\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregorian",
                "\u0413\u0440\u0435\u0433\u043e\u0440\u0438\u0458\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregory",
                "\u0413\u0440\u0435\u0433\u043e\u0440\u0438\u0458\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic",
                "\u0418\u0441\u043b\u0430\u043c\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic-civil",
                "\u0418\u0441\u043b\u0430\u043c\u0441\u043a\u0438 \u0433\u0440\u0430\u0453\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.japanese",
                "\u0408\u0430\u043f\u043e\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.roc",
                "\u041c\u0438\u043d\u0433\u0443\u043e-\u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "field.dayperiod",
                "\u043f\u0440\u0435\u0442\u043f\u043b\u0430\u0434\u043d\u0435/\u043f\u043e\u043f\u043b\u0430\u0434\u043d\u0435" },
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
                "\u043d\u0435\u0434\u0435\u043b\u0430" },
            { "field.weekday",
                "\u0434\u0435\u043d \u0432\u043e \u043d\u0435\u0434\u0435\u043b\u0430\u0442\u0430" },
            { "field.year",
                "\u0433\u043e\u0434\u0438\u043d\u0430" },
            { "field.zone",
                "\u0432\u0440\u0435\u043c\u0435\u043d\u0441\u043a\u0430 \u0437\u043e\u043d\u0430" },
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
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
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
                    "\u043f\u0440\u0435\u0434 \u043d\u0430\u0448\u0430\u0442\u0430 \u0435\u0440\u0430",
                    "\u043e\u0434 \u043d\u0430\u0448\u0430\u0442\u0430 \u0435\u0440\u0430",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u043f\u0440.\u043d.\u0435.",
                    "\u0430\u0435.",
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
                    "\u0458\u0430\u043d.",
                    "\u0444\u0435\u0432.",
                    "\u043c\u0430\u0440.",
                    "\u0430\u043f\u0440.",
                    "\u043c\u0430\u0458",
                    "\u0458\u0443\u043d.",
                    "\u0458\u0443\u043b.",
                    "\u0430\u0432\u0433.",
                    "\u0441\u0435\u043f\u0442.",
                    "\u043e\u043a\u0442.",
                    "\u043d\u043e\u0435\u043c.",
                    "\u0434\u0435\u043a.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u0458\u0430\u043d\u0443\u0430\u0440\u0438",
                    "\u0444\u0435\u0432\u0440\u0443\u0430\u0440\u0438",
                    "\u043c\u0430\u0440\u0442",
                    "\u0430\u043f\u0440\u0438\u043b",
                    "\u043c\u0430\u0458",
                    "\u0458\u0443\u043d\u0438",
                    "\u0458\u0443\u043b\u0438",
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
                    "\u0458",
                    "\u0444",
                    "\u043c",
                    "\u0430",
                    "\u043c",
                    "\u0458",
                    "\u0458",
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
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
