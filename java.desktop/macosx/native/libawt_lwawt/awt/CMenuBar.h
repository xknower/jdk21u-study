#import "CMenuComponent.h"

extern NSString *CMenuBarDidReuseItemNotification;

@class CMenu;

@interface CMenuBar : CMenuComponent {
    // Menus in this menubar.  Objects in fMenuList must be CMenu's.
    NSMutableArray *fMenuList;
    CMenu *fHelpMenu;
    BOOL fModallyDisabled;
}
- (void) nativeAddMenuAtIndex_OnAppKitThread:(NSArray *)args;
- (void) nativeDeleteMenu_OnAppKitThread:(id)indexObj;

+ (void)clearMenuBarExcludingAppleMenu_OnAppKitThread:(BOOL) excludingAppleMenu;
+ (BOOL) isActiveMenuBar:(CMenuBar *)menuBar;
- (id) initWithPeer:(jobject)peer;
+ (void) activate:(CMenuBar *)menubar modallyDisabled:(BOOL)modallyDisabled;
- (void) deactivate;
- (void) javaAddMenu: (CMenu *)theMenu;
- (void) javaAddMenu: (CMenu *)theMenu atIndex:(jint)index;
- (void) javaDeleteMenu: (jint)index;
- (void) javaSetHelpMenu:(CMenu *)theMenu;

@end
