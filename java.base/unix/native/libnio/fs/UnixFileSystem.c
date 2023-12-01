#include "jni.h"
#include "jni_util.h"
#include "jlong.h"

#include "nio.h"

#include <stdlib.h>
#include <unistd.h>
#include <errno.h>

#include "sun_nio_fs_UnixFileSystem.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

static void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByName(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

// Copy via an intermediate temporary direct buffer
JNIEXPORT void JNICALL
Java_sun_nio_fs_UnixFileSystem_bufferedCopy0
    (JNIEnv* env, jclass this, jint dst, jint src, jlong address,
     jint transferSize, jlong cancelAddress)
{
    volatile jint* cancel = (jint*)jlong_to_ptr(cancelAddress);

    char* buf = (char*)jlong_to_ptr(address);

    for (;;) {
        ssize_t n, pos, len;
        RESTARTABLE(read((int)src, buf, transferSize), n);
        if (n <= 0) {
            if (n < 0)
                throwUnixException(env, errno);
            return;
        }
        if (cancel != NULL && *cancel != 0) {
            throwUnixException(env, ECANCELED);
            return;
        }
        pos = 0;
        len = n;
        do {
            char* bufp = buf;
            bufp += pos;
            RESTARTABLE(write((int)dst, bufp, len), n);
            if (n == -1) {
                throwUnixException(env, errno);
                return;
            }
            pos += n;
            len -= n;
        } while (len > 0);
    }
}
