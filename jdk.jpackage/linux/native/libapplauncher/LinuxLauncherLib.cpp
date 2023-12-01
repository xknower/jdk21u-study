#include "kludge_c++11.h"

#include <memory>
#include "JvmLauncher.h"
#include "AppLauncher.h"
#include "FileUtils.h"
#include "UnixSysInfo.h"
#include "Package.h"
#include "Log.h"
#include "app.h"
#include "ErrorHandling.h"


namespace {

size_t hash(const std::string& str) {
    size_t h = 0;
    for(std::string::const_iterator it = str.begin(); it != str.end(); ++it) {
        h = 31 * h + (*it & 0xff);
    }
    return h;
}

Jvm* jvmLauncher;

void launchApp() {
    const tstring launcherPath = SysInfo::getProcessModulePath();

    const Package ownerPackage = Package::findOwnerOfFile(launcherPath);

    AppLauncher appLauncher;
    appLauncher.addJvmLibName(_T("lib/libjli.so"));
    // add backup - older version such as JDK11 have it in jli sub-dir
    appLauncher.addJvmLibName(_T("lib/jli/libjli.so"));

    if (ownerPackage.name().empty()) {
        // Launcher should be in "bin" subdirectory of app image.
        const tstring appImageRoot = FileUtils::dirname(
                FileUtils::dirname(launcherPath));

        appLauncher
            .setImageRoot(appImageRoot)
            .setAppDir(FileUtils::mkpath() << appImageRoot << _T("lib/app"))
            .setLibEnvVariableName(_T("LD_LIBRARY_PATH"))
            .setDefaultRuntimePath(FileUtils::mkpath() << appImageRoot
                    << _T("lib/runtime"));
    } else {
        ownerPackage.initAppLauncher(appLauncher);

        tstring homeDir;
        JP_TRY;
        homeDir = SysInfo::getEnvVariable("HOME");
        JP_CATCH_ALL;

        if (!homeDir.empty()) {
            appLauncher.addCfgFileLookupDir(FileUtils::mkpath()
                    << homeDir << ".local" << ownerPackage.name());
            appLauncher.addCfgFileLookupDir(FileUtils::mkpath()
                    << homeDir << "." + ownerPackage.name());
        }
    }

    const std::string _JPACKAGE_LAUNCHER = "_JPACKAGE_LAUNCHER";

    std::string launchInfo = SysInfo::getEnvVariable(std::nothrow,
            _JPACKAGE_LAUNCHER, "");

    const std::string thisLdLibraryPath = SysInfo::getEnvVariable(std::nothrow,
            "LD_LIBRARY_PATH", "");

    const size_t thisHash = hash(thisLdLibraryPath);

    if (!launchInfo.empty()) {
        LOG_TRACE(tstrings::any() << "Found "
                << _JPACKAGE_LAUNCHER << "=[" << launchInfo << "]");

        tistringstream iss(launchInfo);
        iss.exceptions(std::ios::failbit | std::ios::badbit);

        size_t hash = 0;
        iss >> hash;

        launchInfo = "";

        if (thisHash != hash) {
            // This launcher execution is the result of execve() call from
            // within JVM.
            // This means all JVM arguments are already configured in launcher
            // process command line.
            // No need to construct command line for JVM.
            LOG_TRACE("Not building JVM arguments from cfg file");
            appLauncher.setInitJvmFromCmdlineOnly(true);
        }
    } else {
        // Changed LD_LIBRARY_PATH environment variable might result in
        // execve() call from within JVM.
        // Set _JPACKAGE_LAUNCHER environment variable accordingly so that
        // restarted launcher process can detect a restart.

        launchInfo = (tstrings::any() << thisHash).str();
    }

    jvmLauncher = appLauncher.createJvmLauncher();

    jvmLauncher->addEnvVariable(_JPACKAGE_LAUNCHER, launchInfo);
}

} // namespace


extern "C" {

JNIEXPORT JvmlLauncherHandle jvmLauncherCreate(int argc, char *argv[]) {
    SysInfo::argc = argc;
    SysInfo::argv = argv;
    jvmLauncher = 0;
    app::launch(std::nothrow, launchApp);

    JvmlLauncherHandle jlh = 0;
    if (jvmLauncher) {
        jlh = jvmLauncher->exportLauncher();
        const std::unique_ptr<Jvm> deleter(jvmLauncher);
    }

    return jlh;
}

} // extern "C"


namespace {

void dcon() __attribute__((destructor));

void dcon() {
   LOG_TRACE("unload");
}

} // namespace
