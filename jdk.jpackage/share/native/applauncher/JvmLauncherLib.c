#include <string.h>
#include <stdlib.h>
#include <errno.h>
#ifdef LINUX
#include <unistd.h>
#endif

#include "JvmLauncher.h"


typedef int (JNICALL *JLI_LaunchFuncType)(int argc, char ** argv,
        int jargc, const char** jargv,
        int appclassc, const char** appclassv,
        const char* fullversion,
        const char* dotversion,
        const char* pname,
        const char* lname,
        jboolean javaargs,
        jboolean cpwildcard,
        jboolean javaw,
        jint ergo);


JvmlLauncherData* jvmLauncherCreateJvmlLauncherData(
                    JvmlLauncherAPI* api, JvmlLauncherHandle h, int* size) {
    JvmlLauncherData* result = 0;
    void* buf = 0;
    int jvmLauncherDataBufferSize;

    if (!h) {
        return 0;
    }

    jvmLauncherDataBufferSize = jvmLauncherGetJvmlLauncherDataSize(api, h);
    if (jvmLauncherDataBufferSize <= 0) {
        goto cleanup;
    }

    buf = malloc(jvmLauncherDataBufferSize);
    if (!buf) {
        JP_LOG_ERRNO;
        goto cleanup;
    }

    result = jvmLauncherInitJvmlLauncherData(api, h, buf,
                                                jvmLauncherDataBufferSize);
    if (result) {
        /* Don't free the buffer in clean up. */
        buf = 0;
        if (size) {
            *size = jvmLauncherDataBufferSize;
        }
    }

cleanup:
    jvmLauncherCloseHandle(api, h);
    free(buf);

    return result;
}


static void dumpJvmlLauncherData(const JvmlLauncherData* jvmArgs) {
    int i = 0;
    JP_LOG_TRACE("jli lib: [%s]", jvmArgs->jliLibPath);
    for (i = 0; i < jvmArgs->jliLaunchArgc; ++i) {
        JP_LOG_TRACE("jli arg[%d]: [%s]", i, jvmArgs->jliLaunchArgv[i]);
    }
    for (i = 0; i < jvmArgs->envVarCount; ++i) {
        JP_LOG_TRACE("env var[%d]: %s=[%s]", i, jvmArgs->envVarNames[i],
                                                jvmArgs->envVarValues[i]);
    }
}


int jvmLauncherStartJvm(JvmlLauncherData* jvmArgs, void* JLI_Launch) {
    int i;
    int exitCode;

    dumpJvmlLauncherData(jvmArgs);

    for (i = 0; i < jvmArgs->envVarCount; ++i) {
#ifdef _WIN32
        if (!SetEnvironmentVariable(jvmArgs->envVarNames[i],
                                                jvmArgs->envVarValues[i])) {
            JP_LOG_TRACE("SetEnvironmentVariable(%d) failed", i);
        }
#else
        if (setenv(jvmArgs->envVarNames[i],
                                        jvmArgs->envVarValues[i], 1) != 0) {
            JP_LOG_TRACE("setenv(%d) failed", i);
        }
#endif
    }

    exitCode = (*((JLI_LaunchFuncType)JLI_Launch))(
        jvmArgs->jliLaunchArgc, jvmArgs->jliLaunchArgv,
        0, 0,
        0, 0,
        "",
        "",
        "java",
        "java",
        JNI_FALSE,
        JNI_FALSE,
        JNI_FALSE,
        0);

    return exitCode;
}


void jvmLauncherLog(const char* format, ...) {
    const char *withLog = getenv("JPACKAGE_DEBUG");
    if (!withLog || strcmp(withLog, "true")) {
        return;
    }

    va_list args;
    va_start(args, format);

#if defined(__GNUC__) && __GNUC__ >= 5
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wformat-nonliteral"
#endif
#ifdef LINUX
    fprintf(stderr, "[%d]: ", getpid());
#endif
    vfprintf(stderr, format, args);
    fprintf(stderr, "\n");
#if defined(__GNUC__) && __GNUC__ >= 5
#pragma GCC diagnostic pop
#endif

    va_end (args);
}
