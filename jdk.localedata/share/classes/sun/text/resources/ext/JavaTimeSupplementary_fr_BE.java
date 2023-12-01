package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_fr_BE extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedTimePatterns = {
            "H 'h' mm 'min' ss 's' zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "d/MM/yy GGGGG",
        };

        return new Object[][] {
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE d MMMM y GGGG",
                    "d MMMM y GGGG",
                    "d MMM y GGGG",
                    "d/MM/yy G",
                }
            },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.roc.DatePatterns",
                new String[] {
                    "G y MMMM d, EEEE",
                    "G y MMMM d",
                    "G y MMM d",
                    "d/MM/yy GGGGG",
                }
            },
            { "roc.DatePatterns",
                new String[] {
                    "GGGG y MMMM d, EEEE",
                    "GGGG y MMMM d",
                    "GGGG y MMM d",
                    "d/MM/yy G",
                }
            },
            { "roc.TimePatterns",
                sharedTimePatterns },
        };
    }
}
