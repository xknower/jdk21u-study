#include <winsock2.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "net_util.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_DatagramChannelImpl.h"

/*
 * This function "purges" all outstanding ICMP port unreachable packets
 * outstanding on a socket and returns JNI_TRUE if any ICMP messages
 * have been purged. The rational for purging is to emulate normal BSD
 * behaviour whereby receiving a "connection reset" status resets the
 * socket.
 */
jboolean purgeOutstandingICMP(JNIEnv *env, jclass clazz, jint fd)
{
    jboolean got_icmp = JNI_FALSE;
    char buf[1];
    fd_set tbl;
    struct timeval t = { 0, 0 };
    SOCKETADDRESS sa;
    int addrlen = sizeof(sa);

    /*
     * Peek at the queue to see if there is an ICMP port unreachable. If there
     * is then receive it.
     */
    FD_ZERO(&tbl);
    FD_SET((u_int)fd, &tbl);
    while(1) {
        if (select(/*ignored*/fd+1, &tbl, 0, 0, &t) <= 0) {
            break;
        }
        if (recvfrom(fd, buf, 1, MSG_PEEK,
                     &sa.sa, &addrlen) != SOCKET_ERROR) {
            break;
        }
        if (WSAGetLastError() != WSAECONNRESET) {
            /* some other error - we don't care here */
            break;
        }

        recvfrom(fd, buf, 1, 0, &sa.sa, &addrlen);
        got_icmp = JNI_TRUE;
    }

    return got_icmp;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_DatagramChannelImpl_disconnect0(JNIEnv *env, jclass clazz,
                                                jobject fdo, jboolean isIPv6)
{
    jint fd = fdval(env, fdo);
    int rv = 0;
    SOCKETADDRESS sa;
    int sa_len = sizeof(sa);

    memset(&sa, 0, sa_len);

    rv = connect((SOCKET)fd, &sa.sa, sa_len);
    if (rv == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLastError(), "connect");
    } else {
        /* Disable WSAECONNRESET errors as socket is no longer connected */
        BOOL enable = FALSE;
        DWORD bytesReturned = 0;
        WSAIoctl((SOCKET)fd, SIO_UDP_CONNRESET, &enable, sizeof(enable),
                 NULL, 0, &bytesReturned, NULL, NULL);
    }
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_DatagramChannelImpl_receive0(JNIEnv *env, jclass clazz,
                                             jobject fdo, jlong bufAddress,
                                             jint len, jlong senderAddress,
                                             jboolean connected)
{
    jint fd = fdval(env, fdo);
    void *buf = (void *)jlong_to_ptr(bufAddress);
    SOCKETADDRESS *sa = (SOCKETADDRESS *)jlong_to_ptr(senderAddress);
    int sa_len = sizeof(SOCKETADDRESS);
    BOOL retry = FALSE;
    jint n;

    do {
        retry = FALSE;
        n = recvfrom((SOCKET)fd,
                     (char *)buf,
                     len,
                     0,
                     (struct sockaddr *)sa,
                     &sa_len);

        if (n == SOCKET_ERROR) {
            int theErr = (jint)WSAGetLastError();
            if (theErr == WSAEMSGSIZE) {
                /* Spec says the rest of the data will be discarded... */
                n = len;
            } else if (theErr == WSAECONNRESET) {
                purgeOutstandingICMP(env, clazz, fd);
                if (connected == JNI_FALSE) {
                    retry = TRUE;
                } else {
                    JNU_ThrowByName(env, JNU_JAVANETPKG "PortUnreachableException", 0);
                    return IOS_THROWN;
                }
            } else if (theErr == WSAEWOULDBLOCK) {
                return IOS_UNAVAILABLE;
            } else {
                NET_ThrowNew(env, theErr, "recvfrom");
                return IOS_THROWN;
            }
        }
    } while (retry);

    return n;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_DatagramChannelImpl_send0(JNIEnv *env, jclass clazz,
                                          jobject fdo, jlong bufAddress, jint len,
                                          jlong targetAddress, jint targetAddressLen)
{
    jint fd = fdval(env, fdo);
    void *buf = (void *)jlong_to_ptr(bufAddress);
    SOCKETADDRESS *sa = (SOCKETADDRESS *)jlong_to_ptr(targetAddress);
    int sa_len = targetAddressLen;
    jint rv;

    rv = sendto((SOCKET)fd, buf, len, 0,(struct sockaddr *)sa, sa_len);
    if (rv == SOCKET_ERROR) {
        int theErr = (jint)WSAGetLastError();
        if (theErr == WSAEWOULDBLOCK) {
            return IOS_UNAVAILABLE;
        }
        NET_ThrowNew(env, (jint)WSAGetLastError(), "sendto");
        return IOS_THROWN;
    }
    return rv;
}
