#ifndef MTLStencilManager_h_Included
#define MTLStencilManager_h_Included

#import <Metal/Metal.h>

#include "RenderOptions.h"

@class MTLContex;


@interface MTLStencilManager : NSObject
- (id _Nonnull)initWithDevice:(_Nonnull id<MTLDevice>) device;
- (void)dealloc;
@property (readonly) _Nonnull id<MTLDepthStencilState> stencilState;
@property (readonly) _Nonnull id<MTLDepthStencilState> genStencilState;
@end

#endif // MTLSamplerManager_h_Included
