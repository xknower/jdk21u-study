/**
 * Provides the locale data for locales other than {@linkplain java.util.Locale#US US locale}.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.localedata {
    provides sun.util.locale.provider.LocaleDataMetaInfo with
        sun.util.resources.cldr.provider.CLDRLocaleDataMetaInfo,
        sun.util.resources.provider.NonBaseLocaleDataMetaInfo;
    provides sun.util.resources.LocaleData.CommonResourceBundleProvider with
        sun.util.resources.provider.LocaleDataProvider;
    provides sun.util.resources.LocaleData.SupplementaryResourceBundleProvider with
        sun.util.resources.provider.SupplementaryLocaleDataProvider;
}
