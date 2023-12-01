#ifndef AppLauncher_h
#define AppLauncher_h

#include "tstrings.h"

class Jvm;

class AppLauncher {
public:
    AppLauncher();

    AppLauncher& setImageRoot(const tstring& v) {
        imageRoot = v;
        return *this;
    }

    AppLauncher& setDefaultRuntimePath(const tstring& v) {
        defaultRuntimePath = v;
        return *this;
    }

    AppLauncher& addCfgFileLookupDir(const tstring& v) {
        cfgFileLookupDirs.push_back(v);
        return *this;
    }

    AppLauncher& setAppDir(const tstring& v) {
        appDirPath = v;
        return *this;
    }

    AppLauncher& setLibEnvVariableName(const tstring& v) {
        libEnvVarName = v;
        return *this;
    }

    AppLauncher& setInitJvmFromCmdlineOnly(bool v) {
        initJvmFromCmdlineOnly = v;
        return *this;
    }

    AppLauncher& addJvmLibName(const tstring& v) {
        jvmLibNames.push_back(v);
        return *this;
    }

    bool libEnvVariableContainsAppDir() const;

    Jvm* createJvmLauncher() const;

    void launch() const;

private:
  tstring getCfgFilePath() const;

private:
    tstring_array args;
    tstring launcherPath;
    tstring defaultRuntimePath;
    tstring appDirPath;
    tstring libEnvVarName;
    tstring imageRoot;
    tstring_array jvmLibNames;
    tstring_array cfgFileLookupDirs;
    bool initJvmFromCmdlineOnly;
};

#endif // AppLauncher_h
