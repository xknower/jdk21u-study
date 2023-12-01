#ifndef macosx_port_awt_debug_h
#define macosx_port_awt_debug_h

#include "jni.h"

#import <Cocoa/Cocoa.h>

JNIEXPORT bool ShouldPrintVerboseDebugging();

#define kInternalError "java/lang/InternalError"

#define AWT_DEBUG_LOG(str) \
    NSLog(@"\tCocoa AWT: %@ %@", str, [NSThread callStackSymbols])

#define AWT_STARTUP_LOG(str) \
    if (ShouldPrintVerboseDebugging()) AWT_DEBUG_LOG((str))

#define AWT_DEBUG_BUG_REPORT_MESSAGE \
    NSLog(@"\tPlease file a bug report at https://bugreport.java.com/bugreport \
with this message and a reproducible test case.")

#endif
