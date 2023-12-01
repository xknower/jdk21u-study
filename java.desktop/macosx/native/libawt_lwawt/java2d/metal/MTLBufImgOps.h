#ifndef MTLBufImgOps_h_Included
#define MTLBufImgOps_h_Included

#include "MTLContext.h"

@interface MTLRescaleOp : NSObject
- (id)init:(jboolean)isNonPremult factors:(unsigned char *)factors offsets:(unsigned char *)offsets;
- (jfloat *)getScaleFactors;
- (jfloat *)getOffsets;
- (NSString *)getDescription; // creates autorelease string

@property (readonly) jboolean isNonPremult;
@end

@interface MTLConvolveOp : NSObject
- (id)init:(jboolean)edgeZeroFill kernelWidth:(jint)kernelWidth
                                 kernelHeight:(jint)kernelHeight
                                     srcWidth:(jint)srcWidth
                                    srcHeight:(jint)srcHeight
                                       kernel:(unsigned char *)kernel
                                       device:(id<MTLDevice>)device;
- (void) dealloc;

- (id<MTLBuffer>) getBuffer;
- (const float *) getImgEdge;
- (NSString *)getDescription; // creates autorelease string

@property (readonly) jboolean isEdgeZeroFill;
@property (readonly) int kernelSize;
@end

@interface MTLLookupOp : NSObject
- (id)init:(jboolean)nonPremult shortData:(jboolean)shortData
                                 numBands:(jint)numBands
                               bandLength:(jint)bandLength
                                   offset:(jint)offset
                              tableValues:(void *)tableValues
                                   device:(id<MTLDevice>)device;
- (void) dealloc;

- (jfloat *)getOffset;
- (id<MTLTexture>) getLookupTexture;
- (NSString *)getDescription; // creates autorelease string

@property (readonly) jboolean isUseSrcAlpha;
@property (readonly) jboolean isNonPremult;
@end

#endif /* MTLBufImgOps_h_Included */
