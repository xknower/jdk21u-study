package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_de extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "1. Quartal",
            "2. Quartal",
            "3. Quartal",
            "4. Quartal",
        };

        final String[] sharedAmPmMarkers = {
            "vorm.",
            "nachm.",
        };

        final String[] sharedDatePatterns = {
            "EEEE, d. MMMM y GGGG",
            "d. MMMM y GGGG",
            "dd.MM.y GGGG",
            "dd.MM.yy G",
        };

        final String[] sharedDayAbbreviations = {
            "So.",
            "Mo.",
            "Di.",
            "Mi.",
            "Do.",
            "Fr.",
            "Sa.",
        };

        final String[] sharedDayNames = {
            "Sonntag",
            "Montag",
            "Dienstag",
            "Mittwoch",
            "Donnerstag",
            "Freitag",
            "Samstag",
        };

        final String[] sharedDayNarrows = {
            "S",
            "M",
            "D",
            "M",
            "D",
            "F",
            "S",
        };

        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "vm.",
            "nm.",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d. MMMM y G",
            "d. MMMM y G",
            "dd.MM.y G",
            "dd.MM.yy GGGGG",
        };

        final String[] sharedJavaTimeLongEras = {
            "v. Chr.",
            "n. Chr.",
        };

        final String[] sharedEras = {
            "Before R.O.C.",
            "Minguo",
        };

        return new Object[][] {
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Buddhistischer Kalender" },
            { "calendarname.gregorian",
                "Gregorianischer Kalender" },
            { "calendarname.gregory",
                "Gregorianischer Kalender" },
            { "calendarname.islamic",
                "Islamischer Kalender" },
            { "calendarname.islamic-civil",
                "B\u00fcrgerlicher islamischer Kalender" },
            { "calendarname.islamic-umalqura",
                "Islamischer Kalender (Umm al-Qura" },
            { "calendarname.japanese",
                "Japanischer Kalender" },
            { "calendarname.roc",
                "Kalender der Republik China" },
            { "field.dayperiod",
                "Tagesh\u00e4lfte" },
            { "field.era",
                "Epoche" },
            { "field.hour",
                "Stunde" },
            { "field.month",
                "Monat" },
            { "field.second",
                "Sekunde" },
            { "field.week",
                "Woche" },
            { "field.weekday",
                "Wochentag" },
            { "field.year",
                "Jahr" },
            { "field.zone",
                "Zeitzone" },
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
                new String[] {
                    "Jan.",
                    "Feb.",
                    "M\u00e4rz",
                    "Apr.",
                    "Mai",
                    "Juni",
                    "Juli",
                    "Aug.",
                    "Sep.",
                    "Okt.",
                    "Nov.",
                    "Dez.",
                    "",
                }
            },
            { "roc.MonthNames",
                new String[] {
                    "Januar",
                    "Februar",
                    "M\u00e4rz",
                    "April",
                    "Mai",
                    "Juni",
                    "Juli",
                    "August",
                    "September",
                    "Oktober",
                    "November",
                    "Dezember",
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
