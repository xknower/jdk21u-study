#ifndef JAVA_COMPONENT_ACCESSIBILITY
#define JAVA_COMPONENT_ACCESSIBILITY

#include "jni.h"

#import <AppKit/AppKit.h>
#import "JavaAccessibilityUtilities.h"

@interface CommonComponentAccessibility : NSAccessibilityElement {
    NSView *fView;
    NSObject *fParent;

    NSString *fNSRole;
    NSString *fJavaRole;

    jint fIndex;
    jobject fAccessible;
    jobject fComponent;

    NSMutableDictionary *fActions;
    NSMutableArray *fActionSelectors;
    NSObject *fActionsLOCK;
}

@property(nonnull, readonly) NSArray *actionSelectors;

- (id _Nonnull)initWithParent:(NSObject* _Nonnull)parent withEnv:(JNIEnv _Nonnull * _Nonnull)env withAccessible:(jobject _Nullable)accessible withIndex:(jint)index withView:(NSView* _Nonnull)view withJavaRole:(NSString* _Nullable)javaRole;
- (void)unregisterFromCocoaAXSystem;
- (void)postValueChanged;
- (void)postSelectedTextChanged;
- (void)postSelectionChanged;
- (void)postTitleChanged;
- (void)postTreeNodeExpanded;
- (void)postTreeNodeCollapsed;
- (void)postSelectedCellsChanged;
- (BOOL)isEqual:(nonnull id)anObject;
- (BOOL)isAccessibleWithEnv:(JNIEnv _Nonnull * _Nonnull)env forAccessible:(nonnull jobject)accessible;

+ (void)postFocusChanged:(nullable id)message;

+ (void) initializeRolesMap;

+ (Class _Nonnull) getComponentAccessibilityClass:(NSString* _Nonnull)role;
+ (Class _Nonnull) getComponentAccessibilityClass:(NSString * _Nonnull)role andParent:(CommonComponentAccessibility * _Nonnull)parent;

+ (NSArray* _Nullable)childrenOfParent:(CommonComponentAccessibility* _Nonnull)parent withEnv:(JNIEnv _Nonnull * _Nonnull)env withChildrenCode:(NSInteger)whichChildren allowIgnored:(BOOL)allowIgnored;
+ (NSArray* _Nullable)childrenOfParent:(CommonComponentAccessibility* _Nonnull)parent withEnv:(JNIEnv _Nonnull * _Nonnull)env withChildrenCode:(NSInteger)whichChildren allowIgnored:(BOOL)allowIgnored recursive:(BOOL)recursive;
+ (CommonComponentAccessibility* _Nullable) createWithParent:(CommonComponentAccessibility* _Nullable)parent accessible:(jobject _Nonnull)jaccessible role:(NSString* _Nonnull)javaRole index:(jint)index withEnv:(JNIEnv _Nonnull * _Nonnull)env withView:(NSView* _Nonnull)view;
+ (CommonComponentAccessibility* _Nullable) createWithParent:(CommonComponentAccessibility* _Nullable)parent withClass:(Class _Nonnull)classType accessible:(jobject _Nonnull)jaccessible role:(NSString* _Nonnull)javaRole index:(jint)index withEnv:(JNIEnv _Nonnull * _Nonnull)env withView:(NSView* _Nonnull)view;
+ (CommonComponentAccessibility* _Nullable) createWithAccessible:(jobject _Nonnull)jaccessible role:(NSString* _Nonnull)role index:(jint)index withEnv:(JNIEnv _Nonnull * _Nonnull)env withView:(NSView* _Nonnull)view;
+ (CommonComponentAccessibility* _Nullable) createWithAccessible:(jobject _Nonnull)jaccessible withEnv:(JNIEnv _Nonnull * _Nonnull)env withView:(NSView* _Nonnull)view;

// The current parameter is used to bypass the check for an item's index on the parent so that the item is created. This is necessary,
// for example, for AccessibleJTreeNode, whose currentComponent has index -1
+ (CommonComponentAccessibility* _Nullable) createWithAccessible:(jobject _Nonnull)jaccessible withEnv:(JNIEnv _Nonnull * _Nonnull)env withView:(NSView* _Nonnull)view isCurrent:(BOOL)current;

- (jobject _Nullable)axContextWithEnv:(JNIEnv _Nonnull * _Nonnull)env;
- (NSView* _Nonnull)view;
- (NSWindow* _Nonnull)window;
- (id _Nonnull)parent;
- (CommonComponentAccessibility* _Nullable)typeSafeParent;
- (NSString* _Nonnull)javaRole;

- (BOOL)isMenu;
- (BOOL)isSelected:(JNIEnv _Nonnull * _Nonnull)env;
- (BOOL)isSelectable:(JNIEnv _Nonnull * _Nonnull)env;
- (BOOL)isVisible:(JNIEnv _Nonnull * _Nonnull)env;

- (NSArray* _Nullable)accessibleChildrenWithChildCode:(NSInteger)childCode;

- (NSDictionary* _Nullable)getActions:(JNIEnv _Nonnull * _Nonnull)env;
- (void)getActionsWithEnv:(JNIEnv _Nonnull * _Nonnull)env;
- (BOOL)accessiblePerformAction:(NSAccessibilityActionName _Nonnull)actionName;

- (BOOL)performAccessibleAction:(int)index;

- (NSRect)accessibilityFrame;
- (id _Nullable)accessibilityParent;
- (BOOL)isAccessibilityElement;
@end

#endif
