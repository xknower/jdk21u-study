package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_pl extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "K1",
            "K2",
            "K3",
            "K4",
        };

        final String[] sharedQuarterNames = {
            "I kwarta\u0142",
            "II kwarta\u0142",
            "III kwarta\u0142",
            "IV kwarta\u0142",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "dd.MM.y GGGG",
        };

        final String[] sharedDayAbbreviations = {
            "niedz.",
            "pon.",
            "wt.",
            "\u015br.",
            "czw.",
            "pt.",
            "sob.",
        };

        final String[] sharedDayNames = {
            "niedziela",
            "poniedzia\u0142ek",
            "wtorek",
            "\u015broda",
            "czwartek",
            "pi\u0105tek",
            "sobota",
        };

        final String[] sharedDayNarrows = {
            "N",
            "P",
            "W",
            "\u015a",
            "C",
            "P",
            "S",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "dd.MM.y G",
        };

        final String[] sharedJavaTimeLongEras = {
            "p.n.e.",
            "n.e.",
        };

        final String[] sharedEras = {
            "Przed ROC",
            "ROC",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "kalendarz buddyjski" },
            { "calendarname.gregorian",
                "kalendarz gregoria\u0144ski" },
            { "calendarname.gregory",
                "kalendarz gregoria\u0144ski" },
            { "calendarname.islamic",
                "kalendarz islamski (metoda wzrokowa)" },
            { "calendarname.islamic-civil",
                "kalendarz islamski (metoda obliczeniowa)" },
            { "calendarname.islamic-umalqura",
                "kalendarz islamski (Umm al-Kura)" },
            { "calendarname.japanese",
                "kalendarz japo\u0144ski" },
            { "calendarname.roc",
                "kalendarz Republiki Chi\u0144skiej" },
            { "field.dayperiod",
                "rano / po po\u0142udniu / wieczorem" },
            { "field.era",
                "era" },
            { "field.hour",
                "godzina" },
            { "field.minute",
                "minuta" },
            { "field.month",
                "miesi\u0105c" },
            { "field.second",
                "sekunda" },
            { "field.week",
                "tydzie\u0144" },
            { "field.weekday",
                "dzie\u0144 tygodnia" },
            { "field.year",
                "rok" },
            { "field.zone",
                "strefa czasowa" },
            { "islamic.AmPmMarkers",
                new String[] {
                    "AM",
                    "PM",
                }
            },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayAbbreviations",
                sharedDayAbbreviations },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.MonthAbbreviations",
                new String[] {
                    "Muh.",
                    "Saf.",
                    "Rab. I",
                    "Rab. II",
                    "D\u017cu. I",
                    "D\u017cu. II",
                    "Ra.",
                    "Sza.",
                    "Ram.",
                    "Szaw.",
                    "Zu al-k.",
                    "Zu al-h.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "Muharram",
                    "Safar",
                    "Rabi\u02bb I",
                    "Rabi\u02bb II",
                    "D\u017cumada I",
                    "D\u017cumada II",
                    "Rad\u017cab",
                    "Szaban",
                    "Ramadan",
                    "Szawwal",
                    "Zu al-kada",
                    "Zu al-hid\u017cd\u017ca",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
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
            { "java.time.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                sharedJavaTimeLongEras },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                sharedDayAbbreviations },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedEras },
            { "roc.MonthAbbreviations",
                new String[] {
                    "sty",
                    "lut",
                    "mar",
                    "kwi",
                    "maj",
                    "cze",
                    "lip",
                    "sie",
                    "wrz",
                    "pa\u017a",
                    "lis",
                    "gru",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "stycznia",
                    "lutego",
                    "marca",
                    "kwietnia",
                    "maja",
                    "czerwca",
                    "lipca",
                    "sierpnia",
                    "wrze\u015bnia",
                    "pa\u017adziernika",
                    "listopada",
                    "grudnia",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "s",
                    "l",
                    "m",
                    "k",
                    "m",
                    "c",
                    "l",
                    "s",
                    "w",
                    "p",
                    "l",
                    "g",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
        };
    }
}
