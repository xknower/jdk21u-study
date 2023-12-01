#ifndef PlatformLogEvent_h
#define PlatformLogEvent_h

#include <windows.h>


struct PlatformLogEvent {
    SYSTEMTIME ts;
    long tid;
    long pid;
    LPCTSTR moduleName;

    PlatformLogEvent();
};

#endif // PlatformLogEvent_h
