#include <limits.h>
#include <unistd.h>
#include "UnixSysInfo.h"
#include "FileUtils.h"
#include "ErrorHandling.h"

namespace SysInfo {

tstring getProcessModulePath() {
    const char* path = "/proc/self/exe";
    char buffer[PATH_MAX] = { 0 };
    ssize_t len = readlink(path, buffer, sizeof(buffer));
    if (len < 0) {
        JP_THROW(tstrings::any() << "readlink(" << path
                << ") failed. Error: " << lastCRTError());
    }

    return tstring(buffer, len);
}

tstring_array getCommandArgs(CommandArgProgramNameMode progNameMode) {
    tstring_array result;
    for (int i = progNameMode == ExcludeProgramName ? 1 : 0; i < argc; i++) {
        result.push_back(argv[i]);
    }
    return result;
}

} // end of namespace SysInfo
