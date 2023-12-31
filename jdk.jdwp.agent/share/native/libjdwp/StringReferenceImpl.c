#include "util.h"
#include "StringReferenceImpl.h"
#include "inStream.h"
#include "outStream.h"

static jboolean
value(PacketInputStream *in, PacketOutputStream *out)
{
    JNIEnv *env;
    jstring string;

    env = getEnv();

    string = inStream_readStringRef(env, in);
    if (inStream_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        char *utf;

        utf = (char *)JNI_FUNC_PTR(env,GetStringUTFChars)(env, string, NULL);
        if (!(*env)->ExceptionCheck(env)) {
            (void)outStream_writeString(out, utf);
            JNI_FUNC_PTR(env,ReleaseStringUTFChars)(env, string, utf);
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

Command StringReference_Commands[] = {
    {value, "Value"}
};

DEBUG_DISPATCH_DEFINE_CMDSET(StringReference)
