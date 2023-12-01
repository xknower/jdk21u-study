package sun.text.resources.ext;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_id_ID extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "TimePatterns",
                new String[] {
                    "H:mm:ss",
                    "H:mm:ss",
                    "H:mm:ss",
                    "H:mm",
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE dd MMMM yyyy",
                    "dd MMMM yyyy",
                    "dd MMM yy",
                    "dd/MM/yy",
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}",
                }
            },
        };
    }
}
