#include <winsock2.h>
#include <ws2tcpip.h>
#include <afunix.h>

#include "jni.h"

/**
 * The maximum buffer size for WSASend/WSARecv. Microsoft recommendation for
 * blocking operations is to use buffers no larger than 64k. We need the
 * maximum to be less than 128k to support asynchronous close on Windows
 * Server 2003 and newer editions of Windows.
 */
#define MAX_BUFFER_SIZE             ((128*1024)-1)

#define MAX_UNIX_DOMAIN_PATH_LEN \
        (int)(sizeof(((struct sockaddr_un *)0)->sun_path)-2)

jint fdval(JNIEnv *env, jobject fdo);
void setfdval(JNIEnv *env, jobject fdo, jint val);
jlong handleval(JNIEnv *env, jobject fdo);
jint convertReturnVal(JNIEnv *env, jint n, jboolean r);
jlong convertLongReturnVal(JNIEnv *env, jlong n, jboolean r);
jboolean purgeOutstandingICMP(JNIEnv *env, jclass clazz, jint fd);

#ifdef _WIN64

struct iovec {
    jlong  iov_base;
    jint  iov_len;
};

#else

struct iovec {
    jint  iov_base;
    jint  iov_len;
};

#endif

/* Defined in UnixDomainSockets.c */

jbyteArray sockaddrToUnixAddressBytes(JNIEnv *env, struct sockaddr_un *sa, socklen_t len);

jint unixSocketAddressToSockaddr(JNIEnv *env, jbyteArray uaddr,
                                struct sockaddr_un *sa, int *len);

