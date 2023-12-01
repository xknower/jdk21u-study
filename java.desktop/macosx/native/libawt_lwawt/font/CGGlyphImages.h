#ifndef __CGGLYPHIMAGES_H
#define __CGGLYPHIMAGES_H

#import "jni.h"
#import "AWTStrike.h"

void
CGGlyphImages_GetGlyphImagePtrs(jlong glyphInfos[],
                                const AWTStrike *strike,
                                jint rawGlyphCodes[], const CFIndex len);
void
CGGlyphImages_GetGlyphMetrics(const CTFontRef font,
                              const CGAffineTransform *tx,
                              const JRSFontRenderingStyle style,
                              const CGGlyph glyphs[],
                              size_t count,
                              CGRect bboxes[],
                              CGSize advances[]);
#endif /* __CGGLYPHIMAGES_H */
