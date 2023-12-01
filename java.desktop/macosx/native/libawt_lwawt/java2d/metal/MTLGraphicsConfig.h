#ifndef MTLGraphicsConfig_h_Included
#define MTLGraphicsConfig_h_Included

#import "JNIUtilities.h"
#import "MTLSurfaceDataBase.h"
#import "MTLContext.h"
#import <Cocoa/Cocoa.h>
#import <Metal/Metal.h>


@interface MTLGraphicsConfigUtil : NSObject {}
+ (void) _getMTLConfigInfo: (NSMutableArray *)argValue;
@end

/**
 * The MTLGraphicsConfigInfo structure contains information specific to a
 * given MTLGraphicsConfig (pixel format).
 *     MTLContext *context;
 * The context associated with this MTLGraphicsConfig.
 */
typedef struct _MTLGraphicsConfigInfo {
    MTLContext          *context;
} MTLGraphicsConfigInfo;

#endif /* MTLGraphicsConfig_h_Included */
