package sun.text.resources.ext;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_es_NI extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberElements",
                new String[] {
                    ".", // decimal separator
                    ",", // group (thousands) separator
                    ";", // list separator
                    "%", // percent sign
                    "0", // native 0 digit
                    "#", // pattern digit
                    "-", // minus sign
                    "E", // exponential
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NaN
                }
            },
            { "TimePatterns",
                new String[] {
                    "hh:mm:ss a z", // full time pattern
                    "hh:mm:ss a z", // long time pattern
                    "hh:mm:ss a", // medium time pattern
                    "hh:mm a", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE dd' de 'MMMM' de 'yyyy", // full date pattern
                    "dd' de 'MMMM' de 'yyyy", // long date pattern
                    "MM-dd-yyyy", // medium date pattern
                    "MM-dd-yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
        };
    }
}
