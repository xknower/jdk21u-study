package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_fr_CA extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "yy-MM-dd GGGGG",
        };

        return new Object[][] {
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE d MMMM y GGGG",
                    "d MMMM y GGGG",
                    "d MMM y GGGG",
                    "y-MM-dd G",
                }
            },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                new String[] {
                    "EEEE d MMMM y G",
                    "d MMMM y G",
                    "d MMM y G",
                    "y-MM-dd GGGGG",
                }
            },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.roc.DatePatterns",
                new String[] {
                    "G y MMMM d, EEEE",
                    "G y MMMM d",
                    "G y MMM d",
                    "yy-MM-dd GGGGG",
                }
            },
            { "roc.DatePatterns",
                new String[] {
                    "GGGG y MMMM d, EEEE",
                    "GGGG y MMMM d",
                    "GGGG y MMM d",
                    "yy-MM-dd G",
                }
            },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
