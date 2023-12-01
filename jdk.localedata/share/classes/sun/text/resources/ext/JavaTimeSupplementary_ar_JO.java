package sun.text.resources.ext;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ar_JO extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedMonthNames = {
            "\u0643\u0627\u0646\u0648\u0646 \u0627\u0644\u062b\u0627\u0646\u064a",
            "\u0634\u0628\u0627\u0637",
            "\u0622\u0630\u0627\u0631",
            "\u0646\u064a\u0633\u0627\u0646",
            "\u0623\u064a\u0627\u0631",
            "\u062d\u0632\u064a\u0631\u0627\u0646",
            "\u062a\u0645\u0648\u0632",
            "\u0622\u0628",
            "\u0623\u064a\u0644\u0648\u0644",
            "\u062a\u0634\u0631\u064a\u0646 \u0627\u0644\u0623\u0648\u0644",
            "\u062a\u0634\u0631\u064a\u0646 \u0627\u0644\u062b\u0627\u0646\u064a",
            "\u0643\u0627\u0646\u0648\u0646 \u0627\u0644\u0623\u0648\u0644",
            "",
        };

        return new Object[][] {
            { "roc.MonthAbbreviations",
                sharedMonthNames },
            { "roc.MonthNames",
                sharedMonthNames },
            { "roc.MonthNarrows",
                new String[] {
                    "\u0643",
                    "\u0634",
                    "\u0622",
                    "\u0646",
                    "\u0623",
                    "\u062d",
                    "\u062a",
                    "\u0622",
                    "\u0623",
                    "\u062a",
                    "\u062a",
                    "\u0643",
                    "",
                }
            },
        };
    }
}
