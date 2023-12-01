#include <algorithm>
#include "AppLauncher.h"
#include "JvmLauncher.h"
#include "CfgFile.h"
#include "Log.h"
#include "Dll.h"
#include "Toolbox.h"
#include "SysInfo.h"
#include "FileUtils.h"


AppLauncher::AppLauncher() {
    setInitJvmFromCmdlineOnly(false);
    launcherPath = SysInfo::getProcessModulePath();
    args = SysInfo::getCommandArgs();
}


namespace {

struct find_jvmlib {
    find_jvmlib(const tstring& v): runtimePath(v) {
    }

    bool operator () (const tstring& jvmLibName) const {
        const tstring path = FileUtils::mkpath() << runtimePath << jvmLibName;
        return FileUtils::isFileExists(path);
    }

private:
    const tstring& runtimePath;
};

tstring findJvmLib(const CfgFile& cfgFile, const tstring& defaultRuntimePath,
        const tstring_array& jvmLibNames) {
    const CfgFile::Properties& appOptions = cfgFile.getProperties(
            SectionName::Application);

    const CfgFile::Properties::const_iterator runtimePathProp = appOptions.find(
            PropertyName::runtime);
    tstring runtimePath;
    if (runtimePathProp != appOptions.end()) {
        runtimePath = CfgFile::asString(*runtimePathProp);
    } else {
        runtimePath = defaultRuntimePath;
        LOG_TRACE(tstrings::any()
                << "Property \"" << PropertyName::runtime.name()
                << "\" not found in \"" << SectionName::Application.name()
                << "\" section of launcher config file."
                << " Using Java runtime from \""
                << runtimePath << "\" directory");
    }

    const tstring_array::const_iterator jvmLibNameEntry = std::find_if(
            jvmLibNames.begin(),
            jvmLibNames.end(),
            find_jvmlib(runtimePath));

    if (jvmLibNameEntry == jvmLibNames.end()) {
        JP_THROW(tstrings::any() << "Failed to find JVM in \""
            << runtimePath
            << "\" directory.");
    }

    return FileUtils::mkpath() << runtimePath << *jvmLibNameEntry;
}
} // namespace

bool AppLauncher::libEnvVariableContainsAppDir() const {
    tstring value = SysInfo::getEnvVariable(std::nothrow,
            libEnvVarName, tstring());
#ifdef _WIN32
    value = tstrings::toLower(value);
#endif

    const tstring_array tokens = tstrings::split(value,
            tstring(1, FileUtils::pathSeparator));
    return tokens.end() != std::find(tokens.begin(), tokens.end(),
#ifdef _WIN32
        tstrings::toLower(appDirPath)
#else
        appDirPath
#endif
    );
}

Jvm* AppLauncher::createJvmLauncher() const {
    const tstring cfgFilePath = getCfgFilePath();

    LOG_TRACE(tstrings::any() << "Launcher config file path: \""
            << cfgFilePath << "\"");

    CfgFile::Macros macros;
    macros[_T("$APPDIR")] = appDirPath;
    macros[_T("$BINDIR")] = FileUtils::dirname(launcherPath);
    macros[_T("$ROOTDIR")] = imageRoot;

    CfgFile cfgFile = CfgFile::load(cfgFilePath).expandMacros(macros);

    if (!args.empty()) {
        // Override default launcher arguments.
        cfgFile.setPropertyValue(SectionName::ArgOptions,
            PropertyName::arguments, args);
    }

    std::unique_ptr<Jvm> jvm(new Jvm());

    if (!libEnvVariableContainsAppDir()) {
        (*jvm).addEnvVariable(libEnvVarName, SysInfo::getEnvVariable(
                std::nothrow, libEnvVarName)
                + FileUtils::pathSeparator
                + appDirPath);
    }

    (*jvm)
        .setPath(findJvmLib(cfgFile, defaultRuntimePath, jvmLibNames))
        .addArgument(launcherPath);

    if (initJvmFromCmdlineOnly) {
        tstring_array::const_iterator argIt = args.begin();
        const tstring_array::const_iterator argEnd = args.end();
        for (; argIt != argEnd; ++argIt) {
            (*jvm).addArgument(*argIt);
        }
    } else {
        (*jvm).initFromConfigFile(cfgFile);
    }

    return jvm.release();
}


void AppLauncher::launch() const {
    std::unique_ptr<Jvm>(createJvmLauncher())->launch();
}


tstring AppLauncher::getCfgFilePath() const {
    tstring_array::const_iterator it = cfgFileLookupDirs.begin();
    tstring_array::const_iterator end = cfgFileLookupDirs.end();
    const tstring cfgFileName = FileUtils::stripExeSuffix(
            FileUtils::basename(launcherPath)) + _T(".cfg");
    for (; it != end; ++it) {
        const tstring cfgFilePath = FileUtils::mkpath() << *it << cfgFileName;
        LOG_TRACE(tstrings::any() << "Check [" << cfgFilePath << "] file exit");
        if (FileUtils::isFileExists(cfgFilePath)) {
            return cfgFilePath;
        }
    }

    return FileUtils::mkpath() << appDirPath << cfgFileName;
}
