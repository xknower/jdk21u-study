#ifndef JNIUTILS_H
#define JNIUTILS_H

#include <cstddef>
#include <memory>
#include "jni.h"
#include "tstrings.h"


namespace jni {

struct JniObjWithEnv {
    JniObjWithEnv(): env(0), obj(0) {

    }

    JniObjWithEnv(JNIEnv *env, jobject obj) : env(env), obj(obj) {
    }

    JniObjWithEnv(const std::nullptr_t ptr) : env(ptr), obj(ptr) {
    }

    bool operator == (const JniObjWithEnv& other) const {
        return env == other.env && obj == other.obj;
    }

    bool operator != (const JniObjWithEnv& other) const {
        return ! operator == (other);
    }

    bool operator == (const std::nullptr_t ptr) const {
        return env == ptr || obj == ptr;
    }

    bool operator != (const std::nullptr_t ptr) const {
        return env != ptr && obj != ptr;
    }

    explicit operator bool() const {
        return env && obj;
    }

    JNIEnv *env;
    jobject obj;

    struct LocalRefDeleter {
        typedef JniObjWithEnv pointer;

        void operator()(pointer v);
    };
};

typedef std::unique_ptr<JniObjWithEnv, JniObjWithEnv::LocalRefDeleter> LocalRef;

tstring toUnicodeString(JNIEnv *env, jstring val);

jstring toJString(JNIEnv *env, const tstring& val);

tstring_array toUnicodeStringArray(JNIEnv *env, jobjectArray val);

} // namespace jni

#endif // JNIUTILS_H
