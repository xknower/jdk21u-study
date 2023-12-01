package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_sr_BA extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedTimePatterns = {
            "HH:mm:ss zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        return new Object[][] {
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "roc.TimePatterns",
                sharedTimePatterns },
        };
    }
}
