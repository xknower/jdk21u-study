package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_zh_HK extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "Gy\u5e74M\u6708d\u65e5EEEE",
            "Gy\u5e74M\u6708d\u65e5",
            "Gy\u5e74M\u6708d\u65e5",
            "Gy/M/d",
        };

        final String[] sharedJavaTimeLongEras = {
            "\u516c\u5143\u524d",
            "\u516c\u5143",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "field.week",
                "\u661f\u671f" },
            { "field.weekday",
                "\u661f\u671f\u5e7e" },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                sharedJavaTimeLongEras },
            { "roc.DatePatterns",
                new String[] {
                    "GGGGy\u5e74M\u6708d\u65e5EEEE",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy/M/d",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
        };
    }
}
