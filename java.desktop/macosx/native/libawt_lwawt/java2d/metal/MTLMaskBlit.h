#ifndef MTLMaskBlit_h_Included
#define MTLMaskBlit_h_Included

#include "MTLContext.h"

void MTLMaskBlit_MaskBlit(JNIEnv *env, MTLContext *mtlc, BMTLSDOps * dstOps,
                          jint dstx, jint dsty,
                          jint width, jint height,
                          void *pPixels);

#endif /* MTLMaskBlit_h_Included */
