package jdk.jpackage.internal;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import static jdk.jpackage.internal.MacBaseInstallerBundler.SIGNING_KEYCHAIN;
import static jdk.jpackage.internal.MacBaseInstallerBundler.SIGNING_KEY_USER;
import static jdk.jpackage.internal.StandardBundlerParam.APP_STORE;
import static jdk.jpackage.internal.StandardBundlerParam.MAIN_CLASS;
import static jdk.jpackage.internal.StandardBundlerParam.VERSION;
import static jdk.jpackage.internal.StandardBundlerParam.SIGN_BUNDLE;

public class MacAppBundler extends AppImageBundler {
     public MacAppBundler() {
        setAppImageSupplier(imageOutDir -> {
            return new MacAppImageBuilder(imageOutDir, isDependentTask());
        });
        setParamsValidator(MacAppBundler::doValidate);
    }

    private static final String TEMPLATE_BUNDLE_ICON = "JavaApp.icns";

    public static final BundlerParamInfo<String> DEFAULT_ICNS_ICON =
            new StandardBundlerParam<>(
            ".mac.default.icns",
            String.class,
            params -> TEMPLATE_BUNDLE_ICON,
            (s, p) -> s);

    public static final BundlerParamInfo<String> DEVELOPER_ID_APP_SIGNING_KEY =
            new StandardBundlerParam<>(
            "mac.signing-key-developer-id-app",
            String.class,
            params -> {
                    String user = SIGNING_KEY_USER.fetchFrom(params);
                    String keychain = SIGNING_KEYCHAIN.fetchFrom(params);
                    String result = null;
                    if (APP_STORE.fetchFrom(params)) {
                        result = MacCertificate.findCertificateKey(
                            "3rd Party Mac Developer Application: ",
                            user, keychain);
                    }
                    // if either not signing for app store or couldn't find
                    if (result == null) {
                        result = MacCertificate.findCertificateKey(
                            "Developer ID Application: ", user, keychain);
                    }

                    if (result != null) {
                        MacCertificate certificate = new MacCertificate(result);

                        if (!certificate.isValid()) {
                            Log.error(MessageFormat.format(I18N.getString(
                                    "error.certificate.expired"), result));
                        }
                    }

                    return result;
                },
            (s, p) -> s);

    public static final BundlerParamInfo<String> BUNDLE_ID_SIGNING_PREFIX =
            new StandardBundlerParam<>(
            Arguments.CLIOptions.MAC_BUNDLE_SIGNING_PREFIX.getId(),
            String.class,
            params -> getIdentifier(params) + ".",
            (s, p) -> s);

    static String getIdentifier(Map<String, ? super Object> params) {
        String s = MAIN_CLASS.fetchFrom(params);
        if (s == null) return null;

        int idx = s.lastIndexOf(".");
        if (idx >= 1) {
            return s.substring(0, idx);
        }
        return s;
    }

    private static void doValidate(Map<String, ? super Object> params)
            throws ConfigException {

        if (StandardBundlerParam.getPredefinedAppImage(params) != null) {
            if (!Optional.ofNullable(
                    SIGN_BUNDLE.fetchFrom(params)).orElse(Boolean.FALSE)) {
                throw new ConfigException(
                        I18N.getString("error.app-image.mac-sign.required"),
                        null);
            }
        } else {
            // validate short version
            try {
                String version = VERSION.fetchFrom(params);
                CFBundleVersion.of(version);
            } catch (IllegalArgumentException ex) {
                throw new ConfigException(ex.getMessage(), I18N.getString(
                        "error.invalid-cfbundle-version.advice"), ex);
            }
        }

        // reject explicitly set sign to true and no valid signature key
        if (Optional.ofNullable(
                    SIGN_BUNDLE.fetchFrom(params)).orElse(Boolean.FALSE)) {
            String signingIdentity =
                    DEVELOPER_ID_APP_SIGNING_KEY.fetchFrom(params);
            if (signingIdentity == null) {
                throw new ConfigException(
                        I18N.getString("error.explicit-sign-no-cert"),
                        I18N.getString("error.explicit-sign-no-cert.advice"));
            }

            // Signing will not work without Xcode with command line developer tools
            try {
                ProcessBuilder pb = new ProcessBuilder("/usr/bin/xcrun", "--help");
                Process p = pb.start();
                int code = p.waitFor();
                if (code != 0) {
                    throw new ConfigException(
                        I18N.getString("error.no.xcode.signing"),
                        I18N.getString("error.no.xcode.signing.advice"));
                }
            } catch (IOException | InterruptedException ex) {
                throw new ConfigException(ex);
            }
        }
    }
}
