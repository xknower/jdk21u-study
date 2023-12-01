
#import "PrintModel.h"

#import "PrinterView.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"

@implementation PrintModel

- (id)initWithPrintInfo:(NSPrintInfo*)printInfo {
    self = [super init];
    if (self) {
        fPrintInfo = [printInfo retain];
    }

    return self;
}

- (void)dealloc {
    [fPrintInfo release];
    fPrintInfo = nil;

    [super dealloc];
}

- (BOOL)runPageSetup {
    __block BOOL fResult = NO;

    [ThreadUtilities performOnMainThreadWaiting:YES block:^(){
        NSPageLayout* pageLayout = [NSPageLayout pageLayout];
        fResult = ([pageLayout runModalWithPrintInfo:fPrintInfo] == NSOKButton);
    }];

    return fResult;
}

- (BOOL)runJobSetup {
    __block BOOL fResult = NO;

    [ThreadUtilities performOnMainThreadWaiting:YES block:^(){
        NSPrintPanel* printPanel = [NSPrintPanel printPanel];
        fResult = ([printPanel runModalWithPrintInfo:fPrintInfo] == NSOKButton);
    }];

    return fResult;
}

- (BOOL)runPrintLoopWithView:(NSView*)printerView waitUntilDone:(BOOL)wait withEnv:(JNIEnv *)env
{
AWT_ASSERT_NOT_APPKIT_THREAD;

    BOOL fResult = NO;

    // <rdar://problem/4310184> Because people like to put up modal dialogs during print operations,
    // we have to run the print operation on a non-AppKit thread or else we get a deadlock and errors
    // the AppKit team believes it's OK for us to call runOperation from non-AppKit threads,
    // as long as we don't show any panels, and we don't touch the NSPrintInfo or the NSView from other threads.
    if (wait) {
        fResult = [self safePrintLoop:printerView withEnv:env];
    } else {
        // Retain these so they don't go away while we're in Java
        [self retain];
        [printerView retain];

        DECLARE_CLASS_RETURN(jc_CPrinterJob, "sun/lwawt/macosx/CPrinterJob", NO);
        DECLARE_STATIC_METHOD_RETURN(jm_detachPrintLoop, jc_CPrinterJob, "detachPrintLoop", "(JJ)V", NO);
        (*env)->CallStaticVoidMethod(env, jc_CPrinterJob, jm_detachPrintLoop, ptr_to_jlong(self), ptr_to_jlong(printerView)); // AWT_THREADING Safe (known object)
        CHECK_EXCEPTION();
    }

    return fResult;
}

- (BOOL) safePrintLoop:(id)arg withEnv:(JNIEnv *)env
{
AWT_ASSERT_NOT_APPKIT_THREAD;

    PrinterView* printerView = (PrinterView*)arg;
    BOOL fResult;
    @try {
        NSPrintOperation* printLoop = [NSPrintOperation printOperationWithView:printerView printInfo:fPrintInfo];
        [printLoop setShowPanels:NO];    //+++gdb Problem: This will avoid progress bars...
        //[printLoop setCanSpawnSeparateThread:YES]; //+++gdb Need to check this...

        fResult = [printLoop runOperation];
    } @finally {
        // Tell CPrinterJob that things are done.
        [printerView complete:env];
    }
    return fResult;
}

@end

/*
 * Class:     sun_lwawt_macosx_CPrinterJob
 * Method:    _safePrintLoop
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_sun_lwawt_macosx_CPrinterJob__1safePrintLoop
(JNIEnv *env, jclass clz, jlong target, jlong view)
{
JNI_COCOA_ENTER(env);

    PrintModel *model = (PrintModel *)jlong_to_ptr(target);
    PrinterView *arg = (PrinterView *)jlong_to_ptr(view);

    [model safePrintLoop:arg withEnv:env];

    // These are to match the retains in runPrintLoopWithView:
    [model release];
    [arg release];

JNI_COCOA_EXIT(env);
}

