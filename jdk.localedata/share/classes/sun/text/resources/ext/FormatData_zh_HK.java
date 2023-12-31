package sun.text.resources.ext;

import sun.util.resources.ParallelListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.ResourceBundleBasedAdapter;

public class FormatData_zh_HK extends ParallelListResourceBundle {

    // reparent to zh_TW for traditional Chinese names
    public FormatData_zh_HK() {
        ResourceBundle bundle = ((ResourceBundleBasedAdapter)LocaleProviderAdapter.forJRE())
            .getLocaleData().getDateFormatData(Locale.TAIWAN);
        setParent(bundle);
    }

    /**
     * Overrides ParallelListResourceBundle
     */
    @Override
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthAbbreviations",
                new String[] {
                    "1\u6708", // abb january
                    "2\u6708", // abb february
                    "3\u6708", // abb march
                    "4\u6708", // abb april
                    "5\u6708", // abb may
                    "6\u6708", // abb june
                    "7\u6708", // abb july
                    "8\u6708", // abb august
                    "9\u6708", // abb september
                    "10\u6708", // abb october
                    "11\u6708", // abb november
                    "12\u6708", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u65e5", // abb Sunday
                    "\u4e00", // abb Monday
                    "\u4e8c", // abb Tuesday
                    "\u4e09", // abb Wednesday
                    "\u56db", // abb Thursday
                    "\u4e94", // abb Friday
                    "\u516d" // abb Saturday
                }
            },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "\u00A4#,##0.00;(\u00A4#,##0.00)", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
            { "TimePatterns",
                new String[] {
                    "ahh'\u6642'mm'\u5206'ss'\u79d2' z", // full time pattern
                    "ahh'\u6642'mm'\u5206'ss'\u79d2'", // long time pattern
                    "ahh:mm:ss", // medium time pattern
                    "ah:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "yyyy'\u5e74'MM'\u6708'dd'\u65e5' EEEE", // full date pattern
                    "yyyy'\u5e74'MM'\u6708'dd'\u65e5' EEEE", // long date pattern
                    "yyyy'\u5e74'M'\u6708'd'\u65e5'", // medium date pattern
                    "yy'\u5e74'M'\u6708'd'\u65e5'", // short date pattern
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
