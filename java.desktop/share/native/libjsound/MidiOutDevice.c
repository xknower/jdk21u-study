/*****************************************************************************/
/*
**      Native functions for interfacing Java with the native implementation
**      of PlatformMidi.h's functions.
*/
/*****************************************************************************/

#define USE_ERROR
#define USE_TRACE


#include <jni.h>
#include "SoundDefs.h"
#include "PlatformMidi.h"
#include "Utilities.h"
#include "com_sun_media_sound_MidiOutDevice.h"


// NATIVE METHODS


JNIEXPORT jlong JNICALL
Java_com_sun_media_sound_MidiOutDevice_nOpen(JNIEnv* e, jobject thisObj, jint index) {

    void* deviceHandle = NULL;
    INT32 err = MIDI_NOT_SUPPORTED;

    TRACE1("Java_com_sun_media_sound_MidiOutDevice_nOpen: index: %d\n", index);

#if USE_PLATFORM_MIDI_OUT == TRUE
    err = MIDI_OUT_OpenDevice((INT32) index, (MidiDeviceHandle**) (&deviceHandle));
#endif

    // if we didn't get a valid handle, throw a MidiUnavailableException
    if (!deviceHandle) {
        ERROR0("Java_com_sun_media_sound_MidiOutDevice_nOpen:");
        ThrowJavaMessageException(e, JAVA_MIDI_PACKAGE_NAME"/MidiUnavailableException",
                                  MIDI_OUT_InternalGetErrorString(err));
    } else {
        TRACE0("Java_com_sun_media_sound_MidiOutDevice_nOpen succeeded\n");
    }
    return (jlong) (INT_PTR) deviceHandle;
}


JNIEXPORT void JNICALL
Java_com_sun_media_sound_MidiOutDevice_nClose(JNIEnv* e, jobject thisObj, jlong deviceHandle) {

    TRACE0("Java_com_sun_media_sound_MidiOutDevice_nClose.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    MIDI_OUT_CloseDevice((MidiDeviceHandle*) (UINT_PTR) deviceHandle);
#endif

    TRACE0("Java_com_sun_media_sound_MidiOutDevice_nClose succeeded\n");
}


JNIEXPORT jlong JNICALL
Java_com_sun_media_sound_MidiOutDevice_nGetTimeStamp(JNIEnv* e, jobject thisObj, jlong deviceHandle) {

    jlong ret = -1;

    TRACE0("Java_com_sun_media_sound_MidiOutDevice_nGetTimeStamp.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    ret = (jlong) MIDI_OUT_GetTimeStamp((MidiDeviceHandle*) (UINT_PTR) deviceHandle);
#endif

    /* Handle error codes. */
    if (ret < -1) {
        ERROR1("Java_com_sun_media_sound_MidiOutDevice_nGetTimeStamp: MIDI_IN_GetTimeStamp returned %lld\n", ret);
        ret = -1;
    }
    return ret;
}


JNIEXPORT void JNICALL
Java_com_sun_media_sound_MidiOutDevice_nSendShortMessage(JNIEnv* e, jobject thisObj, jlong deviceHandle,
                                                         jint packedMsg, jlong timeStamp) {

    TRACE0("Java_com_sun_media_sound_MidiOutDevice_nSendShortMessage.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    MIDI_OUT_SendShortMessage((MidiDeviceHandle*) (UINT_PTR) deviceHandle,
                              (UINT32) packedMsg, (UINT32)timeStamp);
#endif

    TRACE0("Java_com_sun_media_sound_MidiOutDevice_nSendShortMessage succeeded\n");
}


JNIEXPORT void JNICALL
Java_com_sun_media_sound_MidiOutDevice_nSendLongMessage(JNIEnv* e, jobject thisObj, jlong deviceHandle,
                                                        jbyteArray jData, jint size, jlong timeStamp) {
#if USE_PLATFORM_MIDI_OUT == TRUE
    UBYTE* data;
#endif

    TRACE0("Java_com_sun_media_sound_MidiOutDevice_nSendLongMessage.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    data = (UBYTE*) ((*e)->GetByteArrayElements(e, jData, NULL));
    if (!data) {
        ERROR0("MidiOutDevice: Java_com_sun_media_sound_MidiOutDevice_nSendLongMessage: could not get array elements\n");
        return;
    }
    /* "continuation" sysex messages start with F7 (instead of F0), but
       are sent without the F7. */
    if (data[0] == 0xF7 && size > 1) {
        data++;
        size--;
    }
    MIDI_OUT_SendLongMessage((MidiDeviceHandle*) (UINT_PTR) deviceHandle, data,
                             (UINT32) size, (UINT32)timeStamp);
    // release the byte array
    (*e)->ReleaseByteArrayElements(e, jData, (jbyte*) data, JNI_ABORT);
#endif

    TRACE0("Java_com_sun_media_sound_MidiOutDevice_nSendLongMessage succeeded\n");
}
