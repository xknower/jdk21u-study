#include "awt_Insets.h"
#include "awt.h"

/************************************************************************
 * AwtInsets fields
 */

jfieldID AwtInsets::leftID;
jfieldID AwtInsets::rightID;
jfieldID AwtInsets::topID;
jfieldID AwtInsets::bottomID;

/************************************************************************
 * AwtInsets native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_Insets_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtInsets::leftID = env->GetFieldID(cls, "left", "I");
    DASSERT(AwtInsets::leftID != NULL);
    CHECK_NULL(AwtInsets::leftID);

    AwtInsets::rightID = env->GetFieldID(cls, "right", "I");
    DASSERT(AwtInsets::rightID != NULL);
    CHECK_NULL(AwtInsets::rightID);

    AwtInsets::topID = env->GetFieldID(cls, "top", "I");
    DASSERT(AwtInsets::topID != NULL);
    CHECK_NULL(AwtInsets::topID);

    AwtInsets::bottomID = env->GetFieldID(cls, "bottom", "I");
    DASSERT(AwtInsets::bottomID != NULL);
    CHECK_NULL(AwtInsets::bottomID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
