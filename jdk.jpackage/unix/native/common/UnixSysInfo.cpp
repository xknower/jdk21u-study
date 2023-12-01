#include <stdlib.h>
#include "SysInfo.h"
#include "UnixSysInfo.h"
#include "ErrorHandling.h"

namespace SysInfo {

tstring getEnvVariable(const tstring& name) {
    char *value = ::getenv(name.c_str());
    if (!value) {
        JP_THROW(tstrings::any()    << "getenv("
                                    << name
                                    << ") failed. Variable not set");
    }
    return tstring(value);
}


tstring getEnvVariable(const std::nothrow_t&, const tstring& name,
                                                    const tstring& defValue) {
    char *value = ::getenv(name.c_str());
    if (value) {
        return tstring(value);
    }
    return defValue;
}


bool isEnvVariableSet(const tstring& name) {
    return ::getenv(name.c_str()) != 0;
}

void setEnvVariable(const tstring& name, const tstring& value) {
    if (::setenv(name.c_str(), value.c_str(), 1) != 0) {
        JP_THROW(tstrings::any() << "setenv(" << name << ", " << value
                                    << ") failed. Error: " << lastCRTError());
    }
}


int argc = 0;
char** argv = 0;

} // end of namespace SysInfo
