#ifndef PLATFORM_API_WINOS_CHARSET_UTILS_H
#define PLATFORM_API_WINOS_CHARSET_UTILS_H

#include <windows.h>

#ifdef __cplusplus
extern "C" {
#endif

// NOTE: It's a caller responbility to free the allocated memory using delete[], just like in UnicodeToUTF8AndCopy function
LPSTR _cdecl UnicodeToUTF8(const LPCWSTR lpAnsiStr);

void _cdecl UnicodeToUTF8AndCopy(LPSTR dest, LPCWSTR src, SIZE_T maxLength);

#ifdef __cplusplus
}
#endif

#endif
