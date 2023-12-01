
#import <Foundation/Foundation.h>
#import <jni.h>

@class NSPrintInfo;
@class NSView;

@interface PrintModel : NSObject {
    NSPrintInfo* fPrintInfo;
}

- (id)initWithPrintInfo:(NSPrintInfo*)printInfo;
- (BOOL)runPageSetup;
- (BOOL)runJobSetup;
- (BOOL)runPrintLoopWithView:(NSView*)printerView waitUntilDone:(BOOL)wait withEnv:(JNIEnv *)env;
- (BOOL)safePrintLoop:(id)arg withEnv:(JNIEnv *)env;

@end
