package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_id extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "K1",
            "K2",
            "K3",
            "K4",
        };

        final String[] sharedQuarterNames = {
            "Kuartal ke-1",
            "Kuartal ke-2",
            "Kuartal ke-3",
            "Kuartal ke-4",
        };

        final String[] sharedAmPmMarkers = {
            "AM",
            "PM",
        };

        final String[] sharedDatePatterns = {
            "EEEE, dd MMMM y GGGG",
            "d MMMM y GGGG",
            "d MMM y GGGG",
            "d/M/y G",
        };

        final String[] sharedDayAbbreviations = {
            "Min",
            "Sen",
            "Sel",
            "Rab",
            "Kam",
            "Jum",
            "Sab",
        };

        final String[] sharedDayNames = {
            "Minggu",
            "Senin",
            "Selasa",
            "Rabu",
            "Kamis",
            "Jumat",
            "Sabtu",
        };

        final String[] sharedDayNarrows = {
            "M",
            "S",
            "S",
            "R",
            "K",
            "J",
            "S",
        };

        final String[] sharedTimePatterns = {
            "HH.mm.ss zzzz",
            "HH.mm.ss z",
            "HH.mm.ss",
            "HH.mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, dd MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "d/M/y GGGGG",
        };

        final String[] sharedJavaTimeLongEras = {
            "M",
            "Meiji",
            "Taish\u014d",
            "Sh\u014dwa",
            "Heisei",
            "Reiwa",
        };

        final String[] sharedEras = {
            "Sebelum R.O.C.",
            "R.O.C.",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Kalender Buddha" },
            { "calendarname.gregorian",
                "Kalender Gregorian" },
            { "calendarname.gregory",
                "Kalender Gregorian" },
            { "calendarname.islamic",
                "Kalender Islam" },
            { "calendarname.islamic-civil",
                "Kalender Sipil Islam" },
            { "calendarname.japanese",
                "Kalender Jepang" },
            { "calendarname.roc",
                "Kalendar Minguo" },
            { "field.dayperiod",
                "AM/PM" },
            { "field.hour",
                "Jam" },
            { "field.minute",
                "Menit" },
            { "field.month",
                "Bulan" },
            { "field.second",
                "Detik" },
            { "field.week",
                "Minggu" },
            { "field.weekday",
                "Hari dalam Seminggu" },
            { "field.year",
                "Tahun" },
            { "field.zone",
                "Zona Waktu" },
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
            { "islamic.MonthAbbreviations",
                new String[] {
                    "Muh.",
                    "Saf.",
                    "Rab. I",
                    "Rab. II",
                    "Jum. I",
                    "Jum. II",
                    "Raj.",
                    "Sha.",
                    "Ram.",
                    "Syaw.",
                    "Dhu\u02bbl-Q.",
                    "Dhu\u02bbl-H.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "Muharram",
                    "Safar",
                    "Rabi\u02bb I",
                    "Rabi\u02bb II",
                    "Jumada I",
                    "Jumada II",
                    "Rajab",
                    "Sya\u2019ban",
                    "Ramadhan",
                    "Syawal",
                    "Dhu\u02bbl-Qi\u02bbdah",
                    "Dhu\u02bbl-Hijjah",
                    "",
                }
            },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.narrow.AmPmMarkers",
                sharedAmPmMarkers },
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
                sharedJavaTimeLongEras },
            { "java.time.japanese.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.long.Eras",
                new String[] {
                    "Sebelum Masehi",
                    "M",
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
                    "Jan",
                    "Feb",
                    "Mar",
                    "Apr",
                    "Mei",
                    "Jun",
                    "Jul",
                    "Agt",
                    "Sep",
                    "Okt",
                    "Nov",
                    "Des",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "Januari",
                    "Februari",
                    "Maret",
                    "April",
                    "Mei",
                    "Juni",
                    "Juli",
                    "Agustus",
                    "September",
                    "Oktober",
                    "November",
                    "Desember",
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
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
            { "timezone.hourFormat",
                "+HH.mm;-HH.mm" },
        };
    }
}
