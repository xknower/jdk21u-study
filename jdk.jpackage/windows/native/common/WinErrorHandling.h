#ifndef WinErrorHandling_h
#define WinErrorHandling_h


#include "ErrorHandling.h"


class SysError : public std::runtime_error {
public:
    SysError(const tstrings::any& msg, const void* caller,
            DWORD errorCode=GetLastError(), const char* label="System error");

    // returns string "system error <errCode> (error_description)"
    // in UNICODE is not defined, the string returned is utf8-encoded
    static std::wstring getSysErrorMessage(DWORD errCode = GetLastError(),
            HMODULE moduleHandle = NULL);

    // returns string "COM error 0x<hr> (error_description)"
    // in UNICODE is not defined, the string returned is utf8-encoded
    static std::wstring getComErrorMessage(HRESULT hr);
};


/**
 * Debug break. The function tests if the given environment variable is set.
 * If it is and value of 'substr' parameter is a substring of value of
 * the given environment variable message box is popped up waiting for
 * user input.
 * If value of environment variable is '*', value of 'substr' parameter is not
 * tested and message box is popped up unconditionally.
 */
void debugBreak(const SourceCodePos& location, const tstring& envVarName,
                                                        const tstring& substr);

#define JP_DEBUG_BREAK(env, substr) ::debugBreak(JP_SOURCE_CODE_POS, _T(#env), _T(#substr))

#endif // #ifndef WinErrorHandling_h
