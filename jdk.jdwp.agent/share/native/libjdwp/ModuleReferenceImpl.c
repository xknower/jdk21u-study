#include "util.h"
#include "inStream.h"
#include "outStream.h"
#include "ModuleReferenceImpl.h"


static jclass jlM(JNIEnv *env) {
    return findClass(env, "Ljava/lang/Module;");
}

static jboolean
getName(PacketInputStream *in, PacketOutputStream *out)
{
    static jmethodID method = NULL;
    JNIEnv *env = getEnv();
    char *name = NULL;
    jstring namestr;
    jobject module;

    if (method == NULL) {
        method = getMethod(env, jlM(env), "getName", "()Ljava/lang/String;");
    }
    module = inStream_readModuleRef(getEnv(), in);
    if (inStream_error(in)) {
        return JNI_TRUE;
    }
    namestr = (jstring)JNI_FUNC_PTR(env, CallObjectMethod) (env, module, method);
    if (namestr != NULL) {
        name = (char*)JNI_FUNC_PTR(env, GetStringUTFChars)(env, namestr, NULL);
    } else {
        // The JDWP converts null into an empty string
    }
    (void)outStream_writeString(out, name);
    if (name != NULL) {
        JNI_FUNC_PTR(env, ReleaseStringUTFChars)(env, namestr, name);
    }
    return JNI_TRUE;
}

static jboolean
getClassLoader(PacketInputStream *in, PacketOutputStream *out)
{
    static jmethodID method = NULL;
    JNIEnv *env = getEnv();
    jobject loader;
    jobject module;

    if (method == NULL) {
        method = getMethod(env, jlM(env), "getClassLoader", "()Ljava/lang/ClassLoader;");
    }
    module = inStream_readModuleRef(env, in);
    if (inStream_error(in)) {
        return JNI_TRUE;
    }
    loader = JNI_FUNC_PTR(env, CallObjectMethod) (env, module, method);

    (void)outStream_writeObjectRef(env, out, loader);
    return JNI_TRUE;
}

Command ModuleReference_Commands[] = {
    {getName, "GetName"},
    {getClassLoader, "GetClassLoader"}
};

DEBUG_DISPATCH_DEFINE_CMDSET(ModuleReference)
