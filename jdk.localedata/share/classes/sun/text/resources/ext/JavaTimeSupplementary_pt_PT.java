package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_pt_PT extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "1.\u00ba trimestre",
            "2.\u00ba trimestre",
            "3.\u00ba trimestre",
            "4.\u00ba trimestre",
        };

        final String[] sharedAmPmMarkers = {
            "da manh\u00e3",
            "da tarde",
        };

        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "GGGG y MMM d",
            "d/M/y GGGG",
        };

        final String[] sharedDayAbbreviations = {
            "domingo",
            "segunda",
            "ter\u00e7a",
            "quarta",
            "quinta",
            "sexta",
            "s\u00e1bado",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "G y MMM d",
            "d/M/y G",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterNames },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Calend\u00e1rio budista" },
            { "calendarname.gregorian",
                "Calend\u00e1rio gregoriano" },
            { "calendarname.gregory",
                "Calend\u00e1rio gregoriano" },
            { "calendarname.islamic",
                "Calend\u00e1rio isl\u00e2mico" },
            { "calendarname.japanese",
                "Calend\u00e1rio japon\u00eas" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayAbbreviations",
                sharedDayAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.abbreviated.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE, d 'de' MMMM 'de' y G",
                    "d 'de' MMMM 'de' y G",
                    "dd/MM/y G",
                    "d/M/y G",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.abbreviated.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
