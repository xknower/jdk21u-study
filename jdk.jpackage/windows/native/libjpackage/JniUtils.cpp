#include "JniUtils.h"
#include "ErrorHandling.h"
#include "Toolbox.h"


namespace jni {

void JniObjWithEnv::LocalRefDeleter::operator()(pointer v) {
    if (v.env && v.obj) {
        v.env->DeleteLocalRef(v.obj);
    }
}


#ifdef TSTRINGS_WITH_WCHAR
std::wstring toUnicodeString(JNIEnv *env, jstring val) {
    const jchar* chars = env->GetStringChars(val, 0);
    if (!chars) {
        JP_THROW("GetStringChars() failed");
    }

    const auto releaseStringChars =
            runAtEndOfScope([env, val, chars]() -> void {
        env->ReleaseStringChars(val, chars);
    });

    const jsize len = env->GetStringLength(val);

    return std::wstring(reinterpret_cast<const wchar_t*>(chars), len);
}


jstring toJString(JNIEnv *env, const std::wstring& val) {
    jstring result = env->NewString(
            reinterpret_cast<const jchar*>(val.c_str()), jsize(val.size()));
    if (!result) {
        JP_THROW("NewString() failed");
    }
    return result;
}
#endif


tstring_array toUnicodeStringArray(JNIEnv *env, jobjectArray val) {
    tstring_array result;

    const jsize len = env->GetArrayLength(val);
    for (int i = 0; i < len; ++i) {
        LocalRef localRef(JniObjWithEnv(env,
                env->GetObjectArrayElement(val, i)));
        result.push_back(toUnicodeString(env,
                static_cast<jstring>(localRef.get().obj)));
    }

    return result;
}

} // namespace jni
