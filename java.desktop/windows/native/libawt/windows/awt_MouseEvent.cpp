#include "awt_MouseEvent.h"
#include "awt.h"

/************************************************************************
 * AwtMouseEvent fields
 */

jfieldID AwtMouseEvent::xID;
jfieldID AwtMouseEvent::yID;
jfieldID AwtMouseEvent::causedByTouchEventID;
jfieldID AwtMouseEvent::buttonID;

/************************************************************************
 * AwtMouseEvent native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_event_MouseEvent_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtMouseEvent::xID = env->GetFieldID(cls, "x", "I");
    DASSERT(AwtMouseEvent::xID != NULL);
    CHECK_NULL(AwtMouseEvent::xID);

    AwtMouseEvent::yID = env->GetFieldID(cls, "y", "I");
    DASSERT(AwtMouseEvent::yID != NULL);
    CHECK_NULL(AwtMouseEvent::yID);

    AwtMouseEvent::causedByTouchEventID = env->GetFieldID(
        cls, "causedByTouchEvent", "Z");
    DASSERT(AwtMouseEvent::causedByTouchEventID != NULL);
    CHECK_NULL(AwtMouseEvent::causedByTouchEventID);

    AwtMouseEvent::buttonID = env->GetFieldID(cls, "button", "I");
    DASSERT(AwtMouseEvent::buttonID != NULL);
    CHECK_NULL(AwtMouseEvent::buttonID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
