package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_en_ZA extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedDatePatterns = {
            "EEEE, dd MMMM y GGGG",
            "dd MMMM y GGGG",
            "dd MMM y GGGG",
            "G y/MM/dd",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, dd MMMM y G",
            "dd MMMM y G",
            "dd MMM y G",
            "GGGGG y/MM/dd",
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
