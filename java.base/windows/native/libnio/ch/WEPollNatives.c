#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"
#include "wepoll.h"
#include "sun_nio_ch_WEPoll.h"

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WEPoll_eventSize(JNIEnv* env, jclass clazz)
{
    return sizeof(struct epoll_event);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WEPoll_eventsOffset(JNIEnv* env, jclass clazz)
{
    return offsetof(struct epoll_event, events);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WEPoll_dataOffset(JNIEnv* env, jclass clazz)
{
    return offsetof(struct epoll_event, data);
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_WEPoll_create(JNIEnv *env, jclass clazz) {
    HANDLE h = epoll_create1(0);
    if (h == NULL) {
        JNU_ThrowIOExceptionWithLastError(env, "epoll_create1 failed");
    }
    return ptr_to_jlong(h);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WEPoll_ctl(JNIEnv *env, jclass clazz, jlong h,
                           jint opcode, jlong s, jint events)
{
    struct epoll_event event;
    int res;
    SOCKET socket = (SOCKET) jlong_to_ptr(s);

    event.events = (uint32_t) events;
    event.data.sock = socket;

    res = epoll_ctl(jlong_to_ptr(h), opcode, socket, &event);
    return (res == 0) ? 0 : errno;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WEPoll_wait(JNIEnv *env, jclass clazz, jlong h,
                            jlong address, jint numfds, jint timeout)
{
    struct epoll_event *events = jlong_to_ptr(address);
    int res = epoll_wait(jlong_to_ptr(h), events, numfds, timeout);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "epoll_wait failed");
        return IOS_THROWN;
    }
    return res;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_WEPoll_close(JNIEnv *env, jclass clazz, jlong h) {
    epoll_close(jlong_to_ptr(h));
}
