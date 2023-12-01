package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_hu extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "N1",
            "N2",
            "N3",
            "N4",
        };

        final String[] sharedQuarterNames = {
            "I. negyed\u00e9v",
            "II. negyed\u00e9v",
            "III. negyed\u00e9v",
            "IV. negyed\u00e9v",
        };

        final String[] sharedQuarterNarrows = {
            "1.",
            "2.",
            "3.",
            "4.",
        };

        final String[] sharedAmPmMarkers = {
            "de.",
            "du.",
        };

        final String[] sharedDatePatterns = {
            "GGGG y. MMMM d., EEEE",
            "GGGG y. MMMM d.",
            "GGGG y. MMM d.",
            "G y. M. d.",
        };

        final String[] sharedDayAbbreviations = {
            "V",
            "H",
            "K",
            "Sze",
            "Cs",
            "P",
            "Szo",
        };

        final String[] sharedDayNames = {
            "vas\u00e1rnap",
            "h\u00e9tf\u0151",
            "kedd",
            "szerda",
            "cs\u00fct\u00f6rt\u00f6k",
            "p\u00e9ntek",
            "szombat",
        };

        final String[] sharedDayNarrows = {
            "V",
            "H",
            "K",
            "Sz",
            "Cs",
            "P",
            "Sz",
        };

        final String[] sharedEras = {
            "",
            "MF",
        };

        final String[] sharedTimePatterns = {
            "H:mm:ss zzzz",
            "H:mm:ss z",
            "H:mm:ss",
            "H:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y. MMMM d., EEEE",
            "G y. MMMM d.",
            "G y. MMM d.",
            "GGGGG y. M. d.",
        };

        final String[] sharedJavaTimeLongEras = {
            "BC",
            "BK",
        };

        final String[] sharedShortEras = {
            "R.O.C. el\u0151tt",
            "",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "calendarname.buddhist",
                "Buddhista napt\u00e1r" },
            { "calendarname.gregorian",
                "Gergely-napt\u00e1r" },
            { "calendarname.gregory",
                "Gergely-napt\u00e1r" },
            { "calendarname.islamic",
                "Iszl\u00e1m napt\u00e1r" },
            { "calendarname.islamic-civil",
                "Iszl\u00e1m civil napt\u00e1r" },
            { "calendarname.japanese",
                "Jap\u00e1n napt\u00e1r" },
            { "calendarname.roc",
                "K\u00ednai k\u00f6zt\u00e1rsas\u00e1gi napt\u00e1r" },
            { "field.dayperiod",
                "napszak" },
            { "field.era",
                "\u00e9ra" },
            { "field.hour",
                "\u00f3ra" },
            { "field.minute",
                "perc" },
            { "field.month",
                "h\u00f3nap" },
            { "field.second",
                "m\u00e1sodperc" },
            { "field.week",
                "h\u00e9t" },
            { "field.weekday",
                "h\u00e9t napja" },
            { "field.year",
                "\u00e9v" },
            { "field.zone",
                "id\u0151z\u00f3na" },
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
            { "islamic.MonthAbbreviations",
                new String[] {
                    "Moh.",
                    "Saf.",
                    "R\u00e9b. 1",
                    "R\u00e9b. 2",
                    "Dsem. I",
                    "Dsem. II",
                    "Red.",
                    "Sab.",
                    "Ram.",
                    "Sev.",
                    "Ds\u00fcl k.",
                    "Ds\u00fcl h.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "Moharrem",
                    "Safar",
                    "R\u00e9bi el avvel",
                    "R\u00e9bi el accher",
                    "Dsem\u00e1di el avvel",
                    "Dsem\u00e1di el accher",
                    "Redseb",
                    "Sab\u00e1n",
                    "Ramad\u00e1n",
                    "Sevv\u00e1l",
                    "Ds\u00fcl kade",
                    "Ds\u00fcl hedse",
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
                sharedAmPmMarkers },
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
                    "G y. MMMM d., EEEE",
                    "G y. MMMM d.",
                    "G y.MM.dd.",
                    "GGGGG y.MM.dd.",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "id\u0151sz\u00e1m\u00edt\u00e1sunk el\u0151tt",
                    "id\u0151sz\u00e1m\u00edt\u00e1sunk szerint",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "i.e.",
                    "i.u.",
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
                    "jan.",
                    "febr.",
                    "m\u00e1rc.",
                    "\u00e1pr.",
                    "m\u00e1j.",
                    "j\u00fan.",
                    "j\u00fal.",
                    "aug.",
                    "szept.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "janu\u00e1r",
                    "febru\u00e1r",
                    "m\u00e1rcius",
                    "\u00e1prilis",
                    "m\u00e1jus",
                    "j\u00fanius",
                    "j\u00falius",
                    "augusztus",
                    "szeptember",
                    "okt\u00f3ber",
                    "november",
                    "december",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "\u00c1",
                    "M",
                    "J",
                    "J",
                    "A",
                    "Sz",
                    "O",
                    "N",
                    "D",
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
                sharedAmPmMarkers },
            { "roc.narrow.Eras",
                sharedShortEras },
            { "roc.short.Eras",
                sharedShortEras },
        };
    }
}
