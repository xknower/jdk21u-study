#include <io.h>
#include <fcntl.h>
#include <stdlib.h>
#include <windows.h>

#include "AppLauncher.h"
#include "JvmLauncher.h"
#include "Log.h"
#include "Dll.h"
#include "WinApp.h"
#include "Toolbox.h"
#include "Executor.h"
#include "FileUtils.h"
#include "PackageFile.h"
#include "UniqueHandle.h"
#include "ErrorHandling.h"
#include "WinSysInfo.h"
#include "WinErrorHandling.h"


// AllowSetForegroundWindow - Requires linking with user32


namespace {

std::unique_ptr<Dll> loadDllWithAlteredPATH(const tstring& dllFullPath) {
    LOG_TRACE_FUNCTION();

    const tstring vanillaPathEnvVariable = SysInfo::getEnvVariable(_T("PATH"));

    tstring pathEnvVariable = vanillaPathEnvVariable
            + _T(";")
            + FileUtils::dirname(dllFullPath);

    SysInfo::setEnvVariable(_T("PATH"), pathEnvVariable);

    LOG_TRACE(tstrings::any() << "New value of PATH: " << pathEnvVariable);

    // Schedule restore of PATH after attempt to load the given dll
    const auto resetPATH = runAtEndOfScope([&vanillaPathEnvVariable]() -> void {
        SysInfo::setEnvVariable(_T("PATH"), vanillaPathEnvVariable);
    });

    return std::unique_ptr<Dll>(new Dll(dllFullPath));
}

std::unique_ptr<Dll> loadDllWithAddDllDirectory(const tstring& dllFullPath) {
    LOG_TRACE_FUNCTION();

    const tstring dirPath = FileUtils::dirname(dllFullPath);

    typedef DLL_DIRECTORY_COOKIE(WINAPI *AddDllDirectoryFunc)(PCWSTR);

    DllFunction<AddDllDirectoryFunc> _AddDllDirectory(
            Dll("kernel32.dll", Dll::System()), "AddDllDirectory");

    AddDllDirectoryFunc func = _AddDllDirectory;
    DLL_DIRECTORY_COOKIE res = func(dirPath.c_str());
    if (!res) {
        JP_THROW(SysError(tstrings::any()
                << "AddDllDirectory(" << dirPath << ") failed", func));
    }

    LOG_TRACE(tstrings::any() << "AddDllDirectory(" << dirPath << "): OK");

    // Important: use LOAD_LIBRARY_SEARCH_DEFAULT_DIRS flag,
    // but not LOAD_LIBRARY_SEARCH_USER_DIRS!
    HMODULE dllHandle = LoadLibraryEx(dllFullPath.c_str(), NULL,
            LOAD_LIBRARY_SEARCH_DEFAULT_DIRS);

    LOG_TRACE(tstrings::any() << "LoadLibraryEx(" << dllFullPath
            << ", LOAD_LIBRARY_SEARCH_DEFAULT_DIRS): " << dllHandle);

    const auto freeDll = runAtEndOfScope([&dllHandle]() -> void {
        Dll::freeLibrary(dllHandle);
    });

    return std::unique_ptr<Dll>(new Dll(dllFullPath));
}


class DllWrapper {
public:
    DllWrapper(const tstring& dllName) {
        try {
            // Adjust the DLL search paths with AddDllDirectory() WINAPI CALL
            dll = loadDllWithAddDllDirectory(dllName);
        } catch (const std::exception&) {
            // Alter PATH environment variable as the last resort.
            dll = loadDllWithAlteredPATH(dllName);
        }
    }

private:
    DllWrapper(const DllWrapper&);
    DllWrapper& operator=(const DllWrapper&);

private:
    std::unique_ptr<Dll> dll;
};


tstring getJvmLibPath(const Jvm& jvm) {
    FileUtils::mkpath path;

    path << FileUtils::dirname(jvm.getPath()) << _T("server") << _T("jvm.dll");

    return path;
}


void addCfgFileLookupDirForEnvVariable(
        const PackageFile& pkgFile, AppLauncher& appLauncher,
        const tstring& envVarName) {

    tstring path;
    JP_TRY;
    path = SysInfo::getEnvVariable(envVarName);
    JP_CATCH_ALL;

    if (!path.empty()) {
        appLauncher.addCfgFileLookupDir(FileUtils::mkpath() << path
                << pkgFile.getPackageName());
    }
}


void launchApp() {
    // [RT-31061] otherwise UI can be left in back of other windows.
    ::AllowSetForegroundWindow(ASFW_ANY);

    const tstring launcherPath = SysInfo::getProcessModulePath();
    const tstring appImageRoot = FileUtils::dirname(launcherPath);
    const tstring appDirPath = FileUtils::mkpath() << appImageRoot << _T("app");

    const PackageFile pkgFile = PackageFile::loadFromAppDir(appDirPath);

    AppLauncher appLauncher = AppLauncher().setImageRoot(appImageRoot)
        .addJvmLibName(_T("bin\\jli.dll"))
        .setAppDir(appDirPath)
        .setLibEnvVariableName(_T("PATH"))
        .setDefaultRuntimePath(FileUtils::mkpath() << appImageRoot
            << _T("runtime"));

    if (!pkgFile.getPackageName().empty()) {
        addCfgFileLookupDirForEnvVariable(pkgFile, appLauncher, _T("LOCALAPPDATA"));
        addCfgFileLookupDirForEnvVariable(pkgFile, appLauncher, _T("APPDATA"));
    }

    const bool restart = !appLauncher.libEnvVariableContainsAppDir();

    std::unique_ptr<Jvm> jvm(appLauncher.createJvmLauncher());

    if (restart) {
        jvm->setEnvVariables();

        jvm = std::unique_ptr<Jvm>();

        UniqueHandle jobHandle(CreateJobObject(NULL, NULL));
        if (jobHandle.get() == NULL) {
            JP_THROW(SysError(tstrings::any() << "CreateJobObject() failed",
                                                            CreateJobObject));
        }
        JOBOBJECT_EXTENDED_LIMIT_INFORMATION jobInfo = { };
        jobInfo.BasicLimitInformation.LimitFlags =
                                          JOB_OBJECT_LIMIT_KILL_ON_JOB_CLOSE;
        if (!SetInformationJobObject(jobHandle.get(),
                JobObjectExtendedLimitInformation, &jobInfo, sizeof(jobInfo))) {
            JP_THROW(SysError(tstrings::any() <<
                                            "SetInformationJobObject() failed",
                                                    SetInformationJobObject));
        }

        Executor exec(launcherPath);
        exec.visible(true).withJobObject(jobHandle.get()).suspended(true).inherit(true);
        const auto args = SysInfo::getCommandArgs();
        std::for_each(args.begin(), args.end(), [&exec] (const tstring& arg) {
            exec.arg(arg);
        });

        DWORD exitCode = static_cast<DWORD>(exec.execAndWaitForExit());

        exit(exitCode);
        return;
    }

    // zip.dll (and others) may be loaded by java without full path
    // make sure it will look in runtime/bin
    const tstring runtimeBinPath = FileUtils::dirname(jvm->getPath());
    SetDllDirectory(runtimeBinPath.c_str());
    LOG_TRACE(tstrings::any() << "SetDllDirectory to: " << runtimeBinPath);

    const DllWrapper jliDll(jvm->getPath());
    std::unique_ptr<DllWrapper> splashDll;
    if (jvm->isWithSplash()) {
        const DllWrapper jvmDll(getJvmLibPath(*jvm));
        splashDll = std::unique_ptr<DllWrapper>(new DllWrapper(
                FileUtils::mkpath()
                        << FileUtils::dirname(jvm->getPath())
                        << _T("splashscreen.dll")));
    }

    jvm->launch();
}

} // namespace

#ifndef JP_LAUNCHERW

int __cdecl  wmain() {
    return app::launch(std::nothrow, launchApp);
}

#else

int __stdcall wWinMain(HINSTANCE, HINSTANCE, LPWSTR, int) {
    return app::wlaunch(std::nothrow, launchApp);
}

#endif
