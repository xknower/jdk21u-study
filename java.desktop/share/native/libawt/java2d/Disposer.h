#ifndef _Included_Disposer
#define _Included_Disposer

#include "jlong.h"

/*
 * Adds the object to the Disposer queue.
 */

#ifdef __cplusplus
extern "C" {
#endif

typedef void GeneralDisposeFunc(JNIEnv *env, jlong pData);

/*
 * This method is used for registering native data associated with
 * the object for disposal when the object becomes non-reachable.
 */
JNIEXPORT void JNICALL
Disposer_AddRecord(JNIEnv *env, jobject obj,
                   GeneralDisposeFunc disposer, jlong pData);

#ifdef __cplusplus
}
#endif

#endif /* _Included_Disposer */
