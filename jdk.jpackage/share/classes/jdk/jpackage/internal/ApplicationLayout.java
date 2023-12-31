package jdk.jpackage.internal;

import jdk.internal.util.OperatingSystem;

import java.nio.file.Path;
import java.util.Map;


/**
 * Application directory layout.
 */
public final class ApplicationLayout implements PathGroup.Facade<ApplicationLayout> {
    enum PathRole {
        /**
         * Java run-time directory.
         */
        RUNTIME,

        /**
         * Java run-time home directory.
         */
        RUNTIME_HOME,

        /**
         * Application data directory.
         */
        APP,

        /**
         * Directory with application launchers.
         */
        LAUNCHERS,

        /**
         * Directory for files for desktop integration.
         */
        DESKTOP,

        /**
         * Directory with application Java modules.
         */
        MODULES,

        /**
         * Linux app launcher shared library.
         */
        LINUX_APPLAUNCHER_LIB,

        /**
         * Location of additional application content
         */
        CONTENT
    }

    ApplicationLayout(Map<Object, Path> paths) {
        data = new PathGroup(paths);
    }

    private ApplicationLayout(PathGroup data) {
        this.data = data;
    }

    @Override
    public PathGroup pathGroup() {
        return data;
    }

    @Override
    public ApplicationLayout resolveAt(Path root) {
        return new ApplicationLayout(pathGroup().resolveAt(root));
    }

    /**
     * Path to launchers directory.
     */
    public Path launchersDirectory() {
        return pathGroup().getPath(PathRole.LAUNCHERS);
    }

    /**
     * Path to application data directory.
     */
    public Path appDirectory() {
        return pathGroup().getPath(PathRole.APP);
    }

    /**
     * Path to Java run-time directory.
     */
    public Path runtimeDirectory() {
        return pathGroup().getPath(PathRole.RUNTIME);
    }

    /**
     * Path to Java run-time home directory.
     */
    public Path runtimeHomeDirectory() {
        return pathGroup().getPath(PathRole.RUNTIME_HOME);
    }

    /**
     * Path to application mods directory.
     */
    public Path appModsDirectory() {
        return pathGroup().getPath(PathRole.MODULES);
    }

    /**
     * Path to directory with application's desktop integration files.
     */
    public Path destktopIntegrationDirectory() {
        return pathGroup().getPath(PathRole.DESKTOP);
    }

    /**
     * Path to directory with additional application content.
     */
    public Path contentDirectory() {
        return pathGroup().getPath(PathRole.CONTENT);
    }

    static ApplicationLayout linuxAppImage() {
        return new ApplicationLayout(Map.of(
                PathRole.LAUNCHERS, Path.of("bin"),
                PathRole.APP, Path.of("lib/app"),
                PathRole.RUNTIME, Path.of("lib/runtime"),
                PathRole.RUNTIME_HOME, Path.of("lib/runtime"),
                PathRole.DESKTOP, Path.of("lib"),
                PathRole.MODULES, Path.of("lib/app/mods"),
                PathRole.LINUX_APPLAUNCHER_LIB, Path.of("lib/libapplauncher.so"),
                PathRole.CONTENT, Path.of("lib")
        ));
    }

    static ApplicationLayout windowsAppImage() {
        return new ApplicationLayout(Map.of(
                PathRole.LAUNCHERS, Path.of(""),
                PathRole.APP, Path.of("app"),
                PathRole.RUNTIME, Path.of("runtime"),
                PathRole.RUNTIME_HOME, Path.of("runtime"),
                PathRole.DESKTOP, Path.of(""),
                PathRole.MODULES, Path.of("app/mods"),
                PathRole.CONTENT, Path.of("")
        ));
    }

    static ApplicationLayout macAppImage() {
        return new ApplicationLayout(Map.of(
                PathRole.LAUNCHERS, Path.of("Contents/MacOS"),
                PathRole.APP, Path.of("Contents/app"),
                PathRole.RUNTIME, Path.of("Contents/runtime"),
                PathRole.RUNTIME_HOME, Path.of("Contents/runtime/Contents/Home"),
                PathRole.DESKTOP, Path.of("Contents/Resources"),
                PathRole.MODULES, Path.of("Contents/app/mods"),
                PathRole.CONTENT, Path.of("Contents")
        ));
    }

    public static ApplicationLayout platformAppImage() {
        if (OperatingSystem.isWindows()) {
            return windowsAppImage();
        }

        if (OperatingSystem.isLinux()) {
            return linuxAppImage();
        }

        if (OperatingSystem.isMacOS()) {
            return macAppImage();
        }

        throw new IllegalArgumentException("Unknown platform: " + OperatingSystem.current());
    }

    public static ApplicationLayout javaRuntime() {
        return new ApplicationLayout(Map.of(PathRole.RUNTIME, Path.of("")));
    }

    public static ApplicationLayout linuxUsrTreePackageImage(Path prefix,
            String packageName) {
        final Path lib = prefix.resolve(Path.of("lib", packageName));
        return new ApplicationLayout(Map.of(
                PathRole.LAUNCHERS, prefix.resolve("bin"),
                PathRole.APP, lib.resolve("app"),
                PathRole.RUNTIME, lib.resolve("runtime"),
                PathRole.RUNTIME_HOME, lib.resolve("runtime"),
                PathRole.DESKTOP, lib,
                PathRole.MODULES, lib.resolve("app/mods"),
                PathRole.LINUX_APPLAUNCHER_LIB, lib.resolve(
                        "lib/libapplauncher.so"),
                PathRole.CONTENT, lib
        ));
    }

    private final PathGroup data;
}
