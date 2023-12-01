#import "CommonComponentAccessibility.h"

@interface TabGroupAccessibility : CommonComponentAccessibility {
    NSInteger _numTabs;
}

@property(readonly) NSInteger numTabs;

- (id)currentTabWithEnv:(JNIEnv *)env withAxContext:(jobject)axContext;
- (NSArray *)tabButtonsWithEnv:(JNIEnv *)env withTabGroupAxContext:(jobject)axContext withTabCode:(NSInteger)whichTabs allowIgnored:(BOOL)allowIgnored;
- (NSArray *)contentsWithEnv:(JNIEnv *)env withTabGroupAxContext:(jobject)axContext withTabCode:(NSInteger)whichTabs allowIgnored:(BOOL)allowIgnored;

@end
