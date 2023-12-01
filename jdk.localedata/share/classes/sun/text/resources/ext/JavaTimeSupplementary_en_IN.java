package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_en_IN extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedDatePatterns = {
            "EEEE d MMMM y GGGG",
            "GGGG y MMMM d",
            "dd-MMM-y GGGG",
            "G y-MM-dd",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE d MMMM y G",
            "G y MMMM d",
            "dd-MMM-y G",
            "GGGGG y-MM-dd",
        };

        return new Object[][] {
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE d MMMM y G",
                    "MMMM d, y G",
                    "dd-MMM-y G",
                    "M/d/y GGGGG",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.DatePatterns",
                sharedDatePatterns },
        };
    }
}
