#ifndef VERSIONINFO_H
#define VERSIONINFO_H

#include <map>
#include <string>
#include "ResourceEditor.h"


class VersionInfo {
public:
    VersionInfo();

    VersionInfo& setProperty(const std::wstring& id, const std::wstring& value);

    /**
     * Replaces existing VS_VERSIONINFO structure in the file locked
     * with the passed in ResourceEditor::FileLock instance with data
     * configured for this instance.
     */
    const VersionInfo& apply(const ResourceEditor::FileLock& fileLock) const;

    VersionInfo& apply(const ResourceEditor::FileLock& fileLock) {
        static_cast<const VersionInfo&>(*this).apply(fileLock);
        return *this;
    }

private:
    void fillBuffer(std::ostream& buf) const;

    VS_FIXEDFILEINFO createFIXEDFILEINFO() const;

    typedef std::map<std::wstring, std::wstring> PropertyMap;

    PropertyMap props;
};

#endif // #ifndef VERSIONINFO_H
