package sun.text.resources.ext;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_ar extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    @Override
    protected final Object[][] getContents() {
        final String[] rocEras = {
            "Before R.O.C.",
            "\u062c\u0645\u0647\u0648\u0631\u064a\u0629 \u0627\u0644\u0635\u064a",
        };
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "\u064a\u0646\u0627\u064a\u0631", // january
                    "\u0641\u0628\u0631\u0627\u064a\u0631", // february
                    "\u0645\u0627\u0631\u0633", // march
                    "\u0623\u0628\u0631\u064a\u0644", // april
                    "\u0645\u0627\u064a\u0648", // may
                    "\u064a\u0648\u0646\u064a\u0648", // june
                    "\u064a\u0648\u0644\u064a\u0648", // july
                    "\u0623\u063a\u0633\u0637\u0633", // august
                    "\u0633\u0628\u062a\u0645\u0628\u0631", // september
                    "\u0623\u0643\u062a\u0648\u0628\u0631", // october
                    "\u0646\u0648\u0641\u0645\u0628\u0631", // november
                    "\u062f\u064a\u0633\u0645\u0628\u0631", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "\u064a\u0646\u0627", // abb january
                    "\u0641\u0628\u0631", // abb february
                    "\u0645\u0627\u0631", // abb march
                    "\u0623\u0628\u0631", // abb april
                    "\u0645\u0627\u064a", // abb may
                    "\u064a\u0648\u0646", // abb june
                    "\u064a\u0648\u0644", // abb july
                    "\u0623\u063a\u0633", // abb august
                    "\u0633\u0628\u062a", // abb september
                    "\u0623\u0643\u062a", // abb october
                    "\u0646\u0648\u0641", // abb november
                    "\u062f\u064a\u0633", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "\u064a",
                    "\u0641",
                    "\u0645",
                    "\u0623",
                    "\u0648",
                    "\u0646",
                    "\u0644",
                    "\u063a",
                    "\u0633",
                    "\u0643",
                    "\u0628",
                    "\u062f",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "\u0627\u0644\u0623\u062d\u062f", // Sunday
                    "\u0627\u0644\u0627\u062b\u0646\u064a\u0646", // Monday
                    "\u0627\u0644\u062b\u0644\u0627\u062b\u0627\u0621", // Tuesday
                    "\u0627\u0644\u0623\u0631\u0628\u0639\u0627\u0621", // Wednesday
                    "\u0627\u0644\u062e\u0645\u064a\u0633", // Thursday
                    "\u0627\u0644\u062c\u0645\u0639\u0629", // Friday
                    "\u0627\u0644\u0633\u0628\u062a" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u062d", // abb Sunday
                    "\u0646", // abb Monday
                    "\u062b", // abb Tuesday
                    "\u0631", // abb Wednesday
                    "\u062e", // abb Thursday
                    "\u062c", // abb Friday
                    "\u0633" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "\u0627\u0644\u0623\u062d\u062f",
                    "\u0627\u0644\u0627\u062b\u0646\u064a\u0646",
                    "\u0627\u0644\u062b\u0644\u0627\u062b\u0627\u0621",
                    "\u0627\u0644\u0623\u0631\u0628\u0639\u0627\u0621",
                    "\u0627\u0644\u062e\u0645\u064a\u0633",
                    "\u0627\u0644\u062c\u0645\u0639\u0629",
                    "\u0627\u0644\u0633\u0628\u062a",
                }
            },
            { "DayNarrows",
                new String[] {
                    "\u062d",
                    "\u0646",
                    "\u062b",
                    "\u0631",
                    "\u062e",
                    "\u062c",
                    "\u0633",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "\u0635", // am marker
                    "\u0645" // pm marker
                }
            },
            { "Eras",
                new String[] { // era strings
                    "\u0642.\u0645",
                    "\u0645"
                }
            },
            { "short.Eras",
                new String[] {
                    "\u0642.\u0645",
                    "\u0645",
                }
            },
            { "japanese.Eras",
                new String[] {
                    "\u0645",
                    "\u0645\u064a\u062c\u064a",
                    "\u062a\u064a\u0634\u0648",
                    "\u0634\u0648\u0648\u0627",
                    "\u0647\u064a\u0633\u064a",
                    "\u0631\u064a\u0648\u0627",
                }
            },
            { "japanese.short.Eras",
                new String[] {
                    "\u0645",
                    "\u0645\u064a\u062c\u064a",
                    "\u062a\u064a\u0634\u0648",
                    "\u0634\u0648\u0648\u0627",
                    "\u0647\u064a\u0633\u064a",
                    "\u0631\u064a\u0648\u0627",
                }
            },
            { "buddhist.Eras",
                new String[] {
                    "BC",
                    "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0628\u0648\u0630\u064a",
                }
            },
            { "buddhist.short.Eras",
                new String[] {
                    "BC",
                    "\u0627\u0644\u062a\u0642\u0648\u064a\u0645 \u0627\u0644\u0628\u0648\u0630\u064a",
                }
            },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;#,##0.###-", // decimal pattern
                    "\u00A4 #,##0.###;\u00A4 #,##0.###-", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
            { "TimePatterns",
                new String[] {
                    "z hh:mm:ss a", // full time pattern
                    "z hh:mm:ss a", // long time pattern
                    "hh:mm:ss a", // medium time pattern
                    "hh:mm a", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "dd MMMM, yyyy", // full date pattern
                    "dd MMMM, yyyy", // long date pattern
                    "dd/MM/yyyy", // medium date pattern
                    "dd/MM/yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GanjkHmsSEDFwWxhKzZ" },
        };
    }
}
