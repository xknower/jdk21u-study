#ifndef HB_JDK_H
#define HB_JDK_H

#include "hb.h"
#include <jni.h>
#include <sunfontids.h>

# ifdef __cplusplus
extern "C" {
#endif

typedef struct JDKFontInfo_Struct {
    JNIEnv* env;
    jobject font2D;
    jobject fontStrike;
    float matrix[4];
    float ptSize;
    float xPtSize;
    float yPtSize;
    float devScale; // How much applying the full glyph tx scales x distance.
} JDKFontInfo;


// Use 16.16 for better precision than 26.6
#define HBFloatToFixedScale ((float)(1 << 16))
#define HBFloatToFixed(f) ((unsigned int)((f) * HBFloatToFixedScale))

/*
 * Note:
 *
 * Set face size on ft-face before creating hb-font from it.
 * Otherwise hb-ft would NOT pick up the font size correctly.
 */

hb_face_t *
hb_jdk_face_create(JDKFontInfo*   jdkFontInfo,
                   hb_destroy_func_t destroy);
hb_font_t *
hb_jdk_font_create(hb_face_t* hbFace,
                   JDKFontInfo*   jdkFontInfo,
                   hb_destroy_func_t destroy);


/* Makes an hb_font_t use JDK internally to implement font functions. */
void
hb_jdk_font_set_funcs(hb_font_t *font);


# ifdef __cplusplus
}
#endif

#endif /* HB_JDK_H */
