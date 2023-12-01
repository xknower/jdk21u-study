package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_fr_CH extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedTimePatterns = {
            "HH.mm:ss 'h' zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "dd.MM.yy GGGGG",
        };

        return new Object[][] {
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE, d MMMM y GGGG",
                    "d MMMM y GGGG",
                    "d MMM y GGGG",
                    "dd.MM.yy G",
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
                    "EEEE, d MMMM y G",
                    "G y MMMM d",
                    "G y MMM d",
                    "dd.MM.yy GGGGG",
                }
            },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE, d MMMM y GGGG",
                    "GGGG y MMMM d",
                    "GGGG y MMM d",
                    "dd.MM.yy G",
                }
            },
            { "roc.TimePatterns",
                sharedTimePatterns },
        };
    }
}
