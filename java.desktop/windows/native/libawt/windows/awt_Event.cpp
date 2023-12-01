#include "awt_Event.h"
#include "awt.h"

/************************************************************************
 * AwtEvent fields
 */

jfieldID AwtEvent::targetID;
jfieldID AwtEvent::xID;
jfieldID AwtEvent::yID;

/************************************************************************
 * AwtEvent native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_Event_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtEvent::targetID = env->GetFieldID(cls, "target", "Ljava/lang/Object;");
    DASSERT(AwtEvent::targetID != NULL);
    CHECK_NULL(AwtEvent::targetID);

    AwtEvent::xID = env->GetFieldID(cls, "x", "I");
    DASSERT(AwtEvent::xID != NULL);
    CHECK_NULL(AwtEvent::xID);

    AwtEvent::yID = env->GetFieldID(cls, "y", "I");
    DASSERT(AwtEvent::yID != NULL);
    CHECK_NULL(AwtEvent::yID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
