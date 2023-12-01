package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_zh_SG extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedDatePatterns = {
            "GGGGy\u5e74M\u6708d\u65e5EEEE",
            "GGGGy\u5e74M\u6708d\u65e5",
            "GGGGy\u5e74M\u6708d\u65e5",
            "GGGGd/M/yy",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "Gy\u5e74M\u6708d\u65e5EEEE",
            "Gy\u5e74M\u6708d\u65e5",
            "Gy\u5e74M\u6708d\u65e5",
            "Gd/M/yy",
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
