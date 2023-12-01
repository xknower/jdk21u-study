#ifndef RESOURCEEDITOR_H
#define RESOURCEEDITOR_H

#include <windows.h>
#include <vector>
#include <string>


class ResourceEditor {
public:
    class FileLock {
    public:
        explicit FileLock(const std::wstring& binaryPath);
        explicit FileLock(HANDLE h);
        ~FileLock();

        HANDLE get() const {
            return h;
        }

        void discard(bool v = true) {
            theDiscard = v;
        }

        FileLock& ownHandle(bool v) {
            theOwnHandle = v;
            return *this;
        }

    private:
        FileLock(const FileLock&);
        FileLock& operator=(const FileLock&);
    private:
        HANDLE h;
        bool theOwnHandle;
        bool theDiscard;
    };

public:
    ResourceEditor();

    /**
     * Set the language identifier of the resource to be updated.
     */
    ResourceEditor& language(unsigned v) {
        lang = v;
        return *this;
    }

    /**
     * Set the resource type to be updated.
     */
    ResourceEditor& type(unsigned v);

    /**
     * Set the resource type to be updated.
     */
    ResourceEditor& type(LPCWSTR v);

    /**
     * Set resource ID.
     */
    ResourceEditor& id(unsigned v);

    /**
     * Set resource ID.
     */
    ResourceEditor& id(LPCWSTR v);

    /**
     * Replaces resource configured in the given binary with the given data stream.
     */
    ResourceEditor& apply(const FileLock& dstBinary, std::istream& srcStream, std::streamsize size=0);

    /**
     * Replaces resource configured in the given binary with contents of
     * the given binary file.
     */
    ResourceEditor& apply(const FileLock& dstBinary, const std::wstring& srcFile);

private:
    unsigned lang;
    std::wstring theId;
    LPCWSTR theIdPtr;
    std::wstring theType;
    LPCWSTR theTypePtr;
};

#endif // #ifndef RESOURCEEDITOR_H
