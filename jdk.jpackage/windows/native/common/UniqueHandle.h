#ifndef UNIQUEHANDLE_H
#define UNIQUEHANDLE_H

#include <windows.h>
#include <memory>


struct WndHandleDeleter {
    typedef HANDLE pointer;

    void operator()(HANDLE h) {
        ::CloseHandle(h);
    }
};

typedef std::unique_ptr<HANDLE, WndHandleDeleter> UniqueHandle;

#endif // #ifndef UNIQUEHANDLE_H
