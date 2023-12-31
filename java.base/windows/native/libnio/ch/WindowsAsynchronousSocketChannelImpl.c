#include <windows.h>
#include <winsock2.h>
#include <stddef.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"
#include "net_util.h"

#include "sun_nio_ch_WindowsAsynchronousSocketChannelImpl.h"

#ifndef WSAID_CONNECTEX
#define WSAID_CONNECTEX {0x25a207b9,0xddf3,0x4660,{0x8e,0xe9,0x76,0xe5,0x8c,0x74,0x06,0x3e}}
#endif

#ifndef SO_UPDATE_CONNECT_CONTEXT
#define SO_UPDATE_CONNECT_CONTEXT 0x7010
#endif

typedef BOOL (PASCAL *ConnectEx_t)
(
    SOCKET s,
    const struct sockaddr* name,
    int namelen,
    PVOID lpSendBuffer,
    DWORD dwSendDataLength,
    LPDWORD lpdwBytesSent,
    LPOVERLAPPED lpOverlapped
);

static ConnectEx_t ConnectEx_func;


JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousSocketChannelImpl_initIDs(JNIEnv* env, jclass this) {
    GUID GuidConnectEx = WSAID_CONNECTEX;
    SOCKET s;
    int rv;
    DWORD dwBytes;

    s = socket(AF_INET, SOCK_STREAM, 0);
    if (s == INVALID_SOCKET && WSAGetLastError() == WSAEAFNOSUPPORT) {
        /* IPv4 unavailable... try IPv6 instead */
        s = socket(AF_INET6, SOCK_STREAM, 0);
    }
    if (s == INVALID_SOCKET) {
        JNU_ThrowIOExceptionWithLastError(env, "socket failed");
        return;
    }
    rv = WSAIoctl(s,
                  SIO_GET_EXTENSION_FUNCTION_POINTER,
                  (LPVOID)&GuidConnectEx,
                  sizeof(GuidConnectEx),
                  &ConnectEx_func,
                  sizeof(ConnectEx_func),
                  &dwBytes,
                  NULL,
                  NULL);
    if (rv != 0)
        JNU_ThrowIOExceptionWithLastError(env, "WSAIoctl failed");
    closesocket(s);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WindowsAsynchronousSocketChannelImpl_connect0(JNIEnv* env, jclass this,
    jlong socket, jboolean preferIPv6, jobject iao, jint port, jlong ov)
{
    SOCKET s = (SOCKET)jlong_to_ptr(socket);
    OVERLAPPED *lpOverlapped = (OVERLAPPED *)jlong_to_ptr(ov);

    SOCKETADDRESS sa;
    int sa_len = 0;
    BOOL res;

    if (NET_InetAddressToSockaddr(env, iao, port, &sa, &sa_len,
                                  preferIPv6) != 0) {
        return IOS_THROWN;
    }

    ZeroMemory((PVOID)lpOverlapped, sizeof(OVERLAPPED));

    res = (*ConnectEx_func)(s, &sa.sa, sa_len, NULL, 0, NULL, lpOverlapped);
    if (res == 0) {
        int error = GetLastError();
        if (error == ERROR_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLastError(env, "ConnectEx failed");
        return IOS_THROWN;
    }
    return 0;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousSocketChannelImpl_updateConnectContext(JNIEnv* env, jclass this,
    jlong socket)
{
    SOCKET s = (SOCKET)jlong_to_ptr(socket);
    setsockopt(s, SOL_SOCKET, SO_UPDATE_CONNECT_CONTEXT, NULL, 0);
}


JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousSocketChannelImpl_shutdown0(JNIEnv *env, jclass cl,
    jlong socket, jint how)
{
    SOCKET s =(SOCKET) jlong_to_ptr(socket);
    if (shutdown(s, how) == SOCKET_ERROR) {
        JNU_ThrowIOExceptionWithLastError(env, "shutdown failed");
    }
}


JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousSocketChannelImpl_closesocket0(JNIEnv* env, jclass this,
    jlong socket)
{
    SOCKET s = (SOCKET)jlong_to_ptr(socket);
    if (closesocket(s) == SOCKET_ERROR)
        JNU_ThrowIOExceptionWithLastError(env, "closesocket failed");
}


JNIEXPORT jint JNICALL
Java_sun_nio_ch_WindowsAsynchronousSocketChannelImpl_read0(JNIEnv* env, jclass this,
    jlong socket, jint count, jlong address, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(socket);
    WSABUF* lpWsaBuf = (WSABUF*) jlong_to_ptr(address);
    OVERLAPPED* lpOverlapped = (OVERLAPPED*) jlong_to_ptr(ov);
    BOOL res;
    DWORD flags = 0;

    ZeroMemory((PVOID)lpOverlapped, sizeof(OVERLAPPED));
    res = WSARecv(s,
                  lpWsaBuf,
                  (DWORD)count,
                  NULL,
                  &flags,
                  lpOverlapped,
                  NULL);

    if (res == SOCKET_ERROR) {
        int error = WSAGetLastError();
        if (error == WSA_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        if (error == WSAESHUTDOWN) {
            return IOS_EOF;       // input shutdown
        }
        JNU_ThrowIOExceptionWithLastError(env, "WSARecv failed");
        return IOS_THROWN;
    }
    return IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WindowsAsynchronousSocketChannelImpl_write0(JNIEnv* env, jclass this,
    jlong socket, jint count, jlong address, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(socket);
    WSABUF* lpWsaBuf = (WSABUF*) jlong_to_ptr(address);
    OVERLAPPED* lpOverlapped = (OVERLAPPED*) jlong_to_ptr(ov);
    BOOL res;

    ZeroMemory((PVOID)lpOverlapped, sizeof(OVERLAPPED));
    res = WSASend(s,
                  lpWsaBuf,
                  (DWORD)count,
                  NULL,
                  0,
                  lpOverlapped,
                  NULL);

    if (res == SOCKET_ERROR) {
        int error = WSAGetLastError();
        if (error == WSA_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        if (error == WSAESHUTDOWN) {
            return IOS_EOF;     // output shutdown
        }
        JNU_ThrowIOExceptionWithLastError(env, "WSASend failed");
        return IOS_THROWN;
    }
    return IOS_UNAVAILABLE;
}
