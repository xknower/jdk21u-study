#import "CMenuComponent.h"

#import "ThreadUtilities.h"

@class CMenuItem;

@implementation CMenuComponent

-(id) initWithPeer:(jobject)peer {
    self = [super init];
    if (self) {
        // the peer has been made clobal ref before
        fPeer = peer;
    }
    return self;
}

- (void)dealloc {
    JNIEnv *env = [ThreadUtilities getJNIEnvUncached];
    (*env)->DeleteGlobalRef(env, fPeer);
    fPeer = NULL;

    [super dealloc];
}
@end
