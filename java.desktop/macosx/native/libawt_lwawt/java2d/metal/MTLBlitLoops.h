#ifndef MTLBlitLoops_h_Included
#define MTLBlitLoops_h_Included

#include "sun_java2d_metal_MTLBlitLoops.h"
#include "MTLSurfaceDataBase.h"
#include "MTLContext.h"

#define OFFSET_SRCTYPE sun_java2d_metal_MTLBlitLoops_OFFSET_SRCTYPE
#define OFFSET_HINT    sun_java2d_metal_MTLBlitLoops_OFFSET_HINT
#define OFFSET_TEXTURE sun_java2d_metal_MTLBlitLoops_OFFSET_TEXTURE
#define OFFSET_RTT     sun_java2d_metal_MTLBlitLoops_OFFSET_RTT
#define OFFSET_XFORM   sun_java2d_metal_MTLBlitLoops_OFFSET_XFORM
#define OFFSET_ISOBLIT sun_java2d_metal_MTLBlitLoops_OFFSET_ISOBLIT

void MTLBlitLoops_IsoBlit(JNIEnv *env,
                          MTLContext *mtlc, jlong pSrcOps, jlong pDstOps,
                          jboolean xform, jint hint,
                          jboolean texture,
                          jint sx1, jint sy1,
                          jint sx2, jint sy2,
                          jdouble dx1, jdouble dy1,
                          jdouble dx2, jdouble dy2);

void MTLBlitLoops_Blit(JNIEnv *env,
                       MTLContext *mtlc, jlong pSrcOps, jlong pDstOps,
                       jboolean xform, jint hint,
                       jint srctype, jboolean texture,
                       jint sx1, jint sy1,
                       jint sx2, jint sy2,
                       jdouble dx1, jdouble dy1,
                       jdouble dx2, jdouble dy2);

void MTLBlitLoops_SurfaceToSwBlit(JNIEnv *env, MTLContext *mtlc,
                                  jlong pSrcOps, jlong pDstOps, jint dsttype,
                                  jint srcx, jint srcy,
                                  jint dstx, jint dsty,
                                  jint width, jint height);

void MTLBlitLoops_CopyArea(JNIEnv *env,
                           MTLContext *mtlc, BMTLSDOps *dstOps,
                           jint x, jint y,
                           jint width, jint height,
                           jint dx, jint dy);

void MTLBlitTex2Tex(MTLContext *mtlc, id<MTLTexture> src, id<MTLTexture> dest);

void drawTex2Tex(MTLContext *mtlc,
                        id<MTLTexture> src, id<MTLTexture> dst,
                        jboolean isSrcOpaque, jboolean isDstOpaque, jint hint,
                        jint sx1, jint sy1, jint sx2, jint sy2,
                        jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2);

#endif /* MTLBlitLoops_h_Included */
