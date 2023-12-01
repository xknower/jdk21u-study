#ifndef MTLRenderer_h_Included
#define MTLRenderer_h_Included

#include "sun_java2d_pipe_BufferedRenderPipe.h"
#include "MTLContext.h"
#include "MTLGraphicsConfig.h"
#import "MTLLayer.h"

#define BYTES_PER_POLY_POINT \
    sun_java2d_pipe_BufferedRenderPipe_BYTES_PER_POLY_POINT
#define BYTES_PER_SCANLINE \
    sun_java2d_pipe_BufferedRenderPipe_BYTES_PER_SCANLINE
#define BYTES_PER_SPAN \
    sun_java2d_pipe_BufferedRenderPipe_BYTES_PER_SPAN

void MTLRenderer_DrawLine(MTLContext *mtlc, BMTLSDOps * dstOps,
                          jint x1, jint y1, jint x2, jint y2);
void MTLRenderer_DrawPixel(MTLContext *mtlc, BMTLSDOps * dstOps,
                          jint x, jint y);
void MTLRenderer_DrawRect(MTLContext *mtlc, BMTLSDOps * dstOps,
                          jint x, jint y, jint w, jint h);
void MTLRenderer_DrawPoly(MTLContext *mtlc, BMTLSDOps * dstOps,
                          jint nPoints, jint isClosed,
                          jint transX, jint transY,
                          jint *xPoints, jint *yPoints);
void MTLRenderer_DrawScanlines(MTLContext *mtlc, BMTLSDOps * dstOps,
                               jint count, jint *scanlines);
void MTLRenderer_DrawParallelogram(MTLContext *mtlc, BMTLSDOps * dstOps,
                                   jfloat fx11, jfloat fy11,
                                   jfloat dx21, jfloat dy21,
                                   jfloat dx12, jfloat dy12,
                                   jfloat lw21, jfloat lw12);
void MTLRenderer_DrawAAParallelogram(MTLContext *mtlc, BMTLSDOps * dstOps,
                                   jfloat fx11, jfloat fy11,
                                   jfloat dx21, jfloat dy21,
                                   jfloat dx12, jfloat dy12,
                                   jfloat lw21, jfloat lw12);
void MTLRenderer_FillRect(MTLContext *mtlc, BMTLSDOps * dstOps,
                          jint x, jint y, jint w, jint h);
void MTLRenderer_FillSpans(MTLContext *mtlc, BMTLSDOps * dstOps,
                           jint count, jint *spans);
void MTLRenderer_FillParallelogram(MTLContext *mtlc, BMTLSDOps * dstOps,
                                   jfloat fx11, jfloat fy11,
                                   jfloat dx21, jfloat dy21,
                                   jfloat dx12, jfloat dy12);
void MTLRenderer_FillAAParallelogram(MTLContext *mtlc, BMTLSDOps * dstOps,
                                   jfloat fx11, jfloat fy11,
                                   jfloat dx21, jfloat dy21,
                                   jfloat dx12, jfloat dy12);

#endif /* MTLRenderer_h_Included */
