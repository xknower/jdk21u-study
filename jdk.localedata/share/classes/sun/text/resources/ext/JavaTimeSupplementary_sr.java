package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_sr extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "\u041a1",
            "\u041a2",
            "\u041a3",
            "\u041a4",
        };

        final String[] sharedQuarterNames = {
            "\u043f\u0440\u0432\u0438 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "\u0434\u0440\u0443\u0433\u0438 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "\u0442\u0440\u0435\u045b\u0438 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            "\u0447\u0435\u0442\u0432\u0440\u0442\u0438 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
        };

        final String[] sharedQuarterNarrows = {
            "1.",
            "2.",
            "3.",
            "4.",
        };

        final String[] sharedAmPmMarkers = {
            "\u043f\u0440\u0435 \u043f\u043e\u0434\u043d\u0435",
            "\u043f\u043e \u043f\u043e\u0434\u043d\u0435",
        };

        final String[] sharedDatePatterns = {
            "EEEE, dd. MMMM y. GGGG",
            "dd. MMMM y. GGGG",
            "dd.MM.y. GGGG",
            "d.M.y. G",
        };

        final String[] sharedDayAbbreviations = {
            "\u043d\u0435\u0434",
            "\u043f\u043e\u043d",
            "\u0443\u0442\u043e",
            "\u0441\u0440\u0435",
            "\u0447\u0435\u0442",
            "\u043f\u0435\u0442",
            "\u0441\u0443\u0431",
        };

        final String[] sharedDayNames = {
            "\u043d\u0435\u0434\u0435\u0459\u0430",
            "\u043f\u043e\u043d\u0435\u0434\u0435\u0459\u0430\u043a",
            "\u0443\u0442\u043e\u0440\u0430\u043a",
            "\u0441\u0440\u0435\u0434\u0430",
            "\u0447\u0435\u0442\u0432\u0440\u0442\u0430\u043a",
            "\u043f\u0435\u0442\u0430\u043a",
            "\u0441\u0443\u0431\u043e\u0442\u0430",
        };

        final String[] sharedDayNarrows = {
            "\u043d",
            "\u043f",
            "\u0443",
            "\u0441",
            "\u0447",
            "\u043f",
            "\u0441",
        };

        final String[] sharedEras = {
            "",
            "\u0410\u0425",
        };

        final String[] sharedTimePatterns = {
            "HH.mm.ss zzzz",
            "HH.mm.ss z",
            "HH.mm.ss",
            "HH.mm",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, dd. MMMM y. G",
            "dd. MMMM y. G",
            "dd.MM.y. G",
            "d.M.y. GGGGG",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "\u0411\u0415",
        };

        final String[] sharedShortEras = {
            "\u041f\u0440\u0435 \u0420\u041a",
            "\u0420\u041a",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "calendarname.buddhist",
                "\u0431\u0443\u0434\u0438\u0441\u0442\u0438\u0447\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregorian",
                "\u0433\u0440\u0435\u0433\u043e\u0440\u0438\u0458\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.gregory",
                "\u0433\u0440\u0435\u0433\u043e\u0440\u0438\u0458\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic",
                "\u0438\u0441\u043b\u0430\u043c\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.islamic-civil",
                "\u0418\u0441\u043b\u0430\u043c\u0441\u043a\u0438 \u0446\u0438\u0432\u0438\u043b\u043d\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.japanese",
                "\u0458\u0430\u043f\u0430\u043d\u0441\u043a\u0438 \u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440" },
            { "calendarname.roc",
                "\u043a\u0430\u043b\u0435\u043d\u0434\u0430\u0440 \u0420\u0435\u043f\u0443\u0431\u043b\u0438\u043a\u0435 \u041a\u0438\u043d\u0435" },
            { "field.dayperiod",
                "\u043f\u0440\u0435 \u043f\u043e\u0434\u043d\u0435/\u043f\u043e \u043f\u043e\u0434\u043d\u0435" },
            { "field.era",
                "\u0435\u0440\u0430" },
            { "field.hour",
                "\u0441\u0430\u0442" },
            { "field.minute",
                "\u043c\u0438\u043d\u0443\u0442" },
            { "field.month",
                "\u043c\u0435\u0441\u0435\u0446" },
            { "field.second",
                "\u0441\u0435\u043a\u0443\u043d\u0434" },
            { "field.week",
                "\u043d\u0435\u0434\u0435\u0459\u0430" },
            { "field.weekday",
                "\u0434\u0430\u043d \u0443 \u043d\u0435\u0434\u0435\u0459\u0438" },
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
            { "islamic.Eras",
                sharedEras },
            { "islamic.MonthNames",
                new String[] {
                    "\u041c\u0443\u0440\u0430\u0445\u0430\u043c",
                    "\u0421\u0430\u0444\u0430\u0440",
                    "\u0420\u0430\u0431\u0438\u02bb I",
                    "\u0420\u0430\u0431\u0438\u02bb II",
                    "\u0408\u0443\u043c\u0430\u0434\u0430 I",
                    "\u0408\u0443\u043c\u0430\u0434\u0430 II",
                    "\u0420\u0430\u0452\u0430\u0431",
                    "\u0428\u0430\u02bb\u0431\u0430\u043d",
                    "\u0420\u0430\u043c\u0430\u0434\u0430\u043d",
                    "\u0428\u0430\u0432\u0430\u043b",
                    "\u0414\u0443\u02bb\u043b-\u041a\u0438\u02bb\u0434\u0430",
                    "\u0414\u0443\u02bb\u043b-\u0445\u0438\u0452\u0430",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
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
                sharedNarrowAmPmMarkers },
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
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE, MMMM d, y G",
                    "MMMM d, y G",
                    "MMM d, y G",
                    "M/d/yy G",
                }
            },
            { "java.time.japanese.long.Eras",
                new String[] {
                    "\u043d\u043e\u0432\u0435 \u0435\u0440\u0435",
                    "\u041c\u0435\u0438\u0452\u0438",
                    "\u0422\u0430\u0438\u0448\u043e",
                    "\u0428\u043e\u0432\u0430",
                    "\u0425\u0430\u0438\u0441\u0435\u0438",
                    "\u0420\u0435\u0438\u0432\u0430",
                }
            },
            { "java.time.japanese.short.Eras",
                new String[] {
                    "\u043d. \u0435.",
                    "\u041c\u0435\u0438\u0452\u0438",
                    "\u0422\u0430\u0438\u0448\u043e",
                    "\u0428\u043e\u0432\u0430",
                    "\u0425\u0430\u0438\u0441\u0435\u0438",
                    "\u0420\u0435\u0438\u0432\u0430",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "\u043f\u0440\u0435 \u043d\u043e\u0432\u0435 \u0435\u0440\u0435",
                    "\u043d\u043e\u0432\u0435 \u0435\u0440\u0435",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "\u043f. \u043d. \u0435.",
                    "\u043d. \u0435",
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
            { "roc.Eras",
                sharedShortEras },
            { "roc.MonthAbbreviations",
                new String[] {
                    "\u0458\u0430\u043d",
                    "\u0444\u0435\u0431",
                    "\u043c\u0430\u0440",
                    "\u0430\u043f\u0440",
                    "\u043c\u0430\u0458",
                    "\u0458\u0443\u043d",
                    "\u0458\u0443\u043b",
                    "\u0430\u0432\u0433",
                    "\u0441\u0435\u043f",
                    "\u043e\u043a\u0442",
                    "\u043d\u043e\u0432",
                    "\u0434\u0435\u0446",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "\u0458\u0430\u043d\u0443\u0430\u0440",
                    "\u0444\u0435\u0431\u0440\u0443\u0430\u0440",
                    "\u043c\u0430\u0440\u0442",
                    "\u0430\u043f\u0440\u0438\u043b",
                    "\u043c\u0430\u0458",
                    "\u0458\u0443\u043d",
                    "\u0458\u0443\u043b",
                    "\u0430\u0432\u0433\u0443\u0441\u0442",
                    "\u0441\u0435\u043f\u0442\u0435\u043c\u0431\u0430\u0440",
                    "\u043e\u043a\u0442\u043e\u0431\u0430\u0440",
                    "\u043d\u043e\u0432\u0435\u043c\u0431\u0430\u0440",
                    "\u0434\u0435\u0446\u0435\u043c\u0431\u0430\u0440",
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
            { "roc.QuarterNarrows",
                sharedQuarterNarrows },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.long.Eras",
                sharedShortEras },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.Eras",
                sharedShortEras },
            { "roc.short.Eras",
                sharedShortEras },
            { "timezone.hourFormat",
                "+HHmm;-HHmm" },
        };
    }
}
