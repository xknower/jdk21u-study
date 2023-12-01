#include "MTLSamplerManager.h"
#include "MTLContext.h"
#include "sun_java2d_SunGraphics2D.h"
#import "common.h"

@implementation MTLSamplerManager {
    id<MTLSamplerState> _samplerNearestClamp;
    id<MTLSamplerState> _samplerLinearClamp;
    id<MTLSamplerState> _samplerNearestRepeat;
    id<MTLSamplerState> _samplerLinearRepeat;
}

- (id _Nonnull)initWithDevice:(id<MTLDevice>) device {
    self = [super init];
    if (self) {
        MTLSamplerDescriptor *samplerDescriptor = [[MTLSamplerDescriptor new] autorelease];

        samplerDescriptor.rAddressMode = MTLSamplerAddressModeClampToEdge;
        samplerDescriptor.sAddressMode = MTLSamplerAddressModeClampToEdge;
        samplerDescriptor.tAddressMode = MTLSamplerAddressModeClampToEdge;

        samplerDescriptor.minFilter = MTLSamplerMinMagFilterNearest;
        samplerDescriptor.magFilter = MTLSamplerMinMagFilterNearest;
        _samplerNearestClamp = [device newSamplerStateWithDescriptor:samplerDescriptor];

        samplerDescriptor.minFilter = MTLSamplerMinMagFilterLinear;
        samplerDescriptor.magFilter = MTLSamplerMinMagFilterLinear;
        _samplerLinearClamp = [device newSamplerStateWithDescriptor:samplerDescriptor];

        samplerDescriptor.rAddressMode = MTLSamplerAddressModeRepeat;
        samplerDescriptor.sAddressMode = MTLSamplerAddressModeRepeat;
        samplerDescriptor.tAddressMode = MTLSamplerAddressModeRepeat;

        samplerDescriptor.minFilter = MTLSamplerMinMagFilterNearest;
        samplerDescriptor.magFilter = MTLSamplerMinMagFilterNearest;
        _samplerNearestRepeat = [device newSamplerStateWithDescriptor:samplerDescriptor];

        samplerDescriptor.minFilter = MTLSamplerMinMagFilterLinear;
        samplerDescriptor.magFilter = MTLSamplerMinMagFilterLinear;
        _samplerLinearRepeat = [device newSamplerStateWithDescriptor:samplerDescriptor];
    }
    return self;
}

- (void) setSamplerWithEncoder:(id<MTLRenderCommandEncoder>) encoder
                 interpolation:(int) interpolation
                        repeat:(bool) repeat {
    id<MTLSamplerState> sampler;
    if (repeat) {
        sampler = interpolation == INTERPOLATION_BILINEAR ? _samplerLinearRepeat : _samplerNearestRepeat;
    } else {
        sampler = interpolation == INTERPOLATION_BILINEAR ? _samplerLinearClamp : _samplerNearestClamp;
    }
    [encoder setFragmentSamplerState:sampler atIndex:0];
}

- (void)dealloc {
    [_samplerNearestClamp release];
    [_samplerLinearClamp release];
    [_samplerNearestRepeat release];
    [_samplerLinearRepeat release];
    [super dealloc];
}

@end
