#include "java_awt_Font.h"
#include "sun_awt_FontDescriptor.h"
#include "sun_awt_PlatformFont.h"


/*
 * Class:     java_awt_Font
 * Method:    initIDs
 * Signature: ()V
 */

/* This function gets called from the static initializer for Font.java
   to initialize the fieldIDs for fields that may be accessed from C */

JNIEXPORT void JNICALL
Java_java_awt_Font_initIDs(JNIEnv *env, jclass cls) {
}

/*
 * Class:     sun_awt_FontDescriptor
 * Method:    initIDs
 * Signature: ()V
 */

/* This function gets called from the static initializer for
   FontDescriptor.java to initialize the fieldIDs for fields
   that may be accessed from C */

JNIEXPORT void JNICALL
Java_sun_awt_FontDescriptor_initIDs(JNIEnv *env, jclass cls) {
}

/*
 * Class:     sun_awt_PlatformFont
 * Method:    initIDs
 * Signature: ()V
 */

/* This function gets called from the static initializer for
   PlatformFont.java to initialize the fieldIDs for fields
   that may be accessed from C */

JNIEXPORT void JNICALL
Java_sun_awt_PlatformFont_initIDs(JNIEnv *env, jclass cls) {
}
