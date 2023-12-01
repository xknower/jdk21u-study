#ifndef MTLLayer_h_Included
#define MTLLayer_h_Included
#import <Metal/Metal.h>
#import <QuartzCore/CAMetalLayer.h>
#include <CoreVideo/CVDisplayLink.h>
#import "common.h"

@interface MTLLayer : CAMetalLayer
{
@private
    jobject javaLayer;

    // intermediate buffer, used the RQ lock to synchronize
    MTLContext* ctx;
    float bufferWidth;
    float bufferHeight;
    id<MTLTexture> buffer;
    int nextDrawableCount;
    int topInset;
    int leftInset;
    CVDisplayLinkRef displayLink;
}

@property (nonatomic) jobject javaLayer;
@property (readwrite, assign) MTLContext* ctx;
@property (readwrite, assign) float bufferWidth;
@property (readwrite, assign) float bufferHeight;
@property (readwrite, assign) id<MTLTexture> buffer;
@property (readwrite, assign) int nextDrawableCount;
@property (readwrite, assign) int topInset;
@property (readwrite, assign) int leftInset;
@property (readwrite, assign) CVDisplayLinkRef displayLink;
@property (readwrite, atomic) int displayLinkCount;

- (id) initWithJavaLayer:(jobject)layer;

- (void) blitTexture;
- (void) fillParallelogramCtxX:(jfloat)x
                             Y:(jfloat)y
                           DX1:(jfloat)dx1
                           DY1:(jfloat)dy1
                           DX2:(jfloat)dx2
                           DY2:(jfloat)dy2;
- (void) blitCallback;
- (void) display;
- (void) redraw;
- (void) startDisplayLink;
- (void) stopDisplayLink;
@end

#endif /* MTLLayer_h_Included */
