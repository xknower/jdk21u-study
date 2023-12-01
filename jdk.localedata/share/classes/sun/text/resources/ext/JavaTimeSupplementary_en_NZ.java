package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_en_NZ extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "d/MM/y GGGG",
            "d/MM/y G",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "d/MM/y G",
            "d/MM/y GGGGG",
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
                    "EEEE, MMMM d, y G",
                    "MMMM d, y G",
                    "d/MM/y G",
                    "d/MM/y GGGGG",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.DatePatterns",
                sharedDatePatterns },
        };
    }
}
