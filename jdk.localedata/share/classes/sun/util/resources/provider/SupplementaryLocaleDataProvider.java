package sun.util.resources.provider;

import java.util.Locale;
import java.util.ResourceBundle;
import sun.util.resources.LocaleData;

/**
 * Service Provider for loading JavaTimeSupplementary resource bundles in jdk.localedata.
 */
public class SupplementaryLocaleDataProvider extends LocaleData.SupplementaryResourceBundleProvider {
    @Override
    public ResourceBundle getBundle(String baseName, Locale locale) {
        var bundleName = toBundleName(baseName, locale);
        var rb = LocaleDataProvider.loadResourceBundle(bundleName);
        if (rb == null) {
            var otherBundleName = toOtherBundleName(baseName, bundleName, locale);
            if (!bundleName.equals(otherBundleName)) {
                rb = LocaleDataProvider.loadResourceBundle(otherBundleName);
            }
        }
        return rb;
    }
}
