package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_en_CA extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedDatePatterns = {
            "EEEE, MMMM d, y GGGG",
            "MMMM d, y GGGG",
            "MMM d, y GGGG",
            "G y-MM-dd",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, MMMM d, y G",
            "MMMM d, y G",
            "MMM d, y G",
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
                sharedJavaTimeDatePatterns },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.DatePatterns",
                sharedDatePatterns },
        };
    }
}
