/*
 * A class to track key JVM instance info from the AT WinAccessBridge
 */

#ifndef __AccessBridgeJavaVMInstance_H__
#define __AccessBridgeJavaVMInstance_H__

#include "AccessBridgePackages.h"

#include <jni.h>
#include <windows.h>

/**
 * The AccessBridgeJavaVMInstance class.
 */
class AccessBridgeJavaVMInstance {
        friend class WinAccessBridge;

        AccessBridgeJavaVMInstance *nextJVMInstance;
        HWND ourAccessBridgeWindow;
        HWND javaAccessBridgeWindow;
        long vmID;

        // IPC variables
        HANDLE memoryMappedFileMapHandle;       // handle to file map
        char *memoryMappedView;                         // ptr to shared memory
        char memoryMappedFileName[cMemoryMappedNameSize];
        BOOL goingAway;


public:
        AccessBridgeJavaVMInstance(HWND ourABWindow, HWND javaABWindow,
                                                           long javaVMID,
                                                           AccessBridgeJavaVMInstance *next);
        ~AccessBridgeJavaVMInstance();
        LRESULT initiateIPC();
        LRESULT sendPackage(char *buffer, long bufsize);
        BOOL sendMemoryPackage(char *buffer, long bufsize);
        HWND findAccessBridgeWindow(long javaVMID);
        AccessBridgeJavaVMInstance *findABJavaVMInstanceFromJavaHWND(HWND window);
};

#endif
