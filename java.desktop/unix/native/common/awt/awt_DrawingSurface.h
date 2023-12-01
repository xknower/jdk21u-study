#ifndef _AWT_DRAWING_SURFACE_H_
#define _AWT_DRAWING_SURFACE_H_

#include <jawt.h>
#include <jni.h>

_JNI_IMPORT_OR_EXPORT_ JAWT_DrawingSurface* JNICALL
    awt_GetDrawingSurface(JNIEnv* env, jobject target);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    awt_FreeDrawingSurface(JAWT_DrawingSurface* ds);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    awt_Lock(JNIEnv* env);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    awt_Unlock(JNIEnv* env);

_JNI_IMPORT_OR_EXPORT_ jobject JNICALL
    awt_GetComponent(JNIEnv* env, void* platformInfo);

_JNI_IMPORT_OR_EXPORT_ jobject JNICALL
    awt_CreateEmbeddedFrame(JNIEnv* env, void* platformInfo);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    awt_SetBounds(JNIEnv *env, jobject embeddedFrame, jint x, jint y,
                  jint w, jint h);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    awt_SynthesizeWindowActivation(JNIEnv *env, jobject embeddedFrame,
                                   jboolean doActivate);
#endif /* !_AWT_DRAWING_SURFACE_H_ */
