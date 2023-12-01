package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ms extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "S1",
            "S2",
            "S3",
            "S4",
        };

        final String[] sharedQuarterNames = {
            "Suku pertama",
            "Suku Ke-2",
            "Suku Ke-3",
            "Suku Ke-4",
        };

        final String[] sharedAmPmMarkers = {
            "PG",
            "PTG",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d MMMM y GGGG",
            "d MMMM y GGGG",
            "dd/MM/y GGGG",
            "d/MM/y G",
        };

        final String[] sharedDayAbbreviations = {
            "Ahd",
            "Isn",
            "Sel",
            "Rab",
            "Kha",
            "Jum",
            "Sab",
        };

        final String[] sharedDayNames = {
            "Ahad",
            "Isnin",
            "Selasa",
            "Rabu",
            "Khamis",
            "Jumaat",
            "Sabtu",
        };

        final String[] sharedDayNarrows = {
            "A",
            "I",
            "S",
            "R",
            "K",
            "J",
            "S",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "dd/MM/y G",
            "d/MM/y GGGGG",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Kalendar Buddha" },
            { "calendarname.gregorian",
                "Kalendar Gregory" },
            { "calendarname.gregory",
                "Kalendar Gregory" },
            { "calendarname.islamic",
                "Kalendar Islam" },
            { "calendarname.islamic-civil",
                "Kalendar Sivil Islam" },
            { "calendarname.japanese",
                "Kalendar Jepun" },
            { "calendarname.roc",
                "Kalendar Minguo" },
            { "field.dayperiod",
                "PG/PTG" },
            { "field.hour",
                "Jam" },
            { "field.minute",
                "Minit" },
            { "field.month",
                "Bulan" },
            { "field.second",
                "Saat" },
            { "field.week",
                "Minggu" },
            { "field.weekday",
                "Hari dalam Minggu" },
            { "field.year",
                "Tahun" },
            { "field.zone",
                "Zon Waktu" },
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
                sharedAmPmMarkers },
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
                new String[] {
                    "S.M.",
                    "TM",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "BCE",
                    "CE",
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
                    "Jan",
                    "Feb",
                    "Mac",
                    "Apr",
                    "Mei",
                    "Jun",
                    "Jul",
                    "Ogo",
                    "Sep",
                    "Okt",
                    "Nov",
                    "Dis",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "Januari",
                    "Februari",
                    "Mac",
                    "April",
                    "Mei",
                    "Jun",
                    "Julai",
                    "Ogos",
                    "September",
                    "Oktober",
                    "November",
                    "Disember",
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
                    "O",
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
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
