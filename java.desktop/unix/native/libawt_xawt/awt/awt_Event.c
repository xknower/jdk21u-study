/***
 *** THIS IMPLEMENTS ONLY THE OBSOLETE java.awt.Event CLASS! SEE
 *** awt_AWTEvent.[ch] FOR THE NEWER EVENT CLASSES.
 ***
 ***/
#ifdef HEADLESS
    #error This file should not be included in headless library
#endif

#include "java_awt_Event.h"

JNIEXPORT void JNICALL
Java_java_awt_Event_initIDs(JNIEnv *env, jclass cls)
{
}
