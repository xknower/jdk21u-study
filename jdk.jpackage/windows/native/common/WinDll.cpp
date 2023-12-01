#include "Dll.h"
#include "SysInfo.h"
#include "FileUtils.h"
#include "WinSysInfo.h"
#include "WinErrorHandling.h"


namespace {

Dll::Handle loadLibrary(const std::wstring& path) {
    HMODULE h = LoadLibraryW(path.c_str());
    if (!h) {
        JP_THROW(SysError(tstrings::any() << "LoadLibraryW(" <<
                                            path << ") failed", LoadLibraryW));
    }
    return Dll::Handle(h);
}

} // namesace

Dll::Dll(const tstrings::any &libPath): thePath(libPath.tstr()),
                                        handle(loadLibrary(libPath.wstr())) {
}

Dll::Dll(const tstrings::any &libName, const System &tag):
        thePath(FileUtils::combinePath(SysInfo::getSystem32Dir(),
                libName.tstr())),
        handle(loadLibrary(tstrings::any(thePath).wstr())) {
}

Dll::Dll(const Dll& other): thePath(other.thePath) {
    HMODULE h = NULL;
    if (!GetModuleHandleExW(0, thePath.c_str(), &h)) {
        JP_THROW(SysError(tstrings::any() << "GetModuleHandleExW("
                                << thePath << ") failed", GetModuleHandleExW));
    }
    handle = Handle(h);
}

void* Dll::getFunction(const std::string &name, bool throwIfNotFound) const {
    void *ptr = GetProcAddress(handle.get(), name.c_str());
    if (throwIfNotFound && ptr == NULL) {
        JP_THROW(SysError(tstrings::any() << "GetProcAddress(" << thePath
                              << ", " << name << ") failed", GetProcAddress));
    }
    return ptr;
}

/*static*/
void Dll::freeLibrary(HMODULE h) {
    if (h) {
        FreeLibrary(h);
    }
}
