#include "jni_util.h"

#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/syslimits.h>
#include <sys/types.h>
#include <sys/un.h>
#include <errno.h>
#include <fcntl.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "sun_tools_attach_VirtualMachineImpl.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

#define ROOT_UID 0

/*
 * Declare library specific JNI_Onload entry if static build
 */
DEF_STATIC_JNI_OnLoad

/*
 * Class:     sun_tools_attach_VirtualMachineImpl
 * Method:    socket
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_sun_tools_attach_VirtualMachineImpl_socket
  (JNIEnv *env, jclass cls)
{
    int fd = socket(PF_UNIX, SOCK_STREAM, 0);
    if (fd == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "socket");
    }
    return (jint)fd;
}

/*
 * Class:     sun_tools_attach_VirtualMachineImpl
 * Method:    connect
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT void JNICALL Java_sun_tools_attach_VirtualMachineImpl_connect
  (JNIEnv *env, jclass cls, jint fd, jstring path)
{
    jboolean isCopy;
    const char* p = GetStringPlatformChars(env, path, &isCopy);
    if (p != NULL) {
        struct sockaddr_un addr;
        int err = 0;

        memset(&addr, 0, sizeof(addr));
        addr.sun_family = AF_UNIX;
        /* strncpy is safe because addr.sun_path was zero-initialized before. */
        strncpy(addr.sun_path, p, sizeof(addr.sun_path) - 1);

        if (connect(fd, (struct sockaddr*)&addr, sizeof(addr)) == -1) {
            err = errno;
        }

        if (isCopy) {
            JNU_ReleaseStringPlatformChars(env, path, p);
        }

        /*
         * If the connect failed then we throw the appropriate exception
         * here (can't throw it before releasing the string as can't call
         * JNI with pending exception)
         */
        if (err != 0) {
            if (err == ENOENT) {
                JNU_ThrowByName(env, "java/io/FileNotFoundException", NULL);
            } else {
                char* msg = strdup(strerror(err));
                JNU_ThrowIOException(env, msg);
                if (msg != NULL) {
                    free(msg);
                }
            }
        }
    }
}

/*
 * Class:     sun_tools_attach_VirtualMachineImpl
 * Method:    sendQuitTo
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_sun_tools_attach_VirtualMachineImpl_sendQuitTo
  (JNIEnv *env, jclass cls, jint pid)
{
    if (kill((pid_t)pid, SIGQUIT)) {
        JNU_ThrowIOExceptionWithLastError(env, "kill");
    }
}

/*
 * Class:     sun_tools_attach_VirtualMachineImpl
 * Method:    checkPermissions
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_sun_tools_attach_VirtualMachineImpl_checkPermissions
  (JNIEnv *env, jclass cls, jstring path)
{
    jboolean isCopy;
    const char* p = GetStringPlatformChars(env, path, &isCopy);
    if (p != NULL) {
        struct stat sb;
        uid_t uid, gid;
        int res;

        memset(&sb, 0, sizeof(struct stat));

        /*
         * Check that the path is owned by the effective uid/gid of this
         * process. Also check that group/other access is not allowed.
         */
        uid = geteuid();
        gid = getegid();

        res = stat(p, &sb);
        if (res != 0) {
            /* save errno */
            res = errno;
        }

        if (res == 0) {
            char msg[100];
            jboolean isError = JNI_FALSE;
            if (sb.st_uid != uid && uid != ROOT_UID) {
                snprintf(msg, sizeof(msg),
                    "file should be owned by the current user (which is %d) but is owned by %d", uid, sb.st_uid);
                isError = JNI_TRUE;
            } else if (sb.st_gid != gid && uid != ROOT_UID) {
                snprintf(msg, sizeof(msg),
                    "file's group should be the current group (which is %d) but the group is %d", gid, sb.st_gid);
                isError = JNI_TRUE;
            } else if ((sb.st_mode & (S_IRGRP|S_IWGRP|S_IROTH|S_IWOTH)) != 0) {
                snprintf(msg, sizeof(msg),
                    "file should only be readable and writable by the owner but has 0%03o access", sb.st_mode & 0777);
                isError = JNI_TRUE;
            }
            if (isError) {
                char buf[256];
                snprintf(buf, sizeof(buf), "well-known file %s is not secure: %s", p, msg);
                JNU_ThrowIOException(env, buf);
            }
        } else {
            char* msg = strdup(strerror(res));
            JNU_ThrowIOException(env, msg);
            if (msg != NULL) {
                free(msg);
            }
        }

        if (isCopy) {
            JNU_ReleaseStringPlatformChars(env, path, p);
        }
    }
}

/*
 * Class:     sun_tools_attach_VirtualMachineImpl
 * Method:    close
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_sun_tools_attach_VirtualMachineImpl_close
  (JNIEnv *env, jclass cls, jint fd)
{
    int res;
    shutdown(fd, SHUT_RDWR);
    RESTARTABLE(close(fd), res);
}

/*
 * Class:     sun_tools_attach_VirtualMachineImpl
 * Method:    read
 * Signature: (I[BI)I
 */
JNIEXPORT jint JNICALL Java_sun_tools_attach_VirtualMachineImpl_read
  (JNIEnv *env, jclass cls, jint fd, jbyteArray ba, jint off, jint baLen)
{
    unsigned char buf[128];
    size_t len = sizeof(buf);
    ssize_t n;

    size_t remaining = (size_t)(baLen - off);
    if (len > remaining) {
        len = remaining;
    }

    RESTARTABLE(read(fd, buf, len), n);
    if (n == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "read");
    } else {
        if (n == 0) {
            n = -1;     // EOF
        } else {
            (*env)->SetByteArrayRegion(env, ba, off, (jint)n, (jbyte *)(buf));
        }
    }
    return n;
}

/*
 * Class:     sun_tools_attach_VirtualMachineImpl
 * Method:    write
 * Signature: (I[B)V
 */
JNIEXPORT void JNICALL Java_sun_tools_attach_VirtualMachineImpl_write
  (JNIEnv *env, jclass cls, jint fd, jbyteArray ba, jint off, jint bufLen)
{
    size_t remaining = bufLen;
    do {
        unsigned char buf[128];
        size_t len = sizeof(buf);
        int n;

        if (len > remaining) {
            len = remaining;
        }
        (*env)->GetByteArrayRegion(env, ba, off, len, (jbyte *)buf);

        RESTARTABLE(write(fd, buf, len), n);
        if (n > 0) {
            off += n;
            remaining -= n;
        } else {
            JNU_ThrowIOExceptionWithLastError(env, "write");
            return;
        }

    } while (remaining > 0);
}

/*
 * Class:     sun_tools_attach_BSDVirtualMachine
 * Method:    createAttachFile
 * Signature: (Ljava.lang.String;)V
 */
JNIEXPORT void JNICALL Java_sun_tools_attach_VirtualMachineImpl_createAttachFile0(JNIEnv *env, jclass cls, jstring path)
{
    const char* _path;
    jboolean isCopy;
    int fd, rc;

    _path = GetStringPlatformChars(env, path, &isCopy);
    if (_path == NULL) {
        JNU_ThrowIOException(env, "Must specify a path");
        return;
    }

    RESTARTABLE(open(_path, O_CREAT | O_EXCL, S_IWUSR | S_IRUSR), fd);
    if (fd == -1) {
        /* release p here before we throw an I/O exception */
        if (isCopy) {
            JNU_ReleaseStringPlatformChars(env, path, _path);
        }
        JNU_ThrowIOExceptionWithLastError(env, "open");
        return;
    }

    RESTARTABLE(chown(_path, geteuid(), getegid()), rc);

    RESTARTABLE(close(fd), rc);

    /* release p here */
    if (isCopy) {
        JNU_ReleaseStringPlatformChars(env, path, _path);
    }
}

/*
 * Class:     sun_tools_attach_BSDVirtualMachine
 * Method:    getTempDir
 * Signature: (V)Ljava.lang.String;
 */
JNIEXPORT jstring JNICALL Java_sun_tools_attach_VirtualMachineImpl_getTempDir(JNIEnv *env, jclass cls)
{
    // This must be hard coded because it's the system's temporary
    // directory not the java application's temp directory, ala java.io.tmpdir.

#ifdef __APPLE__
    // macosx has a secure per-user temporary directory.
    // Don't cache the result as this is only called once.
    char path[PATH_MAX];
    int pathSize = confstr(_CS_DARWIN_USER_TEMP_DIR, path, PATH_MAX);
    if (pathSize == 0 || pathSize > PATH_MAX) {
        strlcpy(path, "/tmp", sizeof(path));
    }
    return JNU_NewStringPlatform(env, path);
#else /* __APPLE__ */
    return (*env)->NewStringUTF(env, "/tmp");
#endif /* __APPLE__ */
}
