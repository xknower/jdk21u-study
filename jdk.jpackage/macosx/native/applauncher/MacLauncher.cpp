#include "AppLauncher.h"
#include "app.h"
#include "FileUtils.h"
#include "PackageFile.h"
#include "UnixSysInfo.h"
#include "JvmLauncher.h"
#include "ErrorHandling.h"


namespace {

Jvm* jvmLauncher = 0;

void launchJvm() {
    // On Mac JLI_Launch() spawns a new thread that actually starts the JVM.
    // This new thread simply re-runs launcher's main() function with
    // arguments passed into JLI_Launch() call.
    // Jvm::launch() calls JLI_Launch() triggering thread spawning.
    jvmLauncher->launch();
}

void initJvmLauncher() {
    const tstring launcherPath = SysInfo::getProcessModulePath();

    // Launcher should be in "Contents/MacOS" subdirectory of app image.
    const tstring appImageRoot = FileUtils::dirname(FileUtils::dirname(
            FileUtils::dirname(launcherPath)));

    const tstring appDirPath = FileUtils::mkpath() << appImageRoot
            << _T("Contents/app");

    const PackageFile pkgFile = PackageFile::loadFromAppDir(appDirPath);

    // Create JVM launcher and save in global variable.
    AppLauncher appLauncher = AppLauncher()
        .setImageRoot(appImageRoot)
        .addJvmLibName(_T("Contents/Home/lib/libjli.dylib"))
        // add backup - older version such as JDK11 have it in jli sub-dir
        .addJvmLibName(_T("Contents/Home/lib/jli/libjli.dylib"))
        .setAppDir(appDirPath)
        .setLibEnvVariableName(_T("DYLD_LIBRARY_PATH"))
        .setDefaultRuntimePath(FileUtils::mkpath() << appImageRoot
                << _T("Contents/runtime"));

    if (!pkgFile.getPackageName().empty()) {
        tstring homeDir;
        JP_TRY;
        homeDir = SysInfo::getEnvVariable("HOME");
        JP_CATCH_ALL;

        if (!homeDir.empty()) {
            appLauncher.addCfgFileLookupDir(FileUtils::mkpath()
                    << homeDir << "Library/Application Support"
                    << pkgFile.getPackageName());
        }
    }

    jvmLauncher = appLauncher.createJvmLauncher();

    // Kick start JVM launching. The function wouldn't return!
    launchJvm();
}

} // namespace


int main(int argc, char *argv[]) {
    if (jvmLauncher) {
        // This is the call from the thread spawned by JVM.
        // Skip initialization phase as we have done this already in the first
        // call of main().
        // Besides we should ignore main() arguments because these are the
        // arguments passed into JLI_Launch() call and not the arguments with
        // which the launcher was started.
        return app::launch(std::nothrow, launchJvm);
    }

    SysInfo::argc = argc;
    SysInfo::argv = argv;
    return app::launch(std::nothrow, initJvmLauncher);
}
