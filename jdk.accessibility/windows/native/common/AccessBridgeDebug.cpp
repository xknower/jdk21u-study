/*
 * A class to manage AccessBridge debugging
 */

#include "AccessBridgeDebug.h"
#include <stdarg.h>
#include <stdio.h>
#include <windows.h>
#include <cstdlib>
#include <cstring>

#ifdef __cplusplus
extern "C" {
#endif

static FILE* logFP = nullptr;

void initializeFileLogger(const char * fileName) {
    auto var = "JAVA_ACCESSBRIDGE_LOGDIR";
    const auto envfilePath = getenv(var);
    if (envfilePath != nullptr && fileName != nullptr) {
        auto envFilePathLength = strlen(envfilePath);
        auto fileNameLength = strlen(fileName);
        auto filePathSize = envFilePathLength + 1 + fileNameLength + 5; //1 for "/", 5 for ".log" and 0;
        auto filePath = new char[filePathSize];
        memset(filePath, 0, filePathSize*sizeof(char));
        memcpy(filePath, envfilePath, envFilePathLength*sizeof(char));
        filePath[envFilePathLength] = '/';
        memcpy(filePath + envFilePathLength + 1, fileName, fileNameLength*sizeof(char));
        memcpy(filePath + envFilePathLength + 1 + fileNameLength, ".log", 4*sizeof(char));

        logFP = fopen(filePath, "w");
        if (logFP == nullptr) {
            printf("\n%s\n", filePath);
            PrintDebugString("Could not open file %s", filePath);
        }

        delete [] filePath;
    }
}

void finalizeFileLogger() {
    if (logFP) {
        fclose(logFP);
        logFP = nullptr;
    }
}

unsigned long long getTimeStamp() {
    FILETIME ft;
    ULARGE_INTEGER uli;
    GetSystemTimeAsFileTime(&ft);
    uli.LowPart = ft.dwLowDateTime;
    uli.HighPart = ft.dwHighDateTime;
    return (uli.QuadPart / 10000ULL) - 11644473600000ULL; // Rebase Epoch from 1601 to 1970
}

/**
 * print a GetLastError message
 */
char *printError(const char *msg) {
    LPVOID lpMsgBuf = nullptr;
    static char retbuf[256] = {0};

    if (msg != nullptr) {
        strncpy((char *)retbuf, msg, sizeof(retbuf));
        // if msg text is >= 256 ensure buffer is null terminated
        retbuf[255] = '\0';
    }
    if (!FormatMessage(
                       FORMAT_MESSAGE_ALLOCATE_BUFFER |
                       FORMAT_MESSAGE_FROM_SYSTEM |
                       FORMAT_MESSAGE_IGNORE_INSERTS,
                       nullptr,
                       GetLastError(),
                       MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
                       (LPTSTR) &lpMsgBuf,
                       0,
                       nullptr))
        {
            PrintDebugString("  %s: FormatMessage failed", msg);
        } else {
            PrintDebugString("  %s: %s", msg, (char *)lpMsgBuf);
        }
    if (lpMsgBuf != nullptr) {
        strncat((char *)retbuf, ": ", sizeof(retbuf) - strlen(retbuf) - 1);
        strncat((char *)retbuf, (char *)lpMsgBuf, sizeof(retbuf) - strlen(retbuf) - 1);
        LocalFree(lpMsgBuf);
    }
    return (char *)retbuf;
}


    /**
     * Send debugging info to the appropriate place
     */
    void PrintDebugString(const char *msg, ...) {
#ifdef DEBUGGING_ON
        char buf[1024] = {0};
        va_list argprt;

        va_start(argprt, msg);     // set up argptr
        vsnprintf(buf, sizeof(buf), msg, argprt);
#ifdef SEND_TO_OUTPUT_DEBUG_STRING
        OutputDebugString(buf);
#endif
#ifdef SEND_TO_CONSOLE
        printf(buf);
        printf("\r\n");
#endif
#endif
        if (logFP) {
            fprintf(logFP, "[%llu] ", getTimeStamp());
            va_list args;
            va_start(args, msg);
            vfprintf(logFP, msg, args);
            va_end(args);
            fprintf(logFP, "\r\n");
        }
    }

    /**
     * Send Java debugging info to the appropriate place
     */
    void PrintJavaDebugString2(const char *msg, ...) {
#ifdef JAVA_DEBUGGING_ON
        char buf[1024] = {0};
        va_list argprt;

        va_start(argprt, msg);     // set up argptr
        vsnprintf(buf, sizeof(buf), msg, argprt);
#ifdef SEND_TO_OUTPUT_DEBUG_STRING
        OutputDebugString(buf);
#endif
#ifdef SEND_TO_CONSOLE
        printf(buf);
        printf("\r\n");
#endif
#endif
        if (logFP) {
            fprintf(logFP, "[%llu] ", getTimeStamp());
            va_list args;
            va_start(args, msg);
            vfprintf(logFP, msg, args);
            va_end(args);
            fprintf(logFP, "\r\n");
        }
    }
    /**
     * Wide version of the method to send debugging info to the appropriate place
     */
    void wPrintDebugString(const wchar_t *msg, ...) {
#ifdef DEBUGGING_ON
        char buf[1024] = {0};
        char charmsg[256];
        va_list argprt;

        va_start(argprt, msg);          // set up argptr
        snprintf(charmsg, sizeof(charmsg), "%ls", msg);  // convert format string to multi-byte
        vsnprintf(buf, sizeof(buf), charmsg, argprt);
#ifdef SEND_TO_OUTPUT_DEBUG_STRING
        OutputDebugString(buf);
#endif
#ifdef SEND_TO_CONSOLE
        printf(buf);
        printf("\r\n");
#endif
#endif
        if (logFP) {
            fprintf(logFP, "[%llu] ", getTimeStamp());
            va_list args;
            va_start(args, msg);
            vfwprintf(logFP, msg, args);
            va_end(args);
            fprintf(logFP, "\r\n");
        }
    }

    /**
     * Wide version of the method to send Java debugging info to the appropriate place
     */
    void wPrintJavaDebugString(const wchar_t *msg, ...) {
#ifdef JAVA_DEBUGGING_ON
        char buf[1024] = {0};
        char charmsg[256] = {0};
        va_list argprt;

        va_start(argprt, msg);          // set up argptr
        snprintf(charmsg, sizeof(charmsg), "%ls", msg);  // convert format string to multi-byte
        vsnprintf(buf, sizeof(buf), charmsg, argprt);
#ifdef SEND_TO_OUTPUT_DEBUG_STRING
        OutputDebugString(buf);
#endif
#ifdef SEND_TO_CONSOLE
        printf(buf);
        printf("\r\n");
#endif
#endif
        if (logFP) {
            fprintf(logFP, "[%llu] ", getTimeStamp());
            va_list args;
            va_start(args, msg);
            vfwprintf(logFP, msg, args);
            va_end(args);
            fprintf(logFP, "\r\n");
        }
    }
#ifdef __cplusplus
}
#endif
