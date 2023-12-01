#ifndef MTLMaskFill_h_Included
#define MTLMaskFill_h_Included

#include "MTLContext.h"

void MTLMaskFill_MaskFill(MTLContext *mtlc, BMTLSDOps * dstOps,
                          jint x, jint y, jint w, jint h,
                          jint maskoff, jint maskscan, jint masklen,
                          unsigned char *pMask);

#endif /* MTLMaskFill_h_Included */
