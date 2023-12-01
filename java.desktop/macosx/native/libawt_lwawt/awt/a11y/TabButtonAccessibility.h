#import "CommonComponentAccessibility.h"

@interface TabButtonAccessibility : CommonComponentAccessibility {
    jobject fTabGroupAxContext;
}

@property(readonly) jobject tabGroup;

// from TabGroup controller
- (id)initWithParent:(NSObject *)parent withEnv:(JNIEnv *)env withAccessible:(jobject)accessible withIndex:(jint)index withTabGroup:(jobject)tabGroup withView:(NSView *)view withJavaRole:(NSString *)javaRole;
- (void)performPressAction;

@end
