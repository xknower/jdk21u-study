#include <limits.h>
#include <sys/stat.h>
#include <unistd.h>
#include "FileUtils.h"
#include "ErrorHandling.h"


namespace FileUtils {

bool isFileExists(const tstring &filePath) {
    struct stat statBuffer;
    return (stat(filePath.c_str(), &statBuffer) != -1);
}


tstring toAbsolutePath(const tstring& path) {
    if (path.empty()) {
        char buffer[PATH_MAX] = { 0 };
        char* buf = getcwd(buffer, sizeof(buffer));
        if (buf) {
            tstring result(buf);
            if (result.empty()) {
                JP_THROW(tstrings::any() << "getcwd() returned empty string");
            }
            return result;
        }

        JP_THROW(tstrings::any() << "getcwd() failed. Error: "
                << lastCRTError());
    }

    if (isDirSeparator(path[0])) {
        return path;
    }

    return mkpath() << toAbsolutePath("") << path;
}

tstring stripExeSuffix(const tstring& path) {
    // for unix - there is no suffix to remove
    return path;
}

} //  namespace FileUtils
