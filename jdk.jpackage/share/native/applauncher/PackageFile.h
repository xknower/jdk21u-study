#ifndef PackageFile_h
#define PackageFile_h

#include "tstrings.h"


class PackageFile {
public:
    static PackageFile loadFromAppDir(const tstring& appDirPath);

    tstring getPackageName() const {
        return packageName;
    }

private:
    PackageFile(const tstring& packageName);

private:
    tstring packageName;
};

#endif // PackageFile_h
