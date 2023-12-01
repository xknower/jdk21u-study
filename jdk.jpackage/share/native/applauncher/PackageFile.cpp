#include "kludge_c++11.h"

#include <fstream>
#include "PackageFile.h"
#include "Log.h"
#include "FileUtils.h"
#include "ErrorHandling.h"


PackageFile::PackageFile(const tstring& v): packageName(v) {
}


PackageFile PackageFile::loadFromAppDir(const tstring& appDirPath) {
    tstring packageName;
    const tstring packageFilePath =
            FileUtils::mkpath() << appDirPath << _T(".package");
    if (FileUtils::isFileExists(packageFilePath)) {
        LOG_TRACE(tstrings::any() << "Read \"" << packageFilePath
                                  << "\" package file");
        std::ifstream input(packageFilePath);
        if (!input.good()) {
            JP_THROW(tstrings::any() << "Error opening \"" << packageFilePath
                    << "\" file: " << lastCRTError());
        }

        std::string utf8line;
        if (std::getline(input, utf8line)) {
            LOG_TRACE(tstrings::any()
                    << "Package name is [" << utf8line << "]");
            packageName = tstrings::any(utf8line).tstr();
        }
    }

    return PackageFile(packageName);
}
