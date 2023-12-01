package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_es_DO extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "dd/MM/y GGGG",
            "G y-MM-dd",
        };

        final String[] sharedDayNarrows = {
            "d",
            "l",
            "m",
            "m",
            "j",
            "v",
            "s",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "dd/MM/y G",
            "GGGGG y-MM-dd",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "field.dayperiod",
                "a.m./p.m." },
            { "field.era",
                "Era" },
            { "field.minute",
                "Minuto" },
            { "field.month",
                "Mes" },
            { "field.second",
                "Segundo" },
            { "field.week",
                "Semana" },
            { "field.weekday",
                "D\u00eda de la semana" },
            { "field.year",
                "A\u00f1o" },
            { "field.zone",
                "Zona horaria" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
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
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.MonthAbbreviations",
                new String[] {
                    "ene.",
                    "feb.",
                    "mar.",
                    "abr.",
                    "may.",
                    "jun.",
                    "jul.",
                    "ago.",
                    "sep.",
                    "oct.",
                    "nov.",
                    "dic.",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "e",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "a",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
        };
    }
}
