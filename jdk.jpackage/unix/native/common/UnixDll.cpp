#include <dlfcn.h>
#include "Dll.h"
#include "FileUtils.h"
#include "ErrorHandling.h"


namespace {

HMODULE loadLibrary(const tstring& path) {
    HMODULE h = dlopen(path.c_str(), RTLD_LAZY);
    if (!h) {
        JP_THROW(tstrings::any() << "dlopen(" << path
                << ") failed. Error: " << dlerror());
    }
    return h;
}

} // namesace

Dll::Dll(const tstrings::any &libPath): thePath(libPath.tstr()),
                                        handle(loadLibrary(thePath)) {
}

Dll::Dll(const Dll& other): thePath(other.thePath),
                            handle(loadLibrary(thePath)) {
}

void* Dll::getFunction(const std::string &name, bool throwIfNotFound) const {
    void *ptr = dlsym(handle.get(), name.c_str());
    if (throwIfNotFound && !ptr) {
        JP_THROW(tstrings::any() << "dlsym(" << thePath
                << ", " << name << ") failed. Error: " << dlerror());
    }
    return ptr;
}

/*static*/
void Dll::freeLibrary(HMODULE h) {
    if (h) {
        dlclose(h);
    }
}
