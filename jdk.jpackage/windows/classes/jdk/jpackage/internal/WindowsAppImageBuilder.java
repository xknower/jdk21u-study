package jdk.jpackage.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static jdk.jpackage.internal.StandardBundlerParam.APP_NAME;
import static jdk.jpackage.internal.StandardBundlerParam.ICON;
import static jdk.jpackage.internal.StandardBundlerParam.ADD_LAUNCHERS;

public class WindowsAppImageBuilder extends AbstractAppImageBuilder {
    private static final ResourceBundle I18N = ResourceBundle.getBundle(
            "jdk.jpackage.internal.resources.WinResources");

    private static final String TEMPLATE_APP_ICON ="JavaApp.ico";

    public static final BundlerParamInfo<Path> ICON_ICO =
            new StandardBundlerParam<>(
            "icon.ico",
            Path.class,
            params -> {
                Path f = ICON.fetchFrom(params);
                if (f != null && f.getFileName() != null && !f.getFileName()
                        .toString().toLowerCase().endsWith(".ico")) {
                    Log.error(MessageFormat.format(
                            I18N.getString("message.icon-not-ico"), f));
                    return null;
                }
                return f;
            },
            (s, p) -> Path.of(s));

    public static final StandardBundlerParam<Boolean> CONSOLE_HINT =
            new StandardBundlerParam<>(
            Arguments.CLIOptions.WIN_CONSOLE_HINT.getId(),
            Boolean.class,
            params -> false,
            // valueOf(null) is false,
            // and we actually do want null in some cases
            (s, p) -> (s == null
            || "null".equalsIgnoreCase(s)) ? true : Boolean.valueOf(s));

    WindowsAppImageBuilder(Path imageOutDir) {
        super(imageOutDir);
    }

    private void writeEntry(InputStream in, Path dstFile) throws IOException {
        Files.createDirectories(IOUtils.getParent(dstFile));
        Files.copy(in, dstFile);
    }

    private static String getLauncherName(Map<String, ? super Object> params) {
        return APP_NAME.fetchFrom(params) + ".exe";
    }

    // Returns launcher resource name for launcher we need to use.
    public static String getLauncherResourceName(
            Map<String, ? super Object> params) {
        if (CONSOLE_HINT.fetchFrom(params)) {
            return "jpackageapplauncher.exe";
        } else {
            return "jpackageapplauncherw.exe";
        }
    }

    @Override
    public void prepareApplicationFiles(Map<String, ? super Object> params)
            throws IOException {
        // create the .exe launchers
        createLauncherForEntryPoint(params, null);

        // copy the jars
        copyApplication(params);

        // create the additional launcher(s), if any
        List<Map<String, ? super Object>> entryPoints = ADD_LAUNCHERS.fetchFrom(params);
        for (Map<String, ? super Object> entryPoint : entryPoints) {
            createLauncherForEntryPoint(AddLauncherArguments.merge(params,
                    entryPoint, ICON.getID(), ICON_ICO.getID()), params);
        }
    }

    private void createLauncherForEntryPoint(Map<String, ? super Object> params,
            Map<String, ? super Object> mainParams) throws IOException {

        var iconResource = createIconResource(TEMPLATE_APP_ICON, ICON_ICO, params,
                mainParams);
        Path iconTarget = null;
        if (iconResource != null) {
            Path iconDir = StandardBundlerParam.TEMP_ROOT.fetchFrom(params).resolve(
                    "icons");
            iconTarget = iconDir.resolve(APP_NAME.fetchFrom(params) + ".ico");
            if (null == iconResource.saveToFile(iconTarget)) {
                iconTarget = null;
            }
        }

        writeCfgFile(params);

        // Copy executable to bin folder
        Path executableFile = appLayout.launchersDirectory().resolve(
                getLauncherName(params));

        try (InputStream is_launcher =
                getResourceAsStream(getLauncherResourceName(params))) {
            writeEntry(is_launcher, executableFile);
        }

        // Update branding of launcher executable
        new ExecutableRebrander().rebrandLauncher(params, iconTarget, executableFile);

        executableFile.toFile().setExecutable(true);
        executableFile.toFile().setReadOnly();
    }
}
