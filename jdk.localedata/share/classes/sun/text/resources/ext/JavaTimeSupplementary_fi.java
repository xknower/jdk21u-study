package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_fi extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "1. nelj.",
            "2. nelj.",
            "3. nelj.",
            "4. nelj.",
        };

        final String[] sharedQuarterNames = {
            "1. nelj\u00e4nnes",
            "2. nelj\u00e4nnes",
            "3. nelj\u00e4nnes",
            "4. nelj\u00e4nnes",
        };

        final String[] sharedAmPmMarkers = {
            "ap.",
            "ip.",
        };

        final String[] sharedDatePatterns = {
            "EEEE d. MMMM y GGGG",
            "d. MMMM y GGGG",
            "d.M.y GGGG",
            "d.M.y G",
        };

        final String[] sharedDayAbbreviations = {
            "su",
            "ma",
            "ti",
            "ke",
            "to",
            "pe",
            "la",
        };

        final String[] sharedDayNames = {
            "sunnuntaina",
            "maanantaina",
            "tiistaina",
            "keskiviikkona",
            "torstaina",
            "perjantaina",
            "lauantaina",
        };

        final String[] sharedDayNarrows = {
            "S",
            "M",
            "T",
            "K",
            "T",
            "P",
            "L",
        };

        final String[] sharedTimePatterns = {
            "H.mm.ss zzzz",
            "H.mm.ss z",
            "H.mm.ss",
            "H.mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "cccc d. MMMM y G",
            "d. MMMM y G",
            "d.M.y G",
            "d.M.y GGGGG",
        };

        final String[] sharedEras = {
            "Before R.O.C.",
            "Minguo",
        };

        final String[] sharedMonthNames = {
            "tammikuuta",
            "helmikuuta",
            "maaliskuuta",
            "huhtikuuta",
            "toukokuuta",
            "kes\u00e4kuuta",
            "hein\u00e4kuuta",
            "elokuuta",
            "syyskuuta",
            "lokakuuta",
            "marraskuuta",
            "joulukuuta",
            "",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "buddhalainen kalenteri" },
            { "calendarname.gregorian",
                "gregoriaaninen kalenteri" },
            { "calendarname.gregory",
                "gregoriaaninen kalenteri" },
            { "calendarname.islamic",
                "islamilainen kalenteri" },
            { "calendarname.islamic-civil",
                "islamilainen siviilikalenteri, perjantai-epookki" },
            { "calendarname.islamic-umalqura",
                "islamilainen Umm al-Qura -kalenteri" },
            { "calendarname.japanese",
                "japanilainen kalenteri" },
            { "calendarname.roc",
                "Kiinan tasavallan kalenteri" },
            { "field.dayperiod",
                "vuorokaudenaika" },
            { "field.era",
                "aikakausi" },
            { "field.hour",
                "tunti" },
            { "field.minute",
                "minuutti" },
            { "field.month",
                "kuukausi" },
            { "field.second",
                "sekunti" },
            { "field.week",
                "viikko" },
            { "field.weekday",
                "viikonp\u00e4iv\u00e4" },
            { "field.year",
                "vuosi" },
            { "field.zone",
                "aikavy\u00f6hyke" },
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
                    "muharram",
                    "safar",
                    "rabi\u2019 al-awwal",
                    "rabi\u2019 al-akhir",
                    "d\u017eumada-l-ula",
                    "d\u017eumada-l-akhira",
                    "rad\u017eab",
                    "\u0161a\u2019ban",
                    "ramadan",
                    "\u0161awwal",
                    "dhu-l-qa\u2019da",
                    "dhu-l-hidd\u017ea",
                    "",
                }
            },
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
            { "java.time.DatePatterns",
                new String[] {
                    "cccc d. MMMM y",
                    "d. MMMM y",
                    "d.M.y",
                    "d.M.y",
                }
            },
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
                new String[] {
                    "ennen Kristuksen syntym\u00e4\u00e4",
                    "j\u00e4lkeen Kristuksen syntym\u00e4n",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "eKr.",
                    "jKr.",
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
                sharedEras },
            { "roc.MonthAbbreviations",
                sharedMonthNames },
            { "roc.MonthNames",
                sharedMonthNames },
            { "roc.MonthNarrows",
                new String[] {
                    "T",
                    "H",
                    "M",
                    "H",
                    "T",
                    "K",
                    "H",
                    "E",
                    "S",
                    "L",
                    "M",
                    "J",
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
            { "timezone.gmtFormat",
                "UTC{0}" },
            { "timezone.hourFormat",
                "+H.mm;-H.mm" },
        };
    }
}
