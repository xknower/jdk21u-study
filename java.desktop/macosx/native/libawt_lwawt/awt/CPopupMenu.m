#import <Cocoa/Cocoa.h>

#import "AWTWindow.h"
#import "AWTView.h"
#import "CPopupMenu.h"
#import "ThreadUtilities.h"
#import "LWCToolkit.h"
#import "GeomUtilities.h"

@implementation CPopupMenu

- (id) initWithPeer:(jobject)peer {
    self = [super initWithPeer:peer];
    if (self == nil) {
        // TODO: not implemented
    }
    return self;
}

- (NSString *)description {
    return [NSString stringWithFormat:@"CMenuItem[ %@ ]", fMenuItem];
}

@end // implementationCPopupMenu : CMenu


  /*
   * Class:     sun_lwawt_macosx_CPopupMenu
   * Method:    nativeCreatePopupMenu
   * Signature: (JII)J
   */
JNIEXPORT jlong JNICALL Java_sun_lwawt_macosx_CPopupMenu_nativeCreatePopupMenu
(JNIEnv *env, jobject peer) {

    __block CPopupMenu *aCPopupMenu = nil;

JNI_COCOA_ENTER(env);

    jobject cPeerObjGlobal = (*env)->NewGlobalRef(env, peer);

    [ThreadUtilities performOnMainThreadWaiting:YES block:^(){
        aCPopupMenu = [[CPopupMenu alloc] initWithPeer:cPeerObjGlobal];
    }];

JNI_COCOA_EXIT(env);

    return ptr_to_jlong(aCPopupMenu);
}

JNIEXPORT void JNICALL Java_sun_lwawt_macosx_CPopupMenu_nativeShowPopupMenu
(JNIEnv *env, jobject peer, jlong menuPtr, jint x, jint y) {

    JNI_COCOA_ENTER(env);

    CPopupMenu* cPopupMenu = (CPopupMenu*)jlong_to_ptr(menuPtr);

    [ThreadUtilities performOnMainThreadWaiting:NO block:^(){
        NSPoint loc = ConvertNSScreenPoint(env, NSMakePoint(x, y));

        [[cPopupMenu menu] popUpMenuPositioningItem: nil
                                         atLocation: loc
                                             inView: nil];
    }];

    JNI_COCOA_EXIT(env);

}

