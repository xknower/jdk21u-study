#ifndef _AWTWINDOW_H
#define _AWTWINDOW_H

#import <Cocoa/Cocoa.h>

#import "CMenuBar.h"
#import "LWCToolkit.h"

@class AWTView;

@interface AWTWindow : NSObject <NSWindowDelegate> {
@private
    jobject javaPlatformWindow; /* This is a weak ref. Always copy to a local ref before using */
    CMenuBar *javaMenuBar;
    NSSize javaMinSize;
    NSSize javaMaxSize;
    jint styleBits;
    BOOL isEnabled;
    NSWindow *nsWindow;
    AWTWindow *ownerWindow;
    jint preFullScreenLevel;
    NSRect standardFrame;
    BOOL isMinimizing;
    BOOL keyNotificationRecd;
}

// An instance of either AWTWindow_Normal or AWTWindow_Panel
@property (nonatomic, retain) NSWindow *nsWindow;

@property (nonatomic) jobject javaPlatformWindow;
@property (nonatomic, retain) CMenuBar *javaMenuBar;
@property (nonatomic, retain) AWTWindow *ownerWindow;
@property (nonatomic) NSSize javaMinSize;
@property (nonatomic) NSSize javaMaxSize;
@property (nonatomic) jint styleBits;
@property (nonatomic) BOOL isEnabled;
@property (nonatomic) jint preFullScreenLevel;
@property (nonatomic) NSRect standardFrame;
@property (nonatomic) BOOL isMinimizing;
@property (nonatomic) BOOL keyNotificationRecd;

- (id) initWithPlatformWindow:(jobject)javaPlatformWindow
                  ownerWindow:owner
                    styleBits:(jint)styleBits
                    frameRect:(NSRect)frameRect
                  contentView:(NSView *)contentView;

- (BOOL) isTopmostWindowUnderMouse;

// NSWindow overrides delegate methods
- (BOOL) canBecomeKeyWindow;
- (BOOL) canBecomeMainWindow;
- (BOOL) worksWhenModal;
- (void)sendEvent:(NSEvent *)event;

+ (void) setLastKeyWindow:(AWTWindow *)window;
+ (AWTWindow *) lastKeyWindow;

@end

@interface AWTWindow_Normal : NSWindow
- (id) initWithDelegate:(AWTWindow *)delegate
              frameRect:(NSRect)rect
              styleMask:(NSUInteger)styleMask
            contentView:(NSView *)view;
@end

@interface AWTWindow_Panel : NSPanel
- (id) initWithDelegate:(AWTWindow *)delegate
              frameRect:(NSRect)rect
              styleMask:(NSUInteger)styleMask
            contentView:(NSView *)view;
@end

#endif _AWTWINDOW_H
