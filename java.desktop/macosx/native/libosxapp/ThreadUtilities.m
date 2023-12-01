#import <AppKit/AppKit.h>
#import <objc/message.h>

#import "ThreadUtilities.h"


// The following must be named "jvm", as there are extern references to it in AWT
JavaVM *jvm = NULL;
static JNIEnv *appKitEnv = NULL;
static jobject appkitThreadGroup = NULL;
static NSString* JavaRunLoopMode = @"AWTRunLoopMode";
static NSArray<NSString*> *javaModes = nil;

static inline void attachCurrentThread(void** env) {
    if ([NSThread isMainThread]) {
        JavaVMAttachArgs args;
        args.version = JNI_VERSION_1_4;
        args.name = "AppKit Thread";
        args.group = appkitThreadGroup;
        (*jvm)->AttachCurrentThreadAsDaemon(jvm, env, &args);
    } else {
        (*jvm)->AttachCurrentThreadAsDaemon(jvm, env, NULL);
    }
}

@implementation ThreadUtilities

+ (void)initialize {
    /* All the standard modes plus ours */
    javaModes = [[NSArray alloc] initWithObjects:NSDefaultRunLoopMode,
                                           NSModalPanelRunLoopMode,
                                           NSEventTrackingRunLoopMode,
                                           JavaRunLoopMode,
                                           nil];
}

+ (JNIEnv*)getJNIEnv {
AWT_ASSERT_APPKIT_THREAD;
    if (appKitEnv == NULL) {
        attachCurrentThread((void **)&appKitEnv);
    }
    return appKitEnv;
}

+ (JNIEnv*)getJNIEnvUncached {
    JNIEnv *env = NULL;
    attachCurrentThread((void **)&env);
    return env;
}

+ (void)detachCurrentThread {
    (*jvm)->DetachCurrentThread(jvm);
}

+ (void)setAppkitThreadGroup:(jobject)group {
    appkitThreadGroup = group;
}

/* This is needed because we can't directly pass a block to
 * performSelectorOnMainThreadWaiting .. since it expects a selector
 */
+ (void)invokeBlock:(void (^)())block {
  block();
}

/*
 * When running a block where either we don't wait, or it needs to run on another thread
 * we need to copy it from stack to heap, use the copy in the call and release after use.
 * Do this only when we must because it could be expensive.
 * Note : if waiting cross-thread, possibly the stack allocated copy is accessible ?
 */
+ (void)invokeBlockCopy:(void (^)(void))blockCopy {
  blockCopy();
  Block_release(blockCopy);
}

+ (void)performOnMainThreadWaiting:(BOOL)wait block:(void (^)())block {
    if ([NSThread isMainThread] && wait == YES) {
        block();
    } else {
        if (wait == YES) {
            [self performOnMainThread:@selector(invokeBlock:) on:self withObject:block waitUntilDone:YES];
        } else {
            void (^blockCopy)(void) = Block_copy(block);
            [self performOnMainThread:@selector(invokeBlockCopy:) on:self withObject:blockCopy waitUntilDone:NO];
        }
    }
}

+ (void)performOnMainThread:(SEL)aSelector on:(id)target withObject:(id)arg waitUntilDone:(BOOL)wait {
    if ([NSThread isMainThread] && wait == YES) {
        [target performSelector:aSelector withObject:arg];
    } else {
        [target performSelectorOnMainThread:aSelector withObject:arg waitUntilDone:wait modes:javaModes];
    }
}

+ (NSString*)javaRunLoopMode {
    return JavaRunLoopMode;
}

@end


void OSXAPP_SetJavaVM(JavaVM *vm)
{
    jvm = vm;
}

