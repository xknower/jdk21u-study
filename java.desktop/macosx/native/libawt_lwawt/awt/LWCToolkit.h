#include "jni.h"

#import <pthread.h>
#import <assert.h>

#import <Cocoa/Cocoa.h>

//#define DEBUG 1

// number of mouse buttons supported
extern int gNumberOfButtons;

// InputEvent mask array
extern jint* gButtonDownMasks;

@interface AWTToolkit : NSObject { }
+ (BOOL) inDoDragDropLoop;
+ (void) setInDoDragDropLoop:(BOOL)val;
+ (long) getEventCount;
+ (void) eventCountPlusPlus;
+ (jint) scrollStateWithEvent: (NSEvent*) event;
+ (BOOL) hasPreciseScrollingDeltas: (NSEvent*) event;
@end

/*
 * Utility Macros
 */

/** Macro to cast a jlong to an Objective-C object (id). Casts to long on 32-bit systems to quiesce the compiler. */
#define OBJC(jl) ((id)jlong_to_ptr(jl))
