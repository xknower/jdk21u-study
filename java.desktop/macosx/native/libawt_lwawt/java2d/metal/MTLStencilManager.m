#include "MTLStencilManager.h"
//#include "MTLContext.h"
//#include "sun_java2d_SunGraphics2D.h"
//#import "common.h"

@implementation MTLStencilManager {
    id<MTLDepthStencilState> _stencilState;
    id<MTLDepthStencilState> _genStencilState;
}

@synthesize stencilState = _stencilState;
@synthesize genStencilState = _genStencilState;

- (id _Nonnull)initWithDevice:(id<MTLDevice>) device {
    self = [super init];
    if (self) {
        MTLDepthStencilDescriptor* stencilDescriptor;
        stencilDescriptor = [[MTLDepthStencilDescriptor new] autorelease];
        stencilDescriptor.frontFaceStencil.stencilCompareFunction = MTLCompareFunctionEqual;
        stencilDescriptor.frontFaceStencil.stencilFailureOperation = MTLStencilOperationKeep;

        // TODO : backFaceStencil can be set to nil if all primitives are drawn as front-facing primitives
        // currently, fill parallelogram uses back-facing primitive drawing - that needs to be changed.
        // Once that part is changed, set backFaceStencil to nil
        //stencilDescriptor.backFaceStencil = nil;

        stencilDescriptor.backFaceStencil.stencilCompareFunction = MTLCompareFunctionEqual;
        stencilDescriptor.backFaceStencil.stencilFailureOperation = MTLStencilOperationKeep;
        _stencilState = [device newDepthStencilStateWithDescriptor:stencilDescriptor];

        MTLDepthStencilDescriptor* genStencilDescriptor;
        genStencilDescriptor = [[MTLDepthStencilDescriptor new] autorelease];
        genStencilDescriptor.backFaceStencil.stencilCompareFunction = MTLCompareFunctionAlways;
        genStencilDescriptor.backFaceStencil.depthStencilPassOperation = MTLStencilOperationReplace;
        genStencilDescriptor.frontFaceStencil.stencilCompareFunction = MTLCompareFunctionAlways;
        genStencilDescriptor.frontFaceStencil.depthStencilPassOperation = MTLStencilOperationReplace;
        _genStencilState = [device newDepthStencilStateWithDescriptor:genStencilDescriptor];
    }
    return self;
}

- (void)dealloc {
    [_stencilState release];
    [super dealloc];
}

@end
