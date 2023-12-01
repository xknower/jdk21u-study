#import "ComponentWrapperAccessibility.h"
#import "ThreadUtilities.h"

@implementation ComponentWrapperAccessibility

@synthesize wrappedChild;

- (NSAccessibilityRole)accessibilityRole {
    @throw [NSException exceptionWithName:NSInternalInconsistencyException
                                   reason:[NSString stringWithFormat:@"You must override -(NSAccessibilityRole)accessibilityRole in a subclass"]
                                 userInfo:nil];
}

- (NSArray *)accessibilityChildren {
    if (!wrappedChild) {
        wrappedChild =
                [[CommonComponentAccessibility alloc] initWithParent:self
                                                             withEnv:[ThreadUtilities getJNIEnv]
                                                      withAccessible:fAccessible
                                                           withIndex:0
                                                            withView:fView
                                                        withJavaRole:fJavaRole];
    }
    return [NSArray arrayWithObject:wrappedChild];
}

- (void)dealloc {
    if (wrappedChild) {
        [wrappedChild release];
    }
    [super dealloc];
}

@end
