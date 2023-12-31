#include <stdlib.h>
#include <string.h>
#include <windows.h>
#include <locale.h>

#include "jni.h"
#include "jni_util.h"

void* getProcessHandle() {
    return (void*)GetModuleHandle(NULL);
}

/*
 * Windows symbols can be simple like JNI_OnLoad or __stdcall format
 * like _JNI_OnLoad@8. We need to handle both.
 */
void buildJniFunctionName(const char *sym, const char *cname,
                          char *jniEntryName) {
    if (cname != NULL) {
        char *p = strrchr(sym, '@');
        if (p != NULL && p != sym) {
            // sym == _JNI_OnLoad@8
            strncpy(jniEntryName, sym, (p - sym));
            jniEntryName[(p-sym)] = '\0';
            // jniEntryName == _JNI_OnLoad
            strcat(jniEntryName, "_");
            strcat(jniEntryName, cname);
            strcat(jniEntryName, p);
            //jniEntryName == _JNI_OnLoad_cname@8
        } else {
            strcpy(jniEntryName, sym);
            strcat(jniEntryName, "_");
            strcat(jniEntryName, cname);
        }
    } else {
        strcpy(jniEntryName, sym);
    }
    return;
}

jstring
getLastErrorString(JNIEnv *env) {

#define BUFSIZE 256
    DWORD errval;
    WCHAR buf[BUFSIZE];

    if ((errval = GetLastError()) != 0) {
        // DOS error
        jsize n = FormatMessageW(
                FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
                NULL,
                errval,
                0,
                buf,
                BUFSIZE,
                NULL);
        if (n > 3) {
            // Drop final '.', CR, LF
            if (buf[n - 1] == L'\n') n--;
            if (buf[n - 1] == L'\r') n--;
            if (buf[n - 1] == L'.') n--;
            buf[n] = L'\0';
        }
        jstring s = (*env)->NewString(env, buf, n);
        return s;
    }
    return NULL;
}

JNIEXPORT int JNICALL
getErrorString(int err, char *buf, size_t len)
{
    int ret = 0;
    if (err == 0 || len < 1) return 0;
    ret = strerror_s(buf, len, err);
    return ret;
}
