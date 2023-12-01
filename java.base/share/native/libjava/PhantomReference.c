#include "jvm.h"
#include "java_lang_ref_PhantomReference.h"

JNIEXPORT jboolean JNICALL
Java_java_lang_ref_PhantomReference_refersTo0(JNIEnv *env, jobject ref, jobject o)
{
    return JVM_PhantomReferenceRefersTo(env, ref, o);
}
