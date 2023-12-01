#include <jni.h>
#import <Foundation/Foundation.h>
#import <AppKit/AppKit.h>
#import <AppKit/NSTrackingArea.h>

#import "CPopupMenu.h"

#ifndef _Included_sun_awt_lwmacosx_CTrayIcon
#define _Included_sun_awt_lwmacosx_CTrayIcon

#ifdef __cplusplus
extern "C" {
#endif

@class AWTTrayIconView;

/*
 * AWTTrayIcon
 */
@interface AWTTrayIcon : NSObject <NSUserNotificationCenterDelegate>{
    jobject peer;
    AWTTrayIconView *view;
    NSStatusItem *theItem;
}

- (id) initWithPeer:(jobject)thePeer;
- (void) setTooltip:(NSString *)tooltip;
- (NSStatusItem *)theItem;
- (jobject) peer;
- (void) setImage:(NSImage *) imagePtr sizing:(BOOL)autosize template:(BOOL)isTemplate;
- (NSPoint) getLocationOnScreen;
- (void) deliverJavaMouseEvent:(NSEvent*) event;

@end //AWTTrayIcon

//==================================================================================
/*
 * AWTTrayIconView */
@interface AWTTrayIconView : NSStatusBarButton <NSMenuDelegate> {
@public
    AWTTrayIcon *trayIcon;
    NSTrackingArea *trackingArea;
    BOOL isHighlighted;
}
-(id)initWithTrayIcon:(AWTTrayIcon *)theTrayIcon;
-(void)setHighlighted:(BOOL)aFlag;
-(void)setTrayIcon:(AWTTrayIcon*)theTrayIcon;
-(void)addTrackingArea;

@end //AWTTrayIconView

#ifdef __cplusplus
}
#endif
#endif
