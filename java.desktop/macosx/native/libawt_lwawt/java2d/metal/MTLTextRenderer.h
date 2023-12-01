#ifndef MTLTextRenderer_h_Included
#define MTLTextRenderer_h_Included

#include <jni.h>
#include <jlong.h>
#include "sun_java2d_pipe_BufferedTextPipe.h"
#include "MTLContext.h"
#include "MTLSurfaceData.h"

#define BYTES_PER_GLYPH_IMAGE \
    sun_java2d_pipe_BufferedTextPipe_BYTES_PER_GLYPH_IMAGE
#define BYTES_PER_GLYPH_POSITION \
    sun_java2d_pipe_BufferedTextPipe_BYTES_PER_GLYPH_POSITION
#define BYTES_PER_POSITIONED_GLYPH \
    (BYTES_PER_GLYPH_IMAGE + BYTES_PER_GLYPH_POSITION)

#define OFFSET_CONTRAST  sun_java2d_pipe_BufferedTextPipe_OFFSET_CONTRAST
#define OFFSET_RGBORDER  sun_java2d_pipe_BufferedTextPipe_OFFSET_RGBORDER
#define OFFSET_SUBPIXPOS sun_java2d_pipe_BufferedTextPipe_OFFSET_SUBPIXPOS
#define OFFSET_POSITIONS sun_java2d_pipe_BufferedTextPipe_OFFSET_POSITIONS

void MTLTR_EnableGlyphVertexCache(MTLContext *mtlc, BMTLSDOps *dstOps);
void MTLTR_DisableGlyphVertexCache(MTLContext *mtlc);
id<MTLTexture> MTLTR_GetGlyphCacheTexture();
void MTLTR_FreeGlyphCaches();

void MTLTR_DrawGlyphList(JNIEnv *env, MTLContext *mtlc, BMTLSDOps *dstOps,
                         jint totalGlyphs, jboolean usePositions,
                         jboolean subPixPos, jboolean rgbOrder,
                         jint lcdContrast,
                         jfloat glyphListOrigX, jfloat glyphListOrigY,
                         unsigned char *images, unsigned char *positions);

#endif /* MTLTextRenderer_h_Included */
