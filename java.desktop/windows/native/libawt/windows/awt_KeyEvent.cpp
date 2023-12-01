#include "awt_KeyEvent.h"
#include "awt.h"

/************************************************************************
 * AwtKeyEvent fields
 */

jfieldID AwtKeyEvent::keyCodeID;
jfieldID AwtKeyEvent::keyCharID;
jfieldID AwtKeyEvent::rawCodeID;
jfieldID AwtKeyEvent::primaryLevelUnicodeID;
jfieldID AwtKeyEvent::scancodeID;
jfieldID AwtKeyEvent::extendedKeyCodeID;

/************************************************************************
 * AwtKeyEvent native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_event_KeyEvent_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtKeyEvent::keyCodeID = env->GetFieldID(cls, "keyCode", "I");
    DASSERT(AwtKeyEvent::keyCodeID != NULL);
    CHECK_NULL(AwtKeyEvent::keyCodeID);

    AwtKeyEvent::keyCharID = env->GetFieldID(cls, "keyChar", "C");
    DASSERT(AwtKeyEvent::keyCharID != NULL);
    CHECK_NULL(AwtKeyEvent::keyCharID);

    AwtKeyEvent::rawCodeID = env->GetFieldID(cls, "rawCode", "J");
    DASSERT(AwtKeyEvent::rawCodeID != NULL);
    CHECK_NULL(AwtKeyEvent::rawCodeID);

    AwtKeyEvent::primaryLevelUnicodeID = env->GetFieldID(cls, "primaryLevelUnicode", "J");
    DASSERT(AwtKeyEvent::primaryLevelUnicodeID != NULL);
    CHECK_NULL(AwtKeyEvent::primaryLevelUnicodeID);

    AwtKeyEvent::scancodeID = env->GetFieldID(cls, "scancode", "J");
    DASSERT(AwtKeyEvent::scancodeID != NULL);
    CHECK_NULL(AwtKeyEvent::scancodeID);

    AwtKeyEvent::extendedKeyCodeID = env->GetFieldID(cls, "extendedKeyCode", "J");
    DASSERT(AwtKeyEvent::extendedKeyCodeID != NULL);
    CHECK_NULL(AwtKeyEvent::extendedKeyCodeID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
