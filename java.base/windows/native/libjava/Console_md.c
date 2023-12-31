#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "java_io_Console.h"

#include <stdlib.h>
#include <Wincon.h>

JNIEXPORT jboolean JNICALL
Java_java_io_Console_istty(JNIEnv *env, jclass cls)
{
    HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
    HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);

    if (hStdIn == INVALID_HANDLE_VALUE ||
        hStdOut == INVALID_HANDLE_VALUE) {
        return JNI_FALSE;
    }

    if (GetFileType(hStdIn) != FILE_TYPE_CHAR ||
        GetFileType(hStdOut) != FILE_TYPE_CHAR) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

JNIEXPORT jstring JNICALL
Java_java_io_Console_encoding(JNIEnv *env, jclass cls)
{
    char buf[64];
    int cp = GetConsoleCP();
    if (cp >= 874 && cp <= 950)
        snprintf(buf, sizeof(buf), "ms%d", cp);
    else if (cp == 65001)
        snprintf(buf, sizeof(buf), "UTF-8");
    else
        snprintf(buf, sizeof(buf), "cp%d", cp);
    return JNU_NewStringPlatform(env, buf);
}
