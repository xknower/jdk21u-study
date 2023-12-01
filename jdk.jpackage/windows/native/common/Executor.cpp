#include <algorithm>
#include "Executor.h"
#include "Log.h"
#include "WinErrorHandling.h"


namespace {

void escapeArg(std::wstring& str) {
    if (str.empty()) {
        return;
    }

    if (str.front() == L'\"' && str.back() == L'\"' && str.size() > 1) {
        return;
    }

    if (str.find_first_of(L" \t") != std::wstring::npos) {
        str = L'"' + str + L'"';
    }
}

} // namespace


std::wstring Executor::args() const {
    tstring_array tmpArgs;
    // argv[0] is the module name.
    tmpArgs.push_back(appPath);
    tmpArgs.insert(tmpArgs.end(), argsArray.begin(), argsArray.end());

    std::for_each(tmpArgs.begin(), tmpArgs.end(), escapeArg);
    return tstrings::join(tmpArgs.begin(), tmpArgs.end(), _T(" "));
}


int Executor::execAndWaitForExit() const {
    UniqueHandle threadHandle;
    UniqueHandle h = startProcess(&threadHandle);

    if (theSuspended) {
        LOG_TRACE(tstrings::any() << "ResumeThread()");
        if (((DWORD)-1) == ResumeThread(threadHandle.get())) {
            JP_THROW(SysError("ResumeThread() failed", ResumeThread));
        }
    }

    const DWORD res = ::WaitForSingleObject(h.get(), INFINITE);
    if (WAIT_FAILED ==  res) {
        JP_THROW(SysError("WaitForSingleObject() failed", WaitForSingleObject));
    }

    DWORD exitCode = 0;
    if (!GetExitCodeProcess(h.get(), &exitCode)) {
        // Error reading process's exit code.
        JP_THROW(SysError("GetExitCodeProcess() failed", GetExitCodeProcess));
    }

    const DWORD processId = GetProcessId(h.get());
    if (!processId) {
        JP_THROW(SysError("GetProcessId() failed.", GetProcessId));
    }

    LOG_TRACE(tstrings::any() << "Process with PID=" << processId
                                << " terminated. Exit code=" << exitCode);

    return static_cast<int>(exitCode);
}


UniqueHandle Executor::startProcess(UniqueHandle* threadHandle) const {
    const std::wstring argsStr = args();

    std::vector<TCHAR> argsBuffer(argsStr.begin(), argsStr.end());
    argsBuffer.push_back(0); // terminating '\0'

    STARTUPINFO startupInfo;
    ZeroMemory(&startupInfo, sizeof(startupInfo));
    startupInfo.cb = sizeof(startupInfo);

    PROCESS_INFORMATION processInfo;
    ZeroMemory(&processInfo, sizeof(processInfo));

    DWORD creationFlags = 0;

    if (theSuspended) {
        creationFlags |= CREATE_SUSPENDED;
    }

    if (!theVisible) {
        // For GUI applications.
        startupInfo.dwFlags |= STARTF_USESHOWWINDOW;
        startupInfo.wShowWindow = SW_HIDE;

        // For console applications.
        creationFlags |= CREATE_NO_WINDOW;
    }

    tstrings::any msg;
    msg << "CreateProcess";
    if (theSuspended) {
        msg << "[suspended]";
    }
    if (theVisible) {
        msg << "[visible]";
    }
    if (theInherit) {
        msg << "[inherit]";
    }
    msg << "(" << appPath << ", " << argsStr << ")";

    if (!CreateProcess(appPath.c_str(), argsBuffer.data(), NULL, NULL,
                      theInherit ? TRUE : FALSE, creationFlags, NULL, NULL,
                      &startupInfo, &processInfo)) {
        msg << " failed";
        JP_THROW(SysError(msg, CreateProcess));
    }

    msg << " succeeded; PID=" << processInfo.dwProcessId;
    LOG_TRACE(msg);

    if (threadHandle) {
        *threadHandle = UniqueHandle(processInfo.hThread);
    } else {
        // Close unneeded handle immediately.
        UniqueHandle(processInfo.hThread);
    }

    if (jobHandle != NULL) {
        LOG_TRACE(tstrings::any() << "AssignProcessToJobObject(PID="
                << processInfo.dwProcessId << ")");
        if (!AssignProcessToJobObject(jobHandle, processInfo.hProcess)) {
            JP_THROW(SysError(tstrings::any() <<
                    "AssignProcessToJobObject() failed",
                    AssignProcessToJobObject));
        }
    }

    // Return process handle.
    return UniqueHandle(processInfo.hProcess);
}
