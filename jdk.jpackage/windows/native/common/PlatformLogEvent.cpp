#include <cstring>
#include "PlatformLogEvent.h"
#include "FileUtils.h"
#include "Log.h"


namespace {

    tstring retrieveModuleName() {
        try {
            return FileUtils::basename(SysInfo::getCurrentModulePath());
        }
        catch (const std::runtime_error&) {
            return _T("Unknown");
        }
    }

    TCHAR moduleName[MAX_PATH] = { 'U', 'n', 'k', 'n', 'o', 'w', 'n', TCHAR(0) };

    const LPCTSTR formatStr = _T("%04u/%02u/%02u %02u:%02u:%02u.%03u, %s (PID: %u, TID: %u), ");

} // namespace


PlatformLogEvent::PlatformLogEvent() {
    std::memset(static_cast<void*>(this), 0, sizeof(*this));
}


void LogEvent::init(PlatformLogEvent& logEvent) {
    GetLocalTime(&logEvent.ts);
    logEvent.pid = GetCurrentProcessId();
    logEvent.tid = GetCurrentThreadId();
    logEvent.moduleName = ::moduleName;
}


void LogEvent::appendFormatted(const PlatformLogEvent& logEvent,
        tstring& buffer) {
    const tstring str = tstrings::unsafe_format(formatStr,
        unsigned(logEvent.ts.wYear),
        unsigned(logEvent.ts.wMonth),
        unsigned(logEvent.ts.wDay),
        unsigned(logEvent.ts.wHour),
        unsigned(logEvent.ts.wMinute),
        unsigned(logEvent.ts.wSecond),
        unsigned(logEvent.ts.wMilliseconds),
        logEvent.moduleName,
        logEvent.pid,
        logEvent.tid);
    buffer.append(str);
}


void Logger::initializingLogging() {
    moduleName[0] = TCHAR(0);
}


void Logger::initializeLogging() {
    tstring mname = retrieveModuleName();
    mname.resize(_countof(moduleName) - 1);
    std::memcpy(moduleName, mname.c_str(), mname.size());
    moduleName[mname.size()] = TCHAR(0);
}
