#ifndef MTLSamplerManager_h_Included
#define MTLSamplerManager_h_Included

#import <Metal/Metal.h>

#include "RenderOptions.h"

@class MTLContex;


@interface MTLSamplerManager : NSObject
- (id _Nonnull)initWithDevice:(_Nonnull id<MTLDevice>) device;
- (void)dealloc;

- (void) setSamplerWithEncoder:(_Nonnull id<MTLRenderCommandEncoder>) encoder
                 interpolation:(int) interpolation
                        repeat:(bool) repeat;
@end

#endif // MTLSamplerManager_h_Included
