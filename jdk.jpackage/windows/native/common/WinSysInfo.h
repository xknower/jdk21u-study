#ifndef WINSYSINFO_H
#define WINSYSINFO_H

#include "SysInfo.h"


//
// Windows specific SysInfo.
//
namespace SysInfo {
    // gets Windows System folder. A typical path is C:\Windows\System32.
    tstring getSystem32Dir();

    // returns full path to msiexec.exe executable
    tstring getWIPath();

    // Returns handle of the current module (exe or dll).
    // The function assumes this code is statically linked to the module.
    HMODULE getCurrentModuleHandle();
}


#endif // WINSYSINFO_H
