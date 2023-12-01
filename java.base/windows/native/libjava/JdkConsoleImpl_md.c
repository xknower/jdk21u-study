#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jdk_internal_io_JdkConsoleImpl.h"

#include <stdlib.h>
#include <Wincon.h>

JNIEXPORT jboolean JNICALL
Java_jdk_internal_io_JdkConsoleImpl_echo(JNIEnv *env, jclass cls, jboolean on)
{
    DWORD fdwMode;
    jboolean old;
    HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);

    if (! GetConsoleMode(hStdIn, &fdwMode)) {
        JNU_ThrowIOExceptionWithLastError(env, "GetConsoleMode failed");
        return !on;
    }
    old = (fdwMode & ENABLE_ECHO_INPUT) != 0;
    if (on) {
        fdwMode |= ENABLE_ECHO_INPUT;
    } else {
        fdwMode &= ~ENABLE_ECHO_INPUT;
    }
    if (! SetConsoleMode(hStdIn, fdwMode)) {
        JNU_ThrowIOExceptionWithLastError(env, "SetConsoleMode failed");
    }
    return old;
}
