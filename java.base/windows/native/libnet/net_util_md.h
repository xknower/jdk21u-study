#include <winsock2.h>
#include <WS2tcpip.h>
#include <iphlpapi.h>
#include <icmpapi.h>
#include <mstcpip.h>

/* used to disable connection reset messages on Windows XP */
#ifndef SIO_UDP_CONNRESET
#define SIO_UDP_CONNRESET _WSAIOW(IOC_VENDOR,12)
#endif

#ifndef IN6_IS_ADDR_ANY
#define IN6_IS_ADDR_ANY(a)      \
    (((a)->s6_words[0] == 0) && ((a)->s6_words[1] == 0) &&      \
    ((a)->s6_words[2] == 0) && ((a)->s6_words[3] == 0) &&       \
    ((a)->s6_words[4] == 0) && ((a)->s6_words[5] == 0) &&       \
    ((a)->s6_words[6] == 0) && ((a)->s6_words[7] == 0))
#endif

#ifndef IPV6_V6ONLY
#define IPV6_V6ONLY     27 /* Treat wildcard bind as AF_INET6-only. */
#endif

#define MAX_BUFFER_LEN          2048
#define MAX_HEAP_BUFFER_LEN     65536

/* true if SO_RCVTIMEO is supported by underlying provider */
extern jboolean isRcvTimeoutSupported;

typedef union {
    struct sockaddr     sa;
    struct sockaddr_in  sa4;
    struct sockaddr_in6 sa6;
} SOCKETADDRESS;

#define SOCKETADDRESS_COPY(DST,SRC) {                           \
    if ((SRC)->sa_family == AF_INET6) {                         \
        memcpy ((DST), (SRC), sizeof (struct sockaddr_in6));    \
    } else {                                                    \
        memcpy ((DST), (SRC), sizeof (struct sockaddr_in));     \
    }                                                           \
}

#define SET_PORT(X,Y) {                    \
    if ((X)->sa.sa_family == AF_INET) {    \
        (X)->sa4.sin_port = (Y);           \
    } else {                               \
        (X)->sa6.sin6_port = (Y);          \
    }                                      \
}

#define GET_PORT(X) ((X)->sa.sa_family == AF_INET ? (X)->sa4.sin_port : (X)->sa6.sin6_port)

/**
 * With dual socket implementation the
 * IPv4 addresseses might be mapped as IPv6.
 * The IPv4 loopback adapter address ranges (127.0.0.0 through 127.255.255.255) will
 * be mapped as the following IPv6 ::ffff:127.0.0.0 through ::ffff:127.255.255.255.
 * For example, this is done by NET_InetAddressToSockaddr.
 */
#define IN6_IS_ADDR_V4MAPPED_LOOPBACK(x) ( \
    (((x)->s6_words[0] == 0)               &&  \
     ((x)->s6_words[1] == 0)               &&  \
     ((x)->s6_words[2] == 0)               &&  \
     ((x)->s6_words[3] == 0)               &&  \
     ((x)->s6_words[4] == 0)               &&  \
     ((x)->s6_words[5] == 0xFFFF)          &&  \
     (((x)->s6_words[6] & 0x00FF) == 0x007F)) \
)

/**
 * Check for IPv4 loopback adapter address ranges (127.0.0.0 through 127.255.255.255)
 */
#define IN4_IS_ADDR_NETLONG_LOOPBACK(l) ( \
    ((l & 0xFF000000) == 0x7F000000) \
)

#define IS_LOOPBACK_ADDRESS(x) ( \
    ((x)->sa.sa_family == AF_INET) ? \
        (IN4_IS_ADDR_NETLONG_LOOPBACK(ntohl((x)->sa4.sin_addr.s_addr))) : \
        ((IN6_IS_ADDR_LOOPBACK(&(x)->sa6.sin6_addr)) || \
         (IN6_IS_ADDR_V4MAPPED_LOOPBACK(&(x)->sa6.sin6_addr))) \
)

int NET_Socket(int domain, int type, int protocol);

void NET_ThrowByNameWithLastError(JNIEnv *env, const char *name,
                                  const char *defaultDetail);

JNIEXPORT int JNICALL NET_WinBind(int s, SOCKETADDRESS *sa, int len,
                                  jboolean exclBind);

JNIEXPORT jint JNICALL NET_EnableFastTcpLoopbackConnect(int fd);

/* XP versions of the native routines */

JNIEXPORT jobject JNICALL Java_java_net_NetworkInterface_getByName0_XP
  (JNIEnv *env, jclass cls, jstring name);

JNIEXPORT jobject JNICALL Java_java_net_NetworkInterface_getByIndex0_XP
  (JNIEnv *env, jclass cls, jint index);

JNIEXPORT jobject JNICALL Java_java_net_NetworkInterface_getByInetAddress0_XP
  (JNIEnv *env, jclass cls, jobject iaObj);

JNIEXPORT jobjectArray JNICALL Java_java_net_NetworkInterface_getAll_XP
  (JNIEnv *env, jclass cls);

JNIEXPORT jboolean JNICALL Java_java_net_NetworkInterface_supportsMulticast0_XP
  (JNIEnv *env, jclass cls, jstring name, jint index);

JNIEXPORT jboolean JNICALL Java_java_net_NetworkInterface_isUp0_XP
  (JNIEnv *env, jclass cls, jstring name, jint index);

JNIEXPORT jboolean JNICALL Java_java_net_NetworkInterface_isP2P0_XP
  (JNIEnv *env, jclass cls, jstring name, jint index);

JNIEXPORT jbyteArray JNICALL Java_java_net_NetworkInterface_getMacAddr0_XP
  (JNIEnv *env, jclass cls, jstring name, jint index);

JNIEXPORT jint JNICALL Java_java_net_NetworkInterface_getMTU0_XP
  (JNIEnv *env, jclass class, jstring name, jint index);

JNIEXPORT jboolean JNICALL Java_java_net_NetworkInterface_isLoopback0_XP
  (JNIEnv *env, jclass cls, jstring name, jint index);
