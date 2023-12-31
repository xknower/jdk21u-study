#include <windows.h>
#include <winsock2.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"
#include "net_util.h"

#include "sun_nio_ch_WindowsAsynchronousServerSocketChannelImpl.h"


#ifndef WSAID_ACCEPTEX
#define WSAID_ACCEPTEX {0xb5367df1,0xcbac,0x11cf,{0x95,0xca,0x00,0x80,0x5f,0x48,0xa1,0x92}}
#endif

#ifndef SO_UPDATE_ACCEPT_CONTEXT
#define SO_UPDATE_ACCEPT_CONTEXT 0x700B
#endif


typedef BOOL (*AcceptEx_t)
(
    SOCKET sListenSocket,
    SOCKET sAcceptSocket,
    PVOID lpOutputBuffer,
    DWORD dwReceiveDataLength,
    DWORD dwLocalAddressLength,
    DWORD dwRemoteAddressLength,
    LPDWORD lpdwBytesReceived,
    LPOVERLAPPED lpOverlapped
);


static AcceptEx_t AcceptEx_func;


JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousServerSocketChannelImpl_initIDs(JNIEnv* env, jclass this) {
    GUID GuidAcceptEx = WSAID_ACCEPTEX;
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
                  (LPVOID)&GuidAcceptEx,
                  sizeof(GuidAcceptEx),
                  &AcceptEx_func,
                  sizeof(AcceptEx_func),
                  &dwBytes,
                  NULL,
                  NULL);
    if (rv != 0)
        JNU_ThrowIOExceptionWithLastError(env, "WSAIoctl failed");
    closesocket(s);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WindowsAsynchronousServerSocketChannelImpl_accept0(JNIEnv* env, jclass this,
    jlong listenSocket, jlong acceptSocket, jlong ov, jlong buf)
{
    BOOL res;
    SOCKET s1 = (SOCKET)jlong_to_ptr(listenSocket);
    SOCKET s2 = (SOCKET)jlong_to_ptr(acceptSocket);
    PVOID outputBuffer = (PVOID)jlong_to_ptr(buf);

    DWORD nread = 0;
    OVERLAPPED* lpOverlapped = (OVERLAPPED*)jlong_to_ptr(ov);
    ZeroMemory((PVOID)lpOverlapped, sizeof(OVERLAPPED));

    res = (*AcceptEx_func)(s1,
                           s2,
                           outputBuffer,
                           0,
                           sizeof(SOCKETADDRESS)+16,
                           sizeof(SOCKETADDRESS)+16,
                           &nread,
                           lpOverlapped);
    if (res == 0) {
        int error = WSAGetLastError();
        if (error == ERROR_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLastError(env, "AcceptEx failed");
        return IOS_THROWN;
    }

    return 0;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousServerSocketChannelImpl_updateAcceptContext(JNIEnv* env, jclass this,
    jlong listenSocket, jlong acceptSocket)
{
    SOCKET s1 = (SOCKET)jlong_to_ptr(listenSocket);
    SOCKET s2 = (SOCKET)jlong_to_ptr(acceptSocket);

    setsockopt(s2, SOL_SOCKET, SO_UPDATE_ACCEPT_CONTEXT, (char *)&s1, sizeof(s1));
}


JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousServerSocketChannelImpl_closesocket0(JNIEnv* env, jclass this,
    jlong socket)
{
    SOCKET s = (SOCKET)jlong_to_ptr(socket);

    if (closesocket(s) == SOCKET_ERROR)
        JNU_ThrowIOExceptionWithLastError(env, "closesocket failed");
}
