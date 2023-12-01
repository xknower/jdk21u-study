#import <AppKit/AppKit.h>
#import <jni.h>

@interface CMenuComponent : NSObject {

@public
    jobject fPeer;
}

// Setup
- (id) initWithPeer:(jobject)peer;
- (void) disposer;
@end
