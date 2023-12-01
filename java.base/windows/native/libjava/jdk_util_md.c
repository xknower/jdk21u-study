#include <windows.h>
#include "jdk_util.h"

#define JVM_DLL "jvm.dll"

JNIEXPORT HMODULE JDK_LoadSystemLibrary(const char* name) {
    HMODULE handle = NULL;
    char path[MAX_PATH];

    if (GetSystemDirectory(path, sizeof(path)) != 0) {
        strcat(path, "\\");
        strcat(path, name);
        handle = LoadLibrary(path);
    }

    if (handle == NULL) {
        if (GetWindowsDirectory(path, sizeof(path)) != 0) {
            strcat(path, "\\");
            strcat(path, name);
            handle = LoadLibrary(path);
        }
    }
    return handle;
}

