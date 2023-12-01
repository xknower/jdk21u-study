/*
 * A class to manage AccessBridge debugging
 */

#ifndef __AccessBridgeDebug_H__
#define __AccessBridgeDebug_H__

#include <crtdbg.h>
#include <windows.h>

#ifdef DEBUG
#define DEBUGGING_ON
#define SEND_TO_OUTPUT_DEBUG_STRING
//#define JAVA_DEBUGGING_ON
#endif

#ifdef DEBUGGING_ON
#define DEBUG_CODE(x) x
#else
#define DEBUG_CODE(x) /* */
#endif

#ifdef __cplusplus
extern "C" {
#endif

    char *printError(const char *msg);
    void PrintDebugString(const char *msg, ...);
    void PrintJavaDebugString(const char *msg, ...);
    void wPrintJavaDebugString(const wchar_t *msg, ...);
    void wPrintDebugString(const wchar_t *msg, ...);
    void initializeFileLogger(const char * fileName);
    void finalizeFileLogger();

#ifdef __cplusplus
}
#endif


#endif
