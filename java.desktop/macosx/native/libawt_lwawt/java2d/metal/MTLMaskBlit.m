#include <stdlib.h>
#include <jlong.h>

#include "MTLMaskBlit.h"
#include "MTLRenderQueue.h"
#include "MTLBlitLoops.h"

/**
 * REMIND: This method assumes that the dimensions of the incoming pixel
 *         array are less than or equal to the cached blit texture tile;
 *         these are rather fragile assumptions, and should be cleaned up...
 */
void
MTLMaskBlit_MaskBlit(JNIEnv *env, MTLContext *mtlc, BMTLSDOps * dstOps,
                     jint dstx, jint dsty,
                     jint width, jint height,
                     void *pPixels)
{
    J2dTraceLn(J2D_TRACE_INFO, "MTLMaskBlit_MaskBlit");

    if (width <= 0 || height <= 0) {
        J2dTraceLn(J2D_TRACE_WARNING, "MTLMaskBlit_MaskBlit: invalid dimensions");
        return;
    }

    RETURN_IF_NULL(pPixels);
    RETURN_IF_NULL(mtlc);

    MTLPooledTextureHandle * texHandle = [mtlc.texturePool
                                                  getTexture:width
                                                      height:height
                                                      format:MTLPixelFormatBGRA8Unorm];
    if (texHandle == nil) {
        J2dTraceLn(J2D_TRACE_ERROR, "MTLMaskBlit_MaskBlit: can't obtain temporary texture object from pool");
        return;
    }
    [[mtlc getCommandBufferWrapper] registerPooledTexture:texHandle];

    id<MTLTexture> texBuff = texHandle.texture;
    MTLRegion region = MTLRegionMake2D(0, 0, width, height);
    [texBuff replaceRegion:region mipmapLevel:0 withBytes:pPixels bytesPerRow:4*width];

    drawTex2Tex(mtlc, texBuff, dstOps->pTexture, JNI_FALSE, dstOps->isOpaque, 0,
                0, 0, width, height, dstx, dsty, dstx + width, dsty + height);
}
