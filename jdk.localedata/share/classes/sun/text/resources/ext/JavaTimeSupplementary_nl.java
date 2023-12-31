package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_nl extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "K1",
            "K2",
            "K3",
            "K4",
        };

        final String[] sharedQuarterNames = {
            "1e kwartaal",
            "2e kwartaal",
            "3e kwartaal",
            "4e kwartaal",
        };

        final String[] sharedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedDatePatterns = {
            "EEEE d MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "dd-MM-yy G",
        };

        final String[] sharedDayAbbreviations = {
            "zo",
            "ma",
            "di",
            "wo",
            "do",
            "vr",
            "za",
        };

        final String[] sharedDayNames = {
            "zondag",
            "maandag",
            "dinsdag",
            "woensdag",
            "donderdag",
            "vrijdag",
            "zaterdag",
        };

        final String[] sharedDayNarrows = {
            "Z",
            "M",
            "D",
            "W",
            "D",
            "V",
            "Z",
        };

        final String[] sharedEras = {
            "",
            "Sa\u02bbna Hizjria",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "dd-MM-yy GGGGG",
        };

        final String[] sharedShortEras = {
            "voor R.O.C.",
            "Minguo",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Boeddhistische kalender" },
            { "calendarname.gregorian",
                "Gregoriaanse kalender" },
            { "calendarname.gregory",
                "Gregoriaanse kalender" },
            { "calendarname.islamic",
                "Islamitische kalender" },
            { "calendarname.islamic-civil",
                "Islamitische kalender (cyclisch)" },
            { "calendarname.islamic-umalqura",
                "Islamitische kalender (Umm al-Qura)" },
            { "calendarname.japanese",
                "Japanse kalender" },
            { "calendarname.roc",
                "Kalender van de Chinese Republiek" },
            { "field.dayperiod",
                "a.m./p.m." },
            { "field.era",
                "tijdperk" },
            { "field.hour",
                "Uur" },
            { "field.minute",
                "minuut" },
            { "field.month",
                "maand" },
            { "field.second",
                "seconde" },
            { "field.week",
                "week" },
            { "field.weekday",
                "dag van de week" },
            { "field.year",
                "jaar" },
            { "field.zone",
                "tijdzone" },
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
                    "Moeh.",
                    "Saf.",
                    "Rab. I",
                    "Rab. II",
                    "Joem. I",
                    "Joem. II",
                    "Raj.",
                    "Sja.",
                    "Ram.",
                    "Sjaw.",
                    "Doe al k.",
                    "Doe al h.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "Moeharram",
                    "Safar",
                    "Rabi\u02bba al awal",
                    "Rabi\u02bba al thani",
                    "Joemad\u02bbal awal",
                    "Joemad\u02bbal thani",
                    "Rajab",
                    "Sja\u02bbaban",
                    "Ramadan",
                    "Sjawal",
                    "Doe al ka\u02bbaba",
                    "Doe al hizja",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
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
                    "na Christus",
                    "Meiji",
                    "Taish\u014d",
                    "Sh\u014dwa",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.japanese.short.Eras",
                new String[] {
                    "n.Chr.",
                    "Meiji",
                    "Taish\u014d",
                    "Sh\u014dwa",
                    "Heisei",
                    "Reiwa",
                }
            },
            { "java.time.long.Eras",
                new String[] {
                    "voor Christus",
                    "na Christus",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "v. Chr.",
                    "n. Chr.",
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
                    "feb.",
                    "mrt.",
                    "apr.",
                    "mei",
                    "jun.",
                    "jul.",
                    "aug.",
                    "sep.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "januari",
                    "februari",
                    "maart",
                    "april",
                    "mei",
                    "juni",
                    "juli",
                    "augustus",
                    "september",
                    "oktober",
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
                    "A",
                    "M",
                    "J",
                    "J",
                    "A",
                    "S",
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
